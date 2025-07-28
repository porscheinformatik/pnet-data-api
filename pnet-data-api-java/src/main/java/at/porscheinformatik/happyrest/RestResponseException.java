package at.porscheinformatik.happyrest;

import java.io.Serial;
import pnet.data.api.ErrorResult;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestResponseException extends RestException {

    @Serial
    private static final long serialVersionUID = 2832735845570939626L;

    private final String description;
    private final int statusCode;
    private final String statusMessage;

    public RestResponseException(String description, int statusCode, String statusMessage, Throwable cause) {
        super(RestUtils.getHttpStatus(statusCode, statusMessage) + " - " + description, cause);
        this.description = description;
        this.statusCode = statusCode;
        this.statusMessage = RestUtils.getHttpStatusMessage(statusCode, statusMessage);
    }

    public RestResponseException(ErrorResult errorResult, int statusCode, String statusMessage, Throwable cause) {
        this(errorResult.toDescription(), statusCode, statusMessage, cause);
    }

    public String getDescription() {
        return description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getStatus() {
        return statusCode + " " + statusMessage;
    }
}
