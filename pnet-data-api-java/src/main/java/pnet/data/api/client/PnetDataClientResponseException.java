package pnet.data.api.client;

import at.porscheinformatik.happyrest.RestResponseException;
import at.porscheinformatik.happyrest.RestUtils;

/**
 * An exception raised if the permissions do not allow access
 *
 * @author ham
 */
public class PnetDataClientResponseException extends PnetDataClientException
{

    private static final long serialVersionUID = 8230688989642189613L;

    private final int statusCode;
    private final String statusMessage;

    public PnetDataClientResponseException(String message, RestResponseException cause, Object... args)
    {
        super(RestUtils.getHttpStatus(cause.getStatusCode(), cause.getStatusMessage()) + " " + message, cause, args);

        statusCode = cause.getStatusCode();
        statusMessage = cause.getStatusMessage();
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public String getStatusMessage()
    {
        return statusMessage;
    }

}
