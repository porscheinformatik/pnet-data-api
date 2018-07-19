package pnet.data.api.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientConfig;

/**
 * Configuration for the PnetRestClient.
 *
 * @author ham
 */
@Configuration
@Import(PnetDataClientConfig.class)
@ComponentScan(basePackageClasses = {PnetSpringRestClientConfig.class})
public class PnetSpringRestClientConfig
{

    /**
     * @return the default preferences for connecting to the Partner.Net DATA API.
     */
    @Bean
    public MutablePnetDataClientPrefs pnetDataClientPrefs()
    {
        return new MutablePnetDataClientPrefs("https://localhost:4443/data", "pnetsample", "pnetsample");
    }
}
