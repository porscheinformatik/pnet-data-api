package pnet.data.api.client;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import org.springframework.core.convert.ConversionService;
import tools.jackson.databind.json.JsonMapper;

/**
 * A class that can be used to configure the PnetDataClient. Define a bean of this type to configure the login method
 * and the RestLoggerAdapter. Configuring the JsonMapper.Builder and the ConversionService is also possible, but not
 * recommended.
 */
public class PnetDataRestCallFactoryConfigurer {

    private final RestLoggerAdapter restLoggerAdapter;
    private final JsonMapper.Builder jsonMapperBuilder;
    private final ConversionService conversionService;

    public PnetDataRestCallFactoryConfigurer() {
        this(null, null, null);
    }

    protected PnetDataRestCallFactoryConfigurer(
        RestLoggerAdapter restLoggerAdapter,
        JsonMapper.Builder jsonMapperBuilder,
        ConversionService conversionService
    ) {
        this.restLoggerAdapter = restLoggerAdapter;
        this.jsonMapperBuilder = jsonMapperBuilder;
        this.conversionService = conversionService;
    }

    public RestLoggerAdapter restLoggerAdapter() {
        return restLoggerAdapter;
    }

    public PnetDataRestCallFactoryConfigurer withRestLoggerAdapter(RestLoggerAdapter restLoggerAdapter) {
        return new PnetDataRestCallFactoryConfigurer(restLoggerAdapter, jsonMapperBuilder, conversionService);
    }

    public JsonMapper.Builder jsonMapperBuilder() {
        return jsonMapperBuilder;
    }

    public PnetDataRestCallFactoryConfigurer withJsonMapperBuilder(JsonMapper.Builder jsonMapperBuilder) {
        return new PnetDataRestCallFactoryConfigurer(restLoggerAdapter, jsonMapperBuilder, conversionService);
    }

    public ConversionService conversionService() {
        return conversionService;
    }

    public PnetDataRestCallFactoryConfigurer withConversionService(ConversionService conversionService) {
        return new PnetDataRestCallFactoryConfigurer(restLoggerAdapter, jsonMapperBuilder, conversionService);
    }
}
