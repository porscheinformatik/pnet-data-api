package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.PnetDataClientException;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 * @deprecated use the {@link SimplePnetDataApiContext} instead, since it does not depend on a repository.
 */
@Deprecated
public abstract class AbstractPnetDataApiContext implements PnetDataApiContext {

    protected final PnetDataApiTokenRepository repository;

    protected AbstractPnetDataApiContext(PnetDataApiTokenRepository repository) {
        super();
        this.repository = repository;
    }

    protected abstract PnetDataApiLoginMethod getLoginMethod();

    @Override
    @Deprecated
    public PnetDataApiContext withUrl(String url) {
        PnetDataApiLoginMethod loginMethod = getLoginMethod();

        if (
            loginMethod instanceof AuthenticationTokenPnetDataApiLoginMethod authenticationTokenPnetDataApiLoginMethod
        ) {
            return withLoginMethod(authenticationTokenPnetDataApiLoginMethod.withUrl(url));
        }

        if (loginMethod instanceof UsernamePasswordPnetDataApiLoginMethod usernamePasswordPnetDataApiLoginMethod) {
            return withLoginMethod(usernamePasswordPnetDataApiLoginMethod.withUrl(url));
        }

        if (loginMethod instanceof PnetDataApiTokenKey pnetDataApiTokenKey) {
            return withLoginMethod(pnetDataApiTokenKey.withUrl(url));
        }

        throw new UnsupportedOperationException(
            "This method is deprecated and does not support loginMethods of type " + loginMethod.getClass()
        );
    }

    @Override
    @Deprecated
    public PnetDataApiContext withCredentials(String username, String password) {
        PnetDataApiLoginMethod loginMethod = getLoginMethod();

        if (loginMethod instanceof UsernamePasswordPnetDataApiLoginMethod usernamePasswordPnetDataApiLoginMethod) {
            return withLoginMethod(
                usernamePasswordPnetDataApiLoginMethod.withUsernamePasswordSupplier(() ->
                    new UsernamePasswordCredentials(username, password)
                )
            );
        }

        if (loginMethod instanceof PnetDataApiTokenKey pnetDataApiTokenKey) {
            return withLoginMethod(pnetDataApiTokenKey.withCredentials(username, password));
        }

        throw new UnsupportedOperationException(
            "This method is deprecated and does not support loginMethods of type " + loginMethod.getClass()
        );
    }

    @Override
    public RestCall restCall() throws PnetDataClientException {
        return repository.restCall(getLoginMethod());
    }

    @Override
    public void invalidateLogin() {
        repository.invalidate(getLoginMethod());
    }
}
