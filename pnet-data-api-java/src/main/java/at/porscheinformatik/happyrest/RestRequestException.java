package at.porscheinformatik.happyrest;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestRequestException extends RestException
{

    private static final long serialVersionUID = -3314554757798478103L;

    public RestRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RestRequestException(String message)
    {
        super(message);
    }

    @Deprecated
    public RestRequestException(String message, Object... args)
    {
        super(message, args);
    }

    @Deprecated
    public RestRequestException(String message, Throwable cause, Object... args)
    {
        super(message, cause, args);
    }

}
