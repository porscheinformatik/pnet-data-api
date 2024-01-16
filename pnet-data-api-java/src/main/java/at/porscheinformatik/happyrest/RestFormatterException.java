package at.porscheinformatik.happyrest;

import java.io.Serial;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestFormatterException extends RestException
{
    @Serial
    private static final long serialVersionUID = -3314554757798478103L;

    public RestFormatterException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RestFormatterException(String message)
    {
        super(message);
    }
}
