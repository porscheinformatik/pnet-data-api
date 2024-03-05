package pnet.data.api.java;

import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.context.AuthenticationTokenPnetDataApiLoginMethod;
import pnet.data.api.client.context.UsernamePasswordCredentials;
import pnet.data.api.client.context.UsernamePasswordPnetDataApiLoginMethod;

/**
 * A template for a simple query using the {@link JavaClientFactory}. You can start it by either providing one argument
 * containing your authentication token or use two arguments containing username and password.
 *
 * @author KRC
 * @author HAM
 */
public final class PnetJavaRestClientTemplate
{
    private PnetJavaRestClientTemplate()
    {
        super();
    }

    /**
     * @param args API user name and password.
     * @throws PnetDataClientException in case of errors.
     */
    public static void main(String[] args) throws PnetDataClientException
    {
        String url = "https://qa-data.auto-partner.net/data";
        JavaClientFactory clientFactory;

        if (args.length == 1)
        {
            String token = args[0];
            AuthenticationTokenPnetDataApiLoginMethod loginMethod =
                new AuthenticationTokenPnetDataApiLoginMethod(url, () -> token);

            clientFactory = JavaClientFactory.of(loginMethod);
        }
        else if (args.length == 2)
        {
            String username = args[0];
            String password = args[1];
            UsernamePasswordPnetDataApiLoginMethod loginMethod = new UsernamePasswordPnetDataApiLoginMethod(url,
                () -> new UsernamePasswordCredentials(username, password));

            clientFactory = JavaClientFactory.of(loginMethod);
        }
        else
        {
            System.out.println(
                "Usage: java " + PnetJavaRestClientTemplate.class.getName() + " <TOKEN> | (<USERNAME> <PASSWORD>)");
            System.exit(-1);
            return;
        }

        clientFactory
            .getCompanyDataClient()
            .find()
            .type("CT_DEAL")
            .tenant("AT")
            .executeAndScroll(Locale.getDefault(), 25)
            .streamAll()
            .forEach(company -> System.out.println(company.getLabelWithNumber()));
    }
}
