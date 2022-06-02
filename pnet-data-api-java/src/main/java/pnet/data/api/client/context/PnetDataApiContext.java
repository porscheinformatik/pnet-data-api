package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.PnetDataClientException;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 */
public interface PnetDataApiContext extends PnetDataApiContextAware<PnetDataApiContext>
{
    /**
     * Create a REST call by either using an existing short-lived authentication token or by getting a new one using the
     * {@link PnetDataApiLoginMethod} of this context.
     *
     * @return a restCall object
     * @throws PnetDataClientException on occasion
     */
    RestCall restCall() throws PnetDataClientException;

    /**
     * Removes the short-lived authentication token from the repository.
     *
     * @throws PnetDataClientException on occasion
     */
    void invalidateLogin() throws PnetDataClientException;
}