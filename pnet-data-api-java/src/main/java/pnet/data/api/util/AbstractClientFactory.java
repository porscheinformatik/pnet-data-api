package pnet.data.api.util;

import java.util.function.Supplier;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import pnet.data.api.about.AboutDataClient;
import pnet.data.api.activity.ActivityDataClient;
import pnet.data.api.advisortype.AdvisorTypeDataClient;
import pnet.data.api.application.ApplicationDataClient;
import pnet.data.api.brand.BrandDataClient;
import pnet.data.api.client.PnetDataClientPrefs;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.client.context.SimplePnetDataApiContext;
import pnet.data.api.client.context.UsernamePasswordCredentials;
import pnet.data.api.client.context.UsernamePasswordPnetDataApiLoginMethod;
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

/**
 * A factory for clients if you are working without Spring.
 *
 * @author HAM
 * @param <T> the type itself
 */
@SuppressWarnings("deprecation")
public abstract class AbstractClientFactory<T extends AbstractClientFactory<T>> implements ClientProvider
{
    private final PnetDataApiLoginMethod loginMethod;
    private final ObjectMapper mapper;
    private final RestLoggerAdapter loggerAdapter;
    private final RestCallFactory restCallFactory;
    private final PnetDataApiContext context;

    private AboutDataClient aboutDataClient = null;
    private ActivityDataClient activityDataClient = null;
    private AdvisorTypeDataClient advisorTypeDataClient = null;
    private ApplicationDataClient applicationDataClient = null;
    private BrandDataClient brandDataClient = null;
    private CompanyDataClient companyDataClient = null;
    private CompanyGroupDataClient companyGroupDataClient = null;
    private CompanyGroupTypeDataClient companyGroupTypeDataClient = null;
    private CompanyNumberTypeDataClient companyNumberTypeDataClient = null;
    private CompanyTypeDataClient companyTypeDataClient = null;
    private ContractStateDataClient contractStateDataClient = null;
    private ContractTypeDataClient contractTypeDataClient = null;
    private ExternalBrandDataClient externalBrandDataClient = null;
    private FunctionDataClient functionDataClient = null;
    private LegalFormDataClient legalFormDataClient = null;
    private NumberTypeDataClient numberTypeDataClient = null;
    private PersonDataClient personDataClient = null;

    //    public AbstractClientFactory(PnetDataClientPrefs prefs, ObjectMapper mapper, RestLoggerAdapter loggerAdapter)

    public AbstractClientFactory(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        super();

        this.loginMethod = loginMethod;
        this.mapper = mapper;
        this.loggerAdapter = loggerAdapter;

        restCallFactory = createRestCallFactory(mapper, loggerAdapter);
        context = new SimplePnetDataApiContext(restCallFactory, loginMethod);
    }

    protected abstract RestCallFactory createRestCallFactory(ObjectMapper mapper, RestLoggerAdapter loggerAdapter);

    protected abstract T copy(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper, RestLoggerAdapter loggerAdapter);

    public T withLoginMethod(PnetDataApiLoginMethod loginMethod)
    {
        return copy(loginMethod, mapper, loggerAdapter);
    }

    /**
     * Creates a new instance of this factory using the specified preferences.
     *
     * @param prefs the preferences
     * @return a new instance
     * @deprecated since {@link PnetDataClientPrefs} are deprecated, use the
     *             {@link #withLoginMethod(PnetDataApiLoginMethod)} method instead
     */
    @Deprecated
    public T withPrefs(PnetDataClientPrefs prefs)
    {
        return copy(
            new UsernamePasswordPnetDataApiLoginMethod(prefs.getPnetDataApiUrl(),
                () -> new UsernamePasswordCredentials(prefs.getPnetDataApiUsername(), prefs.getPnetDataApiPassword())),
            mapper, loggerAdapter);
    }

    /**
     * Creates a new instance of this factory using the specified properties.
     *
     * @param url the URL to the Data API
     * @param username the username
     * @param password the password
     * @return a new instance
     * @deprecated use the {@link #withLoginMethod(PnetDataApiLoginMethod)} method instead
     */
    @Deprecated
    public T withPrefs(String url, String username, String password)
    {
        return copy(
            new UsernamePasswordPnetDataApiLoginMethod(url, () -> new UsernamePasswordCredentials(username, password)),
            mapper, loggerAdapter);
    }

    public T withUsernamePassword(String url, Supplier<UsernamePasswordCredentials> usernamePasswordSupplier)
    {
        return copy(new UsernamePasswordPnetDataApiLoginMethod(url, usernamePasswordSupplier), mapper, loggerAdapter);
    }

    public T withMapper(ObjectMapper mapper)
    {
        return copy(loginMethod, mapper, loggerAdapter);
    }

