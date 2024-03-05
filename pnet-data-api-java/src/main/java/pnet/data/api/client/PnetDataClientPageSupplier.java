package pnet.data.api.client;

import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.util.Pair;

/**
 * Supplier for the specified page
 *
 * @param <T> the type of result
 * @author ham
 */
@FunctionalInterface
public interface PnetDataClientPageSupplier<T>
{
    PnetDataClientResultPage<T> get(List<Pair<String, Object>> restricts) throws PnetDataClientException;
}
