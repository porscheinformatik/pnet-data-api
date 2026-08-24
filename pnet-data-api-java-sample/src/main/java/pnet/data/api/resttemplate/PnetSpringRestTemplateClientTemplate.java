package pnet.data.api.resttemplate;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import java.util.Locale;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.context.*;
import pnet.data.api.company.CompanyDataClient;

/**
 * A template for a simple query using Spring. You can start it by either providing one argument containing your
 * authentication token, two arguments containing username and password, or three arguments containing IDP URL,
 * client ID, and client secret.
 *
 * @author KRC
 * @author HAM
 */
@EnableRestTemplateBasedPnetDataClient
public final class PnetSpringRestTemplateClientTemplate {

    private static PnetDataApiLoginMethod loginMethod = null;

    private PnetSpringRestTemplateClientTemplate() {
        super();
    }

    /**
     * @param args token, username/password, or IDP client credentials.
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
        } else if (args.length == 3) {
            String idpUrl = args[0];
            String clientId = args[1];
            String clientSecret = args[2];

            loginMethod = new IdpClientCredentialsPnetDataApiLoginMethod(url, idpUrl, () ->
                new IdpClientCredentials(clientId, clientSecret)
            );
        } else {
            System.out.println(
                "Usage: java " +
                    PnetSpringRestTemplateClientTemplate.class.getName() +
                    " <TOKEN> | (<USERNAME> <PASSWORD>) | (<IDP_URL> <CLIENT_ID> <CLIENT_SECRET>)"
            );
            System.exit(-1);
            return;
        }

        try (
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                PnetSpringRestTemplateClientTemplate.class
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
