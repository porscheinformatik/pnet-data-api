package at.porscheinformatik.happyrest;

import java.io.Serial;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestRequestException extends RestException
{
    @Serial
    private static final long serialVersionUID = -3314554757798478103L;

    public RestRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RestRequestException(String message)
    {
        super(message);
    }
}
