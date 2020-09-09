package pnet.data.api.apache;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.apache.ApacheRestCallFactory;
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientPrefs;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.AbstractClientFactory;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * A factory for clients using Apache HTTP client. This class is intended to be used without Spring.
 *
 * @author HAM
 */
public class ApacheClientFactory extends AbstractClientFactory<ApacheClientFactory>
{

    public static ApacheClientFactory of(String url, String username, String password)
    {
        return of(new MutablePnetDataClientPrefs(url, username, password));
    }

    public static ApacheClientFactory of(PnetDataClientPrefs prefs)
    {
        return of(prefs, JacksonPnetDataApiModule.createObjectMapper(), SystemRestLoggerAdapter.INSTANCE);
    }

    public static ApacheClientFactory of(PnetDataClientPrefs prefs, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        return new ApacheClientFactory(prefs, mapper, loggerAdapter);
    }

    protected ApacheClientFactory(PnetDataClientPrefs prefs, ObjectMapper mapper, RestLoggerAdapter loggerAdapter)
    {
        super(prefs, mapper, loggerAdapter);
    }

    @Override
    protected RestCallFactory createRestCallFactory(ObjectMapper mapper, RestLoggerAdapter loggerAdapter)
    {
        return ApacheRestCallFactory
            .create(loggerAdapter, mapper)
            .withUserAgent(PnetDataApiUtils.getUserAgent("Apache's HttpClient"));
    }

    @Override
    protected ApacheClientFactory copy(PnetDataClientPrefs prefs, ObjectMapper mapper, RestLoggerAdapter loggerAdapter)
    {
        return new ApacheClientFactory(prefs, mapper, loggerAdapter);
    }

}
