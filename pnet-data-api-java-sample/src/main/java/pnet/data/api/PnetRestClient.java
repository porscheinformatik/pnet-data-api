package pnet.data.api;

import static pnet.data.api.util.PrettyPrint.*;

import java.awt.Canvas;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
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

import at.porscheinformatik.happyrest.RestCall;
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
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;
import pnet.data.api.client.context.PnetDataApiTokenKey;
import pnet.data.api.client.context.PnetDataApiTokenRepository;
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
import pnet.data.api.companytype.CompanyTypeDataFind;
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
import pnet.data.api.person.PersonDataClient;
import pnet.data.api.person.PersonDataDTO;
import pnet.data.api.person.PersonDataFind;
import pnet.data.api.person.PersonDataGet;
import pnet.data.api.person.PersonDataSearch;
import pnet.data.api.person.PersonItemDTO;
import pnet.data.api.todo.TodoCategory;
import pnet.data.api.todo.TodoGroupDataClient;
import pnet.data.api.todo.TodoGroupDataFind;
import pnet.data.api.todo.TodoGroupDataSearch;
import pnet.data.api.todo.TodoGroupEntryLinkDTO;
import pnet.data.api.todo.TodoGroupItemDTO;
import pnet.data.api.todo.TodoGroupPersonLinkDTO;
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
import pnet.data.api.util.Prefs;
import pnet.data.api.util.PrettyPrint;
import pnet.data.api.util.Resource;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompany;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictFunction;
import pnet.data.api.util.RestrictNumberType;
import pnet.data.api.util.RestrictQueryField;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.Table;

