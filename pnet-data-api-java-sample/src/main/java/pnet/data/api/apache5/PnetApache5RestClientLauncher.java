package pnet.data.api.apache5;

import pnet.data.api.PnetRestClient;
import pnet.data.api.util.MutablePnetDataApiLoginMethod;
import pnet.data.api.util.Prefs;

/**
 * Demonstrates the usage of the PNet Data API by using an Apache HttpClient an without Spring.
 *
 * @author HAM
 */
public final class PnetApache5RestClientLauncher {

    private PnetApache5RestClientLauncher() {
    }

    public static void main(String[] args) {
        MutablePnetDataApiLoginMethod loginMethod = MutablePnetDataApiLoginMethod.createFromPrefs(Prefs.DEFAULT_KEY);
        Apache5ClientFactory clientFactory = Apache5ClientFactory.of(loginMethod).loggingToSystemOut();

        PnetRestClient client = new PnetRestClient(
            loginMethod,
            clientFactory.getAboutDataClient(),
            clientFactory.getActivityDataClient(),
            clientFactory.getAdvisorTypeDataClient(),
            clientFactory.getApplicationDataClient(),
            clientFactory.getBrandDataClient(),
            clientFactory.getCompanyDataClient(),
            clientFactory.getCompanyGroupDataClient(),
            clientFactory.getCompanyGroupTypeDataClient(),
            clientFactory.getCompanyNumberTypeDataClient(),
            clientFactory.getCompanyTypeDataClient(),
            clientFactory.getContractStateDataClient(),
            clientFactory.getContractTypeDataClient(),
            clientFactory.getExternalBrandDataClient(),
            clientFactory.getFunctionDataClient(),
            clientFactory.getLegalFormDataClient(),
            clientFactory.getNumberTypeDataClient(),
            clientFactory.getPersonDataClient(),
            clientFactory.getResourceDataClient(),
            clientFactory.getContext()
        );

        client.consume();
    }
}
