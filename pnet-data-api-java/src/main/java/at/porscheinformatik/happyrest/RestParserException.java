package at.porscheinformatik.happyrest;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestParserException extends RestException
{

    private static final long serialVersionUID = -3314554757798478103L;

    public RestParserException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RestParserException(String message)
    {
        super(message);
    }

    @Deprecated
    public RestParserException(String message, Object... args)
    {
        super(message, args);
    }

    @Deprecated
    public RestParserException(String message, Throwable cause, Object... args)
    {
        super(message, cause, args);
    }

}
