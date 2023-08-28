package pnet.data.api.client.context;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import pnet.data.api.about.AboutDataClient;
import pnet.data.api.activity.ActivityDataClient;
import pnet.data.api.advisortype.AdvisorTypeDataClient;
import pnet.data.api.application.ApplicationDataClient;
import pnet.data.api.brand.BrandDataClient;
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
import pnet.data.api.util.ClientProvider;

/**
 * A factory providing all types of client.
 *
 * @author HAM
 */
@Service
public class PnetDataApiClientProvider implements ClientProvider
{
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

    public PnetDataApiClientProvider(@Lazy AboutDataClient aboutDataClient, @Lazy ActivityDataClient activityDataClient,
        @Lazy AdvisorTypeDataClient advisorTypeDataClient, @Lazy ApplicationDataClient applicationDataClient,
        @Lazy BrandDataClient brandDataClient, @Lazy CompanyDataClient companyDataClient,
        @Lazy CompanyGroupDataClient companyGroupDataClient,
        @Lazy CompanyGroupTypeDataClient companyGroupTypeDataClient,
        @Lazy CompanyNumberTypeDataClient companyNumberTypeDataClient,
        @Lazy CompanyTypeDataClient companyTypeDataClient, @Lazy ContractStateDataClient contractStateDataClient,
        @Lazy ContractTypeDataClient contractTypeDataClient, @Lazy ExternalBrandDataClient externalBrandDataClient,
        @Lazy FunctionDataClient functionDataClient, @Lazy LegalFormDataClient legalFormDataClient,
        @Lazy NumberTypeDataClient numberTypeDataClient, @Lazy PersonDataClient personDataClient,
        @Lazy ProposalDataClient proposalDataClient)
    {
        super();
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
    }

    @Override
    public AboutDataClient getAboutDataClient()
    {
        return aboutDataClient;
    }

    @Override
    public ActivityDataClient getActivityDataClient()
    {
        return activityDataClient;
    }

    @Override
    public AdvisorTypeDataClient getAdvisorTypeDataClient()
    {
        return advisorTypeDataClient;
    }

    @Override
    public ApplicationDataClient getApplicationDataClient()
    {
        return applicationDataClient;
    }

    @Override
    public BrandDataClient getBrandDataClient()
    {
        return brandDataClient;
    }

    @Override
    public CompanyDataClient getCompanyDataClient()
    {
        return companyDataClient;
    }

    @Override
    public CompanyGroupDataClient getCompanyGroupDataClient()
    {
        return companyGroupDataClient;
    }

    @Override
    public CompanyGroupTypeDataClient getCompanyGroupTypeDataClient()
    {
        return companyGroupTypeDataClient;
    }

    @Override
    public CompanyNumberTypeDataClient getCompanyNumberTypeDataClient()
    {
        return companyNumberTypeDataClient;
    }

    @Override
    public CompanyTypeDataClient getCompanyTypeDataClient()
    {
        return companyTypeDataClient;
    }

    @Override
    public ContractStateDataClient getContractStateDataClient()
    {
        return contractStateDataClient;
    }

    @Override
    public ContractTypeDataClient getContractTypeDataClient()
    {
        return contractTypeDataClient;
    }

    @Override
    public ExternalBrandDataClient getExternalBrandDataClient()
    {
        return externalBrandDataClient;
    }

    @Override
    public FunctionDataClient getFunctionDataClient()
    {
        return functionDataClient;
    }

    @Override
    public LegalFormDataClient getLegalFormDataClient()
    {
        return legalFormDataClient;
    }

    @Override
    public NumberTypeDataClient getNumberTypeDataClient()
    {
        return numberTypeDataClient;
    }

    @Override
    public PersonDataClient getPersonDataClient()
    {
        return personDataClient;
    }

    @Override
    public ProposalDataClient getProposalDataClient()
    {
        return proposalDataClient;
    }
}
