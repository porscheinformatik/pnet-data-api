package pnet.data.api.apache;

import pnet.data.api.PnetRestClient;
import pnet.data.api.util.MutablePnetDataApiLoginMethod;
import pnet.data.api.util.Prefs;

/**
 * Demonstrates the usage of the PNet Data API by using an Apache HttpClient an without Spring.
 *
 * @author HAM
 */
public final class PnetApacheRestClientLauncher
{
    private PnetApacheRestClientLauncher()
    {
        super();
    }

    public static void main(String[] args)
    {
        MutablePnetDataApiLoginMethod loginMethod = MutablePnetDataApiLoginMethod.createFromPrefs(Prefs.DEFAULT_KEY);
        ApacheClientFactory clientFactory = ApacheClientFactory.of(loginMethod).loggingToSystemOut();

        PnetRestClient client =
            new PnetRestClient(loginMethod, clientFactory.getAboutDataClient(), clientFactory.getActivityDataClient(),
                clientFactory.getAdvisorTypeDataClient(), clientFactory.getApplicationDataClient(),
                clientFactory.getBrandDataClient(), clientFactory.getCompanyDataClient(),
                clientFactory.getCompanyGroupDataClient(), clientFactory.getCompanyGroupTypeDataClient(),
                clientFactory.getCompanyNumberTypeDataClient(), clientFactory.getCompanyTypeDataClient(),
                clientFactory.getContractStateDataClient(), clientFactory.getContractTypeDataClient(),
                clientFactory.getExternalBrandDataClient(), clientFactory.getFunctionDataClient(),
                clientFactory.getLegalFormDataClient(), clientFactory.getNumberTypeDataClient(),
                clientFactory.getPersonDataClient(), clientFactory.getContext());

        client.consume();
    }
}
