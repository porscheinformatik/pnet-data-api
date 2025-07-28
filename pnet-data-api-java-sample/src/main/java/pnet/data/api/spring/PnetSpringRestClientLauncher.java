package pnet.data.api.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pnet.data.api.PnetRestClient;

/**
 * The main class for the sample.
 *
 * @author ham
 */
public final class PnetSpringRestClientLauncher {

    private PnetSpringRestClientLauncher() {
        super();
    }

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
