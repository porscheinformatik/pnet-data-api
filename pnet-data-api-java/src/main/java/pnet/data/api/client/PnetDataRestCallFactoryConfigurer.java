package pnet.data.api.client;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.ConversionService;

/**
 * A class that can be used to configure the PnetDataClient. Define a bean of this type to configure the login method
 * and the RestLoggerAdapter. Configuring the ObjectMapper and the ConversionService is also possible, but not
 * recommended.
 */
public class PnetDataRestCallFactoryConfigurer {

    private final RestLoggerAdapter restLoggerAdapter;
    private final ObjectMapper objectMapper;
    private final ConversionService conversionService;

    public PnetDataRestCallFactoryConfigurer() {
        this(null, null, null);
    }

    protected PnetDataRestCallFactoryConfigurer(
        RestLoggerAdapter restLoggerAdapter,
        ObjectMapper objectMapper,
        ConversionService conversionService
    ) {
        this.restLoggerAdapter = restLoggerAdapter;
        this.objectMapper = objectMapper;
        this.conversionService = conversionService;
    }

    public RestLoggerAdapter restLoggerAdapter() {
        return restLoggerAdapter;
    }

    public PnetDataRestCallFactoryConfigurer withRestLoggerAdapter(RestLoggerAdapter restLoggerAdapter) {
        return new PnetDataRestCallFactoryConfigurer(restLoggerAdapter, objectMapper, conversionService);
    }

    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    public PnetDataRestCallFactoryConfigurer withObjectMapper(ObjectMapper objectMapper) {
        return new PnetDataRestCallFactoryConfigurer(restLoggerAdapter, objectMapper, conversionService);
    }

    public ConversionService conversionService() {
        return conversionService;
    }

    public PnetDataRestCallFactoryConfigurer withConversionService(ConversionService conversionService) {
        return new PnetDataRestCallFactoryConfigurer(restLoggerAdapter, objectMapper, conversionService);
    }
}
