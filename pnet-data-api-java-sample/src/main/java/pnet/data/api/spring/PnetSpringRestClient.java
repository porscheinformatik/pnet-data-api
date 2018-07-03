package pnet.data.api.spring;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        PnetDataApiTokenKey key = new PnetDataApiTokenKey(prefs.getPnetDataApiUrl(), prefs.getPnetDataApiUsername(),
            prefs.getPnetDataApiPassword());

        cli.info("token = %s", repository.restCall(key).getHeader("Authorization"));
    }

    @CLI.Command(description = "Invalidates the stored JSON Web Token.")
    public void logout() throws PnetDataClientException
    {
        PnetDataApiTokenKey key = new PnetDataApiTokenKey(prefs.getPnetDataApiUrl(), prefs.getPnetDataApiUsername(),
            prefs.getPnetDataApiPassword());

        repository.invalidate(key);

        cli.info("Logged out.");
    }

    @CLI.Command(name = "get activity", format = "<MATCHCODE...>",
        description = "Returns the query with the specified matchcode.")
    public void getActivity(String... matchcodes) throws PnetDataClientException
    {
        printResults(activityDataClient.getAllByMatchcodes(Arrays.asList(matchcodes), 0, 10));
    }

    @CLI.Command(name = "get comany by id", format = "<COMPANY-ID...>",
        description = "Returns the company with the specified id.")
    public void getCompany(Integer... ids) throws PnetDataClientException
    {
        printResults(companyDataClient.getAllByIds(Arrays.asList(ids), 0, 10));
    }

    @CLI.Command(name = "find activity by mc", format = "<MATCHCODE...>",
        description = "Find activities by matchcodes.")
    public void findActivities(String... matchcodes) throws PnetDataClientException
    {
        printResults(activityDataClient.find().matchcode(matchcodes).execute(Locale.getDefault()));
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

    @CLI.Command(name = {"search activities", "s a"}, format = "<QUERY>", description = "Query activities.")
    public void searchActivities(String query) throws PnetDataClientException
    {
        printResults(activityDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search advisortypes", "s at"}, format = "<QUERY>", description = "Query advisor types.")
    public void searchAdvisorTypes(String query) throws PnetDataClientException
    {
        printResults(advisorTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search applications", "s ap"}, format = "<QUERY>", description = "Query applications.")
    public void searchApplications(String query) throws PnetDataClientException
    {
        printResults(applicationDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search brands", "s b"}, format = "<QUERY>", description = "Query brands.")
    public void searchBrands(String query) throws PnetDataClientException
    {
        printResults(brandDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search companies", "s c"}, format = "<QUERY>", description = "Query companies.")
    public void searchCompanies(String query) throws PnetDataClientException
    {
        printResults(companyDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search companygrouptypes", "s cgt"}, format = "<QUERY>",
        description = "Query company group types.")
    public void searchCompanyGroups(String query) throws PnetDataClientException
    {
        printResults(companyGroupTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search companynumbertypes", "s cnt"}, format = "<QUERY>",
        description = "Query company number types.")
    public void searchCompanyNumberTypes(String query) throws PnetDataClientException
    {
        printResults(companyNumberTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search companytypes", "s ct"}, format = "<QUERY>", description = "Query company types.")
    public void searchCompanyTypes(String query) throws PnetDataClientException
    {
        printResults(companyTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search contractstates", "s cos"}, format = "<QUERY>",
        description = "Query contract states types.")
    public void searchContractStates(String query) throws PnetDataClientException
    {
        printResults(contractStateDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search contracttypes", "s cot"}, format = "<QUERY>", description = "Query contract types.")
    public void searchContractTypes(String query) throws PnetDataClientException
    {
        printResults(contractTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search externalbrands", "s eb"}, format = "<QUERY>", description = "Query external brands.")
    public void searchExternalBrands(String query) throws PnetDataClientException
    {
        printResults(externalBrandDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search functions", "s f"}, format = "<QUERY>", description = "Query functions.")
    public void searchFunctions(String query) throws PnetDataClientException
    {
        printResults(functionDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = {"search numbertypes", "s nt"}, format = "<QUERY>", description = "Query number types.")
    public void searchNumberTypes(String query) throws PnetDataClientException
    {
        printResults(numberTypeDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(name = "find person by id", format = "<ID...>", description = "Find a person by id.")
    public void findPersonById(Integer... ids) throws PnetDataClientException
    {
        printResults(personDataClient.find().id(ids).execute(Locale.getDefault()));
    }

    @CLI.Command(name = {"search persons", "s p"}, format = "<QUERY>", description = "Search for a person.")
    public void searchPerson(String query) throws PnetDataClientException
    {
        printResults(personDataClient.search().execute(Locale.getDefault(), query));
    }

    @CLI.Command(format = "[<URL>] [<USERNAME>] [<PASSWORD>] ", description = "Prints or overrides the predefined URL.")
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

    @CLI.Command(format = "[<USERNAME>]", description = "Prints or overrides the username and password.")
    public void user(String username) throws IOException
    {
        if (username != null)
        {
            Arguments arguments = cli.consume("Password: ");
            String password = arguments.consume(String.class).orElse(null);

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

    protected void printResults(PnetDataClientResultPage<?> page) throws PnetDataClientException
    {
        cli.info("Found %d results.", page.getTotalNumberOfItems());

        while (true)
        {
            cli.info("\nPage %d of %d:", page.getPageIndex() + 1, page.getNumberOfPages());

            page.forEach(cli::info);

            if (!page.hasNextPage())
            {
                break;
            }

            Optional<String> result = cli.consume("\nNext page (y/n/<INDEX>)? ").consume(String.class);

            if (!result.isPresent())
            {
                break;
            }

            try
            {
                int index = Integer.parseInt(result.get());
                page = page.getPage(index);

                continue;
            }
            catch (NumberFormatException e)
            {
                // it's ok, passt scho
            }

            if (!"y".equals(result.get()))
            {
                break;
            }

            page = page.nextPage();
        }

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
