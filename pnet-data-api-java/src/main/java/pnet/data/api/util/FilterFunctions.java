package pnet.data.api.util;

/**
 * Filters functions
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface FilterFunctions<SELF extends Filter<SELF>> extends Filter<SELF>
{

    default SELF filterFunction(String... functionMatchcodes)
    {
        return filter("function", (Object[]) functionMatchcodes);
    }

}
