package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.PnetDataClientException;

/**
 * A mock for the {@link PnetDataApiContext}
 *
 * @author HAM
 */
public class PnetDataApiContextMock implements PnetDataApiContext {

    @Override
    public PnetDataApiContext withLoginMethod(PnetDataApiLoginMethod loginMethod) {
        throw new UnsupportedOperationException("Method \"withLoginMethod(..)\" not implemented");
    }

    @Override
    @Deprecated
    public PnetDataApiContext withUrl(String url) {
        throw new UnsupportedOperationException("Method \"withUrl(..)\" not implemented");
    }

    @Override
    @Deprecated
    public PnetDataApiContext withCredentials(String username, String password) {
        throw new UnsupportedOperationException("Method \"withCredentials(..)\" not implemented");
    }

    @Override
    public RestCall restCall() throws PnetDataClientException {
        throw new UnsupportedOperationException("Method \"restCall(..)\" not implemented");
    }

    @Override
    public void invalidateLogin() throws PnetDataClientException {
        throw new UnsupportedOperationException("Method \"invalidateLogin(..)\" not implemented");
    }
}
