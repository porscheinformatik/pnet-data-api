package pnet.data.api;

import static pnet.data.api.util.PrettyPrint.*;

import java.awt.Canvas;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
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
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import at.porscheinformatik.happyrest.RestException;
import pnet.data.api.about.AboutDataClient;
import pnet.data.api.about.AboutDataDTO;
import pnet.data.api.activity.ActivityDataClient;
import pnet.data.api.activity.ActivityDataDTO;
import pnet.data.api.activity.ActivityDataFind;
import pnet.data.api.activity.ActivityDataGet;
import pnet.data.api.activity.ActivityDataSearch;
import pnet.data.api.activity.ActivityItemDTO;
import pnet.data.api.advisortype.AdvisorTypeDataClient;
import pnet.data.api.advisortype.AdvisorTypeDataDTO;
import pnet.data.api.advisortype.AdvisorTypeDataFind;
import pnet.data.api.advisortype.AdvisorTypeDataGet;
import pnet.data.api.advisortype.AdvisorTypeDataSearch;
import pnet.data.api.advisortype.AdvisorTypeItemDTO;
import pnet.data.api.apache.PnetApacheRestClientLauncher;
import pnet.data.api.application.ApplicationDataClient;
import pnet.data.api.application.ApplicationDataDTO;
import pnet.data.api.application.ApplicationDataFind;
import pnet.data.api.application.ApplicationDataGet;
import pnet.data.api.application.ApplicationDataSearch;
import pnet.data.api.application.ApplicationItemDTO;
import pnet.data.api.brand.BrandDataClient;
import pnet.data.api.brand.BrandDataDTO;
import pnet.data.api.brand.BrandDataFind;
import pnet.data.api.brand.BrandDataGet;
import pnet.data.api.brand.BrandDataSearch;
import pnet.data.api.brand.BrandItemDTO;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.company.CompanyAutoCompleteDTO;
import pnet.data.api.company.CompanyDataAutoComplete;
import pnet.data.api.company.CompanyDataClient;
import pnet.data.api.company.CompanyDataDTO;
import pnet.data.api.company.CompanyDataFind;
import pnet.data.api.company.CompanyDataGet;
import pnet.data.api.company.CompanyDataSearch;
import pnet.data.api.company.CompanyItemDTO;
import pnet.data.api.company.CompanyMerge;
import pnet.data.api.company.ContractTypeDataFind;
import pnet.data.api.companygroup.CompanyGroupDataClient;
import pnet.data.api.companygroup.CompanyGroupDataDTO;
import pnet.data.api.companygroup.CompanyGroupDataGet;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataClient;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataDTO;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataFind;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataGet;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataSearch;
import pnet.data.api.companygrouptype.CompanyGroupTypeItemDTO;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataClient;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataDTO;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataFind;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataGet;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataSearch;
import pnet.data.api.companynumbertype.CompanyNumberTypeItemDTO;
import pnet.data.api.companytype.CompanyTypeDataClient;
import pnet.data.api.companytype.CompanyTypeDataDTO;
import pnet.data.api.companytype.CompanyTypeDataFind;
import pnet.data.api.companytype.CompanyTypeDataGet;
import pnet.data.api.companytype.CompanyTypeDataSearch;
import pnet.data.api.companytype.CompanyTypeItemDTO;
import pnet.data.api.contractstate.ContractStateDataClient;
import pnet.data.api.contractstate.ContractStateDataDTO;
import pnet.data.api.contractstate.ContractStateDataFind;
import pnet.data.api.contractstate.ContractStateDataGet;
import pnet.data.api.contractstate.ContractStateDataSearch;
import pnet.data.api.contractstate.ContractStateItemDTO;
import pnet.data.api.contracttype.ContractTypeDataClient;
import pnet.data.api.contracttype.ContractTypeDataDTO;
import pnet.data.api.contracttype.ContractTypeDataGet;
import pnet.data.api.contracttype.ContractTypeDataSearch;
import pnet.data.api.contracttype.ContractTypeItemDTO;
import pnet.data.api.externalbrand.ExternalBrandDataClient;
import pnet.data.api.externalbrand.ExternalBrandDataDTO;
import pnet.data.api.externalbrand.ExternalBrandDataFind;
import pnet.data.api.externalbrand.ExternalBrandDataGet;
import pnet.data.api.externalbrand.ExternalBrandDataSearch;
import pnet.data.api.externalbrand.ExternalBrandItemDTO;
import pnet.data.api.function.FunctionDataClient;
import pnet.data.api.function.FunctionDataDTO;
import pnet.data.api.function.FunctionDataFind;
import pnet.data.api.function.FunctionDataGet;
import pnet.data.api.function.FunctionDataSearch;
import pnet.data.api.function.FunctionItemDTO;
import pnet.data.api.java.PnetJavaRestClientLauncher;
import pnet.data.api.legalform.LegalFormDataClient;
import pnet.data.api.legalform.LegalFormDataDTO;
import pnet.data.api.legalform.LegalFormDataFind;
import pnet.data.api.legalform.LegalFormDataGet;
import pnet.data.api.legalform.LegalFormDataSearch;
import pnet.data.api.legalform.LegalFormItemDTO;
import pnet.data.api.numbertype.NumberTypeDataClient;
import pnet.data.api.numbertype.NumberTypeDataDTO;
import pnet.data.api.numbertype.NumberTypeDataFind;
import pnet.data.api.numbertype.NumberTypeDataGet;
import pnet.data.api.numbertype.NumberTypeDataSearch;
import pnet.data.api.numbertype.NumberTypeItemDTO;
import pnet.data.api.person.ActivePersonCompanyLinkDTO;
import pnet.data.api.person.ActivePersonFunctionLinkDTO;
import pnet.data.api.person.PersonAggregationsDTO;
import pnet.data.api.person.PersonAutoCompleteDTO;
import pnet.data.api.person.PersonDataAutoComplete;
import pnet.data.api.person.PersonDataClient;
import pnet.data.api.person.PersonDataDTO;
import pnet.data.api.person.PersonDataFind;
import pnet.data.api.person.PersonDataGet;
import pnet.data.api.person.PersonDataSearch;
import pnet.data.api.person.PersonItemDTO;
import pnet.data.api.proposal.ProposalDataClient;
import pnet.data.api.proposal.ProposalDataFind;
import pnet.data.api.proposal.ProposalDataSearch;
import pnet.data.api.proposal.ProposalItemDTO;
import pnet.data.api.proposal.ProposalState;
import pnet.data.api.settings.Visibility;
import pnet.data.api.spring.PnetSpringRestClientLauncher;
import pnet.data.api.util.AbstractAutoCompleteDTO;
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
import pnet.data.api.util.CLI.Arguments;
import pnet.data.api.util.CompanyMergable;
import pnet.data.api.util.ImageType;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.MutablePnetDataApiLoginMethod;
import pnet.data.api.util.Prefs;
import pnet.data.api.util.PrettyPrint;
import pnet.data.api.util.Resource;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictApprovalNeeded;
import pnet.data.api.util.RestrictApproved;
import pnet.data.api.util.RestrictArchived;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompany;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictContractState;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictCredentialsAvailable;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictFunction;
import pnet.data.api.util.RestrictNumberType;
import pnet.data.api.util.RestrictQueryField;
import pnet.data.api.util.RestrictRejected;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.RestrictVisibility;
import pnet.data.api.util.Table;

/**
 * The client with all commands. See {@link PnetApacheRestClientLauncher}, {@link PnetJavaRestClientLauncher} or
 * {@link PnetSpringRestClientLauncher} on how to launch this client.
 *
 * @author ham
 */
public final class PnetRestClient
{

    private static class CurrentResult<T>
    {
        private PnetDataClientResultPage<T> page;
        private final BiConsumer<Table, T> populateTableFn;

        CurrentResult(PnetDataClientResultPage<T> page, BiConsumer<Table, T> populateTableFn)
        {
            super();

            this.page = page;
            this.populateTableFn = populateTableFn;
        }

        protected int getPageIndex()
        {
            return page.getPageIndex();
        }

        protected boolean hasNextPage()
        {
            return page.hasNextPage();
        }

        protected CurrentResult<T> nextPage() throws PnetDataClientException
        {
            page = page.nextPage();

            return this;
        }

        protected CurrentResult<T> page(int index) throws PnetDataClientException
        {
            page = page.getPage(index);

            return this;
        }

        protected void print(CLI cli, boolean asTable)
        {
            if (asTable && populateTableFn != null)
            {
                Table table = new Table();

                page.stream().forEach(item -> populateTableFn.accept(table, item));

                cli.info(table.toString());
            }
            else
            {
                page.stream().map(PrettyPrint::prettyPrint).forEach(cli::info);
            }

            cli
                .info(
                    "\nThis is page %d of %d (%d of %d results). Type \"next\", \"prev\" or \"page <NUM>\" to navigate.",
                    page.getPageIndex() + 1, page.getNumberOfPages(), page.size(), page.getTotalNumberOfItems());
        }

        protected void printAggregations(CLI cli)
        {
            if (page == null || !(page instanceof PnetDataClientResultPageWithAggregations<?, ?>))
            {
                cli.error("No aggregations available in current page.");

                return;
            }

            cli
                .info(
                    PrettyPrint.prettyPrint(((PnetDataClientResultPageWithAggregations<?, ?>) page).getAggregations()));
        }
    }

    private final CLI cli;

    private final MutablePnetDataApiLoginMethod loginMethod;

    private final AboutDataClient aboutDataClient;

    private final ActivityDataClient activityDataClient;

    private final AdvisorTypeDataClient advisorTypeDataClient;

    private final ApplicationDataClient applicationDataClient;

    private final BrandDataClient brandDataClient;

    private final CompanyDataClient companyDataClient;

    private final CompanyGroupDataClient companyGroupDataClient;

    private final CompanyGroupTypeDataClient companyGroupTypeDataClient;

    private final CompanyNumberTypeDataClient companyNumberTypeDataClient;

