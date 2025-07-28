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

    /**
     * Returns a new instance or changes the state of this object by using the specified URL.
     *
     * @param url the url
     * @return the object itself or a new instance
     * @deprecated There is no direct replacement for this method. Since version 1.20 you may use different
     * {@link PnetDataApiLoginMethod}s that may not be compatible with the concepts of "URLs". Instead of calling this
     * method, you may create a new {@link PnetDataApiLoginMethod} and use the
     * {@link #withLoginMethod(PnetDataApiLoginMethod)} method to set it.
     */
    @Deprecated
    SELF withUrl(String url);

    /**
     * Returns a new instance or changes the state of this object by using the specified username and password.
     *
     * @param username the username
     * @param password the password
     * @return the object itself or a new instance
     * @deprecated There is no direct replacement for this method. Since version 1.20 you may use different
     * {@link PnetDataApiLoginMethod}s that may not be compatible with the concepts of "username" and "password".
     * Instead of calling this method, you may create a new {@link PnetDataApiLoginMethod} and use the
     * {@link #withLoginMethod(PnetDataApiLoginMethod)} method to set it.
     */
    @Deprecated
    SELF withCredentials(String username, String password);
}
