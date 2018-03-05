package pnet.data.api.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.activity.ActivityPnetDataApiClientConfig;
import pnet.data.api.client.context.ContextPnetDataApiClientConfig;
import pnet.data.api.companytype.CompanyTypePnetDataApiClientConfig;

/**
 * Spring configuration for the PnetDataApiClient module
 */
@Configuration
@Import({
    ActivityPnetDataApiClientConfig.class,
    CompanyTypePnetDataApiClientConfig.class,
    ContextPnetDataApiClientConfig.class})
public class PnetDataClientConfig
{

    // intentionally left blank

}
