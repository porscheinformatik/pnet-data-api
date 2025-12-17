package pnet.data.api.apache;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.apache.ApacheRestCallFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.AbstractClientFactory;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * A factory for clients using Apache HTTP client. This class is intended to be used without Spring.
 *
 * @author HAM
 */
public class ApacheClientFactory extends AbstractClientFactory<ApacheClientFactory> {

    public static ApacheClientFactory of(PnetDataApiLoginMethod loginMethod) {
        return of(loginMethod, JacksonPnetDataApiModule.createObjectMapper(), SystemRestLoggerAdapter.INSTANCE);
    }

    public static ApacheClientFactory of(
        PnetDataApiLoginMethod loginMethod,
        ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter
    ) {
        return new ApacheClientFactory(loginMethod, mapper, loggerAdapter);
    }

    protected ApacheClientFactory(
        PnetDataApiLoginMethod loginMethod,
        ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter
    ) {
        super(loginMethod, mapper, loggerAdapter);
    }

    @Override
    protected RestCallFactory createRestCallFactory(ObjectMapper mapper, RestLoggerAdapter loggerAdapter) {
        return ApacheRestCallFactory.create(loggerAdapter, mapper).withUserAgent(
            PnetDataApiUtils.getUserAgent("Apache's HttpClient")
        );
    }

    @Override
    protected ApacheClientFactory copy(
        PnetDataApiLoginMethod loginMethod,
        ObjectMapper mapper,
        RestLoggerAdapter loggerAdapter
    ) {
        return new ApacheClientFactory(loginMethod, mapper, loggerAdapter);
    }
}