    public T loggingTo(RestLoggerAdapter loggerAdapter)
    {
        return copy(loginMethod, mapper, loggerAdapter);
    }

    public T loggingToSlf4J()
    {
        return loggingTo(new Slf4jRestLoggerAdapter());
    }

    public T loggingToSystemOut()
    {
        return loggingTo(new SystemRestLoggerAdapter());
    }

    public ObjectMapper getMapper()
    {
        return mapper;
    }

    public RestLoggerAdapter getLoggerAdapter()
    {
        return loggerAdapter;
    }

    public RestCallFactory getRestCallFactory()
    {
        return restCallFactory;
    }

    public PnetDataApiContext getContext()
    {
        return context;
    }

    @Override
    public synchronized AboutDataClient getAboutDataClient()
    {
        if (aboutDataClient == null)
        {
            aboutDataClient = new AboutDataClient(context);
        }

        return aboutDataClient;
    }

    @Override
    public synchronized ActivityDataClient getActivityDataClient()
    {
        if (activityDataClient == null)
        {
            activityDataClient = new ActivityDataClient(context);
        }

        return activityDataClient;
    }

    @Override
    public synchronized AdvisorTypeDataClient getAdvisorTypeDataClient()
    {
        if (advisorTypeDataClient == null)
        {
            advisorTypeDataClient = new AdvisorTypeDataClient(context);
        }

        return advisorTypeDataClient;
    }

    @Override
    public synchronized ApplicationDataClient getApplicationDataClient()
    {
        if (applicationDataClient == null)
        {
            applicationDataClient = new ApplicationDataClient(context);
        }

        return applicationDataClient;
    }

    @Override
    public synchronized BrandDataClient getBrandDataClient()
    {
        if (brandDataClient == null)
        {
            brandDataClient = new BrandDataClient(context);
        }

        return brandDataClient;
    }

    @Override
    public synchronized CompanyDataClient getCompanyDataClient()
    {
        if (companyDataClient == null)
        {
            companyDataClient = new CompanyDataClient(context);
        }

        return companyDataClient;
    }

    @Override
    public synchronized CompanyGroupDataClient getCompanyGroupDataClient()
    {
        if (companyGroupDataClient == null)
        {
            companyGroupDataClient = new CompanyGroupDataClient(context);
        }

        return companyGroupDataClient;
    }

    @Override
    public synchronized CompanyGroupTypeDataClient getCompanyGroupTypeDataClient()
    {
        if (companyGroupTypeDataClient == null)
        {
            companyGroupTypeDataClient = new CompanyGroupTypeDataClient(context);
        }

        return companyGroupTypeDataClient;
    }

    @Override
    public synchronized CompanyNumberTypeDataClient getCompanyNumberTypeDataClient()
    {
        if (companyNumberTypeDataClient == null)
        {
            companyNumberTypeDataClient = new CompanyNumberTypeDataClient(context);
        }

        return companyNumberTypeDataClient;
    }

    @Override
    public synchronized CompanyTypeDataClient getCompanyTypeDataClient()
    {
        if (companyTypeDataClient == null)
        {
            companyTypeDataClient = new CompanyTypeDataClient(context);
        }

        return companyTypeDataClient;
    }

    @Override
    public synchronized ContractStateDataClient getContractStateDataClient()
    {
        if (contractStateDataClient == null)
        {
            contractStateDataClient = new ContractStateDataClient(context);
        }

        return contractStateDataClient;
    }

    @Override
    public synchronized ContractTypeDataClient getContractTypeDataClient()
    {
        if (contractTypeDataClient == null)
        {
            contractTypeDataClient = new ContractTypeDataClient(context);
        }

        return contractTypeDataClient;
    }

    @Override
    public synchronized ExternalBrandDataClient getExternalBrandDataClient()
    {
        if (externalBrandDataClient == null)
        {
            externalBrandDataClient = new ExternalBrandDataClient(context);
        }

        return externalBrandDataClient;
    }

    @Override
    public synchronized FunctionDataClient getFunctionDataClient()
    {
        if (functionDataClient == null)
        {
            functionDataClient = new FunctionDataClient(context);
        }

        return functionDataClient;
    }

    @Override
    public synchronized LegalFormDataClient getLegalFormDataClient()
    {
        if (legalFormDataClient == null)
        {
            legalFormDataClient = new LegalFormDataClient(context);
        }

        return legalFormDataClient;
    }

    @Override
    public synchronized NumberTypeDataClient getNumberTypeDataClient()
    {
        if (numberTypeDataClient == null)
        {
            numberTypeDataClient = new NumberTypeDataClient(context);
        }

        return numberTypeDataClient;
    }

    @Override
    public synchronized PersonDataClient getPersonDataClient()
    {
        if (personDataClient == null)
        {
            personDataClient = new PersonDataClient(context);
        }

        return personDataClient;
    }
}
