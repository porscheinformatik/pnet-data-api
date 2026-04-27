package pnet.data.api.spring;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pnet.data.api.PnetRestClient;
import pnet.data.api.resttemplate.EnableRestTemplateBasedPnetDataClient;
import pnet.data.api.util.MutablePnetDataApiLoginMethod;
import pnet.data.api.util.Prefs;

/**
 * Configuration for the PnetRestClient.
 *
 * @deprecated use specific WebClient or RestTemplate based configuration instead
 * @author ham
 */
@Configuration
@EnableRestTemplateBasedPnetDataClient
@Import({ PnetRestClient.class })
@ComponentScan(basePackageClasses = { PnetSpringRestClientConfig.class })
@Deprecated(since = "2.13.x", forRemoval = true)
public class PnetSpringRestClientConfig {

    @Bean
    public MutablePnetDataApiLoginMethod pnetDataClientPrefs() {
        return MutablePnetDataApiLoginMethod.createFromPrefs(Prefs.DEFAULT_KEY);
    }

    @Bean
    public RestLoggerAdapter restLoggerAdapter() {
        return SystemRestLoggerAdapter.INSTANCE;
    }
}