    private final CompanyTypeDataClient companyTypeDataClient;

    private final ContractStateDataClient contractStateDataClient;

    private final ContractTypeDataClient contractTypeDataClient;

    private final ExternalBrandDataClient externalBrandDataClient;

    private final FunctionDataClient functionDataClient;

    private final LegalFormDataClient legalFormDataClient;

    private final NumberTypeDataClient numberTypeDataClient;

    private final PersonDataClient personDataClient;

    private final ProposalDataClient proposalDataClient;

    private final PnetDataApiContext context;

    private final List<String> restrictedTenants = new ArrayList<>();
    private final List<String> restrictedBrands = new ArrayList<>();
    private final List<Integer> restrictedCompanyIds = new ArrayList<>();
    private final List<String> restrictedCompanyMatchcodes = new ArrayList<>();
    private final List<String> restrictedCompanyNumbers = new ArrayList<>();
    private final List<String> restrictedFunctionMatchcodes = new ArrayList<>();
    private final List<String> restrictedActivityMatchcodes = new ArrayList<>();
    private final List<String> restrictedNumberTypeMatchcodes = new ArrayList<>();
    private final List<String> restrictedCompanyTypeMatchcodes = new ArrayList<>();
    private final List<String> restrictedContractTypeMatchcodes = new ArrayList<>();
    private final List<String> restrictedContractStateMatchcodes = new ArrayList<>();
    private final List<Visibility> restrictedVisibilities = new ArrayList<>();
    private final List<String> restrictedQueryFields = new ArrayList<>();

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

    public PnetRestClient(MutablePnetDataApiLoginMethod loginMethod, AboutDataClient aboutDataClient,
        ActivityDataClient activityDataClient, AdvisorTypeDataClient advisorTypeDataClient,
        ApplicationDataClient applicationDataClient, BrandDataClient brandDataClient,
        CompanyDataClient companyDataClient, CompanyGroupDataClient companyGroupDataClient,
        CompanyGroupTypeDataClient companyGroupTypeDataClient, CompanyNumberTypeDataClient companyNumberTypeDataClient,
        CompanyTypeDataClient companyTypeDataClient, ContractStateDataClient contractStateDataClient,
        ContractTypeDataClient contractTypeDataClient, ExternalBrandDataClient externalBrandDataClient,
        FunctionDataClient functionDataClient, LegalFormDataClient legalFormDataClient,
        NumberTypeDataClient numberTypeDataClient, PersonDataClient personDataClient,
        ProposalDataClient proposalDataClient, PnetDataApiContext context)
    {
        super();
        this.loginMethod = loginMethod;
        this.aboutDataClient = aboutDataClient;
        this.activityDataClient = activityDataClient;
        this.advisorTypeDataClient = advisorTypeDataClient;
        this.applicationDataClient = applicationDataClient;
        this.brandDataClient = brandDataClient;
        this.companyDataClient = companyDataClient;
        this.companyGroupDataClient = companyGroupDataClient;
        this.companyGroupTypeDataClient = companyGroupTypeDataClient;
        this.companyNumberTypeDataClient = companyNumberTypeDataClient;
        this.companyTypeDataClient = companyTypeDataClient;
        this.contractStateDataClient = contractStateDataClient;
        this.contractTypeDataClient = contractTypeDataClient;
        this.externalBrandDataClient = externalBrandDataClient;
        this.functionDataClient = functionDataClient;
        this.legalFormDataClient = legalFormDataClient;
        this.numberTypeDataClient = numberTypeDataClient;
        this.personDataClient = personDataClient;
        this.proposalDataClient = proposalDataClient;
        this.context = context;

        cli = new CLI();

        cli.register(this);
    }

    @CLI.Command(description = "Info about the Partner.Net Data API and the user.")
    public void about() throws PnetDataClientException
    {
        AboutDataDTO about = aboutDataClient.about();

        cli.info(prettyPrint(about));
    }

    @CLI.Command(description = "Prints the JSON Web Token of the user.")
    public void jwt() throws PnetDataClientException
    {
        cli.info("jwt = %s", context.restCall().getHeader("Authorization"));
    }

