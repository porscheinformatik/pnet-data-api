package pnet.data.api;

import at.porscheinformatik.happyrest.RestResponseException;
import at.porscheinformatik.happyrest.RestUtils;
import java.io.Serial;

/**
 * An exception raised on login error.
 *
 * @author ham
 */
public class PnetDataClientException extends Exception {

    @Serial
    private static final long serialVersionUID = -454458537464803864L;

    private final int statusCode;
    private final String statusMessage;

    public PnetDataClientException(String message) {
        super(message);
        statusCode = -1;
        statusMessage = RestUtils.getHttpStatusMessage(statusCode);
    }

    public PnetDataClientException(String message, Throwable cause, Object... args) {
        super(enhanceMessage(message, cause), cause);
        statusCode = cause instanceof RestResponseException restResponseException
            ? restResponseException.getStatusCode()
            : -1;

        statusMessage = cause instanceof RestResponseException restResponseException
            ? restResponseException.getStatusMessage()
            : RestUtils.getHttpStatusMessage(statusCode);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public static String enhanceMessage(String message, Throwable cause) {
        if (cause instanceof RestResponseException e) {
            return message + " - " + e.getDescription();
        }

        return message;
    }
}
