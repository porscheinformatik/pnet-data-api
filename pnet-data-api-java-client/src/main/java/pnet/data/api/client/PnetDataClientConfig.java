package pnet.data.api.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.companytype.CompanyTypePnetDataApiClientConfig;
import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Spring configuration for the PnetDataApiClient module
 */
@Configuration
@Import({CompanyTypePnetDataApiClientConfig.class, ContextPnetDataApiClientConfig.class})
public class PnetDataClientConfig
{

    // intentionally left blank

}
