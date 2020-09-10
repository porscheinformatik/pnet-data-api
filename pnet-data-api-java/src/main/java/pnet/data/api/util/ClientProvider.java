package pnet.data.api.util;

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
import pnet.data.api.todo.TodoGroupDataClient;

public interface ClientProvider
{

    AboutDataClient getAboutDataClient();

    ActivityDataClient getActivityDataClient();

    AdvisorTypeDataClient getAdvisorTypeDataClient();

    ApplicationDataClient getApplicationDataClient();

    BrandDataClient getBrandDataClient();

    CompanyDataClient getCompanyDataClient();

    CompanyGroupDataClient getCompanyGroupDataClient();

    CompanyGroupTypeDataClient getCompanyGroupTypeDataClient();

    CompanyNumberTypeDataClient getCompanyNumberTypeDataClient();

    CompanyTypeDataClient getCompanyTypeDataClient();

    ContractStateDataClient getContractStateDataClient();

    ContractTypeDataClient getContractTypeDataClient();

    ExternalBrandDataClient getExternalBrandDataClient();

    FunctionDataClient getFunctionDataClient();

    LegalFormDataClient getLegalFormDataClient();

    NumberTypeDataClient getNumberTypeDataClient();

    PersonDataClient getPersonDataClient();

    ProposalDataClient getProposalDataClient();

    TodoGroupDataClient getTodoGroupDataClient();

}