package pnet.data.api.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import pnet.data.api.PnetRestClient;
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientConfig;
import pnet.data.api.util.Prefs;

/**
 * Configuration for the PnetRestClient.
 *
 * @author ham
 */
@Configuration
@Import({PnetDataClientConfig.class, PnetRestClient.class})
@ComponentScan(basePackageClasses = {PnetSpringRestClientConfig.class})
public class PnetSpringRestClientConfig
{

    /**
     * @return the default preferences for connecting to the Partner.Net DATA API.
     */
    @Bean
    public MutablePnetDataClientPrefs pnetDataClientPrefs()
    {
        String url = Prefs.getUrl(Prefs.DEFAULT_KEY);
        String username = Prefs.getUsername(Prefs.DEFAULT_KEY);
        String password = Prefs.getPassword(Prefs.DEFAULT_KEY);

        return new MutablePnetDataClientPrefs(url, username, password);
    }

    @Bean
    public RestLoggerAdapter restLoggerAdapter()
    {
        return SystemRestLoggerAdapter.INSTANCE;
    }
}
