package pnet.data.api.person;

public enum PersonType
{
    /**
     * A regular person, physically existing.
     */
    PERSON,

    /**
     * A privileged (admin) user directly related to a regular person.
     */
    PRIVILEGED,

    /**
     * A user for automated tasks, e.g. for automatically executed tests.
     */
    BOT,

    /**
     * A user for manual tests.
     */
    TEST_USER,

    /**
     * A user for manual tests with privileged rights.
     */
    PRIVILEGED_TEST_USER
}