/**
 * The client with all commands.
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

    private final MutablePnetDataClientPrefs prefs;

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

    private final TodoGroupDataClient todoGroupDataClient;

    private final PnetDataApiTokenRepository repository;

    private final List<String> restrictedTenants = new ArrayList<>();
    private final List<String> restrictedBrands = new ArrayList<>();
    private final List<Integer> restrictedCompanyIds = new ArrayList<>();
    private final List<String> restrictedCompanyMatchcodes = new ArrayList<>();
    private final List<String> restrictedCompanyNumbers = new ArrayList<>();
    private final List<String> restrictedFunctionMatchcodes = new ArrayList<>();
    private final List<String> restrictedActivityMatchcodes = new ArrayList<>();
    private final List<String> restrictedNumberTypeMatchcodes = new ArrayList<>();
    private final List<String> restrictedQueryFields = new ArrayList<>();

    private CompanyMerge companyMerge = CompanyMerge.NONE;
    private LocalDateTime datedBackUntil = null;
    private boolean aggs = true;
    private boolean compact = true;
    private CurrentResult<?> currentResult = null;

    public PnetRestClient(MutablePnetDataClientPrefs prefs, AboutDataClient aboutDataClient,
        ActivityDataClient activityDataClient, AdvisorTypeDataClient advisorTypeDataClient,
        ApplicationDataClient applicationDataClient, BrandDataClient brandDataClient,
        CompanyDataClient companyDataClient, CompanyGroupDataClient companyGroupDataClient,
        CompanyGroupTypeDataClient companyGroupTypeDataClient, CompanyNumberTypeDataClient companyNumberTypeDataClient,
        CompanyTypeDataClient companyTypeDataClient, ContractStateDataClient contractStateDataClient,
        ContractTypeDataClient contractTypeDataClient, ExternalBrandDataClient externalBrandDataClient,
        FunctionDataClient functionDataClient, LegalFormDataClient legalFormDataClient,
        NumberTypeDataClient numberTypeDataClient, PersonDataClient personDataClient,
        TodoGroupDataClient todoGroupDataClient, PnetDataApiTokenRepository repository)
    {
        super();
        this.prefs = prefs;
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
        this.todoGroupDataClient = todoGroupDataClient;
        this.repository = repository;

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
    public void token() throws PnetDataClientException
    {
        PnetDataApiTokenKey key = key();

        cli.info("token = %s", repository.restCall(key).getHeader("Authorization"));
    }

    @CLI.Command(description = "Invalidates the stored JSON Web Token.")
    public void logout() throws PnetDataClientException
    {
        PnetDataApiTokenKey key = key();

        repository.invalidate(key);

        cli.info("Logged out.");
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get activity by mc", format = "<MC...>",
        description = "Returns the activities with the specified matchcodes.")
    public void getActivities(String... matchcodes) throws PnetDataClientException
    {
        ActivityDataGet query = restrict(activityDataClient.get());
        PnetDataClientResultPage<ActivityDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all activities", description = "Exports all activities.")
    public void exportAllActivities() throws PnetDataClientException
    {
        ActivityDataFind query = restrict(activityDataClient.find().scroll());
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated activities", format = "[<DAYS>:1]",
        description = "Exports all activities updated since yesterday.")
    public void exportAllUpdatedActivities(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        ActivityDataFind query = restrict(activityDataClient.find().updatedAfter(updatedAfter).scroll());
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find activities by mc", format = "<MC...>", description = "Find activities by matchcodes.")
    public void findActivities(String... matchcodes) throws PnetDataClientException
    {
        ActivityDataFind query = restrict(activityDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search activities", format = "<QUERY>", description = "Query activities.")
    public void searchActivities(String... qs) throws PnetDataClientException
    {
        ActivityDataSearch query = restrict(activityDataClient.search());
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ActivityItemDTO dto)
    {
        table
            .addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getTenants(), dto.getBrands(),
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get advisor type by mc", format = "<MC...>",
        description = "Returns the advisor types with the specified matchcodes.")
    public void getAdvisorTypes(String... matchcodes) throws PnetDataClientException
    {
        AdvisorTypeDataGet query = restrict(advisorTypeDataClient.get());
        PnetDataClientResultPage<AdvisorTypeDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all advisor types", description = "Exports all advisor types.")
    public void exportAllAdvisorTypes() throws PnetDataClientException
    {
        AdvisorTypeDataFind query = restrict(advisorTypeDataClient.find());
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated advisor types", format = "[<DAYS>:1]",
        description = "Exports all advisor types updated since yesterday.")
    public void exportAllUpdatedAdvisorTypes(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        AdvisorTypeDataFind query = restrict(advisorTypeDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find advisor types by mc", format = "<MC...>",
        description = "Find advisor types by matchcodes.")
    public void findAdvisorTypes(String... matchcodes) throws PnetDataClientException
    {
        AdvisorTypeDataFind query = restrict(advisorTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search advisor types", format = "<QUERY>", description = "Query advisor types.")
    public void searchAdvisorTypes(String... qs) throws PnetDataClientException
    {
        AdvisorTypeDataSearch query = restrict(advisorTypeDataClient.search());
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, AdvisorTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get application by mc", format = "<MC...>",
        description = "Returns the applications with the specified matchcodes.")
    public void getApplications(String... matchcodes) throws PnetDataClientException
    {
        ApplicationDataGet query = restrict(applicationDataClient.get());
        PnetDataClientResultPage<ApplicationDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all applications", description = "Exports all applications.")
    public void exportAllApplications() throws PnetDataClientException
    {
        ApplicationDataFind query = restrict(applicationDataClient.find().scroll());
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated applications", format = "[<DAYS>:1]",
        description = "Exports all applications updated since yesterday.")
    public void exportAllUpdatedApplications(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        ApplicationDataFind query = restrict(applicationDataClient.find().updatedAfter(updatedAfter).scroll());
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find applications by mc", format = "<MC...>", description = "Find applications by matchcodes.")
    public void findApplications(String... matchcodes) throws PnetDataClientException
    {
        ApplicationDataFind query = restrict(applicationDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search applications", format = "<QUERY>", description = "Query applications.")
    public void searchApplications(String... qs) throws PnetDataClientException
    {
        ApplicationDataSearch query = restrict(applicationDataClient.search());
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ApplicationItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get brand by mc", format = "<MC...>",
        description = "Returns the brands with the specified matchcodes.")
    public void getBrands(String... matchcodes) throws PnetDataClientException
    {
        BrandDataGet query = restrict(brandDataClient.get());
        PnetDataClientResultPage<BrandDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all brands", description = "Export all brands updated since yesterday.")
    public void exportAllBrands() throws PnetDataClientException
    {
        BrandDataFind query = restrict(brandDataClient.find());
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated brands", format = "[<DAYS>:1]",
        description = "Export all brands updated since yesterday.")
    public void exportAllUpdatedBrands(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        BrandDataFind query = restrict(brandDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find brands by mc", format = "<MC...>", description = "Find brands by matchcodes.")
    public void findBrands(String... matchcodes) throws PnetDataClientException
    {
        BrandDataFind query = restrict(brandDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search brands", format = "<QUERY>", description = "Query brands.")
    public void searchBrands(String... qs) throws PnetDataClientException
    {
        BrandDataSearch query = restrict(brandDataClient.search());
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "restrict brands", format = "[<BRAND>...]",
        description = "Places a restriction with brands for subsequent operations.")
    public void restrictBrands(String... brands)
    {
        if (brands != null && brands.length > 0)
        {
            Arrays.stream(brands).forEach(restrictedBrands::add);
        }

        cli.info("Requests are restricted to brands: %s", restrictedBrands);
    }

    @CLI.Command(name = "clear brand restrictions", description = "Removes all restrictions for brands.")
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

    @CLI.Command(name = "get company by id", format = "<COMPANY-ID...>",
        description = "Returns the companies with the specified ids.")
    public void getCompaniesByIds(Integer... ids) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByIds(Arrays.asList(ids), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get company by mc", format = "<COMPANY-MC...>",
        description = "Returns the companies with the specified matchcode.")
    public void getCompaniesByMatchcodes(String... matchcodes) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get company by vat", format = "<COMPANY-VATIDNUMBER...>",
        description = "Returns the companies with the specified vat id numbers.")
    public void getCompaniesByVatIdNumbers(String... vatIdNumbers) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByVatIdNumbers(Arrays.asList(vatIdNumbers), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get company by number", format = "<COMPANY-NUMBER...>",
        description = "Returns the companies with the specified company numbers.")
    public void getCompaniesByCompanyNumbers(String... companyNumbers) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result =
            query.allByCompanyNumbers(Arrays.asList(companyNumbers), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get company by iban", format = "<COMPANY-IBAN...>",
        description = "Returns the company with the specified ibans.")
    public void getCompaniesByIbans(String... ibans) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByIbans(Arrays.asList(ibans), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get company by email", format = "<COMPANY-EMAIL...>",
        description = "Returns the company with the specified emails.")
    public void getCompaniesByEmails(String... emails) throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByEmails(Arrays.asList(emails), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get company by dvr", format = "<COMPANY-DPRN...>",
        description = "Returns the companies with the specified data processing register numbers.")
    public void getCompaniesByDataProcessingRegisterNumbers(String... dataProcessingRegisterNumbers)
        throws PnetDataClientException
    {
        CompanyDataGet query = restrict(companyDataClient.get());
        PnetDataClientResultPage<CompanyDataDTO> result =
            query.allByDataProcessingRegisterNumbers(Arrays.asList(dataProcessingRegisterNumbers), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all companies", description = "Exports all companies.")
    public void exportAllCompanies() throws PnetDataClientException
    {
        CompanyDataFind query = restrict(companyDataClient.find().scroll());
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated companies", format = "[<DAYS>:1]",
        description = "Exports all companies updated since yesterday.")
    public void exportAllUpdatedCompanies(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        CompanyDataFind query = restrict(companyDataClient.find().updatedAfter(updatedAfter).scroll());
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find companies by id", format = "<ID...>", description = "Find companies by id.")
    public void findCompaniesByIds(Integer... ids) throws PnetDataClientException
    {
        CompanyDataFind query = restrict(companyDataClient.find().id(ids));
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "find companies by mc", format = "<MC...>", description = "Find companies by matchcode.")
    public void findCompaniesByMatchcodes(String... mcs) throws PnetDataClientException
    {
        CompanyDataFind query = restrict(companyDataClient.find().matchcode(mcs));
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "find companies by number", format = "<NUMBER...>",
        description = "Find companies by company number.")
    public void findCompaniesByNumbers(String... numbers) throws PnetDataClientException
    {
        CompanyDataFind query = restrict(companyDataClient.find().companyNumber(numbers));
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search companies", format = "<QUERY>", description = "Query companies.")
    public void searchCompanies(String... qs) throws PnetDataClientException
    {
        CompanyDataSearch query = restrict(companyDataClient.search());
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "restrict company ids", format = "<ID...>",
        description = "Places a restriction with company numbers for subsequent operations.")
    public void restrictCompaniesById(Integer... ids)
    {
        if (ids != null && ids.length > 0)
        {
            Arrays.stream(ids).forEach(restrictedCompanyIds::add);
        }

        cli.info("Requests are restricted to company ids: %s", restrictedCompanyIds);
    }

    @CLI.Command(name = "restrict company mcs", format = "<MC...>",
        description = "Places a restriction with company matchcodes for subsequent operations.")
    public void restrictCompaniesByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedCompanyMatchcodes::add);
        }

        cli.info("Requests are restricted to company matchcodes: %s", restrictedCompanyMatchcodes);
    }

    @CLI.Command(name = "restrict company numbers", format = "<NUMBER...>",
        description = "Places a restriction with company numbers for subsequent operations.")
    public void restrictCompaniesByNumber(String... numbers)
    {
        if (numbers != null && numbers.length > 0)
        {
            Arrays.stream(numbers).forEach(restrictedCompanyNumbers::add);
        }

        cli.info("Requests are restricted to company numbers: %s", restrictedCompanyNumbers);
    }

    @CLI.Command(name = "restrict functions", format = "<MC...>",
        description = "Places a restriction of functions for subsequent operations.")
    public void restrictFunctionsByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedFunctionMatchcodes::add);
        }

        cli.info("Requests are restricted to function matchcodes: %s", restrictedFunctionMatchcodes);
    }

    @CLI.Command(name = "restrict activities", format = "<MC...>",
        description = "Places a restriction of activities for subsequent operations.")
    public void restrictActivitiesByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedActivityMatchcodes::add);
        }

        cli.info("Requests are restricted to activity matchcodes: %s", restrictedActivityMatchcodes);
    }

    @CLI.Command(name = "restrict number types", format = "<MC...>",
        description = "Places a restriction of number types for subsequent operations.")
    public void restrictNumberTypesByMatchcode(String... matchcodes)
    {
        if (matchcodes != null && matchcodes.length > 0)
        {
            Arrays.stream(matchcodes).forEach(restrictedNumberTypeMatchcodes::add);
        }

        cli.info("Requests are restricted to number type matchcodes: %s", restrictedNumberTypeMatchcodes);
    }

    @CLI.Command(name = "clear company restrictions", description = "Removes all restrictions for companies.")
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

    @CLI.Command(name = "dated back", format = "[<DAYS>]",
        description = "Sets the dated back parameter for the specified days")
    public void datedBackUnitl(Integer days)
    {
        if (days == null)
        {
            datedBackUntil = null;

            cli.info("Only up-to-date items will be searched and shown.");
        }
        else
        {
            datedBackUntil = LocalDateTime.now().minusDays(days.longValue());

            cli.info("Items will be searched and shown, that are not older than %s.", datedBackUntil);
        }
    }

    protected void populateTable(Table table, CompanyItemDTO dto)
    {
        table
            .addRow(dto.getCompanyId(), dto.getMatchcode(), dto.getLabel(), dto.getAdministrativeTenant(),
                dto.getBrands(), dto.getTenants(), dto.getTypes(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get company group by leading company id", format = "<COMPANY-ID...>",
        description = "Returns the company groups with the specified ids.")
    public void getCompanyGroupByLeadingCompanyIds(Integer... ids) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByLeadingCompanyIds(Arrays.asList(ids), 0, 10);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "get company group by leading company number", format = "<COMPANY-NUMBER...>",
        description = "Returns the company groups with the specified numbers.")
    public void getCompanyGroupByLeadingCompanyNumbers(String... numbers) throws PnetDataClientException
    {
        CompanyGroupDataGet request = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            request.allByLeadingCompanyNumbers(Arrays.asList(numbers), 0, 10);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "get company group by leading company mc", format = "<COMPANY-MC...>",
        description = "Returns the company groups with the specified matchcodes.")
    public void getCompanyGroupByLeadingCompanyMatchcodes(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByLeadingCompanies(Arrays.asList(matchcodes), 0, 10);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "get company group by company id", format = "<COMPANY-ID...>",
        description = "Returns the company groups with the specified ids.")
    public void getCompanyGroupByCompanyIds(Integer... ids) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanyIds(Arrays.asList(ids), 0, 10);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "get company group by company number", format = "<COMPANY-NUMBER...>",
        description = "Returns the company groups with the specified numbers.")
    public void getCompanyGroupByCompanyNumbers(String... numbers) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanyNumbers(Arrays.asList(numbers), 0, 10);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "get company group by company mc", format = "<COMPANY-MC...>",
        description = "Returns the company groups with the specified matchcodes.")
    public void getCompanyGroupByCompanyMatchcodes(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanies(Arrays.asList(matchcodes), 0, 10);

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
            .execute(Locale.getDefault(), 0, 100)
            .stream()
            .map(CompanyGroupTypeItemDTO::getMatchcode)
            .collect(Collectors.toList());

        CompanyGroupDataGet query = restrict(companyGroupDataClient.get().scroll());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByCompanyGroupTypes(companyGroupTypeMatchcodes, 0, 25);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find company groups by leader", format = "<COMPANY-ID...>",
        description = "Find all company groups with the specified leader.")
    public void findCompanyGroupsByLeader(Integer... ids) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByLeadingCompanyIds(Arrays.asList(ids), 0, 10);

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "find company groups by member", format = "<COMPANY-ID...>",
        description = "Find all company groups with the specified member.")
    public void findCompanyGroupsByMember(Integer... ids) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanyIds(Arrays.asList(ids), 0, 10);

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, CompanyGroupDataDTO dto)
    {
        table
            .addRow(dto.getLeadingCompanyId(), dto.getLeadingCompanyMatchcode(), dto.getLeadingCompanyNumber(),
                dto.getMembers());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get company group type by mc", format = "<MC...>",
        description = "Returns the company group types with the specified matchcodes.")
    public void getCompanyGroupTypes(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupTypeDataGet query = restrict(companyGroupTypeDataClient.get());
        PnetDataClientResultPage<CompanyGroupTypeDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get company group by type", format = "<MC...>",
        description = "Returns the company groups with the specified matchcodes.")
    public void getCompanyGroupTypesByType(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupDataGet query = restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result =
            query.allByCompanyGroupTypes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all company group types", description = "Exports all company group types.")
    public void exportAllCompanyGroupTypes() throws PnetDataClientException
    {
        CompanyGroupTypeDataFind query = restrict(companyGroupTypeDataClient.find());
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated company group types", format = "[<DAYS>:1]",
        description = "Exports all company group types updated since yesterday.")
    public void exportAllUpdatedCompanyGroupTypes(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        CompanyGroupTypeDataFind query = restrict(companyGroupTypeDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find company group types by mc", format = "<MC...>",
        description = "Find comany group types by matchcodes.")
    public void findCompanyGroupTypes(String... matchcodes) throws PnetDataClientException
    {
        CompanyGroupTypeDataFind query = restrict(companyGroupTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search company group types", format = "<QUERY>", description = "Query company group types.")
    public void searchCompanyGroupTypes(String... qs) throws PnetDataClientException
    {
        CompanyGroupTypeDataSearch query = restrict(companyGroupTypeDataClient.search());
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, CompanyGroupTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get company number type by mc", format = "<MC...>",
        description = "Returns the company number types with the specified matchcodes.")
    public void getCompanyNumberTypes(String... matchcodes) throws PnetDataClientException
    {
        CompanyNumberTypeDataGet query = restrict(companyNumberTypeDataClient.get());
        PnetDataClientResultPage<CompanyNumberTypeDataDTO> result =
            query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all company number types", description = "Exports all company number types.")
    public void exportAllCompanyNumberTypes() throws PnetDataClientException
    {
        CompanyNumberTypeDataFind query = restrict(companyNumberTypeDataClient.find());
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated company number types", format = "[<DAYS>:1]",
        description = "Exports all updated company number types.")
    public void exportAllUpdatedCompanyNumberTypes(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        CompanyNumberTypeDataFind query = restrict(companyNumberTypeDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find company number types by mc", format = "<MC...>",
        description = "Find comany number types by matchcodes.")
    public void findCompanyNumberTypes(String... matchcodes) throws PnetDataClientException
    {
        CompanyNumberTypeDataFind query = restrict(companyNumberTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search company number types", format = "<QUERY>", description = "Query company number types.")
    public void searchCompanyNumberTypes(String... qs) throws PnetDataClientException
    {
        CompanyNumberTypeDataSearch query = restrict(companyNumberTypeDataClient.search());
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, CompanyNumberTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "export all company types", description = "Exports all company types.")
    public void exportAllCompanyTypes() throws PnetDataClientException
    {
        CompanyTypeDataFind query = restrict(companyTypeDataClient.find());
        PnetDataClientResultPage<CompanyTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated company types", format = "[<DAYS>:1]",
        description = "Exports all company types updated since yesterday.")
    public void exportAllUpdatedCompanyTypes(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        CompanyTypeDataFind query = restrict(companyTypeDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<CompanyTypeItemDTO> results = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(results, this::populateTable);
    }

    @CLI.Command(name = "search company types", format = "<QUERY>", description = "Query company types.")
    public void searchCompanyTypes(String... qs) throws PnetDataClientException
    {
        CompanyTypeDataSearch query = restrict(companyTypeDataClient.search());
        PnetDataClientResultPage<CompanyTypeItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, CompanyTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getTenants(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get contract state by mc", format = "<MC...>",
        description = "Returns the contract states with the specified matchcodes.")
    public void getContractStates(String... matchcodes) throws PnetDataClientException
    {
        ContractStateDataGet query = restrict(contractStateDataClient.get());
        PnetDataClientResultPage<ContractStateDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all contract states", description = "Exports all contract states.")
    public void exportAllContractStates() throws PnetDataClientException
    {
        ContractStateDataFind query = restrict(contractStateDataClient.find());
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated contract states", format = "[<DAYS>:1]",
        description = "Exports all contract states updated since yesterday.")
    public void exportAllUpdatedContractStates(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);

        ContractStateDataFind query = restrict(contractStateDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find contract states by mc", format = "<MC...>",
        description = "Find contract states by matchcodes.")
    public void findContractStates(String... matchcodes) throws PnetDataClientException
    {
        ContractStateDataFind query = restrict(contractStateDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search contract states", format = "<QUERY>", description = "Query contract states types.")
    public void searchContractStates(String... qs) throws PnetDataClientException
    {
        ContractStateDataSearch query = restrict(contractStateDataClient.search());
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ContractStateItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get contract type by mc", format = "<MC...>",
        description = "Returns the contract types with the specified matchcodes.")
    public void getContractTypes(String... matchcodes) throws PnetDataClientException
    {
        ContractTypeDataGet query = restrict(contractTypeDataClient.get());
        PnetDataClientResultPage<ContractTypeDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all contract types", description = "Exports all contract types.")
    public void exportAllContractTypes() throws PnetDataClientException
    {
        ContractTypeDataFind query = restrict(contractTypeDataClient.find());
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated contract types", format = "[<DAYS>:1]",
        description = "Exports all contract types updated since yesterday.")
    public void exportAllUpdatedContractTypes(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);

        ContractTypeDataFind query = restrict(contractTypeDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find contract types by mc", format = "<MC...>",
        description = "Find contract types by matchcodes.")
    public void findContractTypes(String... matchcodes) throws PnetDataClientException
    {
        ContractTypeDataFind query = restrict(contractTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search contract types", format = "<QUERY>", description = "Query contract types.")
    public void searchContractTypes(String... qs) throws PnetDataClientException
    {
        ContractTypeDataSearch query = restrict(contractTypeDataClient.search());
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ContractTypeItemDTO dto)
    {
        table
            .addRow(dto.getMatchcode(), dto.getLabel(), dto.getType(), dto.getTenants(), dto.getBrands(),
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get external brand by mc", format = "<MC...>",
        description = "Returns the external brands with the specified matchcodes.")
    public void getExternalBrands(String... matchcodes) throws PnetDataClientException
    {
        ExternalBrandDataGet query = restrict(externalBrandDataClient.get());
        PnetDataClientResultPage<ExternalBrandDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all external brands", description = "Exports all external brands.")
    public void exportAllExternalBrands() throws PnetDataClientException
    {
        ExternalBrandDataFind query = restrict(externalBrandDataClient.find());
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated external brands", format = "[<DAYS>:1]",
        description = "Exports all external brands updated since yesterday.")
    public void exportAllUpdatedExternalBrands(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        ExternalBrandDataFind query = restrict(externalBrandDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find external brands by mc", format = "<MC...>",
        description = "Find external brands by matchcodes.")
    public void findExternalBrands(String... matchcodes) throws PnetDataClientException
    {
        ExternalBrandDataFind query = restrict(externalBrandDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search external brands", format = "<QUERY>", description = "Query external brands.")
    public void searchExternalBrands(String... qs) throws PnetDataClientException
    {
        ExternalBrandDataSearch query = restrict(externalBrandDataClient.search());
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, ExternalBrandItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get function by mc", format = "<MC...>",
        description = "Returns the functions with the specified matchcodes.")
    public void getFunctions(String... matchcodes) throws PnetDataClientException
    {
        FunctionDataGet query = restrict(functionDataClient.get());
        PnetDataClientResultPage<FunctionDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all functions", description = "Exports all functions.")
    public void exportAllFunctions() throws PnetDataClientException
    {
        FunctionDataFind query = restrict(functionDataClient.find().scroll());
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated functions", format = "[<DAYS>:1]",
        description = "Exports all functions updated since yesterday.")
    public void exportAllUpdatedFunctions(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        FunctionDataFind query = restrict(functionDataClient.find().updatedAfter(updatedAfter).scroll());
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find functions by mc", format = "<MC...>", description = "Find functions by matchcodes.")
    public void findFunctions(String... matchcodes) throws PnetDataClientException
    {
        FunctionDataFind query = restrict(functionDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search functions", format = "<QUERY>", description = "Query functions.")
    public void searchFunctions(String... qs) throws PnetDataClientException
    {
        FunctionDataSearch query = restrict(functionDataClient.search());
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, FunctionItemDTO dto)
    {
        table
            .addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getTenants(), dto.getBrands(),
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get legal form by mc", format = "<MC...>",
        description = "Returns the legal forms with the specified matchcodes.")
    public void getLegalForms(String... matchcodes) throws PnetDataClientException
    {
        LegalFormDataGet query = restrict(legalFormDataClient.get());
        PnetDataClientResultPage<LegalFormDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all legal forms", description = "Exports all legal forms.")
    public void exportAllLegalForms() throws PnetDataClientException
    {
        LegalFormDataFind query = restrict(legalFormDataClient.find());
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated legal forms", format = "[<DAYS>:1]",
        description = "Exports all legal forms updated since yesterday.")
    public void exportAllUpdatedLegalForms(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        LegalFormDataFind query = restrict(legalFormDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find legal forms by mc", format = "<MC...>",
        description = "Find comany group types by matchcodes.")
    public void findLegalForms(String... matchcodes) throws PnetDataClientException
    {
        LegalFormDataFind query = restrict(legalFormDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search legal forms", format = "<QUERY>", description = "Query legal forms.")
    public void searchLegalForms(String... qs) throws PnetDataClientException
    {
        LegalFormDataSearch query = restrict(legalFormDataClient.search());
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, LegalFormItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get number type by mc", format = "<MC...>",
        description = "Returns the number types with the specified matchcodes.")
    public void getNumberTypes(String... matchcodes) throws PnetDataClientException
    {
        NumberTypeDataGet query = restrict(numberTypeDataClient.get());
        PnetDataClientResultPage<NumberTypeDataDTO> result = query.allByMatchcodes(Arrays.asList(matchcodes), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all number types", description = "Exports all number types.")
    public void exportAllNumberTypes() throws PnetDataClientException
    {
        NumberTypeDataFind query = restrict(numberTypeDataClient.find());
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated number types", format = "[<DAYS>:1]",
        description = "Exports all number types updated since yesterday.")
    public void exportAllUpdatedNumberTypes(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);

        NumberTypeDataFind query = restrict(numberTypeDataClient.find().updatedAfter(updatedAfter));
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find number types by mc", format = "<MC...>", description = "Find number types by matchcodes.")
    public void findNumberTypes(String... matchcodes) throws PnetDataClientException
    {
        NumberTypeDataFind query = restrict(numberTypeDataClient.find().matchcode(matchcodes));
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search number types", format = "<QUERY>", description = "Query number types.")
    public void searchNumberTypes(String... qs) throws PnetDataClientException
    {
        NumberTypeDataSearch query = restrict(numberTypeDataClient.search());
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, NumberTypeItemDTO dto)
    {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "get person by id", format = "<ID...>",
        description = "Returns all details of persons with the specified ids.")
    public void getPersonById(Integer... ids) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByIds(Arrays.asList(ids), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get person by external id", format = "<EXTERNALID...>",
        description = "Returns all details of persons with the specified external ids.")
    public void getPersonByExternalId(String... externalIds) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByExternalIds(Arrays.asList(externalIds), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get person by guid", format = "<GUID...>",
        description = "Returns all details of persons with the specified guids.")
    public void getPersonByGuid(String... guids) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByGuids(Arrays.asList(guids), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get person by preferredUserId", format = "<PREFID...>",
        description = "Returns all details of persons with the specified preferredUserIds.")
    public void getPersonByPreferredUserId(String... preferredUserIds) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result =
            query.allByPreferredUserIds(Arrays.asList(preferredUserIds), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get person by email", format = "<EMAIL...>",
        description = "Returns all details of persons with the specified emails.")
    public void getPersonByEmail(String... emails) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByEmails(Arrays.asList(emails), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "get person by personnelNumber", format = "<PERSNUMBER...>",
        description = "Returns all details of persons with the specified personnelNumbers.")
    public void getPersonByPersonnelNumber(String... personnelNumbers) throws PnetDataClientException
    {
        PersonDataGet query = restrict(personDataClient.get());
        PnetDataClientResultPage<PersonDataDTO> result =
            query.allByPersonnelNumbers(Arrays.asList(personnelNumbers), 0, 10);

        printResults(result, null);
    }

    @CLI.Command(name = "export all persons", description = "Exports all persons available for the current user.")
    public void exportAllPersons() throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().scroll());
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated persons", format = "[<DAYS>:1]",
        description = "Exports all persons available for the current user, that have been updated since yesterday.")
    public void exportAllUpdatedPersons(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);
        PersonDataFind query = restrict(personDataClient.find().updatedAfter(updatedAfter).scroll());
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find persons by personnel number", format = "<NUMBER...>",
        description = "Find persons by personnel number.")
    public void findPersonsByPersonnelNumber(String... numbers) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().personnelNumber(numbers));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "find persons by salesman number", format = "<NUMBER...>",
        description = "Find persons by salesman number.")
    public void findPersonsBySalesmanNumber(String... numbers) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().numbersType("NT_VERK_NR").number(numbers));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "find persons by id", format = "<ID...>", description = "Find a person by id.")
    public void findPersonById(Integer... ids) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().id(ids));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "find persons by company", format = "<COMPANY-MC...>",
        description = "Find persons at a specific company.")
    public void findPersonsByCompany(String... matchcodes) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().company(matchcodes));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "find persons by role", format = "<ROLE-MC...>",
        description = "Find persons by functions and activities.")
    public void findPersonsByRole(String... matchcodes) throws PnetDataClientException
    {
        PersonDataFind query = restrict(personDataClient.find().role(matchcodes));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "search persons", format = "<QUERY>", description = "Search for a person.")
    public void searchPersons(String... qs) throws PnetDataClientException
    {
        PersonDataSearch query = restrict(personDataClient.search());
        PnetDataClientResultPageWithAggregations<PersonItemDTO, PersonAggregationsDTO> result =
            query.execute(Locale.getDefault(), joinQuery(qs));

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
                    .filter(link -> link.isMainFunction())
                    .map(ActivePersonFunctionLinkDTO::getLabel)
                    .collect(Collectors.joining(", ")) : null,
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "export all todo groups", description = "Exports all todo groups.")
    public void exportAllTodoGroups() throws PnetDataClientException
    {
        TodoGroupDataFind query = restrict(todoGroupDataClient.find().scroll());
        PnetDataClientResultPage<TodoGroupItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "export all updated todo groups", format = "[<DAYS>:1]",
        description = "Exports all todo groups updated since yesterday.")
    public void exportAllUpdatedTodoGroups(Integer days) throws PnetDataClientException
    {
        LocalDateTime updatedAfter = LocalDateTime.now().minusDays(days != null ? days : 1);

        TodoGroupDataFind query = restrict(todoGroupDataClient.find().updatedAfter(updatedAfter).scroll());
        PnetDataClientResultPage<TodoGroupItemDTO> result = query.execute(Locale.getDefault(), 0, 100);

        printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find todo groups by mc", format = "<ID...>",
        description = "Find todo groups by reference matchcode.")
    public void findTodoGroupsByReferenceMatchcode(String... referenceMatchcodes) throws PnetDataClientException
    {
        TodoGroupDataFind query = restrict(todoGroupDataClient.find().referenceMatchcode(referenceMatchcodes));
        PnetDataClientResultPage<TodoGroupItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "find todo groups by category", format = "<CATEGORY...>",
        description = "Find todo groups by category.")
    public void findTodoGroupsByCategory(TodoCategory... categories) throws PnetDataClientException
    {
        TodoGroupDataFind query = restrict(todoGroupDataClient.find().category(categories));
        PnetDataClientResultPage<TodoGroupItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "find todo groups by person id", format = "<PERSION-ID...>",
        description = "Find todo groups by person id.")
    public void findTodoGroupsByOPersonId(Integer... personIds) throws PnetDataClientException
    {
        TodoGroupDataFind query = restrict(todoGroupDataClient.find().personId(personIds));
        PnetDataClientResultPage<TodoGroupItemDTO> result = query.execute(Locale.getDefault());

        printResults(result, this::populateTable);
    }

    @CLI.Command(name = "test",
        description = "Method to implement complex requests or test something data-api related.")
    public void test() throws PnetDataClientException
    {
        throw new UnsupportedOperationException("IMPLEMENT WHEN NEEDED!");
    }

    @CLI.Command(name = "search todo groups", format = "<QUERY>", description = "Query todo groups.")
    public void searchTodoGroups(String... qs) throws PnetDataClientException
    {
        TodoGroupDataSearch query = restrict(todoGroupDataClient.search());
        PnetDataClientResultPage<TodoGroupItemDTO> result = query.execute(Locale.getDefault(), joinQuery(qs));

        printResults(result, this::populateTable);
    }

    protected void populateTable(Table table, TodoGroupItemDTO dto)
    {
        table
            .addRow(dto.getCategory(), dto.getReferenceId(), dto.getReferenceMatchcode(), dto.getLabel(),
                dto.getPersons().stream().map(TodoGroupPersonLinkDTO::getName).collect(Collectors.joining(", ")),
                dto.getEntries().stream().map(TodoGroupEntryLinkDTO::getHeadline).collect(Collectors.joining(", ")),
                dto.getLastUpdate(), dto.getScore());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(description = "Opens the Swagger Documentation.")
    public void swagger() throws IOException
    {
        String url = prefs.getPnetDataApiUrl();

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
        repository
            .restCall(key())
            .variable("indexName", indexName)
            .post("/api/v1/migrator/full/{indexName}", Void.class);

        cli.info("Performing a full migration on index: %s.", indexName);
    }

    @CLI.Command(name = "migrate delta", format = "<INDEXNAME>",
        description = "Performs a delta migration for the specified index.")
    public void migrateDelta(String indexName) throws RestException, PnetDataClientException
    {
        repository
            .restCall(key())
            .variable("indexName", indexName)
            .post("/api/v1/migrator/delta/{indexName}", Void.class);

        cli.info("Performing a delta migration on index: %s.", indexName);
    }

    @CLI.Command(name = "migrate state", format = "<INDEXNAME>", description = "Prints the state of the migration.")
    public void migrateState(String indexName) throws RestException, PnetDataClientException
    {
        HashMap<?, ?> state = repository
            .restCall(key())
            .variable("indexName", indexName)
            .get("/api/v1/migrator/states/{indexName}", HashMap.class);

        cli.info("Migration state of index: %s", indexName);
        cli.info(PrettyPrint.prettyPrint(state));
    }

    @CLI.Command(name = "migrate explicit", format = "<INDEXNAME> [<IDS>]", description = "Runs an explicit migration.")
    public void migrateExplicit(String indexName, String... ids) throws RestException, PnetDataClientException
    {
        HashMap<?, ?> state = repository
            .restCall(key())
            .variable("indexName", indexName)
            .parameter("id", (Object[]) ids)
            .post("/api/v1/migrator/explicit/{indexName}", HashMap.class);

        cli.info("Explicit migration state of index: %s", indexName);
        cli.info(PrettyPrint.prettyPrint(state));
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "restrict tenants", format = "[<TENANT>...]",
        description = "Places a restriction with tenants for subsequent operations.")
    public void restrictTenants(String... tenants)
    {
        if (tenants != null && tenants.length > 0)
        {
            Arrays.stream(tenants).forEach(restrictedTenants::add);
        }

        cli.info("Requests are restricted to tenants: %s", restrictedTenants);
    }

    @CLI.Command(name = "clear tenant restrictions", description = "Removes all restrictions for tenants.")
    public void clearTenantRestrictions()
    {
        cli.info("Removed %s tenant restrictions.", restrictedTenants.size());

        restrictedTenants.clear();
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "restrict query fields", format = "[<FIELDS>...]",
        description = "Places a restriction for query fields.")
    public void restrictQueryFields(String... queryFields)
    {
        if (queryFields != null && queryFields.length > 0)
        {
            Arrays.stream(queryFields).forEach(restrictedQueryFields::add);
        }

        cli.info("Queries are restricted to following fields: %s", restrictedQueryFields);
    }

    @CLI.Command(name = "clear query field restrictions", description = "Removes all restrictions to query fields.")
    public void clearQueryFieldRestrictions()
    {
        cli.info("Removed %s query field restrictions.", restrictedQueryFields.size());

        restrictedQueryFields.clear();
    }

    ////////////////////////////////////////////////////////////////////////////

    protected <T extends Restrict<T>> T restrict(T restrict)
    {
        restrict = restrictCompany(restrict);
        restrict = restrictBaseData(restrict);
        restrict = restrictCusom(restrict);
        restrict = restrictAggregates(restrict);

        return restrict;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T restrictCompany(T restrict)
    {
        if (restrict instanceof RestrictCompanyId && !restrictedCompanyIds.isEmpty())
        {
            cli.info("A restriction for company ids is in place: %s", restrictedCompanyIds);

            restrict = ((RestrictCompanyId<T>) restrict).companyIds(restrictedCompanyIds);
        }

        if (restrict instanceof RestrictCompany && !restrictedCompanyMatchcodes.isEmpty())
        {
            cli.info("A restriction for company matchcodes is in place: %s", restrictedCompanyMatchcodes);

            restrict = ((RestrictCompany<T>) restrict).companies(restrictedCompanyMatchcodes);
        }

        if (restrict instanceof RestrictCompanyNumber && !restrictedCompanyNumbers.isEmpty())
        {
            cli.info("A restriction for company numbers is in place: %s", restrictedCompanyNumbers);

            restrict = ((RestrictCompanyNumber<T>) restrict).companyNumbers(restrictedCompanyNumbers);
        }

        return restrict;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T restrictBaseData(T restrict)
    {
        if (restrict instanceof RestrictTenant && !restrictedTenants.isEmpty())
        {
            cli.info("A restriction for tenants is in place: %s", restrictedTenants);

            restrict = ((RestrictTenant<T>) restrict).tenants(restrictedTenants);
        }

        if (restrict instanceof RestrictBrand && !restrictedBrands.isEmpty())
        {
            cli.info("A restriction for brands is in place: %s", restrictedBrands);

            restrict = ((RestrictBrand<T>) restrict).brands(restrictedBrands);
        }

        if (restrict instanceof RestrictFunction && !restrictedFunctionMatchcodes.isEmpty())
        {
            cli.info("A restriction for function matchcodes is in place: %s", restrictedFunctionMatchcodes);

            restrict = ((RestrictFunction<T>) restrict).functions(restrictedFunctionMatchcodes);
        }

        if (restrict instanceof RestrictActivity && !restrictedActivityMatchcodes.isEmpty())
        {
            cli.info("A restriction for activity matchcodes is in place: %s", restrictedActivityMatchcodes);

            restrict = ((RestrictActivity<T>) restrict).activities(restrictedActivityMatchcodes);
        }

        if (restrict instanceof RestrictNumberType && !restrictedNumberTypeMatchcodes.isEmpty())
        {
            cli.info("A restriction for number type matchcodes is in place: %s", restrictedNumberTypeMatchcodes);

            restrict = ((RestrictNumberType<T>) restrict).numberTypes(restrictedNumberTypeMatchcodes);
        }

        if (restrict instanceof RestrictQueryField && !restrictedQueryFields.isEmpty())
        {
            cli.info("A restriction for query fields is in place: %s", restrictedQueryFields);

            restrict = ((RestrictQueryField<T>) restrict).queryFields(restrictedQueryFields);
        }

        return restrict;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T restrictCusom(T restrict)
    {
        if (restrict instanceof CompanyMergable && companyMerge != CompanyMerge.NONE)
        {
            cli.info("Merging companies according to: " + companyMerge);

            restrict = ((CompanyMergable<T>) restrict).merge(companyMerge);
        }

        if (restrict instanceof RestrictDatedBackUntil && datedBackUntil != null)
        {
            cli.info("Dating back until: " + datedBackUntil);

            restrict = ((RestrictDatedBackUntil<T>) restrict).datedBackUntil(datedBackUntil);
        }

        return restrict;
    }

    @SuppressWarnings("unchecked")
    private <T extends Restrict<T>> T restrictAggregates(T restrict)
    {
        if (!aggs)
        {
            return restrict;
        }

        if (restrict instanceof AggregateNumberPerActivity)
        {
            restrict = (T) ((AggregateNumberPerActivity<?>) restrict).aggregateNumberPerActivity();
        }

        if (restrict instanceof AggregateNumberPerBrand)
        {
            restrict = (T) ((AggregateNumberPerBrand<?>) restrict).aggregateNumberPerBrand();
        }

        if (restrict instanceof AggregateNumberPerCategory)
        {
            restrict = (T) ((AggregateNumberPerCategory<?>) restrict).aggregateNumberPerCategory();
        }

        if (restrict instanceof AggregateNumberPerCompany)
        {
            restrict = (T) ((AggregateNumberPerCompany<?>) restrict).aggregateNumberPerCompany();
        }

        if (restrict instanceof AggregateNumberPerContractType)
        {
            restrict = (T) ((AggregateNumberPerContractType<?>) restrict).aggregateNumberPerContractType();
        }

        if (restrict instanceof AggregateNumberPerFunction)
        {
            restrict = (T) ((AggregateNumberPerFunction<?>) restrict).aggregateNumberPerFunction();
        }

        if (restrict instanceof AggregateNumberPerState)
        {
            restrict = (T) ((AggregateNumberPerState<?>) restrict).aggregateNumberPerState();
        }

        if (restrict instanceof AggregateNumberPerTenant)
        {
            restrict = (T) ((AggregateNumberPerTenant<?>) restrict).aggregateNumberPerTenant();
        }

        if (restrict instanceof AggregateNumberPerType)
        {
            restrict = (T) ((AggregateNumberPerType<?>) restrict).aggregateNumberPerType();
        }

        return restrict;
    }

    @CLI.Command(name = "clear restrictions", description = "Removes all restrictions.")
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
        restrictedQueryFields.clear();
        datedBackUntil = null;
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(format = "[<URL>] [<USERNAME>] [<PASSWORD>]", description = "Prints or overrides the predefined URL.")
    public void url(String url, String username, String password) throws PnetDataClientException
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

            prefs.setPnetDataApiUrl(url);
        }

        if (username != null)
        {
            prefs.setPnetDataApiUsername(username);
        }

        if (password != null)
        {
            prefs.setPnetDataApiPassword(password);
        }

        cli.info("url = %s", prefs.getPnetDataApiUrl());
        cli.info("username = %s", prefs.getPnetDataApiUsername());
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

        Prefs.setUrl(key, prefs.getPnetDataApiUrl());
        Prefs.setUsername(key, prefs.getPnetDataApiUsername());
        Prefs.setPassword(key, prefs.getPnetDataApiPassword());

        cli.info("URL, username and (encoded) password have been stored locally with key: %s", key);
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

        String username = Prefs.getUsername(key);
        String password = Prefs.getPassword(key);

        url(url, username, password);
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
            .forEach(key -> cli.info("%s: %s (%s)", key, Prefs.getUrl(key), Prefs.getUsername(key)));
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
                prefs.setPnetDataApiUsername(username);
                prefs.setPnetDataApiPassword(password);
            }
            else
            {
                cli.warn("Aborted.");
            }
        }

        cli.info("username = %s", prefs.getPnetDataApiUsername());
    }

    ////////////////////////////////////////////////////////////////////////////

    @CLI.Command(name = "send get", format = "PATH", description = "Execute a GET request.")
    public void getRequest(String request) throws RestException, PnetDataClientException, URISyntaxException
    {
        RestCall restCall = repository.restCall(key());
        String url = restCall.getUrl();

        if (request.startsWith(url))
        {
            request = request.replace(url, "");
        }

        Object result = restCall.get(request, Object.class);

        cli.info(PrettyPrint.prettyPrint(result));
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

    protected PnetDataApiTokenKey key()
    {
        return new PnetDataApiTokenKey(prefs.getPnetDataApiUrl(), prefs.getPnetDataApiUsername(),
            prefs.getPnetDataApiPassword());
    }

    public void consume()
    {
        cli.info("Partner.Net REST Client Sample application");
        cli.info("==========================================");
        cli.info("");
        cli.info("Type \"help\" for help.");
        cli.info("");

        while (true)
        {
            try
            {
                Thread.sleep(5);

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

}
