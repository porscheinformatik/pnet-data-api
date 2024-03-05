package pnet.data.api.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.java.JavaRestCallFactory;
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientPrefs;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.client.context.UsernamePasswordCredentials;
import pnet.data.api.client.context.UsernamePasswordPnetDataApiLoginMethod;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.AbstractClientFactory;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * A factory for clients using Java 9. This class is intended to be used without Spring.
 *
 * @author HAM
 */
public class JavaClientFactory extends AbstractClientFactory<JavaClientFactory>
{
    @Deprecated
    public static JavaClientFactory of(String url, String username, String password)
    {
        return of(new MutablePnetDataClientPrefs(url, username, password));
    }

    @Deprecated
    public static JavaClientFactory of(PnetDataClientPrefs prefs)
    {
        return of(prefs, JacksonPnetDataApiModule.createObjectMapper(), SystemRestLoggerAdapter.INSTANCE);
    }

    @Deprecated
    public static JavaClientFactory of(PnetDataClientPrefs prefs, ObjectMapper mapper, RestLoggerAdapter loggerAdapter)
    {
        return new JavaClientFactory(new UsernamePasswordPnetDataApiLoginMethod(prefs.getPnetDataApiUrl(),
            () -> new UsernamePasswordCredentials(prefs.getPnetDataApiUsername(), prefs.getPnetDataApiPassword())),
            mapper, loggerAdapter);
    }

    public static JavaClientFactory of(PnetDataApiLoginMethod loginMethod)
    {
        return of(loginMethod, JacksonPnetDataApiModule.createObjectMapper(), SystemRestLoggerAdapter.INSTANCE);
    }

    public static JavaClientFactory of(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        return new JavaClientFactory(loginMethod, mapper, loggerAdapter);
    }

    protected JavaClientFactory(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        super(loginMethod, mapper, loggerAdapter);
    }

    @Override
    protected RestCallFactory createRestCallFactory(ObjectMapper mapper, RestLoggerAdapter loggerAdapter)
    {
        return JavaRestCallFactory
            .create(loggerAdapter, mapper)
            .withUserAgent(PnetDataApiUtils.getUserAgent("Java's HttpClient"));
    }

    @Override
    protected JavaClientFactory copy(PnetDataApiLoginMethod loginMethod, ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter)
    {
        return new JavaClientFactory(loginMethod, mapper, loggerAdapter);
    }
}
