package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCallFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PnetDataApiClientContextConfig {

    @Bean
    public PnetDataApiContext pnetDataApiContext(RestCallFactory restCallFactory, PnetDataApiLoginMethod loginMethod) {
        return new SimplePnetDataApiContext(restCallFactory, loginMethod);
    }
}
