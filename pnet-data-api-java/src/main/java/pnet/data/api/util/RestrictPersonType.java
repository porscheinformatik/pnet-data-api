package pnet.data.api.util;

import java.util.Collection;

import pnet.data.api.person.PersonType;

/**
 * Restricts person types.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictPersonType<SELF extends Restrict<SELF>>  extends Restrict<SELF>
{
    default SELF type(PersonType... types)
    {
        return restrict("type", (Object[]) types);
    }

    default SELF types(Collection<PersonType> types)
    {
        return type(types.toArray(new PersonType[0]));
    }
}
