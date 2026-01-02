package pnet.data.api.apache5;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.apache5.Apache5RestCallFactory;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.AbstractClientFactory;
import pnet.data.api.util.PnetDataApiUtils;
import tools.jackson.databind.json.JsonMapper;

/**
 * A factory for clients using Apache HTTP client 5. This class is intended to be used without Spring.
 *
 * @author HAM
 */
public class Apache5ClientFactory extends AbstractClientFactory<Apache5ClientFactory> {

    public static Apache5ClientFactory of(PnetDataApiLoginMethod loginMethod) {
        return of(loginMethod, JacksonPnetDataApiModule.createJsonMapper(), SystemRestLoggerAdapter.INSTANCE);
    }

    public static Apache5ClientFactory of(
        PnetDataApiLoginMethod loginMethod,
        JsonMapper mapper,
        RestLoggerAdapter loggerAdapter
    ) {
        return new Apache5ClientFactory(loginMethod, mapper, loggerAdapter);
    }

    protected Apache5ClientFactory(
        PnetDataApiLoginMethod loginMethod,
        JsonMapper mapper,
        RestLoggerAdapter loggerAdapter
    ) {
        super(loginMethod, mapper, loggerAdapter);
    }

    @Override
    protected RestCallFactory createRestCallFactory(JsonMapper mapper, RestLoggerAdapter loggerAdapter) {
        return Apache5RestCallFactory.create(loggerAdapter, mapper).withUserAgent(
            PnetDataApiUtils.getUserAgent("Apache's HttpClient 5")
        );
    }

    @Override
    protected Apache5ClientFactory copy(
        PnetDataApiLoginMethod loginMethod,
        JsonMapper mapper,
        RestLoggerAdapter loggerAdapter
    ) {
        return new Apache5ClientFactory(loginMethod, mapper, loggerAdapter);
    }
}
