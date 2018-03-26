package pnet.data.api.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.activity.ActivityPnetDataApiClientConfig;
import pnet.data.api.brand.BrandPnetDataApiClientConfig;
import pnet.data.api.client.context.ContextPnetDataApiClientConfig;
import pnet.data.api.companytype.CompanyTypePnetDataApiClientConfig;
import pnet.data.api.function.FunctionPnetDataApiClientConfig;

/**
 * Spring configuration for the PnetDataApiClient module
 */
@Configuration
@Import({
    ActivityPnetDataApiClientConfig.class,
    BrandPnetDataApiClientConfig.class,
    CompanyTypePnetDataApiClientConfig.class,
    ContextPnetDataApiClientConfig.class,
    FunctionPnetDataApiClientConfig.class})
public class PnetDataClientConfig
{

    // intentionally left blank

}
