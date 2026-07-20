package pnet.data.api;

import java.awt.Canvas;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.Serial;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import at.porscheinformatik.happyrest.RestException;
import pnet.data.api.about.AboutDataClient;
import pnet.data.api.about.AboutDataDTO;
import pnet.data.api.activity.ActivityDataClient;
import pnet.data.api.advisortype.AdvisorTypeDataClient;
import pnet.data.api.apache5.PnetApache5RestClientLauncher;
import pnet.data.api.application.ApplicationDataClient;
import pnet.data.api.brand.BrandDataClient;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.company.CompanyDataClient;
import pnet.data.api.company.CompanyMerge;
import pnet.data.api.companygroup.CompanyGroupDataClient;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataClient;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataClient;
import pnet.data.api.companytype.CompanyTypeDataClient;
import pnet.data.api.contractstate.ContractStateDataClient;
import pnet.data.api.contracttype.ContractTypeDataClient;
import pnet.data.api.externalbrand.ExternalBrandDataClient;
import pnet.data.api.function.FunctionDataClient;
import pnet.data.api.java.PnetJavaRestClientLauncher;
import pnet.data.api.legalform.LegalFormDataClient;
import pnet.data.api.numbertype.NumberTypeDataClient;
import pnet.data.api.person.PersonDataClient;
import pnet.data.api.resource.ResourceDataClient;
import pnet.data.api.settings.Visibility;
import pnet.data.api.util.AggregateNumberPerActivity;
import pnet.data.api.util.AggregateNumberPerBrand;
import pnet.data.api.util.AggregateNumberPerCategory;
import pnet.data.api.util.AggregateNumberPerCompany;
import pnet.data.api.util.AggregateNumberPerContractType;
import pnet.data.api.util.AggregateNumberPerFunction;
import pnet.data.api.util.AggregateNumberPerState;
import pnet.data.api.util.AggregateNumberPerTenant;
import pnet.data.api.util.AggregateNumberPerType;
import pnet.data.api.util.CLI;
import pnet.data.api.util.CompanyMergable;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.MutablePnetDataApiLoginMethod;
import pnet.data.api.util.Prefs;
import pnet.data.api.util.PrettyPrint;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictAdministrativeTenant;
import pnet.data.api.util.RestrictApprovalNeeded;
import pnet.data.api.util.RestrictApproved;
import pnet.data.api.util.RestrictArchived;
import pnet.data.api.util.RestrictCredentialsAvailable;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictQueryField;
import pnet.data.api.util.RestrictRejected;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictVisibility;
import pnet.data.api.util.Table;
import pnet.data.api.webclient.PnetSpringWebClientLauncher;

/**
 * The client with all commands. See {@link PnetApache5RestClientLauncher}, {@link PnetJavaRestClientLauncher} or
 * {@link PnetSpringWebClientLauncher} on how to launch this client.
 *
 * @author ham
 */
public final class PnetRestClient {

    private static class CurrentResult<T> {

        private PnetDataClientResultPage<T> page;
        private final BiConsumer<Table, T> populateTableFn;

        CurrentResult(PnetDataClientResultPage<T> page, BiConsumer<Table, T> populateTableFn) {
            this.page = page;
            this.populateTableFn = populateTableFn;
        }

        private boolean isEmpty() {
            return page.isEmpty();
        }

        private CurrentResult<T> nextPage() throws PnetDataClientException {
            page = page.nextPage();

            return this;
        }

        private void print(CLI cli, boolean asTable) {
            if (asTable && populateTableFn != null) {
                Table table = new Table();

                page.stream().forEach(item -> populateTableFn.accept(table, item));

                cli.info(table.toString());
            } else {
                page.stream().map(PrettyPrint::prettyPrint).forEach(cli::info);
            }

            cli.info(
                "\nShowing %d of %d results. Type \"next\" to load the next page.",
                page.size(),
                page.getTotalNumberOfItems()
            );
        }

        private void printAggregations(CLI cli) {
            if (!(page instanceof PnetDataClientResultPageWithAggregations<?, ?>)) {
                cli.error("No aggregations available in current page.");

                return;
            }

            cli.info(
                PrettyPrint.prettyPrint(((PnetDataClientResultPageWithAggregations<?, ?>) page).getAggregations())
            );
        }
    }

    private final MutablePnetDataApiLoginMethod loginMethod;
    private final AboutDataClient aboutDataClient;
    private final PnetDataApiContext context;

