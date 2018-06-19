package pnet.data.api;

/**
 * An exception for the Partner.Net Data API Client, that indicates, that the server could not fulfill a request.
 *
 * @author ham
 */
public class PnetDataApiAccessException extends PnetDataApiException
{

    private static final long serialVersionUID = 3718598279637275056L;

    public PnetDataApiAccessException(Throwable cause)
    {
        this("Failed to access the server", cause);
    }

    public PnetDataApiAccessException(String message, Object... args)
    {
        super(message, args);
    }

    public PnetDataApiAccessException(String message, Throwable cause, Object... args)
    {
        super(message, cause, args);
    }

}
