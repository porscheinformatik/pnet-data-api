package pnet.data.api.client;

import at.porscheinformatik.happyrest.RestResponseException;
import at.porscheinformatik.happyrest.RestUtils;
import pnet.data.api.PnetDataApiException;

/**
 * An exception raised on login error.
 *
 * @author ham
 */
public class PnetDataClientException extends PnetDataApiException
{

    private static final long serialVersionUID = -454458537464803864L;

    private final int statusCode;
    private final String statusMessage;

    public PnetDataClientException(String message, Object... args)
    {
        super(message, args);

        statusCode = -1;
        statusMessage = RestUtils.getHttpStatusMessage(statusCode);
    }

    public PnetDataClientException(String message, Throwable cause, Object... args)
    {
        super(enhanceMessage(message, cause), cause, args);

        statusCode = cause instanceof RestResponseException ? ((RestResponseException) cause).getStatusCode() : -1;
        statusMessage = cause instanceof RestResponseException ? ((RestResponseException) cause).getStatusMessage()
            : RestUtils.getHttpStatusMessage(statusCode);
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public String getStatusMessage()
    {
        return statusMessage;
    }

    protected static String enhanceMessage(String message, Throwable cause)
    {
        if (cause instanceof RestResponseException)
        {
            RestResponseException e = (RestResponseException) cause;

            return RestUtils.getHttpStatus(e.getStatusCode(), ((RestResponseException) cause).getStatusMessage())
                + " - "
                + message;
        }

        return message;
    }

}
