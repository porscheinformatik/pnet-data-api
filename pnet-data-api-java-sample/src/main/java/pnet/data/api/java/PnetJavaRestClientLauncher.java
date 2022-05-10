package pnet.data.api.java;

import pnet.data.api.PnetRestClient;
import pnet.data.api.util.MutablePnetDataApiLoginMethod;
import pnet.data.api.util.Prefs;

/**
 * Demonstrates the usage of the PNet Data API by using an Java 9 HttpClient without Spring.
 *
 * @author HAM
 */
public final class PnetJavaRestClientLauncher
{
    private PnetJavaRestClientLauncher()
    {
        super();
    }

    public static void main(String[] args)
    {
        MutablePnetDataApiLoginMethod loginMethod = MutablePnetDataApiLoginMethod.createFromPrefs(Prefs.DEFAULT_KEY);
        JavaClientFactory clientFactory = JavaClientFactory.of(loginMethod).loggingToSystemOut();

        PnetRestClient client =
            new PnetRestClient(loginMethod, clientFactory.getAboutDataClient(), clientFactory.getActivityDataClient(),
                clientFactory.getAdvisorTypeDataClient(), clientFactory.getApplicationDataClient(),
                clientFactory.getBrandDataClient(), clientFactory.getCompanyDataClient(),
                clientFactory.getCompanyGroupDataClient(), clientFactory.getCompanyGroupTypeDataClient(),
                clientFactory.getCompanyNumberTypeDataClient(), clientFactory.getCompanyTypeDataClient(),
                clientFactory.getContractStateDataClient(), clientFactory.getContractTypeDataClient(),
                clientFactory.getExternalBrandDataClient(), clientFactory.getFunctionDataClient(),
                clientFactory.getLegalFormDataClient(), clientFactory.getNumberTypeDataClient(),
                clientFactory.getPersonDataClient(), clientFactory.getProposalDataClient(),
                clientFactory.getTodoGroupDataClient(), clientFactory.getRepository());

        client.consume();
    }

}
