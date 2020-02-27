package pnet.data.api.apache;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.apache.ApacheRestCallFactory;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import pnet.data.api.PnetRestClient;
import pnet.data.api.about.AboutDataClient;
import pnet.data.api.activity.ActivityDataClient;
import pnet.data.api.advisortype.AdvisorTypeDataClient;
import pnet.data.api.application.ApplicationDataClient;
import pnet.data.api.brand.BrandDataClient;
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.context.PnetDataApiTokenRepository;
import pnet.data.api.client.context.PrefsBasedPnetDataApiContext;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.company.CompanyDataClient;
import pnet.data.api.companygroup.CompanyGroupDataClient;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataClient;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataClient;
import pnet.data.api.companytype.CompanyTypeDataClient;
import pnet.data.api.contractstate.ContractStateDataClient;
import pnet.data.api.contracttype.ContractTypeDataClient;
import pnet.data.api.externalbrand.ExternalBrandDataClient;
import pnet.data.api.function.FunctionDataClient;
import pnet.data.api.legalform.LegalFormDataClient;
import pnet.data.api.numbertype.NumberTypeDataClient;
import pnet.data.api.person.PersonDataClient;
import pnet.data.api.todo.TodoGroupDataClient;
import pnet.data.api.util.Prefs;

/**
 * Demonstrates the usage of the PNet Data API by using an Apache HttpClient an without Spring.
 *
 * @author HAM
 */
public final class PnetApacheRestClientLauncher
{

    private PnetApacheRestClientLauncher()
    {
        super();
    }

    public static void main(String[] args)
    {
        String url = Prefs.getUrl(Prefs.DEFAULT_KEY);
        String username = Prefs.getUsername(Prefs.DEFAULT_KEY);
        String password = Prefs.getPassword(Prefs.DEFAULT_KEY);
        PnetRestClient client = buildClient(url, username, password);

        client.consume();
    }

    private static PnetRestClient buildClient(String url, String username, String password)
    {
        ObjectMapper mapper = JacksonPnetDataApiModule.createObjectMapper();
        RestLoggerAdapter loggerAdapter = Slf4jRestLoggerAdapter.getDefault();
        RestCallFactory factory = ApacheRestCallFactory.create(loggerAdapter, mapper);
        PnetDataApiTokenRepository repository = new PnetDataApiTokenRepository(factory);
        MutablePnetDataClientPrefs prefs = new MutablePnetDataClientPrefs(url, username, password);
        PrefsBasedPnetDataApiContext context = new PrefsBasedPnetDataApiContext(repository, prefs);

        AboutDataClient aboutDataClient = new AboutDataClient(context);
        ActivityDataClient activityDataClient = new ActivityDataClient(context);
        AdvisorTypeDataClient advisorTypeDataClient = new AdvisorTypeDataClient(context);
        ApplicationDataClient applicationDataClient = new ApplicationDataClient(context);
        BrandDataClient brandDataClient = new BrandDataClient(context);
        CompanyDataClient companyDataClient = new CompanyDataClient(context);
        CompanyGroupDataClient companyGroupDataClient = new CompanyGroupDataClient(context);
        CompanyGroupTypeDataClient companyGroupTypeDataClient = new CompanyGroupTypeDataClient(context);
        CompanyNumberTypeDataClient companyNumberTypeDataClient = new CompanyNumberTypeDataClient(context);
        CompanyTypeDataClient companyTypeDataClient = new CompanyTypeDataClient(context);
        ContractStateDataClient contractStateDataClient = new ContractStateDataClient(context);
        ContractTypeDataClient contractTypeDataClient = new ContractTypeDataClient(context);
        ExternalBrandDataClient externalBrandDataClient = new ExternalBrandDataClient(context);
        FunctionDataClient functionDataClient = new FunctionDataClient(context);
        LegalFormDataClient legalFormDataClient = new LegalFormDataClient(context);
        NumberTypeDataClient numberTypeDataClient = new NumberTypeDataClient(context);
        PersonDataClient personDataClient = new PersonDataClient(context);
        TodoGroupDataClient todoGroupDataClient = new TodoGroupDataClient(context);

        PnetRestClient client = new PnetRestClient(prefs, aboutDataClient, activityDataClient, advisorTypeDataClient,
            applicationDataClient, brandDataClient, companyDataClient, companyGroupDataClient,
            companyGroupTypeDataClient, companyNumberTypeDataClient, companyTypeDataClient, contractStateDataClient,
            contractTypeDataClient, externalBrandDataClient, functionDataClient, legalFormDataClient,
            numberTypeDataClient, personDataClient, todoGroupDataClient, repository);

        return client;
    }

}
