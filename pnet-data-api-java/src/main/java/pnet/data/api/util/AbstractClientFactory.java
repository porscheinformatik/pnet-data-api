package pnet.data.api.util;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import java.util.function.Supplier;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.client.context.SimplePnetDataApiContext;
import pnet.data.api.client.context.UsernamePasswordCredentials;
import pnet.data.api.client.context.UsernamePasswordPnetDataApiLoginMethod;
import tools.jackson.databind.json.JsonMapper;

/**
 * A factory for clients if you are working without Spring.
 *
 * @param <T> the type itself
 * @author HAM
 */
public abstract class AbstractClientFactory<T extends AbstractClientFactory<T>> extends AbstractClientProvider {

    protected final PnetDataApiLoginMethod loginMethod;
    protected final JsonMapper mapper;
    protected final RestLoggerAdapter loggerAdapter;
    protected final RestCallFactory restCallFactory;
    protected final PnetDataApiContext context;

    protected AbstractClientFactory(
        PnetDataApiLoginMethod loginMethod,
        JsonMapper mapper,
        RestLoggerAdapter loggerAdapter
    ) {
        super();
        this.loginMethod = loginMethod;
        this.mapper = mapper;
        this.loggerAdapter = loggerAdapter;

        restCallFactory = createRestCallFactory(mapper, loggerAdapter);
        context = new SimplePnetDataApiContext(restCallFactory, loginMethod);
    }

    protected abstract RestCallFactory createRestCallFactory(JsonMapper mapper, RestLoggerAdapter loggerAdapter);

    protected abstract T copy(PnetDataApiLoginMethod loginMethod, JsonMapper mapper, RestLoggerAdapter loggerAdapter);

    public T withLoginMethod(PnetDataApiLoginMethod loginMethod) {
        return copy(loginMethod, mapper, loggerAdapter);
    }

    public T withUsernamePassword(String url, Supplier<UsernamePasswordCredentials> usernamePasswordSupplier) {
        return copy(new UsernamePasswordPnetDataApiLoginMethod(url, usernamePasswordSupplier), mapper, loggerAdapter);
    }

    public T withMapper(JsonMapper mapper) {
        return copy(loginMethod, mapper, loggerAdapter);
    }

    public T loggingTo(RestLoggerAdapter loggerAdapter) {
        return copy(loginMethod, mapper, loggerAdapter);
    }

    public T loggingToSlf4J() {
        return loggingTo(new Slf4jRestLoggerAdapter());
    }

    public T loggingToSystemOut() {
        return loggingTo(new SystemRestLoggerAdapter());
    }

    public JsonMapper getMapper() {
        return mapper;
    }

    public RestLoggerAdapter getLoggerAdapter() {
        return loggerAdapter;
    }

    public RestCallFactory getRestCallFactory() {
        return restCallFactory;
    }

    public PnetDataApiContext getContext() {
        return context;
    }
}
