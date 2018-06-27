package pnet.data.api.client;

/**
 * The preferences for all request to the Partner.Net Data API.
 *
 * @author ham
 */
public interface PnetDataClientPrefs
{

    /**
     * @return the (default) URL of the Partner.Net Data API.
     */
    String getPnetDataApiUrl();

    /**
     * @return the (default) username of the SystemUser for accessing the Partner.Net Data API.
     */
    String getPnetDataApiUsername();

    /**
     * @return the (default) password of the SystemUser for accessing the Partner.Net Data API.
     */
    String getPnetDataApiPassword();

}
