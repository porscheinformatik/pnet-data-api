package pnet.data.api.util;

/**
 * A filter for Data-API queries
 *
 * @author ham
 * @param <SELF> the type of the filter, for chaining
 */
@FunctionalInterface
public interface Filter<SELF extends Filter<SELF>>
{

    SELF filter(String parameterName, Object... values);

}
