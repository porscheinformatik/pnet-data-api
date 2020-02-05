package at.porscheinformatik.happyrest;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestFormatterException extends RestException
{

    private static final long serialVersionUID = -3314554757798478103L;

    public RestFormatterException(String message, Object... args)
    {
        super(message, args);
    }

    public RestFormatterException(String message, Throwable cause, Object... args)
    {
        super(message, cause, args);
    }

}
