package pnet.data.api;

import java.util.Locale;

import pnet.data.api.java.JavaClientFactory;

/**
 * A template for a simple query using the {@link JavaClientFactory}.
 *
 * @author HAM
 */
public final class PnetRestClientTemplate
{

    private PnetRestClientTemplate()
    {
        super();
    }

    /**
     * @param args API user name and password.
     * @throws PnetDataClientException in case of errors.
     */
    public static void main(String[] args) throws PnetDataClientException
    {
        if (args.length != 2)
        {
            System.out.println("Usage: java pnet.data.api.PnetRestClientTemplate <USERNAME> <PASSWORD>");
            System.exit(-1);
        }

        JavaClientFactory clientFactory = JavaClientFactory.of("https://data.auto-partner.net/data", args[0], args[1]);

        clientFactory
            .getCompanyDataClient()
            .find()
            .type("CT_DEAL")
            .tenant("AT")
            .scroll()
            .execute(Locale.getDefault(), 0, 25)
            .streamAll()
            .forEach(company -> System.out.println(company.getLabelWithNumber()));
    }
}
