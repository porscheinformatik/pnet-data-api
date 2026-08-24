package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestMethod;
import java.util.Objects;
import java.util.function.Supplier;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * Obtains an access token from the Partner.Net IDP using the OAuth2 client credentials flow.
 */
public class IdpClientCredentialsPnetDataApiLoginMethod implements PnetDataApiLoginMethod {

    private final String resource;
    private final String idpUrl;
    private final Supplier<IdpClientCredentials> clientCredentialsSupplier;

    public IdpClientCredentialsPnetDataApiLoginMethod(
        String resource,
        String idpUrl,
        Supplier<IdpClientCredentials> clientCredentialsSupplier
    ) {
        this.resource = resource;
        this.idpUrl = idpUrl;
        this.clientCredentialsSupplier = clientCredentialsSupplier;
    }

    public IdpClientCredentialsPnetDataApiLoginMethod withResource(String resource) {
        return new IdpClientCredentialsPnetDataApiLoginMethod(resource, idpUrl, clientCredentialsSupplier);
    }

    public IdpClientCredentialsPnetDataApiLoginMethod withIdpUrl(String idpUrl) {
        return new IdpClientCredentialsPnetDataApiLoginMethod(resource, idpUrl, clientCredentialsSupplier);
    }

    public IdpClientCredentialsPnetDataApiLoginMethod withClientCredentialsSupplier(
        Supplier<IdpClientCredentials> clientCredentialsSupplier
    ) {
        return new IdpClientCredentialsPnetDataApiLoginMethod(resource, idpUrl, clientCredentialsSupplier);
    }

    @Override
    public RestCall performLogin(RestCallFactory factory) throws PnetDataClientException {
        try {
            Objects.requireNonNull(idpUrl, "IDP URL must not be null");
            Objects.requireNonNull(resource, "Resource must not be null");

            var clientCredentials = Objects.requireNonNull(
                clientCredentialsSupplier.get(),
                "Client credentials must not be null"
            );
            var clientId = Objects.requireNonNull(clientCredentials.clientId(), "Client ID must not be null");
            var clientSecret = Objects.requireNonNull(
                clientCredentials.clientSecret(),
                "Client secret must not be null"
            );

            var restCall = factory.url(idpUrl);
            var response = restCall
                .path("/oauth/token")
                .basicAuthorization(clientId, clientSecret)
                .contentTypeForm()
                .parameter("grant_type", "client_credentials")
                .parameter("resource", resource)
                .invoke(RestMethod.POST, IdpTokenResponse.class);

            if (!response.isSuccessful()) {
                throw new PnetDataClientException("IDP login failed at '%s': %s".formatted(idpUrl, response));
            }

            var accessToken = response.getBody().accessToken();
            if (PnetDataApiUtils.isEmpty(accessToken)) {
                throw new PnetDataClientException("IDP response does not contain an access token");
            }

            return factory.url(resource).bearerAuthorization(accessToken);
        } catch (PnetDataClientException e) {
            throw e;
        } catch (Exception e) {
            throw new PnetDataClientException("IDP login failed at '%s'".formatted(idpUrl), e);
        }
    }
}
