package pnet.data.api;

import at.porscheinformatik.happyrest.RestResponseException;
import at.porscheinformatik.happyrest.RestUtils;

/**
 * An exception raised on login error.
 *
 * @author ham
 */
public class PnetDataClientException extends Exception
{

    private static final long serialVersionUID = -454458537464803864L;

    private final int statusCode;
    private final String statusMessage;

    public PnetDataClientException(String message, Object... args)
    {
        super(String.format(message, args));

        statusCode = -1;
        statusMessage = RestUtils.getHttpStatusMessage(statusCode);
    }

    public PnetDataClientException(String message, Throwable cause, Object... args)
    {
        super(String.format(enhanceMessage(message, cause), args), cause);

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

    public static String enhanceMessage(String message, Throwable cause)
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
