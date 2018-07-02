package pnet.data.api.client;

import pnet.data.api.PnetDataClientException;

/**
 * Supplier for the specified page
 *
 * @author ham
 */
@FunctionalInterface
public interface PnetDataClientPageSupplier<T>
{

    PnetDataClientResultPage<T> get(int index) throws PnetDataClientException;

}