    private final CLI cli = new CLI();
    private final PnetRestClientActivities activities;
    private final PnetRestClientAdvisorTypes advisorTypes;
    private final PnetRestClientApplications applications;
    private final PnetRestClientBrands brands;
    private final PnetRestClientCompanies companies;
    private final PnetRestClientCompanyGroups companyGroups;
    private final PnetRestClientCompanyGroupTypes companyGroupTypes;
    private final PnetRestClientCompanyNumberTypes companyNumberTypes;
    private final PnetRestClientCompanyTypes companyTypes;
    private final PnetRestClientContractStates contractStates;
    private final PnetRestClientContractTypes contractTypes;
    private final PnetRestClientExternalBrands externalBrands;
    private final PnetRestClientFunctions functions;
    private final PnetRestClientLegalForms legalForms;
    private final PnetRestClientNumberTypes numberTypes;
    private final PnetRestClientPersons persons;
    private final PnetRestClientResources resources;

    private final List<PnetRestClientModule> modules;

    private final List<String> restrictedTenants = new ArrayList<>();
    private final List<String> restrictedAdministrativeTenants = new ArrayList<>();
    private final List<Visibility> restrictedVisibilities = new ArrayList<>();
    private final List<String> restrictedQueryFields = new ArrayList<>();

    private int page = 0;
    private int pageSize = 10;
    private CompanyMerge companyMerge = CompanyMerge.NONE;
    private LocalDateTime datedBackUntil = null;
    private Boolean restrictRejected = null;
    private Boolean restrictArchived = null;
    private Boolean restrictCredentialsAvailable = null;
    private Boolean restrictApproved = null;
    private Boolean restrictApprovalNeeded = null;
    private boolean aggs = false;
    private boolean compact = true;
    private CurrentResult<?> currentResult = null;
    private Locale language = Locale.getDefault();
    private boolean includeInactive = false;
    private boolean scroll = false;

    @SuppressWarnings("java:S107") // Too many parameters
    public PnetRestClient(
        MutablePnetDataApiLoginMethod loginMethod,
        AboutDataClient aboutDataClient,
        ActivityDataClient activityDataClient,
        AdvisorTypeDataClient advisorTypeDataClient,
        ApplicationDataClient applicationDataClient,
        BrandDataClient brandDataClient,
        CompanyDataClient companyDataClient,
        CompanyGroupDataClient companyGroupDataClient,
        CompanyGroupTypeDataClient companyGroupTypeDataClient,
        CompanyNumberTypeDataClient companyNumberTypeDataClient,
        CompanyTypeDataClient companyTypeDataClient,
        ContractStateDataClient contractStateDataClient,
        ContractTypeDataClient contractTypeDataClient,
        ExternalBrandDataClient externalBrandDataClient,
        FunctionDataClient functionDataClient,
        LegalFormDataClient legalFormDataClient,
        NumberTypeDataClient numberTypeDataClient,
        PersonDataClient personDataClient,
        ResourceDataClient resourceDataClient,
        PnetDataApiContext context
    ) {
        this.loginMethod = loginMethod;
        this.aboutDataClient = aboutDataClient;
        this.context = context;

        this.activities = cli.register(new PnetRestClientActivities(this, activityDataClient));
        this.advisorTypes = cli.register(new PnetRestClientAdvisorTypes(this, advisorTypeDataClient));
        this.applications = cli.register(new PnetRestClientApplications(this, applicationDataClient));
        this.brands = cli.register(new PnetRestClientBrands(this, brandDataClient));
        this.companies = cli.register(new PnetRestClientCompanies(this, companyDataClient));
        this.companyGroups = cli.register(
            new PnetRestClientCompanyGroups(this, companyGroupDataClient, companyGroupTypeDataClient)
        );
        this.companyGroupTypes = cli.register(
            new PnetRestClientCompanyGroupTypes(this, companyGroupTypeDataClient, companyGroupDataClient)
        );
        this.companyNumberTypes = cli.register(new PnetRestClientCompanyNumberTypes(this, companyNumberTypeDataClient));
        this.companyTypes = cli.register(new PnetRestClientCompanyTypes(this, companyTypeDataClient));
        this.contractStates = cli.register(new PnetRestClientContractStates(this, contractStateDataClient));
        this.contractTypes = cli.register(new PnetRestClientContractTypes(this, contractTypeDataClient));
        this.externalBrands = cli.register(new PnetRestClientExternalBrands(this, externalBrandDataClient));
        this.functions = cli.register(new PnetRestClientFunctions(this, functionDataClient));
        this.legalForms = cli.register(new PnetRestClientLegalForms(this, legalFormDataClient));
        this.numberTypes = cli.register(new PnetRestClientNumberTypes(this, numberTypeDataClient));
        this.persons = cli.register(new PnetRestClientPersons(this, personDataClient));
        this.resources = cli.register(new PnetRestClientResources(this, resourceDataClient));

        this.modules = List.of(
            activities,
            advisorTypes,
            applications,
            brands,
            companies,
            companyGroups,
            companyGroupTypes,
            companyNumberTypes,
            companyTypes,
            contractStates,
            contractTypes,
            externalBrands,
            functions,
            legalForms,
            numberTypes,
            persons,
            resources
        );

        cli.register(this);
    }

