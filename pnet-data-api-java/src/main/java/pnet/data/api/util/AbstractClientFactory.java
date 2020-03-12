package pnet.data.api.util;

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
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientPrefs;
import pnet.data.api.client.context.PnetDataApiTokenRepository;
import pnet.data.api.client.context.PrefsBasedPnetDataApiContext;
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
import pnet.data.api.proposal.ProposalDataClient;
import pnet.data.api.todo.TodoGroupDataClient;

/**
 * A factory for clients if you are working without Spring.
 *
 * @author HAM
 * @param <T> the type itself
 */
public abstract class AbstractClientFactory<T extends AbstractClientFactory<T>>
{
    private final PnetDataClientPrefs prefs;
    private final ObjectMapper mapper;
    private final RestLoggerAdapter loggerAdapter;
    private final RestCallFactory restCallFactory;

    private final PnetDataApiTokenRepository repository;
    private final PrefsBasedPnetDataApiContext context;

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
    private ProposalDataClient proposalDataClient = null;
    private TodoGroupDataClient todoGroupDataClient = null;

    public AbstractClientFactory(PnetDataClientPrefs prefs, ObjectMapper mapper, RestLoggerAdapter loggerAdapter)
    {
        super();

        this.prefs = prefs;
        this.mapper = mapper;
        this.loggerAdapter = loggerAdapter;

        restCallFactory = createRestCallFactory(mapper, loggerAdapter);
        repository = new PnetDataApiTokenRepository(restCallFactory);
        context = new PrefsBasedPnetDataApiContext(repository, prefs);
    }

    protected abstract RestCallFactory createRestCallFactory(ObjectMapper mapper, RestLoggerAdapter loggerAdapter);

    protected abstract T copy(PnetDataClientPrefs prefs, ObjectMapper mapper, RestLoggerAdapter loggerAdapter);

    public T withPrefs(PnetDataClientPrefs prefs)
    {
        return copy(prefs, mapper, loggerAdapter);
    }

    public T withPrefs(String url, String username, String password)
    {
        return withPrefs(new MutablePnetDataClientPrefs(url, username, password));
    }

    public T withMapper(ObjectMapper mapper)
    {
        return copy(prefs, mapper, loggerAdapter);
    }

    public T loggingTo(RestLoggerAdapter loggerAdapter)
    {
        return copy(prefs, mapper, loggerAdapter);
    }

    public T loggingToSlf4J()
    {
        return loggingTo(new Slf4jRestLoggerAdapter());
    }

    public T loggingToSystemOut()
    {
        return loggingTo(new SystemRestLoggerAdapter());
    }

    public PnetDataClientPrefs getPrefs()
    {
        return prefs;
    }

    public ObjectMapper getMapper()
    {
        return mapper;
    }

    public RestLoggerAdapter getLoggerAdapter()
    {
        return loggerAdapter;
    }

    public PnetDataApiTokenRepository getRepository()
    {
        return repository;
    }

    public PrefsBasedPnetDataApiContext getContext()
    {
        return context;
    }

    public synchronized AboutDataClient getAboutDataClient()
    {
        if (aboutDataClient == null)
        {
            aboutDataClient = new AboutDataClient(context);
        }

        return aboutDataClient;
    }

    public synchronized ActivityDataClient getActivityDataClient()
    {
        if (activityDataClient == null)
        {
            activityDataClient = new ActivityDataClient(context);
        }

        return activityDataClient;
    }

    public synchronized AdvisorTypeDataClient getAdvisorTypeDataClient()
    {
        if (advisorTypeDataClient == null)
        {
            advisorTypeDataClient = new AdvisorTypeDataClient(context);
        }

        return advisorTypeDataClient;
    }

    public synchronized ApplicationDataClient getApplicationDataClient()
    {
        if (applicationDataClient == null)
        {
            applicationDataClient = new ApplicationDataClient(context);
        }

        return applicationDataClient;
    }

    public synchronized BrandDataClient getBrandDataClient()
    {
        if (brandDataClient == null)
        {
            brandDataClient = new BrandDataClient(context);
        }

        return brandDataClient;
    }

    public synchronized CompanyDataClient getCompanyDataClient()
    {
        if (companyDataClient == null)
        {
            companyDataClient = new CompanyDataClient(context);
        }

        return companyDataClient;
    }

    public synchronized CompanyGroupDataClient getCompanyGroupDataClient()
    {
        if (companyGroupDataClient == null)
        {
            companyGroupDataClient = new CompanyGroupDataClient(context);
        }

        return companyGroupDataClient;
    }

    public synchronized CompanyGroupTypeDataClient getCompanyGroupTypeDataClient()
    {
        if (companyGroupTypeDataClient == null)
        {
            companyGroupTypeDataClient = new CompanyGroupTypeDataClient(context);
        }

        return companyGroupTypeDataClient;
    }

    public synchronized CompanyNumberTypeDataClient getCompanyNumberTypeDataClient()
    {
        if (companyNumberTypeDataClient == null)
        {
            companyNumberTypeDataClient = new CompanyNumberTypeDataClient(context);
        }

        return companyNumberTypeDataClient;
    }

    public synchronized CompanyTypeDataClient getCompanyTypeDataClient()
    {
        if (companyTypeDataClient == null)
        {
            companyTypeDataClient = new CompanyTypeDataClient(context);
        }

        return companyTypeDataClient;
    }

    public synchronized ContractStateDataClient getContractStateDataClient()
    {
        if (contractStateDataClient == null)
        {
            contractStateDataClient = new ContractStateDataClient(context);
        }

        return contractStateDataClient;
    }

    public synchronized ContractTypeDataClient getContractTypeDataClient()
    {
        if (contractTypeDataClient == null)
        {
            contractTypeDataClient = new ContractTypeDataClient(context);
        }

        return contractTypeDataClient;
    }

    public synchronized ExternalBrandDataClient getExternalBrandDataClient()
    {
        if (externalBrandDataClient == null)
        {
            externalBrandDataClient = new ExternalBrandDataClient(context);
        }

        return externalBrandDataClient;
    }

    public synchronized FunctionDataClient getFunctionDataClient()
    {
        if (functionDataClient == null)
        {
            functionDataClient = new FunctionDataClient(context);
        }

        return functionDataClient;
    }

    public synchronized LegalFormDataClient getLegalFormDataClient()
    {
        if (legalFormDataClient == null)
        {
            legalFormDataClient = new LegalFormDataClient(context);
        }

        return legalFormDataClient;
    }

    public synchronized NumberTypeDataClient getNumberTypeDataClient()
    {
        if (numberTypeDataClient == null)
        {
            numberTypeDataClient = new NumberTypeDataClient(context);
        }

        return numberTypeDataClient;
    }

    public synchronized PersonDataClient getPersonDataClient()
    {
        if (personDataClient == null)
        {
            personDataClient = new PersonDataClient(context);
        }

        return personDataClient;
    }

    public synchronized ProposalDataClient getProposalDataClient()
    {
        if (proposalDataClient == null)
        {
            proposalDataClient = new ProposalDataClient(context);
        }

        return proposalDataClient;
    }

    public synchronized TodoGroupDataClient getTodoGroupDataClient()
    {
        if (todoGroupDataClient == null)
        {
            todoGroupDataClient = new TodoGroupDataClient(context);
        }

        return todoGroupDataClient;
    }
}
