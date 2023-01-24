package pnet.data.api.sample.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import pnet.data.api.client.PnetDataClientConfig;
import pnet.data.api.client.context.AuthenticationTokenPnetDataApiLoginMethod;

@Configuration
@Import({PnetDataClientConfig.class})
public class PnetDataApiSpringBootSampleConfig
{
    @Bean
    public AuthenticationTokenPnetDataApiLoginMethod pnetDataClientPrefs(Environment environment)
    {
        String url = environment.getProperty("pnet-data-api.url");
        String token = environment.getProperty("pnet-data-api.token");

        return new AuthenticationTokenPnetDataApiLoginMethod(url, () -> token);
    }

    @Bean
    public RestLoggerAdapter restLoggerAdapter()
    {
        return SystemRestLoggerAdapter.INSTANCE;
    }
}
