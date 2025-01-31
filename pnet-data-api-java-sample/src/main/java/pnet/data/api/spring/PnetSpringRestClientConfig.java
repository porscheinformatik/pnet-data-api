package pnet.data.api.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import pnet.data.api.PnetRestClient;
import pnet.data.api.client.EnablePnetDataClient;
import pnet.data.api.util.MutablePnetDataApiLoginMethod;
import pnet.data.api.util.Prefs;

/**
 * Configuration for the PnetRestClient.
 *
 * @author ham
 */
@Configuration
@EnablePnetDataClient
@Import({PnetRestClient.class})
@ComponentScan(basePackageClasses = {PnetSpringRestClientConfig.class})
public class PnetSpringRestClientConfig
{
    @Bean
    public MutablePnetDataApiLoginMethod pnetDataClientPrefs()
    {
        return MutablePnetDataApiLoginMethod.createFromPrefs(Prefs.DEFAULT_KEY);
    }

    @Bean
    public RestLoggerAdapter restLoggerAdapter()
    {
        return SystemRestLoggerAdapter.INSTANCE;
    }
}
