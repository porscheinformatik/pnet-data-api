package pnet.data.api.client.context;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IdpTokenResponse(@JsonProperty("access_token") String accessToken) {}
