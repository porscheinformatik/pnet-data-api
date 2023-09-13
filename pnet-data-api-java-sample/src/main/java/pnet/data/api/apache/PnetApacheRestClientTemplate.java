package pnet.data.api.apache;

import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.context.AuthenticationTokenPnetDataApiLoginMethod;
import pnet.data.api.client.context.UsernamePasswordCredentials;
import pnet.data.api.client.context.UsernamePasswordPnetDataApiLoginMethod;

/**
 * A template for a simple query using the {@link ApacheClientFactory}. You can start it by either providing one
 * argument containing your authentication token or use two arguments containing username and password.
 *
 * @author KRC
 * @author HAM
 */
public final class PnetApacheRestClientTemplate
{
    private PnetApacheRestClientTemplate()
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
        ApacheClientFactory clientFactory;

        if (args.length == 1)
        {
            String token = args[0];
            AuthenticationTokenPnetDataApiLoginMethod loginMethod =
                new AuthenticationTokenPnetDataApiLoginMethod(url, () -> token);

            clientFactory = ApacheClientFactory.of(loginMethod);
        }
        else if (args.length == 2)
        {
            String username = args[0];
            String password = args[1];
            UsernamePasswordPnetDataApiLoginMethod loginMethod = new UsernamePasswordPnetDataApiLoginMethod(url,
                () -> new UsernamePasswordCredentials(username, password));

            clientFactory = ApacheClientFactory.of(loginMethod);
        }
        else
        {
            System.out
                .println("Usage: java "
                    + PnetApacheRestClientTemplate.class.getName()
                    + " <TOKEN> | (<USERNAME> <PASSWORD>)");
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
