package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import java.util.function.Supplier;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.util.PnetDataApiUtils;

public class AuthenticationTokenPnetDataApiLoginMethod implements PnetDataApiLoginMethod {

    private static final String TOKEN_PREFIX = "Bearer";

    private final String url;
    private final Supplier<String> authenticationTokenSupplier;

    public AuthenticationTokenPnetDataApiLoginMethod(String url, Supplier<String> authenticationTokenSupplier) {
        super();
        this.url = url;
        this.authenticationTokenSupplier = authenticationTokenSupplier;
    }

    public AuthenticationTokenPnetDataApiLoginMethod withUrl(String url) {
        return new AuthenticationTokenPnetDataApiLoginMethod(url, authenticationTokenSupplier);
    }

    public AuthenticationTokenPnetDataApiLoginMethod withAuthenticationTokenSupplier(
        Supplier<String> authenticationTokenSupplier
    ) {
        return new AuthenticationTokenPnetDataApiLoginMethod(url, authenticationTokenSupplier);
    }

    @Override
    public RestCall performLogin(RestCallFactory factory) throws PnetDataClientException {
        try {
            RestCall restCall = factory.url(url);
            RestResponse<Void> response = restCall
                .bearerAuthorization(authenticationTokenSupplier.get())
                .path("login")
                .invoke(RestMethod.POST, Void.class);

            if (response.isSuccessful()) {
                String token = response.getFirstHeader("Authorization");

                if (PnetDataApiUtils.isEmpty(token)) {
                    throw new PnetDataClientException("Authorization header is missing");
                }

                if (token.startsWith(TOKEN_PREFIX)) {
                    token = token.substring(TOKEN_PREFIX.length()).trim();
                }

                return restCall.bearerAuthorization(token);
            }

            throw new PnetDataClientException("Login failed at \"" + url + "\": " + response);
        } catch (Exception e) {
            throw new PnetDataClientException("Login failed at \"" + url + "\"", e);
        }
    }
}
