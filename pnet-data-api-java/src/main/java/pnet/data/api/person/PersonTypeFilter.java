package pnet.data.api.person;

import java.util.Set;

public enum PersonTypeFilter
{
    PERSON(PersonType.PERSON),
    PRIVILEGED(PersonType.PRIVILEGED),
    BOT(PersonType.BOT),
    TEST_USER(PersonType.TEST_USER),
    PRIVILEGED_TEST_USER(PersonType.PRIVILEGED_TEST_USER),
    NATURAL(PersonType.PERSON),
    VIRTUAL(PersonType.BOT, PersonType.TEST_USER),
    NON_PRIVILEGED(PersonType.PERSON, PersonType.BOT, PersonType.TEST_USER),
    PRIVILEGED_ONLY(PersonType.PRIVILEGED, PersonType.PRIVILEGED_TEST_USER),
    ALL(PersonType.PERSON, PersonType.PRIVILEGED, PersonType.BOT, PersonType.TEST_USER,
        PersonType.PRIVILEGED_TEST_USER);

    private final Set<PersonType> personTypes;

    PersonTypeFilter(PersonType... personTypes)
    {
        this.personTypes = Set.of(personTypes);
    }

    public Set<PersonType> getPersonTypes()
    {
        return personTypes;
    }

    public boolean contains(PersonType personType)
    {
        return personTypes.contains(personType);
    }
}
