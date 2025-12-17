package pnet.data.api.spring;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import java.util.Locale;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.context.AuthenticationTokenPnetDataApiLoginMethod;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.client.context.UsernamePasswordCredentials;
import pnet.data.api.client.context.UsernamePasswordPnetDataApiLoginMethod;
import pnet.data.api.company.CompanyDataClient;
import pnet.data.api.resttemplate.EnableRestTemplateBasedPnetDataClient;

/**
 * A template for a simple query using Spring. You can start it by either providing one argument containing your
 * authentication token or use two arguments containing username and password.
 *
 * @deprecated use specific WebClient or RestTemplate based template instead
 * @author KRC
 * @author HAM
 */
@EnableRestTemplateBasedPnetDataClient
public final class PnetSpringRestClientTemplate {

    private static PnetDataApiLoginMethod loginMethod = null;

    private PnetSpringRestClientTemplate() {
        super();
    }

    /**
     * @param args API user name and password.
     * @throws PnetDataClientException in case of errors.
     */
    public static void main(String[] args) throws PnetDataClientException {
        String url = "https://qa-data.auto-partner.net/data";

        if (args.length == 1) {
            String token = args[0];

            loginMethod = new AuthenticationTokenPnetDataApiLoginMethod(url, () -> token);
        } else if (args.length == 2) {
            String username = args[0];
            String password = args[1];

            loginMethod = new UsernamePasswordPnetDataApiLoginMethod(url, () ->
                new UsernamePasswordCredentials(username, password)
            );
        } else {
            System.out.println(
                "Usage: java " + PnetSpringRestClientTemplate.class.getName() + " <TOKEN> | (<USERNAME> <PASSWORD>)"
            );
            System.exit(-1);
            return;
        }

        try (
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                PnetSpringRestClientTemplate.class
            );
        ) {
            context
                .getBean(CompanyDataClient.class)
                .find()
                .type("CT_DEAL")
                .tenant("AT")
                .executeAndScroll(Locale.getDefault(), 25)
                .streamAll()
                .forEach(company -> System.out.println(company.getLabelWithNumber()));
        }
    }

    @Bean
    public PnetDataApiLoginMethod loginMethod() {
        return loginMethod;
    }

    @Bean
    public RestLoggerAdapter restLoggerAdapter() {
        return SystemRestLoggerAdapter.INSTANCE;
    }
}
