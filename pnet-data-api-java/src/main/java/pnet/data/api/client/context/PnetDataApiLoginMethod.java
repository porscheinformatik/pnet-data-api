package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCallFactory;
import pnet.data.api.PnetDataClientException;

public interface PnetDataApiLoginMethod
{
    String getUrl();

    PnetDataApiLoginMethod withUrl(String url);

    String getKey();

    String performLogin(RestCallFactory factory) throws PnetDataClientException;
}
