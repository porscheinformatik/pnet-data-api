package pnet.data.api;

/**
 * An exception for the Partner.Net Data API Client.
 *
 * @author ham
 */
public class PnetDataApiProhibitedException extends PnetDataApiException
{

    private static final long serialVersionUID = -8051484973670637587L;

    public PnetDataApiProhibitedException(String message, Object... args)
    {
        super(message, args);
    }

    public PnetDataApiProhibitedException(String message, Throwable cause, Object... args)
    {
        super(message, cause, args);
    }

}
