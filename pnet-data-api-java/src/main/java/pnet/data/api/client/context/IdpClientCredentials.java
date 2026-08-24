package pnet.data.api.client.context;

/**
 * OAuth2 client credentials issued by the Partner.Net IDP.
 */
public record IdpClientCredentials(String clientId, String clientSecret) {}
