package pnet.data.api.client;

import pnet.data.api.PnetDataApiException;

/**
 * Supplier for the next page
 *
 * @author ham
 */
@FunctionalInterface
public interface PnetDataClientNextPageSupplier<T>
{

    PnetDataClientResultPage<T> get() throws PnetDataApiException;

}
