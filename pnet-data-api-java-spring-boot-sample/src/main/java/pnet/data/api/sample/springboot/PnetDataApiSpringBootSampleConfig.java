package pnet.data.api.sample.springboot;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pnet.data.api.client.EnablePnetDataClient;
import pnet.data.api.client.context.AuthenticationTokenPnetDataApiLoginMethod;

@Configuration
@EnablePnetDataClient
public class PnetDataApiSpringBootSampleConfig {

    @Bean
    public AuthenticationTokenPnetDataApiLoginMethod pnetDataClientPrefs(Environment environment) {
        String url = Objects.requireNonNull(
            environment.getProperty("pnet-data-api.url"),
            "The parameter \"pnet-data-api.url\" is missing"
        );

        String token = Objects.requireNonNull(
            environment.getProperty("pnet-data-api.token"),
            "The parameter \"pnet-data-api.token\" is missing"
        );

        return new AuthenticationTokenPnetDataApiLoginMethod(url, () -> token);
    }

    @Bean
    public RestLoggerAdapter restLoggerAdapter() {
        return SystemRestLoggerAdapter.INSTANCE;
    }
}
