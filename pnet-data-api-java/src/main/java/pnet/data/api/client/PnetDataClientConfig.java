package pnet.data.api.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.activity.ActivityPnetDataApiClientConfig;
import pnet.data.api.brand.BrandPnetDataApiClientConfig;
import pnet.data.api.client.context.ContextPnetDataApiClientConfig;
import pnet.data.api.company.CompanyPnetDataApiClientConfig;
import pnet.data.api.companytype.CompanyTypePnetDataApiClientConfig;
import pnet.data.api.externalbrand.ExternalBrandPnetDataApiClientConfig;
import pnet.data.api.function.FunctionPnetDataApiClientConfig;
import pnet.data.api.numbertype.NumberTypePnetDataApiClientConfig;

/**
 * Spring configuration for the PnetDataApiClient module
 */
@Configuration
@Import({
    ActivityPnetDataApiClientConfig.class,
    BrandPnetDataApiClientConfig.class,
    CompanyPnetDataApiClientConfig.class,
    CompanyTypePnetDataApiClientConfig.class,
    ContextPnetDataApiClientConfig.class,
    ExternalBrandPnetDataApiClientConfig.class,
    FunctionPnetDataApiClientConfig.class,
    NumberTypePnetDataApiClientConfig.class})
public class PnetDataClientConfig
{

    // intentionally left blank

}
