package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import java.util.Objects;
import pnet.data.api.PnetDataClientException;

/**
 * The context for Data API call.
 *
 * @author ham
 */
public class SimplePnetDataApiContext implements PnetDataApiContext {

    private final RestCallFactory restCallFactory;
    private final PnetDataApiLoginMethod loginMethod;

    private PnetDataApiLoginMethod usedLoginMethodForRestCall;
    private RestCall restCall;

    public SimplePnetDataApiContext(RestCallFactory restCallFactory, PnetDataApiLoginMethod loginMethod) {
        super();
        this.restCallFactory = restCallFactory;
        this.loginMethod = loginMethod;
    }

    @Override
    public PnetDataApiContext withLoginMethod(PnetDataApiLoginMethod loginMethod) {
        return new SimplePnetDataApiContext(restCallFactory, loginMethod);
    }

    @Override
    public RestCall restCall() throws PnetDataClientException {
        synchronized (loginMethod) {
            // Since LoginMethod objects may not be final, it may implement the hashCode and equals method in order to
            // indicate changes to internal parameter.

            if (restCall == null || !Objects.equals(loginMethod, usedLoginMethodForRestCall)) {
                usedLoginMethodForRestCall = loginMethod;
                restCall = loginMethod.performLogin(restCallFactory);
            }
        }

        return restCall;
    }

    @Override
    public void invalidateLogin() throws PnetDataClientException {
        synchronized (loginMethod) {
            restCall = null;
        }
    }
}
