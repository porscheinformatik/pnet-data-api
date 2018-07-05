package pnet.data.api.spring;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.RestException;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.about.AboutDataClient;
import pnet.data.api.about.AboutDataDTO;
import pnet.data.api.activity.ActivityDataClient;
import pnet.data.api.advisortype.AdvisorTypeDataClient;
import pnet.data.api.application.ApplicationDataClient;
import pnet.data.api.brand.BrandDataClient;
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.PnetDataApiTokenKey;
import pnet.data.api.client.context.PnetDataApiTokenRepository;
import pnet.data.api.company.CompanyDataClient;
import pnet.data.api.companygroup.CompanyGroupDataClient;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataClient;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataClient;
import pnet.data.api.companytype.CompanyTypeDataClient;
import pnet.data.api.contractstate.ContractStateDataClient;
import pnet.data.api.contracttype.ContractTypeDataClient;
import pnet.data.api.externalbrand.ExternalBrandDataClient;
import pnet.data.api.function.FunctionDataClient;
import pnet.data.api.numbertype.NumberTypeDataClient;
import pnet.data.api.person.PersonDataClient;
import pnet.data.api.util.CLI;
import pnet.data.api.util.CLI.Arguments;
import pnet.data.api.util.PrettyPrint;

@Service
public class PnetSpringRestClient
{

    private final CLI cli;

    @Autowired
    private MutablePnetDataClientPrefs prefs;

    @Autowired
    private AboutDataClient aboutDataClient;

    @Autowired
    private ActivityDataClient activityDataClient;

    @Autowired
    private AdvisorTypeDataClient advisorTypeDataClient;

    @Autowired
    private ApplicationDataClient applicationDataClient;

    @Autowired
    private BrandDataClient brandDataClient;

    @Autowired
    private CompanyDataClient companyDataClient;

    @Autowired
    private CompanyGroupDataClient companyGroupDataClient;

    @Autowired
    private CompanyGroupTypeDataClient companyGroupTypeDataClient;

    @Autowired
    private CompanyNumberTypeDataClient companyNumberTypeDataClient;

    @Autowired
    private CompanyTypeDataClient companyTypeDataClient;

    @Autowired
    private ContractStateDataClient contractStateDataClient;

    @Autowired
    private ContractTypeDataClient contractTypeDataClient;

    @Autowired
    private ExternalBrandDataClient externalBrandDataClient;

    @Autowired
    private FunctionDataClient functionDataClient;

    @Autowired
    private NumberTypeDataClient numberTypeDataClient;

    @Autowired
    private PersonDataClient personDataClient;

    @Autowired
    private PnetDataApiTokenRepository repository;

    private String[] tenants;
    private PnetDataClientResultPage<?> page;

    private PnetSpringRestClient()
    {
        super();

        cli = new CLI();

        cli.register(this);
    }

