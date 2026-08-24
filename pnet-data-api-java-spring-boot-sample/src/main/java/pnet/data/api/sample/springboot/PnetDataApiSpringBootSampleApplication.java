package pnet.data.api.sample.springboot;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import pnet.data.api.client.context.*;
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

    @Value("${pnet-data-api.login-method:idp-client}")
    private String loginMethod;

    @Value("${pnet-data-api.token:}")
    private String token;

    @Value("${pnet-data-api.username:}")
    private String username;

    @Value("${pnet-data-api.password:}")
    private String password;

    @Value("${pnet-data-api.idp-url:}")
    private String idpUrl;

    @Value("${pnet-data-api.client-id:}")
    private String clientId;

    @Value("${pnet-data-api.client-secret:}")
    private String clientSecret;

    @Bean
    public RestLoggerAdapter restLoggerAdapter() {
        return SystemRestLoggerAdapter.INSTANCE;
    }

    @Bean
    public PnetDataApiLoginMethod pnetDataApiLoginMethod() {
        return switch (loginMethod.toLowerCase(Locale.ROOT)) {
            case "token" -> new AuthenticationTokenPnetDataApiLoginMethod(url, () -> token);
            case "username-password" -> new UsernamePasswordPnetDataApiLoginMethod(url, () ->
                new UsernamePasswordCredentials(username, password)
            );
            case "idp-client" -> new IdpClientCredentialsPnetDataApiLoginMethod(url, idpUrl, () ->
                new IdpClientCredentials(clientId, clientSecret)
            );
            default -> throw new IllegalArgumentException(
                "Unsupported pnet-data-api.login-method: %s".formatted(loginMethod)
            );
        };
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        LOGGER.info("Application started!\n\nOpen http://localhost:8080 in your browser.\n");
    }
}
