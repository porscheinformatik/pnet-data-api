package pnet.data.api.person;

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
}
