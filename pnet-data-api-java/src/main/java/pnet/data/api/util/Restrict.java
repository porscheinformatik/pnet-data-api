package pnet.data.api.util;

/**
 * A filter for Data-API queries
 *
 * @param <SELF> the type of the filter, for chaining
 * @author ham
 */
@FunctionalInterface
public interface Restrict<SELF extends Restrict<SELF>> {
    SELF restrict(String parameterName, Object... values);
}
