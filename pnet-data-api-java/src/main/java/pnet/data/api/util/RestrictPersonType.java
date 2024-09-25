package pnet.data.api.util;

import java.util.Collection;

import pnet.data.api.person.PersonType;
import pnet.data.api.person.PersonTypeFilter;

/**
 * Restricts person types.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictPersonType<SELF extends Restrict<SELF>> extends Restrict<SELF>
{
    default SELF type(PersonTypeFilter... types)
    {
        return restrict("type", (Object[]) types);
    }

    default SELF types(Collection<PersonTypeFilter> types)
    {
        return type(types.toArray(new PersonTypeFilter[0]));
    }
}
