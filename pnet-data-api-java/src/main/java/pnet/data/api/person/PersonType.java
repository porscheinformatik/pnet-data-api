package pnet.data.api.person;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum PersonType
{
    /**
     * A regular person, physically existing.
     */
    PERSON(false),

    /**
     * A user for automated tasks, e.g. for automatically executed tests.
     */
    BOT(true),

    /**
     * A user for manual tests.
     */
    TEST_USER(true);

    private final boolean isVirtual;

    PersonType(boolean isVirtual)
    {
        this.isVirtual = isVirtual;
    }

    public boolean isVirtual()
    {
        return isVirtual;
    }

    public static Set<PersonType> getVirtualTypes()
    {
        return Arrays.stream(values()).filter(PersonType::isVirtual).collect(Collectors.toSet());
    }

    public static Set<PersonType> getNonVirtualTypes()
    {
        return Arrays.stream(values()).filter(type -> !type.isVirtual()).collect(Collectors.toSet());
    }
}
