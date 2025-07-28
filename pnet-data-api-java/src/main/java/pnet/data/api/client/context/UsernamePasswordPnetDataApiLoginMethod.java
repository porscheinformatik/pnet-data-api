package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import java.util.function.Supplier;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.util.PnetDataApiUtils;

public class UsernamePasswordPnetDataApiLoginMethod implements PnetDataApiLoginMethod {

    private static final String TOKEN_PREFIX = "Bearer";

    private final String url;
    private final Supplier<UsernamePasswordCredentials> usernamePasswordSupplier;

    public UsernamePasswordPnetDataApiLoginMethod(
        String url,
        Supplier<UsernamePasswordCredentials> usernamePasswordSupplier
    ) {
        super();
        this.url = url;
        this.usernamePasswordSupplier = usernamePasswordSupplier;
    }

    public UsernamePasswordPnetDataApiLoginMethod withUrl(String url) {
        return new UsernamePasswordPnetDataApiLoginMethod(url, usernamePasswordSupplier);
    }

    public UsernamePasswordPnetDataApiLoginMethod withUsernamePasswordSupplier(
        Supplier<UsernamePasswordCredentials> usernamePasswordSupplier
    ) {
        return new UsernamePasswordPnetDataApiLoginMethod(url, usernamePasswordSupplier);
    }

    @Override
    public RestCall performLogin(RestCallFactory factory) throws PnetDataClientException {
        try {
            RestCall restCall = factory.url(url);
            RestResponse<Void> response = restCall
                .body(usernamePasswordSupplier.get())
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
