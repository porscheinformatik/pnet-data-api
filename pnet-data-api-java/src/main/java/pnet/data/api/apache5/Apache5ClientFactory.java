package pnet.data.api.apache5;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.apache5.Apache5RestCallFactory;
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientPrefs;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.client.context.UsernamePasswordCredentials;
import pnet.data.api.client.context.UsernamePasswordPnetDataApiLoginMethod;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.AbstractClientFactory;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * A factory for clients using Apache HTTP client 5. This class is intended to be used without Spring.
 *
 * @author HAM
 */
@SuppressWarnings("deprecation")
public class Apache5ClientFactory extends AbstractClientFactory<Apache5ClientFactory>
{
    @Deprecated
    public static Apache5ClientFactory of(String url, String username, String password)
    {
        return of(new MutablePnetDataClientPrefs(url, username, password));
    }

    @Deprecated
    public static Apache5ClientFactory of(PnetDataClientPrefs prefs)
    {
        return of(prefs, JacksonPnetDataApiModule.createObjectMapper(), SystemRestLoggerAdapter.INSTANCE);
    }

    @Deprecated
    public static Apache5ClientFactory of(PnetDataClientPrefs prefs, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        return new Apache5ClientFactory(
            new UsernamePasswordPnetDataApiLoginMethod(prefs.getPnetDataApiUrl(),
                () -> new UsernamePasswordCredentials(prefs.getPnetDataApiUsername(), prefs.getPnetDataApiPassword())),
            mapper, loggerAdapter);
    }

    public static Apache5ClientFactory of(PnetDataApiLoginMethod loginMethod)
    {
        return of(loginMethod, JacksonPnetDataApiModule.createObjectMapper(), SystemRestLoggerAdapter.INSTANCE);
    }

    public static Apache5ClientFactory of(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        return new Apache5ClientFactory(loginMethod, mapper, loggerAdapter);
    }

    protected Apache5ClientFactory(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        super(loginMethod, mapper, loggerAdapter);
    }

    @Override
    protected RestCallFactory createRestCallFactory(ObjectMapper mapper, RestLoggerAdapter loggerAdapter)
    {
        return Apache5RestCallFactory
            .create(loggerAdapter, mapper)
            .withUserAgent(PnetDataApiUtils.getUserAgent("Apache's HttpClient 5"));
    }

    @Override
    protected Apache5ClientFactory copy(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        return new Apache5ClientFactory(loginMethod, mapper, loggerAdapter);
    }
}
