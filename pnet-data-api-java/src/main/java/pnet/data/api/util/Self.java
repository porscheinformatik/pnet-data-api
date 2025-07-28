package pnet.data.api.util;

/**
 * Self-reference for fluent APIs
 *
 * @param <SELF> the type of this class itself
 * @author HAM
 */
public interface Self<SELF extends Self<SELF>> {
    @SuppressWarnings("unchecked")
    default SELF self() {
        return (SELF) this;
    }
}
