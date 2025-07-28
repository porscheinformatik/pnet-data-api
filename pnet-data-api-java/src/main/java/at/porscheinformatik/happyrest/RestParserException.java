package at.porscheinformatik.happyrest;

import java.io.Serial;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestParserException extends RestException {

    @Serial
    private static final long serialVersionUID = -3314554757798478103L;

    public RestParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestParserException(String message) {
        super(message);
    }
}
