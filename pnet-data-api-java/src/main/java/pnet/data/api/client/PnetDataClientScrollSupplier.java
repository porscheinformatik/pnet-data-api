package pnet.data.api.client;

import pnet.data.api.PnetDataClientException;

/**
 * Supplier for the next scroll page
 *
 * @param <T> the type of result
 * @author ham
 */
@FunctionalInterface
public interface PnetDataClientScrollSupplier<T>
{

    PnetDataClientResultPage<T> get(String scrollId) throws PnetDataClientException;

}
