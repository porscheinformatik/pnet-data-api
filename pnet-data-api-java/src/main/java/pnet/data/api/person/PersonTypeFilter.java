package pnet.data.api.person;

import java.util.Set;

public enum PersonTypeFilter
{
    PERSON(PersonType.PERSON),
    BOT(PersonType.BOT),
    TEST_USER(PersonType.TEST_USER),
    NATURAL(PersonType.PERSON),
    VIRTUAL(PersonType.BOT, PersonType.TEST_USER),
    ALL(PersonType.PERSON, PersonType.BOT, PersonType.TEST_USER);

    private final Set<PersonType> personTypes;

    PersonTypeFilter(PersonType... personTypes)
    {
        this.personTypes = Set.of(personTypes);
    }

    public Set<PersonType> getPersonTypes()
    {
        return personTypes;
    }
}
