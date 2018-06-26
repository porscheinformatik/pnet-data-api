package at.porscheinformatik.happyrest;

/**
 * Exception for REST calls.
 *
 * @author ham
 */
public class RestException extends Exception
{

    private static final long serialVersionUID = 6828148858084801707L;

    public RestException(String message, Object... args)
    {
        super(String.format(message, args));
    }

    public RestException(String message, Throwable cause, Object... args)
    {
        super(String.format(message, args), cause);
    }

}
