package pnet.data.api.person;

public enum PersonType
{
    /**
     * A regular person, physically existing.
     */
    PERSON,

    /**
     * A user for automated tasks, e.g. for automatically executed tests.
     */
    BOT,

    /**
     * A user for manual tests.
     */
    TEST_USER
}
