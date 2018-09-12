package pnet.data.api.client;

import pnet.data.api.PnetDataClientException;

/**
 * Supplier for the next scroll page
 *
 * @author ham
 * @param <T> the type of result
 */
@FunctionalInterface
public interface PnetDataClientScrollSupplier<T>
{

    PnetDataClientResultPage<T> get(String scrollId) throws PnetDataClientException;

}
