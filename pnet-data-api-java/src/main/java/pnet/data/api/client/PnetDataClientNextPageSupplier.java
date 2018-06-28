package pnet.data.api.client;

import pnet.data.api.PnetDataClientException;

/**
 * Supplier for the next page
 *
 * @author ham
 */
@FunctionalInterface
public interface PnetDataClientNextPageSupplier<T>
{

    PnetDataClientResultPage<T> get() throws PnetDataClientException;

}
