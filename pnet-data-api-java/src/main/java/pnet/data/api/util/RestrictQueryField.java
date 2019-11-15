package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the fields to query for.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictQueryField<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF queryField(String... queryFields)
    {
        return restrict("qf", (Object[]) queryFields);
    }

    default SELF queryFields(Collection<String> queryFields)
    {
        return queryField(queryFields.toArray(new String[queryFields.size()]));
    }

}
