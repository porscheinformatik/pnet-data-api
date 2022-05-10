package pnet.data.api.client;

import pnet.data.api.client.context.PnetDataApiLoginMethod;

/**
 * The preferences for all request to the Partner.Net Data API.
 *
 * @author ham
 * @deprecated provide a {@link PnetDataApiLoginMethod} instead of this Prefs
 */
@Deprecated
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
