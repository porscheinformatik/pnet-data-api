package pnet.data.api.resttemplate;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pnet.data.api.PnetRestClient;
import pnet.data.api.util.MutablePnetDataApiLoginMethod;
import pnet.data.api.util.Prefs;

/**
 * The main class for the sample.
 *
 * @author ham
 */
@Configuration
@EnableRestTemplateBasedPnetDataClient
@Import({ PnetRestClient.class })
public class PnetSpringRestTemplateClientLauncher {

    @Bean
    public MutablePnetDataApiLoginMethod pnetDataClientPrefs() {
        return MutablePnetDataApiLoginMethod.createFromPrefs(Prefs.DEFAULT_KEY);
    }

    @Bean
    public RestLoggerAdapter restLoggerAdapter() {
        return SystemRestLoggerAdapter.INSTANCE;
    }

    public static void main(String[] args) {
        try (
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                PnetSpringRestTemplateClientLauncher.class
            );
        ) {
            context.getBean(PnetRestClient.class).consume();
        }
    }
}
