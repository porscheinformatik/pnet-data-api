package pnet.data.api.client;

import at.porscheinformatik.happyrest.RestResponse;
import pnet.data.api.PnetDataApiException;

/**
 * An exception raised on login error.
 *
 * @author ham
 */
public class PnetDataClientLoginException extends PnetDataApiException
{

    private static final long serialVersionUID = -454458537464803864L;

    private final String url;
    private final String username;
    private final RestResponse<?> response;

    public PnetDataClientLoginException(String message, String url, String username, RestResponse<?> response)
    {
        super(message, url, username, response != null ? response.getStatus() : null);

        this.url = url;
        this.username = username;
        this.response = response;
    }

    public PnetDataClientLoginException(String message, Throwable cause, String url, String username,
        RestResponse<?> response)
    {
        super(message, cause, url, username, response != null ? response.getStatus() : null);

        this.url = url;
        this.username = username;
        this.response = response;
    }

    public String getUrl()
    {
        return url;
    }

    public String getUsername()
    {
        return username;
    }

    public RestResponse<?> getResponse()
    {
        return response;
    }

}
