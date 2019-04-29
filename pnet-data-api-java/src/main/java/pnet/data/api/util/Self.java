package pnet.data.api.util;

/**
 * Self-reference for fluent APIs
 *
 * @author HAM
 * @param <SELF> the type of this class itself
 */
public interface Self<SELF extends Self<SELF>>
{

    @SuppressWarnings("unchecked")
    default SELF self()
    {
        return (SELF) this;
    }

}
