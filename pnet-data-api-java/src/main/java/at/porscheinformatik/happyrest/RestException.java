package at.porscheinformatik.happyrest;

import java.io.Serial;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestException extends Exception {

    @Serial
    private static final long serialVersionUID = 6828148858084801707L;

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }
}
