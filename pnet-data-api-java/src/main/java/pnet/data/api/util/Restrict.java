package pnet.data.api.util;

/**
 * A filter for Data-API queries
 *
 * @author ham
 * @param <SELF> the type of the filter, for chaining
 */
@FunctionalInterface
public interface Restrict<SELF extends Restrict<SELF>>
{

    SELF restrict(String parameterName, Object... values);

}
