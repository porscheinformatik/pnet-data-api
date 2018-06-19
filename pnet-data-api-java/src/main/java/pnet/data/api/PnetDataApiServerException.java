package pnet.data.api;

/**
 * An exception for the Partner.Net Data API Client, that indicates, that the server could not fulfill a request.
 *
 * @author ham
 */
public class PnetDataApiServerException extends PnetDataApiException
{

    private static final long serialVersionUID = 3718598279637275056L;

    public PnetDataApiServerException(String message, Object... args)
    {
        super(message, args);
    }

    public PnetDataApiServerException(String message, Throwable cause, Object... args)
    {
        super(message, cause, args);
    }

}
