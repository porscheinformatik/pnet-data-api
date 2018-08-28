package pnet.data.api.client;

import pnet.data.api.PnetDataClientException;

/**
 * Supplier for the specified page
 *
 * @author ham
 * @param <T> the type of result
 */
@FunctionalInterface
public interface PnetDataClientPageSupplier<T>
{

    PnetDataClientResultPage<T> get(int index) throws PnetDataClientException;

}