    @CLI.Command(description = "Info about the Partner.Net Data API and the user.")
    public void about() throws PnetDataClientException {
        AboutDataDTO about = aboutDataClient.about();

        cli.info(PrettyPrint.prettyPrint(about));
    }

    @CLI.Command(description = "Prints the JSON Web Token of the user.")
    public void jwt() throws PnetDataClientException {
        cli.info("jwt = %s", context.restCall().getHeader("Authorization"));
    }

    @CLI.Command(description = "Invalidates the stored JSON Web Token.")
    public void logout() throws PnetDataClientException {
        context.invalidateLogin();

        cli.info("Logged out.");
    }

    @CLI.Command(
        name = "merge internet groups",
        description = "Merges companies according to their internet group " + "settings."
    )
    public void mergeInternetGroups() {
        cli.info("Companies will be merged according to their internet group settings.");

        companyMerge = CompanyMerge.INTERNET_GROUP;
    }

    @CLI.Command(name = "merge none", description = "Do not merge companies.")
    public void mergeNone() {
        cli.info("Companies will not be merged.");

        companyMerge = CompanyMerge.NONE;
    }

    @CLI.Command(
        name = "dated back",
        format = "[updatedAfter]",
        description = "Sets the dated back parameter for the" + " specified days"
    )
    public void datedBackUnitl(String updatedAfter) {
        if (updatedAfter == null) {
            datedBackUntil = null;

            cli.info("Only up-to-date items will be searched and shown.");
        } else {
            datedBackUntil = PnetRestClient.parseUpdatedAfter(updatedAfter);

            cli.info("Items will be searched and shown, that are not older than %s.", datedBackUntil);
        }
    }

    @CLI.Command(name = "rejected only", description = "Show only results, that have been rejected.")
    public void rejectedOnly() {
        restrictRejected = Boolean.TRUE;

        cli.info("Only rejected items will be searched and shown.");
    }

    @CLI.Command(name = "not rejected only", description = "Show only results, that are not rejected.")
    public void notRejectedOnly() {
        restrictRejected = Boolean.FALSE;

        cli.info("Only not rejected items will be searched and shown.");
    }

    @CLI.Command(name = "ignore rejected flag", description = "Ignore the rejected flag.")
    public void clearRejected() {
        restrictRejected = null;

        cli.info("The rejected state of items will be ignored.");
    }

    @CLI.Command(
        name = "credentials available only",
        description = "Show only persons who are qualified to access the Partner.Net"
    )
    public void credentialsAvailableOnly() {
        restrictCredentialsAvailable = Boolean.TRUE;

        cli.info(
            "Only persons with login credentials, any account or any active function (active now or in future) will be searched and shown."
        );
    }

    @CLI.Command(
        name = "no credentials available only",
        description = "Show only persons who are not qualified to access the Partner.Net"
    )
    public void noCredentialsAvailableOnly() {
        restrictCredentialsAvailable = Boolean.FALSE;

        cli.info(
            "Only persons without login credentials, any account or any active function (active now or in future) will be searched and shown."
        );
    }

    @CLI.Command(name = "ignore credentials available", description = "Ignore the crdentials available flag.")
    public void clearCredentialsAvailable() {
        restrictCredentialsAvailable = null;

        cli.info("The credentials available state of persons will be ignored.");
    }

    @CLI.Command(name = "approved only", description = "Show only persons, that have been approved.")
    public void approvedOnly() {
        restrictApproved = Boolean.TRUE;

        cli.info("Only persons that have been approved will be searched and shown.");
    }

    @CLI.Command(name = "approval pending only", description = "Show only persons, that have not been approved, yet.")
    public void notApprovedOnly() {
        restrictApproved = Boolean.FALSE;

        cli.info("Only persons, that have not been approved, yet, will be searched and shown.");
    }

    @CLI.Command(name = "ignore approved", description = "Ignore the approved flag.")
    public void clearApproved() {
        restrictApproved = null;

        cli.info("The approval state of persons will be ignored.");
    }

    @CLI.Command(name = "archived only", description = "Show only results, that have been archived.")
    public void archivedOnly() {
        restrictArchived = Boolean.TRUE;

        cli.info("Only archived items will be searched and shown.");
    }

