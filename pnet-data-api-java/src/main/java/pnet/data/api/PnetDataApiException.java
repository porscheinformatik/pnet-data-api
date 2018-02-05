package pnet.data.api;

/**
 * An exception for the Partner.Net Data API Client.
 *
 * @author ham
 */
public class PnetDataApiException extends Exception
{

    private static final long serialVersionUID = -4526094856573858071L;

    public PnetDataApiException(String message, Object... args)
    {
        super(String.format(message, args));
    }

    public PnetDataApiException(String message, Throwable cause, Object... args)
    {
        super(String.format(message, args), cause);
    }

}