    @CLI.Command(description = "Info about the Partner.Net Data API and the user.")
    public void about() throws PnetDataClientException
    {
        AboutDataDTO about = aboutDataClient.about();

        cli.info(about);
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

    @CLI.Command(name = "get activity by matchcode", format = "<MATCHCODE...>",
        description = "Returns the query with the specified matchcode.")
    public void getActivity(String... matchcodes) throws PnetDataClientException
    {
        printResults(activityDataClient.get().tenant(tenants).allByMatchcodes(Arrays.asList(matchcodes), 0, 10));
    }

    @CLI.Command(name = "get company by id", format = "<COMPANY-ID...>",
        description = "Returns the company with the specified id.")
    public void getCompanyByIds(Integer... ids) throws PnetDataClientException
    {
        printResults(companyDataClient.get().tenant(tenants).allByIds(Arrays.asList(ids), 0, 10));
    }

    @CLI.Command(name = "get company by vat", format = "<COMPANY-VATIDNUMBER...>",
        description = "Returns the company with the specified vat id number.")
    public void getCompanyByVatIdNumbers(String... vatIdNumbers) throws PnetDataClientException
    {
        printResults(companyDataClient.get().tenant(tenants).allByVatIdNumbers(Arrays.asList(vatIdNumbers), 0, 10));
    }

    @CLI.Command(name = "get company by sap", format = "<COMPANY-SAPNUMBER...>",
        description = "Returns the company with the specified id.")
    public void getCompanyBySapNumbers(String... sapNumbers) throws PnetDataClientException
    {
        printResults(companyDataClient.get().tenant(tenants).allBySapNumbers(Arrays.asList(sapNumbers), 0, 10));
    }

    @CLI.Command(name = "get company by number", format = "<COMPANY-NUMBER...>",
        description = "Returns the company with the specified company number.")
    public void getCompanyByCompanyNumbers(String... companyNumbers) throws PnetDataClientException
    {
        printResults(companyDataClient.get().tenant(tenants).allByCompanyNumbers(Arrays.asList(companyNumbers), 0, 10));
    }

    @CLI.Command(name = "get company by iban", format = "<COMPANY-IBAN...>",
        description = "Returns the company with the specified iban.")
    public void getCompanyByIbans(String... ibans) throws PnetDataClientException
    {
        printResults(companyDataClient.get().tenant(tenants).allByIbans(Arrays.asList(ibans), 0, 10));
    }

    @CLI.Command(name = "get company by email", format = "<COMPANY-EMAIL...>",
        description = "Returns the company with the specified email.")
    public void getCompanyByEmails(String... emails) throws PnetDataClientException
    {
        printResults(companyDataClient.get().tenant(tenants).allByEmails(Arrays.asList(emails), 0, 10));
    }

    @CLI.Command(name = "get company by dvr", format = "<COMPANY-DPRN...>",
        description = "Returns the company with the specified data processing register number.")
    public void getCompanyByDataProcessingRegisterNumbers(String... dataProcessingRegisterNumbers)
        throws PnetDataClientException
    {
        printResults(companyDataClient.get().tenant(tenants).allByDataProcessingRegisterNumbers(
            Arrays.asList(dataProcessingRegisterNumbers), 0, 10));
    }

    @CLI.Command(name = "get brand by matchcode", format = "<MATCHCODE...>",
        description = "Returns the brand with the specified matchcode.")
    public void getBrand(String... matchcodes) throws PnetDataClientException
    {
        printResults(brandDataClient.get().tenant(tenants).allByMatchcodes(Arrays.asList(matchcodes), 0, 10));
    }

    @CLI.Command(name = "get contract type by matchcode", format = "<MATCHCODE...>",
        description = "Returns the contract type with the specified matchcode.")
    public void getContractType(String... matchcodes) throws PnetDataClientException
    {
        printResults(contractTypeDataClient.get().tenant(tenants).allByMatchcodes(Arrays.asList(matchcodes), 0, 10));
    }

    @CLI.Command(name = "get function by matchcode", format = "<MATCHCODE...>",
        description = "Returns the function with the specified matchcode.")
    public void getFunction(String... matchcodes) throws PnetDataClientException
    {
        printResults(functionDataClient.get().tenant(tenants).allByMatchcodes(Arrays.asList(matchcodes), 0, 10));
    }

    @CLI.Command(name = "find activity by mc", format = "<MATCHCODE...>",
        description = "Find activities by matchcodes.")
    public void findActivities(String... matchcodes) throws PnetDataClientException
    {
        printResults(activityDataClient.find().tenant(tenants).matchcode(matchcodes).execute(Locale.getDefault()));
    }

    @CLI.Command(name = "find company groups by leader", format = "<COMPANY-ID...>",
        description = "Find all company groups with the specified leader.")
    public void findCompanyGroupsByLeader(Integer... ids) throws PnetDataClientException
    {
        printResults(companyGroupDataClient.getAllByLeadingCompanyIds(Arrays.asList(ids), 0, 10));
    }

    @CLI.Command(name = "find company groups by member", format = "<COMPANY-ID...>",
        description = "Find all company groups with the specified member.")
    public void findCompanyGroupsByMember(Integer... ids) throws PnetDataClientException
    {
        printResults(companyGroupDataClient.getAllByCompanyIds(Arrays.asList(ids), 0, 10));
    }

    @CLI.Command(name = "search activities", format = "<QUERY>", description = "Query activities.")
    public void searchActivities(String query) throws PnetDataClientException
    {
        printResults(activityDataClient.search().tenant(tenants).execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search advisor types", format = "<QUERY>", description = "Query advisor types.")
    public void searchAdvisorTypes(String query) throws PnetDataClientException
    {
        printResults(advisorTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search applications", format = "<QUERY>", description = "Query applications.")
    public void searchApplications(String query) throws PnetDataClientException
    {
        printResults(applicationDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search brands", format = "<QUERY>", description = "Query brands.")
    public void searchBrands(String query) throws PnetDataClientException
    {
        printResults(brandDataClient.search().tenant(tenants).execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search companies", format = "<QUERY>", description = "Query companies.")
    public void searchCompanies(String query) throws PnetDataClientException
    {
        printResults(companyDataClient.search().tenant(tenants).execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search company group types", format = "<QUERY>", description = "Query company group types.")
    public void searchCompanyGroups(String query) throws PnetDataClientException
    {
        printResults(companyGroupTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search company number types", format = "<QUERY>", description = "Query company number types.")
    public void searchCompanyNumberTypes(String query) throws PnetDataClientException
    {
        printResults(companyNumberTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search company types", format = "<QUERY>", description = "Query company types.")
    public void searchCompanyTypes(String query) throws PnetDataClientException
    {
        printResults(companyTypeDataClient.search().tenant(tenants).execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search contract states", format = "<QUERY>", description = "Query contract states types.")
    public void searchContractStates(String query) throws PnetDataClientException
    {
        printResults(contractStateDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search contract types", format = "<QUERY>", description = "Query contract types.")
    public void searchContractTypes(String query) throws PnetDataClientException
    {
        printResults(contractTypeDataClient.search().tenant(tenants).execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search external brands", format = "<QUERY>", description = "Query external brands.")
    public void searchExternalBrands(String query) throws PnetDataClientException
    {
        printResults(externalBrandDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search functions", format = "<QUERY>", description = "Query functions.")
    public void searchFunctions(String query) throws PnetDataClientException
    {
        printResults(functionDataClient.search().tenant(tenants).execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "search number types", format = "<QUERY>", description = "Query number types.")
    public void searchNumberTypes(String query) throws PnetDataClientException
    {
        printResults(numberTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "find person by id", format = "<ID...>", description = "Find a person by id.")
    public void findPersonById(Integer... ids) throws PnetDataClientException
    {
        printResults(personDataClient.find().id(ids).tenant(tenants).execute(Locale.getDefault()));
    }

    @CLI.Command(name = "get person by id", format = "<ID...>",
        description = "Returns all details of person with the specified id.")
    public void getPersonById(Integer... ids) throws PnetDataClientException
    {
        printResults(personDataClient.get().allByIds(Arrays.asList(ids), 0, 10));
    }

    @CLI.Command(name = "get person by guid", format = "<GUID...>",
        description = "Returns all details of person with the specified guid.")
    public void getPersonByGuid(String... guids) throws PnetDataClientException
    {
        printResults(personDataClient.get().allByGuids(Arrays.asList(guids), 0, 10));
    }

    @CLI.Command(name = "get person by preferredUserId", format = "<PREFID...>",
        description = "Returns all details of person with the specified prefferedUserId.")
    public void getPersonByPreferredUserId(String... preferredUserIds) throws PnetDataClientException
    {
        printResults(personDataClient.get().allByPrefferedUserIds(Arrays.asList(preferredUserIds), 0, 10));
    }

    @CLI.Command(name = "get person by email", format = "<EMAIL...>",
        description = "Returns all details of person with the specified email.")
    public void getPersonByEmail(String... emails) throws PnetDataClientException
    {
        printResults(personDataClient.get().allByEmails(Arrays.asList(emails), 0, 10));
    }

    @CLI.Command(name = "get person by personnelNumber", format = "<PERSNUMBER...>",
        description = "Returns all details of person with the specified personnelNumber.")
    public void getPersonByPersonnelNumber(String... personnelNumbers) throws PnetDataClientException
    {
        printResults(personDataClient.get().allByPersonnelNumbers(Arrays.asList(personnelNumbers), 0, 10));
    }

    @CLI.Command(name = "search persons", format = "<QUERY>", description = "Search for a person.")
    public void searchPerson(String query) throws PnetDataClientException
    {
        printResults(personDataClient.search().tenant(tenants).execute(Locale.getDefault(), query));
    }

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

    @CLI.Command(format = "[<TENANT>...]", description = "Sets the tenant filter.")
    public void tenant(String... tenants)
    {
        this.tenants = tenants;

        cli.info("tenants = %s", Arrays.toString(tenants));
    }

    @CLI.Command(format = "[<URL>] [<USERNAME>] [<PASSWORD>]", description = "Prints or overrides the predefined URL.")
    public void url(String url, String username, String password)
    {
        if (url != null)
        {
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
    }

    @CLI.Command(format = "[<USERNAME>] [<PASSWORD>]", description = "Prints or overrides the username and password.")
    public void user(String username, String password) throws IOException
    {
        if (username != null)
        {
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

    @CLI.Command(description = "Prints the next page of the last result.")
    public void next() throws PnetDataClientException
    {
        if (page == null)
        {
            cli.error("No result available.");
            return;
        }

        if (!page.hasNextPage())
        {
            cli.error("There is no next page.");
            return;
        }

        printPage(page.nextPage());
    }

    @CLI.Command(description = "Prints the previous page of the last result.")
    public void prev() throws PnetDataClientException
    {
        if (page == null)
        {
            cli.error("No result available.");
            return;
        }

        int index = page.getPageIndex();

        if (index <= 0)
        {
            cli.error("There is no previous page.");
            return;
        }

        printPage(page.getPage(index - 1));
    }

    @CLI.Command(format = "[<NUMBER>]", description = "Prints the page with the specified number.")
    public void page(Integer number) throws PnetDataClientException
    {
        if (page == null)
        {
            cli.error("No result available.");
            return;
        }

        if (number == null)
        {
            printPage();
        }
        else
        {
            printPage(page.getPage(number - 1));
        }
    }

    protected void printResults(PnetDataClientResultPage<?> page) throws PnetDataClientException
    {
        cli.info("Found %d results.", page.getTotalNumberOfItems());

        printPage(page);
    }

    protected void printPage(PnetDataClientResultPage<?> page)
    {
        this.page = page;

        printPage();
    }

    protected void printPage()
    {
        page.stream().map(PrettyPrint::prettyPrint).forEach(cli::info);

        cli.info("\nThis is page %d of %d. Type \"next\", \"prev\" or \"page <NUM>\" to navigate.",
            page.getPageIndex() + 1, page.getNumberOfPages());
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
                Thread.sleep(500);

                cli.consumeCommand(null);
            }
            catch (Exception e)
            {
                cli.error("Command failed", e);
            }
        }
    }

}
