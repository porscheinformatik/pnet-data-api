package pnet.data.api.client;

import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.util.Pair;

/**
 * Supplier for the specified page
 *
 * @author ham
 * @param <T> the type of result
 */
@FunctionalInterface
public interface PnetDataClientPageSupplier<T>
{
    PnetDataClientResultPage<T> get(List<Pair<String, Object>> restricts) throws PnetDataClientException;
}
