package pnet.data.api.client.context;

/**
 * @param <SELF> the own type for fluently chaining calls
 * @author ham
 */
public interface PnetDataApiContextAware<SELF> {
    /**
     * Returns a new instance or changes the state of this object by using the specified login method.
     *
     * @param loginMethod the login method
     * @return the object itself or a new instance
     */
    SELF withLoginMethod(PnetDataApiLoginMethod loginMethod);
}
