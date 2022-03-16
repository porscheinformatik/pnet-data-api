package at.porscheinformatik.happyrest;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestFormatterException extends RestException
{

    private static final long serialVersionUID = -3314554757798478103L;

    public RestFormatterException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RestFormatterException(String message)
    {
        super(message);
    }

    @Deprecated
    public RestFormatterException(String message, Object... args)
    {
        super(message, args);
    }

    @Deprecated
    public RestFormatterException(String message, Throwable cause, Object... args)
    {
        super(message, cause, args);
    }

}
