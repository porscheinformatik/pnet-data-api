package pnet.data.api.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pnet.data.api.about.AboutPnetDataApiClientConfig;
import pnet.data.api.activity.ActivityPnetDataApiClientConfig;
import pnet.data.api.advisortype.AdvisorTypePnetDataApiClientConfig;
import pnet.data.api.application.ApplicationPnetDataApiClientConfig;
import pnet.data.api.brand.BrandPnetDataApiClientConfig;
import pnet.data.api.client.context.PnetDataApiClientContextConfig;
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
import pnet.data.api.resource.ResourcePnetDataApiClientConfig;

/**
 * Spring configuration for the PnetDataApiClient module.
 */
@Configuration
@Import(
    {
        AboutPnetDataApiClientConfig.class,
        ActivityPnetDataApiClientConfig.class,
        AdvisorTypePnetDataApiClientConfig.class,
        ApplicationPnetDataApiClientConfig.class,
        BrandPnetDataApiClientConfig.class,
        CompanyGroupPnetDataApiClientConfig.class,
        CompanyGroupTypePnetDataApiClientConfig.class,
        CompanyNumberTypePnetDataApiClientConfig.class,
        CompanyPnetDataApiClientConfig.class,
        CompanyTypePnetDataApiClientConfig.class,
        ContractStatePnetDataApiClientConfig.class,
        ContractTypePnetDataApiClientConfig.class,
        ExternalBrandPnetDataApiClientConfig.class,
        FunctionPnetDataApiClientConfig.class,
        LegalFormPnetDataApiClientConfig.class,
        NumberTypePnetDataApiClientConfig.class,
        PersonPnetDataApiClientConfig.class,
        ResourcePnetDataApiClientConfig.class,
        PnetDataApiClientContextConfig.class,
    }
)
public class PnetDataClientConfig {
    // intentionally left blank
}
