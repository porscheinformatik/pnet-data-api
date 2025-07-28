package pnet.data.api;

/**
 * An exception raised on technical issues.
 *
 * @author ham
 */
public class PnetDataClientTechnicalException extends RuntimeException {

    private static final long serialVersionUID = -8205449003064890663L;

    public PnetDataClientTechnicalException(String message, Object... args) {
        super(String.format(message, args));
    }

    public PnetDataClientTechnicalException(String message, Throwable cause, Object... args) {
        super(String.format(PnetDataClientException.enhanceMessage(message, cause), args), cause);
    }
}