    @CLI.Command(name = "not archived only", description = "Show only results, that are not archived.")
    public void notArchivedOnly() {
        restrictArchived = Boolean.FALSE;

        cli.info("Only not archived items will be searched and shown.");
    }

    @CLI.Command(name = "ignore archived flag", description = "Ignore the archived flag.")
    public void clearArchived() {
        restrictArchived = null;

        cli.info("The archived state of items will be ignored.");
    }

    @CLI.Command(description = "Opens the Swagger Documentation.")
    public void swagger() throws IOException {
        String url = loginMethod.getUrl();

        if (!url.endsWith("/")) {
            url += "/";
        }

        url += "swagger-ui.html";

        cli.info("Opening: %s", url);

        Desktop.getDesktop().browse(URI.create(url));
    }

    @CLI.Command(
        name = "migrate all",
        format = "<INDEXNAME>",
        description = "Performs a full migration for the " + "specified index."
    )
    public void migrateFull(String indexName) throws RestException, PnetDataClientException {
        context
            .restCall()
            .variable(PnetRestClient.INDEX_NAME, indexName)
            .path("/api/v1/migrator/full/{indexName}")
            .post(Void.class);

        cli.info("Performing a full migration on index: %s.", indexName);
    }

    @CLI.Command(
        name = "migrate delta",
        format = "<INDEXNAME>",
        description = "Performs a delta migration for the " + "specified index."
    )
    public void migrateDelta(String indexName) throws RestException, PnetDataClientException {
        context
            .restCall()
            .variable(PnetRestClient.INDEX_NAME, indexName)
            .path("/api/v1/migrator/delta/{indexName}")
            .post(Void.class);

        cli.info("Performing a delta migration on index: %s.", indexName);
    }

    @CLI.Command(name = "migrate state", format = "<INDEXNAME>", description = "Prints the state of the migration.")
    public void migrateState(String indexName) throws RestException, PnetDataClientException {
        HashMap<?, ?> state = context
            .restCall()
            .variable(PnetRestClient.INDEX_NAME, indexName)
            .path("/api/v1/migrator/states/{indexName}")
            .get(HashMap.class);

        cli.info("Migration state of index: %s", indexName);
        cli.info(PrettyPrint.prettyPrint(state));
    }

    @CLI.Command(name = "migrate explicit", format = "<INDEXNAME> [<IDS>]", description = "Runs an explicit migration.")
    public void migrateExplicit(String indexName, String... ids) throws RestException, PnetDataClientException {
        HashMap<?, ?> state = context
            .restCall()
            .variable(PnetRestClient.INDEX_NAME, indexName)
            .parameter("id", (Object[]) ids)
            .path("/api/v1/migrator/explicit/{indexName}")
            .post(HashMap.class);

        cli.info("Explicit migration state of index: %s", indexName);
        cli.info(PrettyPrint.prettyPrint(state));
    }

    @CLI.Command(
        name = { "restrict tenants", "restrict tenant" },
        format = "[<TENANT>...]",
        description = "Places a restriction with tenants for subsequent operations."
    )
    public void restrictTenants(String... tenants) {
        if (tenants != null && tenants.length > 0) {
            restrictedTenants.addAll(Arrays.asList(tenants));
        }

        cli.info("Requests are restricted to tenants: %s", restrictedTenants);
    }

    @CLI.Command(
        name = { "clear tenant restrictions", "clear tenant restriction" },
        description = "Removes all restrictions for tenants."
    )
    public void clearTenantRestrictions() {
        cli.info("Removed %s tenant restrictions.", restrictedTenants.size());

        restrictedTenants.clear();
    }

    @CLI.Command(
        name = {"restrict administrative tenants", "restrict administrative tenant"},
        format = "[<TENANT>...]",
        description = "Places a restriction with administrative tenants for subsequent operations."
    )
    public void restrictAdministrativeTenants(String... tenants) {
        if (tenants != null && tenants.length > 0) {
            restrictedAdministrativeTenants.addAll(Arrays.asList(tenants));
        }

        cli.info("Requests are restricted to administrative tenants: %s", restrictedAdministrativeTenants);
    }

    @CLI.Command(
        name = { "clear administrative tenant restrictions", "clear administrative tenant restriction" },
        description = "Removes all restrictions for administrative tenants."
    )
    public void clearAdministrativeTenantRestrictions() {
        cli.info("Removed %s administrative tenant restrictions.", restrictedAdministrativeTenants.size());

        restrictedAdministrativeTenants.clear();
    }