    @CLI.Command(description = "Invalidates the stored JSON Web Token.")
    public void logout() throws PnetDataClientException
    {
        context.invalidateLogin();

        cli.info("Logged out.");
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get activity by mc", "get activities by mc"}, format = "<MC...>",
        description = "Returns the activities with the specified matchcodes.")
    public void getActivities(String... matchcodes) throws PnetDataClientException
    {
        ActivityDataGet query = restrict(activityDataClient.get());
        PnetDataClientResultPage<ActivityDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all activities", description = "Exports all activities.")
    public void exportAllActivities() throws PnetDataClientException
    {
        ActivityDataFind query = restrict(activityDataClient.find().scroll());
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated activities", format = "[updatedAfter]",
        description = "Exports all activities updated since yesterday.")
    public void exportAllUpdatedActivities(String updatedAfter) throws PnetDataClientException
    {
        ActivityDataFind query =
            restrict(activityDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)).scroll());
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find activities by mc", "find activity by mc"}, format = "<MC...>",
        description = "Find activities by matchcodes.")
    public void findActivities(String... matchcodes) throws PnetDataClientException
    {
        ActivityDataFind query = restrict(activityDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search activities", "search activity"}, format = "<QUERY>", description = "Query activities.")
    public void searchActivities(String... qs) throws PnetDataClientException
    {
        ActivityDataSearch query = restrict(activityDataClient.search());
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ActivityItemDTO dto)
    {
        table
            .addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getTenants(), dto.getBrands(),
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get advisor type by mc", "get advisor types by mc"}, format = "<MC...>",
        description = "Returns the advisor types with the specified matchcodes.")
    public void getAdvisorTypes(String... matchcodes) throws PnetDataClientException
    {
        AdvisorTypeDataGet query = restrict(advisorTypeDataClient.get());
        PnetDataClientResultPage<AdvisorTypeDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all advisor types", description = "Exports all advisor types.")
    public void exportAllAdvisorTypes() throws PnetDataClientException
    {
        AdvisorTypeDataFind query = restrict(advisorTypeDataClient.find());
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated advisor types", format = "[updatedAfter]",
        description = "Exports all advisor types updated since yesterday.")
    public void exportAllUpdatedAdvisorTypes(String updatedAfter) throws PnetDataClientException
    {
        AdvisorTypeDataFind query =
            restrict(advisorTypeDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find advisor types by mc", "find advisor type by mc"}, format = "<MC...>",
        description = "Find advisor types by matchcodes.")
    public void findAdvisorTypes(String... matchcodes) throws PnetDataClientException
    {
        AdvisorTypeDataFind query = restrict(advisorTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search advisor types", "search advisor type"}, format = "<QUERY>",
        description = "Query advisor types.")
    public void searchAdvisorTypes(String... qs) throws PnetDataClientException
    {
        AdvisorTypeDataSearch query = restrict(advisorTypeDataClient.search());
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, AdvisorTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get application by mc", "get applications by mc"}, format = "<MC...>",
        description = "Returns the applications with the specified matchcodes.")
    public void getApplications(String... matchcodes) throws PnetDataClientException
    {
        ApplicationDataGet query = restrict(applicationDataClient.get());
        PnetDataClientResultPage<ApplicationDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all applications", description = "Exports all applications.")
    public void exportAllApplications() throws PnetDataClientException
    {
        ApplicationDataFind query = restrict(applicationDataClient.find().scroll());
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated applications", format = "[updatedAfter]",
        description = "Exports all applications updated since yesterday.")
    public void exportAllUpdatedApplications(String updatedAfter) throws PnetDataClientException
    {
        ApplicationDataFind query =
            restrict(applicationDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)).scroll());
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find applications by mc", "find application by mc"}, format = "<MC...>",
        description = "Find applications by matchcodes.")
    public void findApplications(String... matchcodes) throws PnetDataClientException
    {
        ApplicationDataFind query = restrict(applicationDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search applications", "search application"}, format = "<QUERY>",
        description = "Query applications.")
    public void searchApplications(String... qs) throws PnetDataClientException
    {
        ApplicationDataSearch query = restrict(applicationDataClient.search());
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ApplicationItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get brand by mc", "get brands by mc"}, format = "<MC...>",
        description = "Returns the brands with the specified matchcodes.")
    public void getBrands(String... matchcodes) throws PnetDataClientException
    {
        BrandDataGet query = restrict(brandDataClient.get());
        PnetDataClientResultPage<BrandDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all brands", description = "Export all brands updated since yesterday.")
    public void exportAllBrands() throws PnetDataClientException
    {
        BrandDataFind query = restrict(brandDataClient.find());
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated brands", format = "[updatedAfter]",
        description = "Export all brands updated since yesterday.")
    public void exportAllUpdatedBrands(String updatedAfter) throws PnetDataClientException
    {
        BrandDataFind query = restrict(brandDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find brands by mc", "find brand by mc"}, format = "<MC...>",
        description = "Find brands by matchcodes.")
    public void findBrands(String... matchcodes) throws PnetDataClientException
    {
        BrandDataFind query = restrict(brandDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search brands", "search brand"}, format = "<QUERY>", description = "Query brands.")
    public void searchBrands(String... qs) throws PnetDataClientException
    {
        BrandDataSearch query = restrict(brandDataClient.search());
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"restrict brands", "restrict brand"}, format = "[<BRAND>...]",
        description = "Places a restriction with brands for subsequent operations.")
    public void restrictBrands(String... brands)
    {
        if (brands != null && brands.length > 0)
        {
            Arrays.stream(brands).forEach(restrictedBrands::add);
        }

        cli.info("Requests are restricted to brands: %s", restrictedBrands);
    }

    @CLI.Command(name = {"clear brand restrictions", "clear brand restriction"},
        description = "Removes all restrictions for brands.")
    public void clearBrandRestrictions()
    {
        cli.info("Removed %s brand restrictions.", restrictedBrands.size());

        restrictedBrands.clear();
    }

    protected void populateTable(Table table, BrandItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getTenants(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get company by id", "get companies by id"}, format = "<COMPANY-ID...>",
        description = "Returns the companies with the specified ids.")
    public void getCompaniesByIds(Integer... ids) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByIds(Arrays.asList(ids), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get company by mc", "get companies by mc"}, format = "<COMPANY-MC...>",
        description = "Returns the companies with the specified matchcode.")
    public void getCompaniesByMatchcodes(String... matchcodes) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get company by vat", "get companies by vat"}, format = "<COMPANY-VATIDNUMBER...>",
        description = "Returns the companies with the specified vat id numbers.")
    public void getCompaniesByVatIdNumbers(String... vatIdNumbers) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result =
            query.allByVatIdNumbers(Arrays.asList(vatIdNumbers), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get company by number", "get companies by number"}, format = "<COMPANY-NUMBER...>",
        description = "Returns the companies with the specified company numbers.")
    public void getCompaniesByCompanyNumbers(String... companyNumbers) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result =
            query.allByCompanyNumbers(Arrays.asList(companyNumbers), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get company by iban", "get companies by iban"}, format = "<COMPANY-IBAN...>",
        description = "Returns the company with the specified ibans.")
    public void getCompaniesByIbans(String... ibans) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByIbans(Arrays.asList(ibans), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get company by email", "get companies by email"}, format = "<COMPANY-EMAIL...>",
        description = "Returns the company with the specified emails.")
    public void getCompaniesByEmails(String... emails) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByEmails(Arrays.asList(emails), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get company by dvr", "get companies by dvr"}, format = "<COMPANY-DPRN...>",
        description = "Returns the companies with the specified data processing register numbers.")
    public void getCompaniesByDataProcessingRegisterNumbers(String... dataProcessingRegisterNumbers)
        throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result =
            query.allByDataProcessingRegisterNumbers(Arrays.asList(dataProcessingRegisterNumbers), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all companies", description = "Exports all companies.")
    public void exportAllCompanies() throws PnetDataClientException
    {
        CompanyDataFind query = restrict(companyDataClient.find().scroll());
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated companies", format = "[updatedAfter]",
        description = "Exports all companies updated since yesterday.")
    public void exportAllUpdatedCompanies(String updatedAfter) throws PnetDataClientException
    {
        CompanyDataFind query =
            restrict(companyDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)).scroll());
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find companies by id", "find company by id"}, format = "<ID...>",
        description = "Find companies by id.")
    public void findCompaniesByIds(Integer... ids) throws PnetDataClientException
    {
        CompanyDataFind query = restrict(companyDataClient.find().id(ids));
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find companies by mc", "find company by mc"}, format = "<MC...>",
        description = "Find companies by matchcode.")
    public void findCompaniesByMatchcodes(String... mcs) throws PnetDataClientException
    {
        CompanyDataFind query = restrict(companyDataClient.find().matchcode(mcs));
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find companies by number", "find company by number"}, format = "<NUMBER...>",
        description = "Find companies by company number.")
    public void findCompaniesByNumbers(String... numbers) throws PnetDataClientException
    {
        CompanyDataFind query = restrict(companyDataClient.find().companyNumber(numbers));
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"auto complete companies", "auto complete company"}, format = "<QUERY>",
        description = "Auto complete the name of companies.")
    public void autoCompleteCompanies(String... qs) throws PnetDataClientException
    {
        CompanyDataAutoComplete query = restrict(companyDataClient.autoComplete());
        List<CompanyAutoCompleteDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search companies", "search company"}, format = "<QUERY>", description = "Query companies.")
    public void searchCompanies(String... qs) throws PnetDataClientException
    {
        CompanyDataSearch query = restrict(companyDataClient.search());
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"restrict company ids", "restrict company id"}, format = "<ID...>",
        description = "Places a restriction with company numbers for subsequent operations.")
    public void restrictCompaniesById(Integer... ids)
    {
        if (ids != null && ids.length > 0)
        {
            Arrays.stream(ids).forEach(restrictedCompanyIds::add);
        }

        cli.info("Requests are restricted to company ids: %s", restrictedCompanyIds);
    }

    @CLI.Command(name = {"restrict company mcs", "restrict company mc"}, format = "<MC...>",
        description = "Places a restriction with company matchcodes for subsequent operations.")
    public void restrictCompaniesByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedCompanyMatchcodes::add);
        }

        cli.info("Requests are restricted to company matchcodes: %s", restrictedCompanyMatchcodes);
    }

    @CLI.Command(name = {"restrict company numbers", "restrict company number"}, format = "<NUMBER...>",
        description = "Places a restriction with company numbers for subsequent operations.")
    public void restrictCompaniesByNumber(String... numbers)
    {
        if (numbers != null && numbers.length > 0)
        {
            Arrays.stream(numbers).forEach(restrictedCompanyNumbers::add);
        }

        cli.info("Requests are restricted to company numbers: %s", restrictedCompanyNumbers);
    }

    @CLI.Command(name = {"restrict functions", "restrict function"}, format = "<MC...>",
        description = "Places a restriction of functions for subsequent operations.")
    public void restrictFunctionsByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedFunctionMatchcodes::add);
        }

        cli.info("Requests are restricted to function matchcodes: %s", restrictedFunctionMatchcodes);
    }

    @CLI.Command(name = {"restrict activities", "restrict activity"}, format = "<MC...>",
        description = "Places a restriction of activities for subsequent operations.")
    public void restrictActivitiesByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedActivityMatchcodes::add);
        }

        cli.info("Requests are restricted to activity matchcodes: %s", restrictedActivityMatchcodes);
    }

    @CLI.Command(name = {"restrict number types", "restrict number type"}, format = "<MC...>",
        description = "Places a restriction of number types for subsequent operations.")
    public void restrictNumberTypesByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedNumberTypeMatchcodes::add);
        }

        cli.info("Requests are restricted to number type matchcodes: %s", restrictedNumberTypeMatchcodes);
    }

    @CLI.Command(name = {"restrict company types", "restrict company type"}, format = "<MC...>",
        description = "Places a restriction of company types for subsequent operations.")
    public void restrictCompanyTypesByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedCompanyTypeMatchcodes::add);
        }

        cli.info("Requests are restricted to company type matchcodes: %s", restrictedCompanyTypeMatchcodes);
    }

    @CLI.Command(name = {"restrict contract types", "restrict contract type"}, format = "<MC...>",
        description = "Places a restriction of contract types for subsequent operations.")
    public void restrictContractTypesByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedContractTypeMatchcodes::add);
        }

        cli.info("Requests are restricted to contract type matchcodes: %s", restrictedContractTypeMatchcodes);
    }

    @CLI.Command(name = {"restrict contract states", "restrict contract state"}, format = "<MC...>",
        description = "Places a restriction of contract states for subsequent operations.")
    public void restrictContractStatesByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedContractStateMatchcodes::add);
        }

        cli.info("Requests are restricted to contract state matchcodes: %s", restrictedContractStateMatchcodes);
    }

    @CLI.Command(name = {"clear company restrictions", "clear company restriction"},
        description = "Removes all restrictions for companies.")
    public void clearCompanyRestrictions()
    {
        cli
            .info("Removed %s company restrictions.",
                restrictedCompanyIds.size() + restrictedCompanyMatchcodes.size() + restrictedCompanyNumbers.size());

        restrictedCompanyIds.clear();
        restrictedCompanyMatchcodes.clear();
        restrictedCompanyNumbers.clear();
    }

    @CLI.Command(name = "merge internet groups",
        description = "Merges companies according to their internet group settings.")
    public void mergeInternetGroups()
    {
        cli.info("Companies will be merged according to their internet group settings.");

        companyMerge = CompanyMerge.INTERNET_GROUP;
    }

    @CLI.Command(name = "merge none", description = "Do not merge companies.")
    public void mergeNone()
    {
        cli.info("Companies will not be merged.");

        companyMerge = CompanyMerge.NONE;
    }

    @CLI.Command(name = "dated back", format = "[updatedAfter]",
        description = "Sets the dated back parameter for the specified days")
    public void datedBackUnitl(String updatedAfter)
    {
        if (updatedAfter == null)
        {
            datedBackUntil = null;

            cli.info("Only up-to-date items will be searched and shown.");
        }
        else
        {
            datedBackUntil = parseUpdatedAfter(updatedAfter);

            cli.info("Items will be searched and shown, that are not older than %s.", datedBackUntil);
        }
    }

    @CLI.Command(name = "rejected only", description = "Show only results, that have been rejected.")
    public void rejectedOnly()
    {
        restrictRejected = Boolean.TRUE;

        cli.info("Only rejected items will be searched and shown.");
    }

    @CLI.Command(name = "not rejected only", description = "Show only results, that are not rejected.")
    public void notRejectedOnly()
    {
        restrictRejected = Boolean.FALSE;

        cli.info("Only not rejected items will be searched and shown.");
    }

    @CLI.Command(name = "ignore rejected flag", description = "Ignore the rejected flag.")
    public void clearRejected()
    {
        restrictRejected = null;

        cli.info("The rejected state of items will be ignored.");
    }

    @CLI.Command(name = "credentials available only", description = "Show only persons with login credentials")
    public void credentialsAvailableOnly()
    {
        restrictCredentialsAvailable = Boolean.TRUE;

        cli.info("Only persons with login credentials will be searched and shown.");
    }

    @CLI.Command(name = "no credentials available only", description = "Show only persons without login credentials")
    public void noCredentialsAvailableOnly()
    {
        restrictCredentialsAvailable = Boolean.FALSE;

        cli.info("Only persons without login credentials will be searched and shown.");
    }

    @CLI.Command(name = "ignore credentials available", description = "Ignore the crdentials available flag.")
    public void clearCredentialsAvailable()
    {
        restrictCredentialsAvailable = null;

        cli.info("The credentials available state of persons will be ignored.");
    }

    @CLI.Command(name = "approved only", description = "Show only persons, that have been approved.")
    public void approvedOnly()
    {
        restrictApproved = Boolean.TRUE;

        cli.info("Only persons that have been approved will be searched and shown.");
    }

    @CLI.Command(name = "approval pending only", description = "Show only persons, that have not been approved, yet.")
    public void notApprovedOnly()
    {
        restrictApproved = Boolean.FALSE;

        cli.info("Only persons, that have not been approved, yet, will be searched and shown.");
    }

    @CLI.Command(name = "ignore approved", description = "Ignore the approved flag.")
    public void clearApproved()
    {
        restrictApproved = null;

        cli.info("The approval state of persons will be ignored.");
    }

    @CLI.Command(name = "archived only", description = "Show only results, that have been archived.")
    public void archivedOnly()
    {
        restrictArchived = Boolean.TRUE;

        cli.info("Only archived items will be searched and shown.");
    }

    @CLI.Command(name = "not archived only", description = "Show only results, that are not archived.")
    public void notArchivedOnly()
    {
        restrictArchived = Boolean.FALSE;

        cli.info("Only not archived items will be searched and shown.");
    }

    @CLI.Command(name = "ignore archived flag", description = "Ignore the archived flag.")
    public void clearArchived()
    {
        restrictArchived = null;

        cli.info("The archived state of items will be ignored.");
    }

    protected void populateTable(Table table, CompanyItemDTO dto)
    {
        table
            .addRow(dto.getCompanyId(), dto.getMatchcode(), dto.getLabel(), dto.getAdministrativeTenant(),
                dto.getBrands(), dto.getTenants(), dto.getTypes(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get company group by leading company id", "get company groups by leading company id"},
        format = "<COMPANY-ID...>", description = "Returns the company groups with the specified ids.")
    public void getCompanyGroupByLeadingCompanyIds(Integer... ids) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByLeadingCompanyIds(Arrays.asList(ids), 0, pageSize);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"get company group by leading company number", "get company groups by leading company number"},
        format = "<COMPANY-NUMBER...>", description = "Returns the company groups with the specified numbers.")
    public void getCompanyGroupByLeadingCompanyNumbers(String... numbers) throws PnetDataClientException
    {
        CompanyGroupDataGet request = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            request.allByLeadingCompanyNumbers(Arrays.asList(numbers), 0, pageSize);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"get company group by leading company mc", "get company groups by leading company mc"},
        format = "<COMPANY-MC...>", description = "Returns the company groups with the specified matchcodes.")
    public void getCompanyGroupByLeadingCompanyMatchcodes(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByLeadingCompanies(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"get company group by company id", "get company groups by company id"},
        format = "<COMPANY-ID...>", description = "Returns the company groups with the specified ids.")
    public void getCompanyGroupByCompanyIds(Integer... ids) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanyIds(Arrays.asList(ids), 0, pageSize);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"get company group by company number", "get company groups by company number"},
        format = "<COMPANY-NUMBER...>", description = "Returns the company groups with the specified numbers.")
    public void getCompanyGroupByCompanyNumbers(String... numbers) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByCompanyNumbers(Arrays.asList(numbers), 0, pageSize);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"get company group by company mc", "get company groups by company mc"},
        format = "<COMPANY-MC...>", description = "Returns the company groups with the specified matchcodes.")
    public void getCompanyGroupByCompanyMatchcodes(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByCompanies(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all company groups", format = "[<GROUP-MC...>]",
        description = "Exports all company groups.")
    public void exportAllCompanyGroups(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupTypeDataFind find = companyGroupTypeDataClient.find();

        if (matchcodes != null && matchcodes.length > 0)
        {
            find = find.matchcode(matchcodes);
        }

        List<String> companyGroupTypeMatchcodes = restrict(find)
            .execute(language, 0, pageSize)
            .stream()
            .map(CompanyGroupTypeItemDTO::getMatchcode)
            .collect(Collectors.toList());

        CompanyGroupDataGet query = restrict(companyGroupDataClient.get().scroll());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByCompanyGroupTypes(companyGroupTypeMatchcodes, 0, 25);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find company groups by leader", "find company group by leader"}, format = "<COMPANY-ID...>",
        description = "Find all company groups with the specified leader.")
    public void findCompanyGroupsByLeader(Integer... ids) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByLeadingCompanyIds(Arrays.asList(ids), 0, pageSize);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find company groups by member", "find company group by member"}, format = "<COMPANY-ID...>",
        description = "Find all company groups with the specified member.")
    public void findCompanyGroupsByMember(Integer... ids) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanyIds(Arrays.asList(ids), 0, pageSize);

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, CompanyGroupDataDTO dto)
    {
        table
            .addRow(dto.getLeadingCompanyId(), dto.getLeadingCompanyMatchcode(), dto.getLeadingCompanyNumber(),
                dto.getMembers());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get company group type by mc", "get company group types by mc"}, format = "<MC...>",
        description = "Returns the company group types with the specified matchcodes.")
    public void getCompanyGroupTypes(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupTypeDataGet query = restrict(companyGroupTypeDataClient.get());
        PnetDataClientResultPage<CompanyGroupTypeDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get company group by type", "get company groups by type"}, format = "<MC...>",
        description = "Returns the company groups with the specified matchcodes.")
    public void getCompanyGroupTypesByType(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByCompanyGroupTypes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all company group types", description = "Exports all company group types.")
    public void exportAllCompanyGroupTypes() throws PnetDataClientException
    {
        CompanyGroupTypeDataFind query = restrict(companyGroupTypeDataClient.find());
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated company group types", format = "[updatedAfter]",
        description = "Exports all company group types updated since yesterday.")
    public void exportAllUpdatedCompanyGroupTypes(String updatedAfter) throws PnetDataClientException
    {
        CompanyGroupTypeDataFind query =
            restrict(companyGroupTypeDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find company group types by mc", "find company group type by mc"}, format = "<MC...>",
        description = "Find comany group types by matchcodes.")
    public void findCompanyGroupTypes(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupTypeDataFind query = restrict(companyGroupTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search company group types", "search company group type"}, format = "<QUERY>",
        description = "Query company group types.")
    public void searchCompanyGroupTypes(String... qs) throws PnetDataClientException
    {
        CompanyGroupTypeDataSearch query = restrict(companyGroupTypeDataClient.search());
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, CompanyGroupTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get company number type by mc", "get company number types by mc"}, format = "<MC...>",
        description = "Returns the company number types with the specified matchcodes.")
    public void getCompanyNumberTypes(String... matchcodes) throws PnetDataClientException
    {
        CompanyNumberTypeDataGet query = restrict(companyNumberTypeDataClient.get());
        PnetDataClientResultPage<CompanyNumberTypeDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all company number types", description = "Exports all company number types.")
    public void exportAllCompanyNumberTypes() throws PnetDataClientException
    {
        CompanyNumberTypeDataFind query = restrict(companyNumberTypeDataClient.find());
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated company number types", format = "[updatedAfter]",
        description = "Exports updated company number types.")
    public void exportAllUpdatedCompanyNumberTypes(String updatedAfter) throws PnetDataClientException
    {
        CompanyNumberTypeDataFind query =
            restrict(companyNumberTypeDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find company number types by mc", "find company number type by mc"}, format = "<MC...>",
        description = "Find comany number types by matchcodes.")
    public void findCompanyNumberTypes(String... matchcodes) throws PnetDataClientException
    {
        CompanyNumberTypeDataFind query = restrict(companyNumberTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search company number types", "search company number type"}, format = "<QUERY>",
        description = "Query company number types.")
    public void searchCompanyNumberTypes(String... qs) throws PnetDataClientException
    {
        CompanyNumberTypeDataSearch query = restrict(companyNumberTypeDataClient.search());
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, CompanyNumberTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get company type by mc", "get company types by mc"}, format = "<MC...>",
        description = "Returns the company types with the specified matchcodes.")
    public void getCompanyTypes(String... matchcodes) throws PnetDataClientException
    {
        CompanyTypeDataGet query = restrict(companyTypeDataClient.get());
        PnetDataClientResultPage<CompanyTypeDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all company types", description = "Exports all company types.")
    public void exportAllCompanyTypes() throws PnetDataClientException
    {
        CompanyTypeDataFind query = restrict(companyTypeDataClient.find());
        PnetDataClientResultPage<CompanyTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated company types", format = "[updatedAfter]",
        description = "Exports all company types updated since yesterday.")
    public void exportAllUpdatedCompanyTypes(String updatedAfter) throws PnetDataClientException
    {
        CompanyTypeDataFind query =
            restrict(companyTypeDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<CompanyTypeItemDTO> results = query.execute(language, 0, pageSize);

        printAllResults(results, this::populateTable);
    }

    @CLI.Command(name = {"search company types", "search company type"}, format = "<QUERY>",
        description = "Query company types.")
    public void searchCompanyTypes(String... qs) throws PnetDataClientException
    {
        CompanyTypeDataSearch query = restrict(companyTypeDataClient.search());
        PnetDataClientResultPage<CompanyTypeItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, CompanyTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getTenants(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get contract state by mc", "get contract states by mc"}, format = "<MC...>",
        description = "Returns the contract states with the specified matchcodes.")
    public void getContractStates(String... matchcodes) throws PnetDataClientException
    {
        ContractStateDataGet query = restrict(contractStateDataClient.get());
        PnetDataClientResultPage<ContractStateDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all contract states", description = "Exports all contract states.")
    public void exportAllContractStates() throws PnetDataClientException
    {
        ContractStateDataFind query = restrict(contractStateDataClient.find());
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated contract states", format = "[updatedAfter]",
        description = "Exports all contract states updated since yesterday.")
    public void exportAllUpdatedContractStates(String updatedAfter) throws PnetDataClientException
    {
        ContractStateDataFind query =
            restrict(contractStateDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find contract states by mc", "find contract state by mc"}, format = "<MC...>",
        description = "Find contract states by matchcodes.")
    public void findContractStates(String... matchcodes) throws PnetDataClientException
    {
        ContractStateDataFind query = restrict(contractStateDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search contract states", "search contract state"}, format = "<QUERY>",
        description = "Query contract states types.")
    public void searchContractStates(String... qs) throws PnetDataClientException
    {
        ContractStateDataSearch query = restrict(contractStateDataClient.search());
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ContractStateItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get contract type by mc", "get contract types by mc"}, format = "<MC...>",
        description = "Returns the contract types with the specified matchcodes.")
    public void getContractTypes(String... matchcodes) throws PnetDataClientException
    {
        ContractTypeDataGet query = restrict(contractTypeDataClient.get());
        PnetDataClientResultPage<ContractTypeDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all contract types", description = "Exports all contract types.")
    public void exportAllContractTypes() throws PnetDataClientException
    {
        ContractTypeDataFind query = restrict(contractTypeDataClient.find());
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated contract types", format = "[updatedAfter]",
        description = "Exports all contract types updated since yesterday.")
    public void exportAllUpdatedContractTypes(String updatedAfter) throws PnetDataClientException
    {
        ContractTypeDataFind query =
            restrict(contractTypeDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find contract types by mc", "find contract type by mc"}, format = "<MC...>",
        description = "Find contract types by matchcodes.")
    public void findContractTypes(String... matchcodes) throws PnetDataClientException
    {
        ContractTypeDataFind query = restrict(contractTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search contract types", "search contract type"}, format = "<QUERY>",
        description = "Query contract types.")
    public void searchContractTypes(String... qs) throws PnetDataClientException
    {
        ContractTypeDataSearch query = restrict(contractTypeDataClient.search());
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ContractTypeItemDTO dto)
    {
        table
            .addRow(dto.getMatchcode(), dto.getLabel(), dto.getType(), dto.getTenants(), dto.getBrands(),
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get external brand by mc", "get external brands by mc"}, format = "<MC...>",
        description = "Returns the external brands with the specified matchcodes.")
    public void getExternalBrands(String... matchcodes) throws PnetDataClientException
    {
        ExternalBrandDataGet query = restrict(externalBrandDataClient.get());
        PnetDataClientResultPage<ExternalBrandDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all external brands", description = "Exports all external brands.")
    public void exportAllExternalBrands() throws PnetDataClientException
    {
        ExternalBrandDataFind query = restrict(externalBrandDataClient.find());
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated external brands", format = "[updatedAfter]",
        description = "Exports all external brands updated since yesterday.")
    public void exportAllUpdatedExternalBrands(String updatedAfter) throws PnetDataClientException
    {
        ExternalBrandDataFind query =
            restrict(externalBrandDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find external brands by mc", "find external brand by mc"}, format = "<MC...>",
        description = "Find external brands by matchcodes.")
    public void findExternalBrands(String... matchcodes) throws PnetDataClientException
    {
        ExternalBrandDataFind query = restrict(externalBrandDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search external brands", "search external brand"}, format = "<QUERY>",
        description = "Query external brands.")
    public void searchExternalBrands(String... qs) throws PnetDataClientException
    {
        ExternalBrandDataSearch query = restrict(externalBrandDataClient.search());
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ExternalBrandItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get function by mc", "get functions by mc"}, format = "<MC...>",
        description = "Returns the functions with the specified matchcodes.")
    public void getFunctions(String... matchcodes) throws PnetDataClientException
    {
        FunctionDataGet query = restrict(functionDataClient.get());
        PnetDataClientResultPage<FunctionDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all functions", description = "Exports all functions.")
    public void exportAllFunctions() throws PnetDataClientException
    {
        FunctionDataFind query = restrict(functionDataClient.find().scroll());
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated functions", format = "[updatedAfter]",
        description = "Exports all functions updated since yesterday.")
    public void exportAllUpdatedFunctions(String updatedAfter) throws PnetDataClientException
    {
        FunctionDataFind query =
            restrict(functionDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)).scroll());
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find functions by mc", "find function by mc"}, format = "<MC...>",
        description = "Find functions by matchcodes.")
    public void findFunctions(String... matchcodes) throws PnetDataClientException
    {
        FunctionDataFind query = restrict(functionDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search functions", "search function"}, format = "<QUERY>", description = "Query functions.")
    public void searchFunctions(String... qs) throws PnetDataClientException
    {
        FunctionDataSearch query = restrict(functionDataClient.search());
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, FunctionItemDTO dto)
    {
        table
            .addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getTenants(), dto.getBrands(),
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get legal form by mc", "get legal forms by mc"}, format = "<MC...>",
        description = "Returns the legal forms with the specified matchcodes.")
    public void getLegalForms(String... matchcodes) throws PnetDataClientException
    {
        LegalFormDataGet query = restrict(legalFormDataClient.get());
        PnetDataClientResultPage<LegalFormDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all legal forms", description = "Exports all legal forms.")
    public void exportAllLegalForms() throws PnetDataClientException
    {
        LegalFormDataFind query = restrict(legalFormDataClient.find());
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated legal forms", format = "[updatedAfter]",
        description = "Exports all legal forms updated since yesterday.")
    public void exportAllUpdatedLegalForms(String updatedAfter) throws PnetDataClientException
    {
        LegalFormDataFind query = restrict(legalFormDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find legal forms by mc", "find legal form by mc"}, format = "<MC...>",
        description = "Find comany group types by matchcodes.")
    public void findLegalForms(String... matchcodes) throws PnetDataClientException
    {
        LegalFormDataFind query = restrict(legalFormDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search legal forms", "search legal form"}, format = "<QUERY>",
        description = "Query legal forms.")
    public void searchLegalForms(String... qs) throws PnetDataClientException
    {
        LegalFormDataSearch query = restrict(legalFormDataClient.search());
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, LegalFormItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get number type by mc", "get number types by mc"}, format = "<MC...>",
        description = "Returns the number types with the specified matchcodes.")
    public void getNumberTypes(String... matchcodes) throws PnetDataClientException
    {
        NumberTypeDataGet query = restrict(numberTypeDataClient.get());
        PnetDataClientResultPage<NumberTypeDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all number types", description = "Exports all number types.")
    public void exportAllNumberTypes() throws PnetDataClientException
    {
        NumberTypeDataFind query = restrict(numberTypeDataClient.find());
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated number types", format = "[updatedAfter]",
        description = "Exports all number types updated since yesterday.")
    public void exportAllUpdatedNumberTypes(String updatedAfter) throws PnetDataClientException
    {
        NumberTypeDataFind query = restrict(numberTypeDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)));
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find number types by mc", "find number type by mc"}, format = "<MC...>",
        description = "Find number types by matchcodes.")
    public void findNumberTypes(String... matchcodes) throws PnetDataClientException
    {
        NumberTypeDataFind query = restrict(numberTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search number types", "search number type"}, format = "<QUERY>",
        description = "Query number types.")
    public void searchNumberTypes(String... qs) throws PnetDataClientException
    {
        NumberTypeDataSearch query = restrict(numberTypeDataClient.search());
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, NumberTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"get person by id", "get persons by id"}, format = "<ID...>",
        description = "Returns all details of persons with the specified ids.")
    public void getPersonById(Integer... ids) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByIds(Arrays.asList(ids), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get person by external id", "get persons by external id"}, format = "<EXTERNALID...>",
        description = "Returns all details of persons with the specified external ids.")
    public void getPersonByExternalId(String... externalIds) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result =
            query.allByExternalIds(Arrays.asList(externalIds), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get person by guid", "get persons by guid"}, format = "<GUID...>",
        description = "Returns all details of persons with the specified guids.")
    public void getPersonByGuid(String... guids) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByGuids(Arrays.asList(guids), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get person by preferredUserId", "get persons by preferredUserId"}, format = "<PREFID...>",
        description = "Returns all details of persons with the specified preferredUserIds.")
    public void getPersonByPreferredUserId(String... preferredUserIds) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result =
            query.allByPreferredUserIds(Arrays.asList(preferredUserIds), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get person by email", "get persons by email"}, format = "<EMAIL...>",
        description = "Returns all details of persons with the specified emails.")
    public void getPersonByEmail(String... emails) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByEmails(Arrays.asList(emails), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = {"get person by personnel number", "get persons by personnel number"},
        format = "<PERSNUMBER...>", description = "Returns all details of persons with the specified personnelNumbers.")
    public void getPersonByPersonnelNumber(String... personnelNumbers) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result =
            query.allByPersonnelNumbers(Arrays.asList(personnelNumbers), 0, pageSize);

        printResults(result, null);
    }

    @CLI.Command(name = "export all persons", description = "Exports all persons available for the current user.")
    public void exportAllPersons() throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().scroll());
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated persons", format = "[updatedAfter]",
        description = "Exports all persons available for the current user, that have been updated since yesterday.")
    public void exportAllUpdatedPersons(String updatedAfter) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)).scroll());
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find persons by personnel number", "find person by personnel number"}, format = "<NUMBER...>",
        description = "Find persons by personnel number.")
    public void findPersonsByPersonnelNumber(String... numbers) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().personnelNumber(numbers));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find persons by email", "find person by email"}, format = "<EMAIL...>",
        description = "Find persons by email.")
    public void findPersonsByEmail(String... emails) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().email(emails));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find persons by salesman number", "find person by salesman number"}, format = "<NUMBER...>",
        description = "Find persons by salesman number.")
    public void findPersonsBySalesmanNumber(String... numbers) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().numbersType("NT_VERK_NR").number(numbers));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find persons by id", "find person by id"}, format = "<ID...>",
        description = "Find a person by id.")
    public void findPersonById(Integer... ids) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().id(ids));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find persons by company", "find person by company"}, format = "<COMPANY-MC...>",
        description = "Find persons at a specific company.")
    public void findPersonsByCompany(String... matchcodes) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().company(matchcodes));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find persons by role", "find person by role"}, format = "<ROLE-MC...>",
        description = "Find persons by functions and activities.")
    public void findPersonsByRole(String... matchcodes) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().role(matchcodes));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"auto complete persons", "auto complete person"}, format = "<QUERY>",
        description = "Auto complete the name of person.")
    public void autoCompletePersons(String... qs) throws PnetDataClientException
    {
        PersonDataAutoComplete query = restrict(personDataClient.autoComplete());
        List<PersonAutoCompleteDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search persons", "search person"}, format = "<QUERY>", description = "Search for a person.")
    public void searchPersons(String... qs) throws PnetDataClientException
    {
        PersonDataSearch query = restrict(personDataClient.search());
        PnetDataClientResultPageWithAggregations<PersonItemDTO, PersonAggregationsDTO> result =
            query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "get portrait of person", format = "<ID>",
        description = "Shows the portrait image of the person.")
    public void getPersonPortraitById(Integer id) throws PnetDataClientException
    {
        Optional<Resource> portrait = personDataClient.portrait(id, ImageType.ORIGINAL);

        if (!portrait.isPresent())
        {
            cli.error("Image not found.");
            return;
        }

        showImage("Portrait of Person " + id, portrait.get().toImage());
    }

    @CLI.Command(name = "get thumbnail of person", format = "<ID>",
        description = "Shows the thumbnail portrait image of the person.")
    public void getPersonPortraitThumbnailById(Integer id) throws PnetDataClientException
    {
        Optional<Resource> portrait = personDataClient.portrait(id, ImageType.THUMBNAIL);

        if (!portrait.isPresent())
        {
            cli.error("Image not found.");
            return;
        }

        showImage("Portrait thumbnail of Person " + id, portrait.get().toImage());
    }

    protected void populateTable(Table table, AbstractAutoCompleteDTO dto)
    {
        table.addRow(dto.getLabel(), dto.getDescription(), dto.getScore());
    }

    protected void populateTable(Table table, PersonItemDTO dto)
    {
        table
            .addRow(dto.getPersonId(), dto.getPersonnelNumber(), dto.getFormOfAddress(), dto.getAcademicTitle(),
                dto.getFirstName(), dto.getLastName(), dto.getAcademicTitlePostNominal(), dto.getAdministrativeTenant(),
                dto.getCompanies() != null ? dto
                    .getCompanies()
                    .stream()
                    .filter(link -> Objects.equals(dto.getContactCompanyId(), link.getCompanyId()))
                    .map(ActivePersonCompanyLinkDTO::getCompanyLabelWithNumber)
                    .collect(Collectors.joining(", ")) : null,
                dto.getFunctions() != null ? dto
                    .getFunctions()
                    .stream()
                    .filter(ActivePersonFunctionLinkDTO::isMainFunction)
                    .map(ActivePersonFunctionLinkDTO::getLabel)
                    .collect(Collectors.joining(", ")) : null,
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "export all proposals", description = "Exports all proposals.")
    public void exportAllProposals() throws PnetDataClientException
    {
        ProposalDataFind query = restrict(proposalDataClient.find().scroll());
        PnetDataClientResultPage<ProposalItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export updated proposals", format = "[updatedAfter]",
        description = "Exports all proposals updated since yesterday.")
    public void exportAllUpdatedProposals(String updatedAfter) throws PnetDataClientException
    {
        ProposalDataFind query =
            restrict(proposalDataClient.find().updatedAfter(parseUpdatedAfter(updatedAfter)).scroll());
        PnetDataClientResultPage<ProposalItemDTO> result = query.execute(language, 0, pageSize);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find proposals by id", "find proposal by id"}, format = "<ID...>",
        description = "Find proposals by id")
    public void findProposalsById(Integer... ids) throws PnetDataClientException
    {
        ProposalDataFind query = restrict(proposalDataClient.find().id(ids));
        PnetDataClientResultPage<ProposalItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find proposals by state", "find proposal by state"}, format = "<STATE...>",
        description = "Find proposals by state.")
    public void findProposalsByCategory(ProposalState... states) throws PnetDataClientException
    {
        ProposalDataFind query = restrict(proposalDataClient.find().state(states));
        PnetDataClientResultPage<ProposalItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"find proposals by person id", "find proposal by person id"}, format = "<PERSION-ID...>",
        description = "Find proposals by person id.")
    public void findProposalsByOPersonId(Integer... personIds) throws PnetDataClientException
    {
        ProposalDataFind query = restrict(proposalDataClient.find().personId(personIds));
        PnetDataClientResultPage<ProposalItemDTO> result = query.execute(language);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = {"search proposals", "search proposal"}, format = "<QUERY>", description = "Query proposals.")
    public void searchProposals(String... qs) throws PnetDataClientException
    {
        ProposalDataSearch query = restrict(proposalDataClient.search());
        PnetDataClientResultPage<ProposalItemDTO> result = query.execute(language, joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ProposalItemDTO dto)
    {
        table
            .addRow(dto.getProposalId(), dto.getLabel(), dto.getDescription(), dto.getMatchcode(), dto.getType(),
                dto.getState(), dto.isRejected(), dto.isArchived(), dto.getTargetDateType(), dto.getTargetDate(),
                dto
                    .getPersons()
                    .stream()
                    .map(link -> link.getName() + " (" + link.getType() + ")")
                    .collect(Collectors.joining(", ")),
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(description = "Opens the Swagger Documentation.")
    public void swagger() throws IOException
    {
        String url = loginMethod.getUrl();

        if (!url.endsWith("/"))
        {
            url += "/";
        }

        url += "swagger-ui.html";

        cli.info("Opening: %s", url);

        Desktop.getDesktop().browse(URI.create(url));
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "migrate all", format = "<INDEXNAME>",
        description = "Performs a full migration for the specified index.")
    public void migrateFull(String indexName) throws RestException, PnetDataClientException
    {
        context.restCall().variable("indexName", indexName).path("/api/v1/migrator/full/{indexName}").post(Void.class);

        cli.info("Performing a full migration on index: %s.", indexName);
    }

    @CLI.Command(name = "migrate delta", format = "<INDEXNAME>",
        description = "Performs a delta migration for the specified index.")
    public void migrateDelta(String indexName) throws RestException, PnetDataClientException
    {
        context.restCall().variable("indexName", indexName).path("/api/v1/migrator/delta/{indexName}").post(Void.class);

        cli.info("Performing a delta migration on index: %s.", indexName);
    }

    @CLI.Command(name = "migrate state", format = "<INDEXNAME>", description = "Prints the state of the migration.")
    public void migrateState(String indexName) throws RestException, PnetDataClientException
    {
        HashMap<?, ?> state = context
            .restCall()
            .variable("indexName", indexName)
            .path("/api/v1/migrator/states/{indexName}")
            .get(HashMap.class);

        cli.info("Migration state of index: %s", indexName);
        cli.info(PrettyPrint.prettyPrint(state));
    }

    @CLI.Command(name = "migrate explicit", format = "<INDEXNAME> [<IDS>]", description = "Runs an explicit migration.")
    public void migrateExplicit(String indexName, String... ids) throws RestException, PnetDataClientException
    {
        HashMap<?, ?> state = context
            .restCall()
            .variable("indexName", indexName)
            .parameter("id", (Object[]) ids)
            .path("/api/v1/migrator/explicit/{indexName}")
            .post(HashMap.class);

        cli.info("Explicit migration state of index: %s", indexName);
        cli.info(PrettyPrint.prettyPrint(state));
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"restrict tenants", "restrict tenant"}, format = "[<TENANT>...]",
        description = "Places a restriction with tenants for subsequent operations.")
    public void restrictTenants(String... tenants)
    {
        if (tenants != null && tenants.length > 0)
        {
            Arrays.stream(tenants).forEach(restrictedTenants::add);
        }

        cli.info("Requests are restricted to tenants: %s", restrictedTenants);
    }

    @CLI.Command(name = {"clear tenant restrictions", "clear tenant restriction"},
        description = "Removes all restrictions for tenants.")
    public void clearTenantRestrictions()
    {
        cli.info("Removed %s tenant restrictions.", restrictedTenants.size());

        restrictedTenants.clear();
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = {"restrict query fields", "restrict query field"}, format = "[<FIELDS>...]",
        description = "Places a restriction for query fields.")
    public void restrictQueryFields(String... queryFields)
    {
        if (queryFields != null && queryFields.length > 0)
        {
            Arrays.stream(queryFields).forEach(restrictedQueryFields::add);
        }

        cli.info("Queries are restricted to following fields: %s", restrictedQueryFields);
    }

    @CLI.Command(name = {"clear query field restrictions", "clear query field restriction"},
        description = "Removes all restrictions to query fields.")
    public void clearQueryFieldRestrictions()
    {
        cli.info("Removed %s query field restrictions.", restrictedQueryFields.size());

        restrictedQueryFields.clear();
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "visible items", description = "Restrict to visible items.")
    public void restrictVisibleOnly()
    {
        restrictedVisibilities.add(Visibility.VISIBLE);

        cli.info("Restrict to visible items.");
    }

    @CLI.Command(name = "hidden items", description = "Restrict to hidden items.")
    public void restrictHiddenOnly()
    {
        restrictedVisibilities.add(Visibility.HIDDEN);

        cli.info("Restrict to hidden items.");
    }

    @CLI.Command(name = "clear visibility restriction", description = "Removes all restrictions to query fields.")
    public void clearVisibilityRestrictions()
    {
        cli.info("Remove visibility restrictions.");

        restrictedVisibilities.clear();
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "approval needed", description = "Include only items, that need approval.")
    public void approvalNeededOnly()
    {
        restrictApprovalNeeded = true;

        cli.info("Include only items, that need approval.");
    }

    @CLI.Command(name = "no approval needed", description = "Include only items, that do not need approval.")
    public void noApprovalNeededOnly()
    {
        restrictApprovalNeeded = false;

        cli.info("Include only items, that do not need approval.");
    }

    @CLI.Command(name = "ignore approval needed inactive", description = "Ignore the approval needed flag.")
    public void createApprovalNeededRestriction()
    {
        restrictApprovalNeeded = null;

        cli.info("Ignore the approval needed flag.");
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "include inactive", description = "Include inactive items.")
    public void includeInactive()
    {
        includeInactive = true;

        cli.info("Inactive items will be included.");
    }

    @CLI.Command(name = "exclude inactive", description = "Exclude inactive items.")
    public void exincludeInactive()
    {
        includeInactive = false;

        cli.info("Inactive items will be excluded.");
    }

    ////////////////////////////////////////////////////////////////////////////

    protected <T extends Restrict<T>> T restrict(T request)
    {
        request = applyCompanyRestrictions(request);
        request = applyAuthorityRestrictions(request);
        request = applyBaseDataRestrictions(request);
        request = applyBaseTypeRestrictions(request);
        request = applyCustomRestrictions(request);
        request = applyAggregations(request);

        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyCompanyRestrictions(T request)
    {
        if (request instanceof RestrictCompanyId && !restrictedCompanyIds.isEmpty())
        {
            cli.info("A restriction for company ids is in place: %s", restrictedCompanyIds);

            request = ((RestrictCompanyId<T>) request).companyIds(restrictedCompanyIds);
        }

        if (request instanceof RestrictCompany && !restrictedCompanyMatchcodes.isEmpty())
        {
            cli.info("A restriction for company matchcodes is in place: %s", restrictedCompanyMatchcodes);

            request = ((RestrictCompany<T>) request).companies(restrictedCompanyMatchcodes);
        }

        if (request instanceof RestrictCompanyNumber && !restrictedCompanyNumbers.isEmpty())
        {
            cli.info("A restriction for company numbers is in place: %s", restrictedCompanyNumbers);

            request = ((RestrictCompanyNumber<T>) request).companyNumbers(restrictedCompanyNumbers);
        }

        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyAuthorityRestrictions(T request)
    {
        if (request instanceof RestrictFunction && !restrictedFunctionMatchcodes.isEmpty())
        {
            cli.info("A restriction for function matchcodes is in place: %s", restrictedFunctionMatchcodes);

            request = ((RestrictFunction<T>) request).functions(restrictedFunctionMatchcodes);
        }

        if (request instanceof RestrictActivity && !restrictedActivityMatchcodes.isEmpty())
        {
            cli.info("A restriction for activity matchcodes is in place: %s", restrictedActivityMatchcodes);

            request = ((RestrictActivity<T>) request).activities(restrictedActivityMatchcodes);
        }
        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyBaseDataRestrictions(T request)
    {
        if (request instanceof RestrictTenant && !restrictedTenants.isEmpty())
        {
            cli.info("A restriction for tenants is in place: %s", restrictedTenants);

            request = ((RestrictTenant<T>) request).tenants(restrictedTenants);
        }

        if (request instanceof RestrictBrand && !restrictedBrands.isEmpty())
        {
            cli.info("A restriction for brands is in place: %s", restrictedBrands);

            request = ((RestrictBrand<T>) request).brands(restrictedBrands);
        }

        if (request instanceof RestrictQueryField && !restrictedQueryFields.isEmpty())
        {
            cli.info("A restriction for query fields is in place: %s", restrictedQueryFields);

            request = ((RestrictQueryField<T>) request).queryFields(restrictedQueryFields);
        }

        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyBaseTypeRestrictions(T request)
    {
        if (request instanceof RestrictNumberType && !restrictedNumberTypeMatchcodes.isEmpty())
        {
            cli.info("A restriction for number type matchcodes is in place: %s", restrictedNumberTypeMatchcodes);

            request = ((RestrictNumberType<T>) request).numberTypes(restrictedNumberTypeMatchcodes);
        }

        if ((request instanceof RestrictCompanyType || request instanceof RestrictType)
            && !restrictedCompanyTypeMatchcodes.isEmpty())
        {
            cli.info("A restriction for company type matchcodes is in place: %s", restrictedCompanyTypeMatchcodes);

            if (request instanceof RestrictCompanyType)
            {
                request = ((RestrictCompanyType<T>) request).companyTypes(restrictedCompanyTypeMatchcodes);
            }

            if (request instanceof RestrictType)
            {
                request = ((RestrictType<T>) request).types(restrictedCompanyTypeMatchcodes);
            }
        }

        if (request instanceof RestrictContractType && !restrictedContractTypeMatchcodes.isEmpty())
        {
            cli.info("A restriction for contract type matchcodes is in place: %s", restrictedContractTypeMatchcodes);

            request = ((RestrictContractType<T>) request).contractTypes(restrictedContractTypeMatchcodes);
        }

        if (request instanceof RestrictContractState && !restrictedContractStateMatchcodes.isEmpty())
        {
            cli.info("A restriction for contract state matchcodes is in place: %s", restrictedContractStateMatchcodes);

            request = ((RestrictContractState<T>) request).contractStates(restrictedContractStateMatchcodes);
        }

        if (request instanceof RestrictVisibility && !restrictedVisibilities.isEmpty())
        {
            cli.info("A restriction for visibilities is in place: %s", restrictedVisibilities);

            request = ((RestrictVisibility<T>) request).visibilities(restrictedVisibilities);
        }

        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyCustomRestrictions(T request)
    {
        if (request instanceof CompanyMergable && companyMerge != CompanyMerge.NONE)
        {
            cli.info("Merging companies according to: " + companyMerge);

            request = ((CompanyMergable<T>) request).merge(companyMerge);
        }

        if (request instanceof RestrictDatedBackUntil && datedBackUntil != null)
        {
            cli.info("Dating back until: " + datedBackUntil);

            request = ((RestrictDatedBackUntil<T>) request).datedBackUntil(datedBackUntil);
        }

        if (request instanceof RestrictCredentialsAvailable && restrictCredentialsAvailable != null)
        {
            cli.info("Credentials available: " + restrictCredentialsAvailable);

            request = ((RestrictCredentialsAvailable<T>) request).credentialsAvailable(restrictCredentialsAvailable);
        }

        if (request instanceof RestrictApproved && restrictApproved != null)
        {
            cli.info("Approved: " + restrictApproved);

            request = ((RestrictApproved<T>) request).approved(restrictApproved);
        }

        if (request instanceof RestrictRejected && restrictRejected != null)
        {
            cli.info("Rejected: " + restrictRejected);

            request = ((RestrictRejected<T>) request).rejected(restrictRejected);
        }

        if (request instanceof RestrictArchived && restrictArchived != null)
        {
            cli.info("Archived: " + restrictArchived);

            request = ((RestrictArchived<T>) request).archived(restrictArchived);
        }

        if (request instanceof RestrictApprovalNeeded && restrictApprovalNeeded != null)
        {
            cli.info("Approval needed: " + restrictApprovalNeeded);

            request = ((RestrictApprovalNeeded<T>) request).approvalNeeded(restrictApprovalNeeded);
        }

        if (request instanceof IncludeInactive && includeInactive)
        {
            cli.info("Including inactive items.");

            request = ((IncludeInactive<T>) request).includeInactive();
        }

        return request;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T applyAggregations(T request)
    {
        if (!aggs)
        {
            return request;
        }

        if (request instanceof AggregateNumberPerActivity)
        {
            request = (T) ((AggregateNumberPerActivity<?>) request).aggregateNumberPerActivity();
        }

        if (request instanceof AggregateNumberPerBrand)
        {
            request = (T) ((AggregateNumberPerBrand<?>) request).aggregateNumberPerBrand();
        }

        if (request instanceof AggregateNumberPerCategory)
        {
            request = (T) ((AggregateNumberPerCategory<?>) request).aggregateNumberPerCategory();
        }

        if (request instanceof AggregateNumberPerCompany)
        {
            request = (T) ((AggregateNumberPerCompany<?>) request).aggregateNumberPerCompany();
        }

        if (request instanceof AggregateNumberPerContractType)
        {
            request = (T) ((AggregateNumberPerContractType<?>) request).aggregateNumberPerContractType();
        }

        if (request instanceof AggregateNumberPerFunction)
        {
            request = (T) ((AggregateNumberPerFunction<?>) request).aggregateNumberPerFunction();
        }

        if (request instanceof AggregateNumberPerState)
        {
            request = (T) ((AggregateNumberPerState<?>) request).aggregateNumberPerState();
        }

        if (request instanceof AggregateNumberPerTenant)
        {
            request = (T) ((AggregateNumberPerTenant<?>) request).aggregateNumberPerTenant();
        }

        if (request instanceof AggregateNumberPerType)
        {
            request = (T) ((AggregateNumberPerType<?>) request).aggregateNumberPerType();
        }

        return request;
    }

    @CLI.Command(name = {"clear restrictions", "clear restriction"}, description = "Removes all restrictions.")
    public void clearRestrictions()
    {
        cli.info("Removed all restrictions.");

        restrictedTenants.clear();
        restrictedBrands.clear();
        restrictedCompanyIds.clear();
        restrictedCompanyMatchcodes.clear();
        restrictedCompanyNumbers.clear();
        restrictedFunctionMatchcodes.clear();
        restrictedActivityMatchcodes.clear();
        restrictedNumberTypeMatchcodes.clear();
        restrictedCompanyTypeMatchcodes.clear();
        restrictedContractTypeMatchcodes.clear();
        restrictedContractStateMatchcodes.clear();
        restrictedVisibilities.clear();
        restrictedQueryFields.clear();
        restrictArchived = null;
        restrictRejected = null;
        restrictCredentialsAvailable = null;
        restrictApproved = null;
        restrictApprovalNeeded = null;
        datedBackUntil = null;
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(format = "[<URL>]", description = "Prints or overrides the predefined URL.")
    public void url(String url) throws PnetDataClientException
    {
        if (url != null)
        {
            try
            {
                logout();
            }
            catch (Exception e)
            {
                // ignore
            }

            loginMethod.setUrl(url);
        }

        cli.info("url = %s", loginMethod.getUrl());
        cli.info("\nTip: Type \"store\" to set this as default.");
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(format = "[<KEY>]", description = "Stores the URL and username/password to your prefernces.")
    public void store(String key)
    {
        if (key == null)
        {
            key = Prefs.DEFAULT_KEY;
        }

        Prefs.setUrl(key, loginMethod.getUrl());
        Prefs.setUsername(key, loginMethod.getUsername());
        Prefs.setPassword(key, loginMethod.getPassword());
        Prefs.setToken(key, loginMethod.getToken());

        cli.info("URL and (encoded) credentials have been stored locally with key: %s", key);
    }

    @CLI.Command(format = "[<KEY>]", description = "Loads the URL and username/password from your prefernces.")
    public void load(String key) throws PnetDataClientException
    {
        if (key == null)
        {
            key = Prefs.DEFAULT_KEY;
        }

        String url = Prefs.getUrl(key);

        if (url == null)
        {
            cli.error("Could not find prefs with key: %s", key);
            return;
        }

        url(url);

        String username = Prefs.getUsername(key);
        String password = Prefs.getPassword(key);
        String token = Prefs.getToken(key);

        if (token != null)
        {
            loginMethod.setToken(token);
        }
        else
        {
            loginMethod.setUsernamePassword(username, password);
        }
    }

    @CLI.Command(format = "[<KEY>]", description = "Remove the URL and username/password from your prefernces.")
    public void remove(String key) throws PnetDataClientException
    {
        if (key == null)
        {
            key = Prefs.DEFAULT_KEY;
        }

        Prefs.remove(key);

        cli.info("URL, username and password with key \"%s\" have been removed.", key);
    }

    @CLI.Command(description = "Lists all locally stored keys")
    public void list()
    {
        cli.info("Locally stored keys:\n");

        Prefs
            .keys()
            .stream()
            .filter(key -> key.endsWith(".url"))
            .map(key -> key.substring(0, key.length() - 4))
            .forEach(key -> cli
                .info("%s: %s (%s)", key, Prefs.getUrl(key),
                    Prefs.getUsername(key) != null ? Prefs.getUsername(key) : "authentication token"));
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(format = "[<USERNAME>] [<PASSWORD>]", description = "Prints or overrides the username and password.")
    public void user(String username, String password) throws IOException, PnetDataClientException
    {
        if (username != null)
        {
            try
            {
                logout();
            }
            catch (Exception e)
            {
                // ignore
            }

            if (password == null)
            {
                Arguments arguments = cli.consume("Password: ");

                password = arguments.consume(String.class).orElse(null);
            }

            if (password != null)
            {
                loginMethod.setUsernamePassword(username, password);
            }
            else
            {
                cli.warn("Aborted.");
            }
        }

        cli.info("username = %s", loginMethod.getUsername());
    }

    @CLI.Command(format = "<TOKEN>", description = "Sets the authentication token.")
    public void token(String token) throws IOException, PnetDataClientException
    {
        if (token == null)
        {
            Arguments arguments = cli.consume("token: ");

            token = arguments.consume(String.class).orElse(null);
        }

        if (token != null)
        {
            try
            {
                logout();
            }
            catch (Exception e)
            {
                // ignore
            }

            loginMethod.setToken(token);

            cli.info("Token set.");
        }
        else
        {
            cli.warn("Aborted.");
        }
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get", format = "URI", description = "Performs an GET request.")
    public void get(String uri) throws RestException, PnetDataClientException
    {
        if (uri == null)
        {
            cli.error("URI is missing.");
        }
        else if (!uri.startsWith("/api"))
        {
            cli.error("URI must start with \"/api\".");
        }
        else
        {
            HashMap<?, ?> result = context.restCall().encodedPathSegment(uri).get(HashMap.class);

            cli.info(PrettyPrint.prettyPrint(result));
        }
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(description = "Print compact results.")
    public void compact()
    {
        cli.info("Printing compact results.");

        compact = true;
    }

    @CLI.Command(description = "Print detailed results.")
    public void detailed()
    {
        cli.info("Printing detailed results.");

        compact = false;
    }

    @CLI.Command(format = "LANGUAGE-TAG", description = "Set the language.")
    public void language(String languageTag)
    {
        language = languageTag == null ? Locale.getDefault() : Locale.forLanguageTag(languageTag);

        cli.info("Language set to: %s", language);
    }

    @CLI.Command(description = "Prints the next page of the last result.")
    public void next() throws PnetDataClientException
    {
        if (currentResult == null)
        {
            cli.error("No result available.");
            return;
        }

        if (!currentResult.hasNextPage())
        {
            cli.error("There is no next page.");
            return;
        }

        currentResult.nextPage().print(cli, compact);
    }

    @CLI.Command(description = "Prints the previous page of the last result.")
    public void prev() throws PnetDataClientException
    {
        if (currentResult == null)
        {
            cli.error("No result available.");
            return;
        }

        int index = currentResult.getPageIndex();

        if (index <= 0)
        {
            cli.error("There is no previous page.");
            return;
        }

        currentResult.page(index - 1).print(cli, compact);
    }

    @CLI.Command(format = "[<NUMBER>]", description = "Prints the page with the specified number.")
    public void page(Integer number) throws PnetDataClientException
    {
        if (currentResult == null)
        {
            cli.error("No result available.");
            return;
        }

        if (number == null)
        {
            currentResult.print(cli, compact);
        }
        else
        {
            currentResult.page(number - 1).print(cli, compact);
        }
    }

    @CLI.Command(name = "page size", format = "<SIZE>", description = "Sets the number of items per page.")
    public void pageSize(Integer pageSize)
    {
        if (pageSize == null || pageSize < 1)
        {
            cli.error("Invalid size.");
            return;
        }

        this.pageSize = pageSize;
    }

    @CLI.Command(name = "no aggs", description = "Disables aggregations.")
    public void noAggs() throws PnetDataClientException
    {
        cli.info("Aggs disabled.");
        aggs = false;
    }

    @CLI.Command(description = "Enables aggregations or prints them, if available.")
    public void aggs() throws PnetDataClientException
    {
        if (!aggs)
        {
            cli.info("Aggs enabled.");
            aggs = true;
            return;
        }

        if (currentResult == null)
        {
            cli.error("No result available.");
            return;
        }

        currentResult.printAggregations(cli);
    }

    ////////////////////////////////////////////////////////////////////////////

    protected <T> void printResults(PnetDataClientResultPage<T> page, BiConsumer<Table, T> populateTableFn)
        throws PnetDataClientException
    {
        cli.info("Found %d results.", page.getTotalNumberOfItems());

        printPage(page, populateTableFn);
    }

    protected <T> void printResults(List<T> list, BiConsumer<Table, T> populateTableFn) throws PnetDataClientException
    {
        cli.info("Found %d results.", list.size());

        printList(list, populateTableFn);
    }

    protected <T> void printAllResults(PnetDataClientResultPage<T> page, BiConsumer<Table, T> populateTableFn)
        throws PnetDataClientException
    {
        long millis = System.currentTimeMillis();
        int count = 0;

        while (page != null && page.size() > 0)
        {
            Table table = new Table();

            page.stream().forEach(item -> populateTableFn.accept(table, item));

            cli.info(table.toString());

            count += page.size();
            page = page.nextPage();
        }

        cli.info("\nFound %d results in %,.3f seconds", count, (System.currentTimeMillis() - millis) / 1000d);
    }

    protected <T> void printPage(PnetDataClientResultPage<T> page, BiConsumer<Table, T> populateTableFn)
    {
        currentResult = new CurrentResult<>(page, populateTableFn);
        currentResult.print(cli, compact);
    }

    protected <T> void printList(List<T> list, BiConsumer<Table, T> populateTableFn)
    {
        Table table = new Table();

        list.stream().forEach(item -> populateTableFn.accept(table, item));

        cli.info(table.toString());
    }

    public void consume()
    {
        cli.info("Partner.Net REST Client Sample application");
        cli.info("==========================================");
        cli.info("");
        cli.info("Type \"? [q]\" for help.");

        while (true)
        {
            try
            {
                Thread.sleep(5);
            }
            catch (InterruptedException e)
            {
                Thread.interrupted();
                break;
            }

            try
            {
                if (!cli.consumeCommand(null))
                {
                    break;
                }
            }
            catch (Exception e)
            {
                cli.error("Command failed", e);
            }
        }

        cli.info("Aborted.");
    }

    protected static String toCsv(Object... args)
    {
        StringBuilder builder = new StringBuilder();
        boolean first = true;

        for (Object arg : args)
        {
            if (!first)
            {
                builder.append(" | ");
            }
            else
            {
                first = false;
            }

            if (arg != null)
            {
                builder.append(arg);
            }
        }

        return builder.toString();
    }

    protected static String joinQuery(String... qs)
    {
        if (qs == null || qs.length == 0)
        {
            return null;
        }

        return Arrays.stream(qs).collect(Collectors.joining(" "));
    }

    protected static void showImage(String title, Image image)
    {
        Canvas canvas = new Canvas()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void paint(Graphics g)
            {
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

    protected static LocalDateTime parseUpdatedAfter(String updatedAfter)
    {
        if (updatedAfter == null)
        {
            return LocalDateTime.now().minusDays(1);
        }

        try
        {
            return LocalDateTime.now().minus(Duration.parse("PT" + updatedAfter));
        }
        catch (DateTimeParseException e)
        {
            try
            {
                LocalDateTime timestamp =
                    LocalDate.now().atTime(LocalTime.parse(updatedAfter, DateTimeFormatter.ofPattern("HH:mm:ss")));

                while (timestamp.isAfter(LocalDateTime.now()))
                {
                    timestamp = timestamp.minusDays(1);
                }

                return timestamp;
            }
            catch (DateTimeParseException e2)
            {
                try
                {
                    LocalDateTime timestamp =
                        LocalDate.parse(updatedAfter, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();

                    while (timestamp.isAfter(LocalDateTime.now()))
                    {
                        timestamp = timestamp.minusDays(1);
                    }

                    return timestamp;
                }
                catch (DateTimeParseException e3)
                {
                    throw new IllegalArgumentException("Failed to parse "
                        + updatedAfter
                        + ". Try using a time like 13:22:10, a date like 2020-03-21 or a duration like 10M.");
                }
            }
        }
    }
}
