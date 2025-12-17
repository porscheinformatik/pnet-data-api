package pnet.data.api.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pnet.data.api.PnetRestClient;

/**
 * The main class for the sample.
 *
 * @deprecated use specific WebClient or RestTemplate based launcher instead
 * @author ham
 */
@Deprecated(forRemoval = true)
public final class PnetSpringRestClientLauncher {

    private PnetSpringRestClientLauncher() {}

    @Deprecated
    public static void main(String[] args) {
        try (
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                PnetSpringRestClientConfig.class
            );
        ) {
            context.getBean(PnetRestClient.class).consume();
        }
    }
}
