package pnet.data.api.apache;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.apache.ApacheRestCallFactory;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import pnet.data.api.client.PnetDataRestCallFactoryConfig;
import pnet.data.api.client.PnetDataRestCallFactoryConfigurer;
import pnet.data.api.util.PnetDataApiUtils;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class ApacheHttpClientBasedRestCallFactoryConfig extends PnetDataRestCallFactoryConfig {

    protected ApacheHttpClientBasedRestCallFactoryConfig(
        Optional<PnetDataRestCallFactoryConfigurer> configurer,
        Optional<RestLoggerAdapter> loggerAdapter,
        Optional<Set<? extends Converter<?, ?>>> attributeConverters
    ) {
        super(configurer, loggerAdapter, attributeConverters);
    }

    @Bean
    public RestCallFactory apacheHttpClientBasedRestCallFactory() {
        JsonMapper.Builder jsonMapperBuilder = jsonMapperBuilder();
        RestLoggerAdapter loggerAdapter = restLoggerAdapter();
        ConversionService conversionService = conversionService();

        return ApacheRestCallFactory.create(loggerAdapter, jsonMapperBuilder.build())
            .withUserAgent(PnetDataApiUtils.getUserAgent("Apache's HttpClient"))
            .withFormatter(new SpringRestFormatter(conversionService));
    }
}
