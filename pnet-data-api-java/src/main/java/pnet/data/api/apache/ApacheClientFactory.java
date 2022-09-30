package pnet.data.api.apache;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.apache.ApacheRestCallFactory;
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientPrefs;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.client.context.UsernamePasswordCredentials;
import pnet.data.api.client.context.UsernamePasswordPnetDataApiLoginMethod;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.AbstractClientFactory;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * A factory for clients using Apache HTTP client. This class is intended to be used without Spring.
 *
 * @author HAM
 */
@SuppressWarnings("deprecation")
public class ApacheClientFactory extends AbstractClientFactory<ApacheClientFactory>
{
    @Deprecated
    public static ApacheClientFactory of(String url, String username, String password)
    {
        return of(new MutablePnetDataClientPrefs(url, username, password));
    }

    @Deprecated
    public static ApacheClientFactory of(PnetDataClientPrefs prefs)
    {
        return of(prefs, JacksonPnetDataApiModule.createObjectMapper(), SystemRestLoggerAdapter.INSTANCE);
    }

    @Deprecated
    public static ApacheClientFactory of(PnetDataClientPrefs prefs, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        return new ApacheClientFactory(
            new UsernamePasswordPnetDataApiLoginMethod(prefs.getPnetDataApiUrl(),
                () -> new UsernamePasswordCredentials(prefs.getPnetDataApiUsername(), prefs.getPnetDataApiPassword())),
            mapper, loggerAdapter);
    }

    public static ApacheClientFactory of(PnetDataApiLoginMethod loginMethod)
    {
        return of(loginMethod, JacksonPnetDataApiModule.createObjectMapper(), SystemRestLoggerAdapter.INSTANCE);
    }

    public static ApacheClientFactory of(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        return new ApacheClientFactory(loginMethod, mapper, loggerAdapter);
    }

    protected ApacheClientFactory(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        super(loginMethod, mapper, loggerAdapter);
    }

    @Override
    protected RestCallFactory createRestCallFactory(ObjectMapper mapper, RestLoggerAdapter loggerAdapter)
    {
        return ApacheRestCallFactory
            .create(loggerAdapter, mapper)
            .withUserAgent(PnetDataApiUtils.getUserAgent("Apache's HttpClient"));
    }

    @Override
    protected ApacheClientFactory copy(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        return new ApacheClientFactory(loginMethod, mapper, loggerAdapter);
    }
}
