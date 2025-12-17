package pnet.data.api.sample.springboot;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import pnet.data.api.client.context.AuthenticationTokenPnetDataApiLoginMethod;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.resttemplate.EnableRestTemplateBasedPnetDataClient;

@SpringBootApplication
// @EnableApacheHttpClientBasedPnetDataClient
// @EnableApache5HttpClientBasedPnetDataClient
// @EnableJavaBasedPnetDataClient
@EnableRestTemplateBasedPnetDataClient
// @EnableWebClientBasedPnetDataClient
public class PnetDataApiSpringBootSampleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PnetDataApiSpringBootSampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PnetDataApiSpringBootSampleApplication.class, args);
    }

    @Value("${pnet-data-api.url:https://qa-data.auto-partner.net/data}")
    private String url;

    @Value("${pnet-data-api.token}")
    private String token;

    @Bean
    public RestLoggerAdapter restLoggerAdapter() {
        return SystemRestLoggerAdapter.INSTANCE;
    }

    @Bean
    public PnetDataApiLoginMethod pnetDataApiLoginMethod() {
        return new AuthenticationTokenPnetDataApiLoginMethod(url, () -> token);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        LOGGER.info("Application started!\n\nOpen http://localhost:8080 in your browser.\n");
    }
}
