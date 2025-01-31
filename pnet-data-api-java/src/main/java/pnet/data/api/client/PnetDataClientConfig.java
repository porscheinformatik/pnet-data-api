package pnet.data.api.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.about.AboutPnetDataApiClientConfig;
import pnet.data.api.activity.ActivityPnetDataApiClientConfig;
import pnet.data.api.advisortype.AdvisorTypePnetDataApiClientConfig;
import pnet.data.api.application.ApplicationPnetDataApiClientConfig;
import pnet.data.api.brand.BrandPnetDataApiClientConfig;
import pnet.data.api.client.context.ContextPnetDataApiClientConfig;
import pnet.data.api.company.CompanyPnetDataApiClientConfig;
import pnet.data.api.companygroup.CompanyGroupPnetDataApiClientConfig;
import pnet.data.api.companygrouptype.CompanyGroupTypePnetDataApiClientConfig;
import pnet.data.api.companynumbertype.CompanyNumberTypePnetDataApiClientConfig;
import pnet.data.api.companytype.CompanyTypePnetDataApiClientConfig;
import pnet.data.api.contractstate.ContractStatePnetDataApiClientConfig;
import pnet.data.api.contracttype.ContractTypePnetDataApiClientConfig;
import pnet.data.api.externalbrand.ExternalBrandPnetDataApiClientConfig;
import pnet.data.api.function.FunctionPnetDataApiClientConfig;
import pnet.data.api.legalform.LegalFormPnetDataApiClientConfig;
import pnet.data.api.numbertype.NumberTypePnetDataApiClientConfig;
import pnet.data.api.person.PersonPnetDataApiClientConfig;

/**
 * Spring configuration for the PnetDataApiClient module
 */
@Configuration
@Import({
    ContextPnetDataApiClientConfig.class,
    AboutPnetDataApiClientConfig.class,
    ActivityPnetDataApiClientConfig.class,
    AdvisorTypePnetDataApiClientConfig.class,
    ApplicationPnetDataApiClientConfig.class,
    BrandPnetDataApiClientConfig.class,
    CompanyPnetDataApiClientConfig.class,
    CompanyGroupPnetDataApiClientConfig.class,
    CompanyGroupTypePnetDataApiClientConfig.class,
    CompanyNumberTypePnetDataApiClientConfig.class,
    CompanyTypePnetDataApiClientConfig.class,
    ContractStatePnetDataApiClientConfig.class,
    ContractTypePnetDataApiClientConfig.class,
    ExternalBrandPnetDataApiClientConfig.class,
    LegalFormPnetDataApiClientConfig.class,
    FunctionPnetDataApiClientConfig.class,
    NumberTypePnetDataApiClientConfig.class,
    PersonPnetDataApiClientConfig.class})
public class PnetDataClientConfig
{

    // intentionally left blank

}