    @CLI.Command(
        name = { "restrict query fields", "restrict query field" },
        format = "[<FIELDS>...]",
        description = "Places a restriction for query fields."
    )
    public void restrictQueryFields(String... queryFields) {
        if (queryFields != null && queryFields.length > 0) {
            restrictedQueryFields.addAll(Arrays.asList(queryFields));
        }

        cli.info("Queries are restricted to following fields: %s", restrictedQueryFields);
    }

    @CLI.Command(
        name = { "clear query field restrictions", "clear query field restriction" },
        description = "Removes all restrictions to query fields."
    )
    public void clearQueryFieldRestrictions() {
        cli.info("Removed %s query field restrictions.", restrictedQueryFields.size());

        restrictedQueryFields.clear();
    }

    @CLI.Command(name = "visible items", description = "Restrict to visible items.")
    public void restrictVisibleOnly() {
        restrictedVisibilities.add(Visibility.VISIBLE);

        cli.info("Restrict to visible items.");
    }

    @CLI.Command(name = "hidden items", description = "Restrict to hidden items.")
    public void restrictHiddenOnly() {
        restrictedVisibilities.add(Visibility.HIDDEN);

        cli.info("Restrict to hidden items.");
    }

    @CLI.Command(name = "clear visibility restriction", description = "Removes all restrictions to query fields.")
    public void clearVisibilityRestrictions() {
        cli.info("Remove visibility restrictions.");

        restrictedVisibilities.clear();
    }

    @CLI.Command(name = "approval needed", description = "Include only items, that need approval.")
    public void approvalNeededOnly() {
        restrictApprovalNeeded = true;

        cli.info("Include only items, that need approval.");
    }

    @CLI.Command(name = "no approval needed", description = "Include only items, that do not need approval.")
    public void noApprovalNeededOnly() {
        restrictApprovalNeeded = false;

        cli.info("Include only items, that do not need approval.");
    }

    @CLI.Command(name = "ignore approval needed inactive", description = "Ignore the approval needed flag.")
    public void createApprovalNeededRestriction() {
        restrictApprovalNeeded = null;

        cli.info("Ignore the approval needed flag.");
    }

    @CLI.Command(name = "include inactive", description = "Include inactive items.")
    public void includeInactive() {
        includeInactive = true;

        cli.info("Inactive items will be included.");
    }

    @CLI.Command(name = "exclude inactive", description = "Exclude inactive items.")
    public void exincludeInactive() {
        includeInactive = false;

        cli.info("Inactive items will be excluded.");
    }

    int getPage() {
        return page;
    }

    int getPageSize() {
        return pageSize;
    }

    Locale getLanguage() {
        return language;
    }

    boolean isScroll() {
        return scroll;
    }

    CLI getCli() {
        return cli;
    }

