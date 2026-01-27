package pnet.data.api.util;

import pnet.data.api.about.AboutDataClient;
import pnet.data.api.activity.ActivityDataClient;
import pnet.data.api.advisortype.AdvisorTypeDataClient;
import pnet.data.api.application.ApplicationDataClient;
import pnet.data.api.brand.BrandDataClient;
import pnet.data.api.client.context.PnetDataApiContext;
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
import pnet.data.api.resource.ResourceDataClient;

public abstract class AbstractClientProvider implements ClientProvider {

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
    private ResourceDataClient resourceDataClient = null;

    protected abstract PnetDataApiContext getContext();

    @Override
    public synchronized AboutDataClient getAboutDataClient() {
        if (aboutDataClient == null) {
            aboutDataClient = new AboutDataClient(getContext());
        }

        return aboutDataClient;
    }

    @Override
    public synchronized ActivityDataClient getActivityDataClient() {
        if (activityDataClient == null) {
            activityDataClient = new ActivityDataClient(getContext());
        }

        return activityDataClient;
    }

    @Override
    public synchronized AdvisorTypeDataClient getAdvisorTypeDataClient() {
        if (advisorTypeDataClient == null) {
            advisorTypeDataClient = new AdvisorTypeDataClient(getContext());
        }

        return advisorTypeDataClient;
    }

    @Override
    public synchronized ApplicationDataClient getApplicationDataClient() {
        if (applicationDataClient == null) {
            applicationDataClient = new ApplicationDataClient(getContext());
        }

        return applicationDataClient;
    }

    @Override
    public synchronized BrandDataClient getBrandDataClient() {
        if (brandDataClient == null) {
            brandDataClient = new BrandDataClient(getContext());
        }

        return brandDataClient;
    }

    @Override
    public synchronized CompanyDataClient getCompanyDataClient() {
        if (companyDataClient == null) {
            companyDataClient = new CompanyDataClient(getContext());
        }

        return companyDataClient;
    }

    @Override
    public synchronized CompanyGroupDataClient getCompanyGroupDataClient() {
        if (companyGroupDataClient == null) {
            companyGroupDataClient = new CompanyGroupDataClient(getContext());
        }

        return companyGroupDataClient;
    }

    @Override
    public synchronized CompanyGroupTypeDataClient getCompanyGroupTypeDataClient() {
        if (companyGroupTypeDataClient == null) {
            companyGroupTypeDataClient = new CompanyGroupTypeDataClient(getContext());
        }

        return companyGroupTypeDataClient;
    }

    @Override
    public synchronized CompanyNumberTypeDataClient getCompanyNumberTypeDataClient() {
        if (companyNumberTypeDataClient == null) {
            companyNumberTypeDataClient = new CompanyNumberTypeDataClient(getContext());
        }

        return companyNumberTypeDataClient;
    }

    @Override
    public synchronized CompanyTypeDataClient getCompanyTypeDataClient() {
        if (companyTypeDataClient == null) {
            companyTypeDataClient = new CompanyTypeDataClient(getContext());
        }

        return companyTypeDataClient;
    }

    @Override
    public synchronized ContractStateDataClient getContractStateDataClient() {
        if (contractStateDataClient == null) {
            contractStateDataClient = new ContractStateDataClient(getContext());
        }

        return contractStateDataClient;
    }

    @Override
    public synchronized ContractTypeDataClient getContractTypeDataClient() {
        if (contractTypeDataClient == null) {
            contractTypeDataClient = new ContractTypeDataClient(getContext());
        }

        return contractTypeDataClient;
    }

    @Override
    public synchronized ExternalBrandDataClient getExternalBrandDataClient() {
        if (externalBrandDataClient == null) {
            externalBrandDataClient = new ExternalBrandDataClient(getContext());
        }

        return externalBrandDataClient;
    }

    @Override
    public synchronized FunctionDataClient getFunctionDataClient() {
        if (functionDataClient == null) {
            functionDataClient = new FunctionDataClient(getContext());
        }

        return functionDataClient;
    }

    @Override
    public synchronized LegalFormDataClient getLegalFormDataClient() {
        if (legalFormDataClient == null) {
            legalFormDataClient = new LegalFormDataClient(getContext());
        }

        return legalFormDataClient;
    }

    @Override
    public synchronized NumberTypeDataClient getNumberTypeDataClient() {
        if (numberTypeDataClient == null) {
            numberTypeDataClient = new NumberTypeDataClient(getContext());
        }

        return numberTypeDataClient;
    }

    @Override
    public synchronized PersonDataClient getPersonDataClient() {
        if (personDataClient == null) {
            personDataClient = new PersonDataClient(getContext());
        }

        return personDataClient;
    }

    @Override
    public synchronized ResourceDataClient getResourceDataClient() {
        if(resourceDataClient == null) {
            resourceDataClient = new ResourceDataClient(getContext());
        }

        return resourceDataClient;
    }
}