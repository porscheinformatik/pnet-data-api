package pnet.data.api.util;

import java.util.Collection;

import pnet.data.api.person.PersonHierarchyType;

/**
 * Restricts the person of the hierarchy
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictPersonHierarchy<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF referencedPerson(Integer... referencedPersonIds)
    {
        return restrict("referencedPersonId", (Object[]) referencedPersonIds);
    }

    default SELF referencedPersons(Collection<Integer> referencedPersonIds)
    {
        return referencedPerson(
            referencedPersonIds.toArray(new Integer[0]));
    }

    default SELF hierarchyType(PersonHierarchyType... hierarchyTypes)
    {
        return restrict("hierarchyType", (Object[]) hierarchyTypes);
    }

    default SELF hierarchyTypes(Collection<PersonHierarchyType> hierarchyTypes)
    {
        return hierarchyType(
            hierarchyTypes.toArray(new PersonHierarchyType[0]));
    }
}