    <T extends Restrict<T>> T restrict(T request) {
        for (PnetRestClientModule module : modules) {
            request = module.applyRestrictions(request);
        }
        request = applyBaseDataRestrictions(request);
        request = applyBaseTypeRestrictions(request);
        request = applyCustomRestrictions(request);
        request = applyApprovalRestrictions(request);
        request = applyAggregations(request);

        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyBaseDataRestrictions(T request) {
        if (request instanceof RestrictTenant && !restrictedTenants.isEmpty()) {
            cli.info("A restriction for tenants is in place: %s", restrictedTenants);

            request = ((RestrictTenant<T>) request).tenants(restrictedTenants);
        }

        if(request instanceof RestrictAdministrativeTenant && !restrictedAdministrativeTenants.isEmpty()) {
            cli.info("A restriction for administrative tenants is in place: %s", restrictedAdministrativeTenants);

            request = ((RestrictAdministrativeTenant<T>) request).administrativeTenants(restrictedAdministrativeTenants);
        }

        if (request instanceof RestrictQueryField && !restrictedQueryFields.isEmpty()) {
            cli.info("A restriction for query fields is in place: %s", restrictedQueryFields);

            request = ((RestrictQueryField<T>) request).queryFields(restrictedQueryFields);
        }

        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyBaseTypeRestrictions(T request) {
        if (request instanceof RestrictVisibility && !restrictedVisibilities.isEmpty()) {
            cli.info("A restriction for visibilities is in place: %s", restrictedVisibilities);

            request = ((RestrictVisibility<T>) request).visibilities(restrictedVisibilities);
        }

        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyCustomRestrictions(T request) {
        if (request instanceof CompanyMergable && companyMerge != CompanyMerge.NONE) {
            cli.info("Merging companies according to: " + companyMerge);

            request = ((CompanyMergable<T>) request).merge(companyMerge);
        }

        if (request instanceof RestrictDatedBackUntil && datedBackUntil != null) {
            cli.info("Dating back until: " + datedBackUntil);

            request = ((RestrictDatedBackUntil<T>) request).datedBackUntil(datedBackUntil);
        }

        if (request instanceof RestrictCredentialsAvailable && restrictCredentialsAvailable != null) {
            cli.info("Credentials available: " + restrictCredentialsAvailable);

            request = ((RestrictCredentialsAvailable<T>) request).credentialsAvailable(restrictCredentialsAvailable);
        }

        if (request instanceof RestrictArchived && restrictArchived != null) {
            cli.info("Archived: " + restrictArchived);

            request = ((RestrictArchived<T>) request).archived(restrictArchived);
        }

        if (request instanceof IncludeInactive && includeInactive) {
            cli.info("Including inactive items.");

            request = ((IncludeInactive<T>) request).includeInactive();
        }

        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyApprovalRestrictions(T request) {
        if (request instanceof RestrictApproved && restrictApproved != null) {
            cli.info("Approved: " + restrictApproved);

            request = ((RestrictApproved<T>) request).approved(restrictApproved);
        }

        if (request instanceof RestrictRejected && restrictRejected != null) {
            cli.info("Rejected: " + restrictRejected);

            request = ((RestrictRejected<T>) request).rejected(restrictRejected);
        }

        if (request instanceof RestrictApprovalNeeded && restrictApprovalNeeded != null) {
            cli.info("Approval needed: " + restrictApprovalNeeded);

            request = ((RestrictApprovalNeeded<T>) request).approvalNeeded(restrictApprovalNeeded);
        }
        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyAggregations(T request) {
        if (!aggs) {
            return request;
        }

        if (request instanceof AggregateNumberPerActivity) {
            request = (T) ((AggregateNumberPerActivity<?>) request).aggregateNumberPerActivity();
        }

        if (request instanceof AggregateNumberPerBrand) {
            request = (T) ((AggregateNumberPerBrand<?>) request).aggregateNumberPerBrand();
        }

        if (request instanceof AggregateNumberPerCategory) {
            request = (T) ((AggregateNumberPerCategory<?>) request).aggregateNumberPerCategory();
        }

        if (request instanceof AggregateNumberPerCompany) {
            request = (T) ((AggregateNumberPerCompany<?>) request).aggregateNumberPerCompany();
        }

        if (request instanceof AggregateNumberPerContractType) {
            request = (T) ((AggregateNumberPerContractType<?>) request).aggregateNumberPerContractType();
        }

        if (request instanceof AggregateNumberPerFunction) {
            request = (T) ((AggregateNumberPerFunction<?>) request).aggregateNumberPerFunction();
        }

        if (request instanceof AggregateNumberPerState) {
            request = (T) ((AggregateNumberPerState<?>) request).aggregateNumberPerState();
        }

        if (request instanceof AggregateNumberPerTenant) {
            request = (T) ((AggregateNumberPerTenant<?>) request).aggregateNumberPerTenant();
        }

        if (request instanceof AggregateNumberPerType) {
            request = (T) ((AggregateNumberPerType<?>) request).aggregateNumberPerType();
        }

        return request;
    }

    @CLI.Command(name = { "clear restrictions", "clear restriction" }, description = "Removes all restrictions.")
    public void clearRestrictions() {
        cli.info("Removed all restrictions.");

        restrictedTenants.clear();
        restrictedAdministrativeTenants.clear();
        modules.forEach(PnetRestClientModule::clearRestrictions);
        restrictedVisibilities.clear();
        restrictedQueryFields.clear();
        restrictArchived = null;
        restrictRejected = null;
        restrictCredentialsAvailable = null;
        restrictApproved = null;
        restrictApprovalNeeded = null;
        datedBackUntil = null;
    }

    @CLI.Command(format = "[<URL>]", description = "Prints or overrides the predefined URL.")
    public void url(String url) {
        if (url != null) {
            try {
                logout();
            } catch (Exception e) {
                // ignore
            }

            loginMethod.setUrl(url);
        }

        cli.info("url = %s", loginMethod.getUrl());
        cli.info("\nTip: Type \"store\" to set this as default.");
    }

    @CLI.Command(format = "[<KEY>]", description = "Stores the URL and username/password to your prefernces.")
    public void store(String key) {
        if (key == null) {
            key = Prefs.DEFAULT_KEY;
        }

        Prefs.setUrl(key, loginMethod.getUrl());
        Prefs.setUsername(key, loginMethod.getUsername());
        Prefs.setPassword(key, loginMethod.getPassword());
        Prefs.setToken(key, loginMethod.getToken());

        cli.info("URL and (encoded) credentials have been stored locally with key: %s", key);
    }

    @CLI.Command(format = "[<KEY>]", description = "Loads the URL and username/password from your prefernces.")
    public void load(String key) {
        if (key == null) {
            key = Prefs.DEFAULT_KEY;
        }

        String url = Prefs.getUrl(key);

        if (url == null) {
            cli.error("Could not find prefs with key: %s", key);
            return;
        }

        url(url);

        String username = Prefs.getUsername(key);
        String password = Prefs.getPassword(key);
        String token = Prefs.getToken(key);

        if (token != null) {
            loginMethod.setToken(token);
        } else {
            loginMethod.setUsernamePassword(username, password);
        }
    }

    @CLI.Command(format = "[<KEY>]", description = "Remove the URL and username/password from your prefernces.")
    public void remove(String key) {
        if (key == null) {
            key = Prefs.DEFAULT_KEY;
        }

        Prefs.remove(key);

        cli.info("URL, username and password with key \"%s\" have been removed.", key);
    }

    @CLI.Command(description = "Lists all locally stored keys")
    public void list() {
        cli.info("Locally stored keys:\n");

        Prefs.keys()
            .stream()
            .filter(key -> key.endsWith(".url"))
            .map(key -> key.substring(0, key.length() - 4))
            .forEach(key ->
                cli.info(
                    "%s: %s (%s)",
                    key,
                    Prefs.getUrl(key),
                    Prefs.getUsername(key) != null ? Prefs.getUsername(key) : "authentication token"
                )
            );
    }

    @CLI.Command(format = "[<USERNAME>] [<PASSWORD>]", description = "Prints or overrides the username and password.")
    public void user(String username, String password) {
        if (username != null) {
            try {
                logout();
            } catch (Exception e) {
                // ignore
            }

            if (password == null) {
                CLI.Arguments arguments = cli.consume("Password: ");

                password = arguments.consume(String.class).orElse(null);
            }

            if (password != null) {
                loginMethod.setUsernamePassword(username, password);
            } else {
                cli.warn(PnetRestClient.ABORTED_TEXT);
            }
        }

        cli.info("username = %s", loginMethod.getUsername());
    }

    @CLI.Command(format = "<TOKEN>", description = "Sets the authentication token.")
    public void token(String token) {
        if (token == null) {
            CLI.Arguments arguments = cli.consume("token: ");

            token = arguments.consume(String.class).orElse(null);
        }

        if (token != null) {
            try {
                logout();
            } catch (Exception e) {
                // ignore
            }

            loginMethod.setToken(token);

            cli.info("Token set.");
        } else {
            cli.warn(PnetRestClient.ABORTED_TEXT);
        }
    }

    @CLI.Command(name = "get", format = "URI", description = "Performs an GET request.")
    public void get(String uri) throws RestException, PnetDataClientException {
        if (uri == null) {
            cli.error("URI is missing.");
        } else if (!uri.startsWith("/api")) {
            cli.error("URI must start with \"/api\".");
        } else {
            HashMap<?, ?> result = context.restCall().encodedPathSegment(uri).get(HashMap.class);

            cli.info(PrettyPrint.prettyPrint(result));
        }
    }

    @CLI.Command(description = "Print compact results.")
    public void compact() {
        cli.info("Printing compact results.");

        compact = true;
    }

    @CLI.Command(description = "Print detailed results.")
    public void detailed() {
        cli.info("Printing detailed results.");

        compact = false;
    }

    @CLI.Command(format = "LANGUAGE-TAG", description = "Set the language.")
    public void language(String languageTag) {
        language = languageTag == null ? Locale.getDefault() : Locale.forLanguageTag(languageTag);

        cli.info("Language set to: %s", language);
    }

    @CLI.Command(format = "<true|false>", description = "Toggle the use of the scrolling feature.")
    public void scroll(Boolean scroll) {
        if (scroll != null) {
            this.scroll = scroll;
        }

        cli.info("Scrolling is %s.", this.scroll ? "enabled" : "disabled");
    }

    @CLI.Command(description = "Prints the next page of the last result.")
    public void next() throws PnetDataClientException {
        if (currentResult == null) {
            cli.error("No result available.");
            return;
        }

        if (currentResult.isEmpty()) {
            cli.error("There is no next page.");
            return;
        }

        currentResult.nextPage().print(cli, compact);
    }

    @CLI.Command(name = "page size", format = "<SIZE>", description = "Sets the number of items per page.")
    public void pageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            cli.error("Invalid size.");
            return;
        }

        this.pageSize = pageSize;
    }

    @CLI.Command(name = "page", format = "<PAGE>", description = "Sets the current page.")
    public void page(Integer page) {
        if (page == null || page < 1) {
            cli.error("Invalid page.");
            return;
        }

        this.page = page;
    }

    @CLI.Command(name = "no aggs", description = "Disables aggregations.")
    public void noAggs() {
        cli.info("Aggs disabled.");
        aggs = false;
    }

    @CLI.Command(description = "Enables aggregations or prints them, if available.")
    public void aggs() {
        if (!aggs) {
            cli.info("Aggs enabled.");
            aggs = true;
            return;
        }

        if (currentResult == null) {
            cli.error("No result available.");
            return;
        }

        currentResult.printAggregations(cli);
    }

    <T> void printResults(PnetDataClientResultPage<T> page, BiConsumer<Table, T> populateTableFn) {
        cli.info(
            "Found %d results (page %s of %s)",
            page.getTotalNumberOfItems(),
            page.getPageIndex(),
            page.getNumberOfPages()
        );

        printPage(page, populateTableFn);
    }

    <T> void printResults(List<T> list, BiConsumer<Table, T> populateTableFn) {
        cli.info("Found %d results.", list.size());

        printList(list, populateTableFn);
    }

    <T> void printAllResults(PnetDataClientResultPage<T> page, BiConsumer<Table, T> populateTableFn)
        throws PnetDataClientException {
        cli.info("Estimating %d results", page.getTotalNumberOfItems());

        long millis = System.currentTimeMillis();
        int count = 0;

        while (page != null && !page.isEmpty()) {
            Table table = new Table();

            page.stream().forEach(item -> populateTableFn.accept(table, item));

            cli.info(table.toString());

            count += page.size();
            page = page.nextPage();
        }

        cli.info("\nFound %d results in %,.3f seconds", count, (System.currentTimeMillis() - millis) / 1000d);
    }

    private <T> void printPage(PnetDataClientResultPage<T> page, BiConsumer<Table, T> populateTableFn) {
        currentResult = new CurrentResult<>(page, populateTableFn);
        currentResult.print(cli, compact);
    }

    private <T> void printList(List<T> list, BiConsumer<Table, T> populateTableFn) {
        Table table = new Table();

        list.forEach(item -> populateTableFn.accept(table, item));

        cli.info(table.toString());
    }

    public void consume() {
        cli.info("Partner.Net REST Client Sample application");
        cli.info("==========================================");
        cli.info("");
        cli.info("Type \"? [q]\" for help.");

        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            try {
                if (!cli.consumeCommand(null)) {
                    break;
                }
            } catch (Exception e) {
                cli.error("Command failed", e);
            }
        }

        cli.info(PnetRestClient.ABORTED_TEXT);
    }

    static String joinQuery(String... qs) {
        if (qs == null || qs.length == 0) {
            return null;
        }

        return String.join(" ", qs);
    }

    static void showImage(String title, Image image) {
        Canvas canvas = new Canvas() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public void paint(Graphics g) {
                g.drawImage(image, 0, 0, this);
            }
        };

        canvas.setPreferredSize(new Dimension(image.getWidth(canvas), image.getHeight(canvas)));

        JFrame frame = new JFrame(title);

        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static LocalDateTime parseUpdatedAfter(String updatedAfter) {
        if (updatedAfter == null) {
            return LocalDateTime.now().minusDays(1);
        }

        try {
            return LocalDateTime.now().minus(Duration.parse("PT" + updatedAfter));
        } catch (DateTimeParseException e) {
            try {
                LocalDateTime timestamp = LocalDate.now().atTime(
                    LocalTime.parse(updatedAfter, DateTimeFormatter.ofPattern("HH:mm:ss"))
                );

                while (timestamp.isAfter(LocalDateTime.now())) {
                    timestamp = timestamp.minusDays(1);
                }

                return timestamp;
            } catch (DateTimeParseException e2) {
                try {
                    LocalDateTime timestamp = LocalDate.parse(
                        updatedAfter,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    ).atStartOfDay();

                    while (timestamp.isAfter(LocalDateTime.now())) {
                        timestamp = timestamp.minusDays(1);
                    }

                    return timestamp;
                } catch (DateTimeParseException e3) {
                    throw new IllegalArgumentException(
                        "Failed to parse " +
                            updatedAfter +
                            ". Try using a time like 13:22:10, a date like 2020-03-21 or a duration like 10M."
                    );
                }
            }
        }
    }

    private static final String INDEX_NAME = "indexName";
    private static final String ABORTED_TEXT = "Aborted.";
}
