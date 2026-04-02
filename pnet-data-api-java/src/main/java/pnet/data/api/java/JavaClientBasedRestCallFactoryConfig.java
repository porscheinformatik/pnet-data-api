package pnet.data.api.java;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.java.JavaRestCallFactory;
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
public class JavaClientBasedRestCallFactoryConfig extends PnetDataRestCallFactoryConfig {

    protected JavaClientBasedRestCallFactoryConfig(
        Optional<PnetDataRestCallFactoryConfigurer> configurer,
        Optional<RestLoggerAdapter> loggerAdapter,
        Optional<Set<? extends Converter<?, ?>>> attributeConverters
    ) {
        super(configurer, loggerAdapter, attributeConverters);
    }

    @Bean
    public RestCallFactory javaClientBasedRestCallFactory() {
        JsonMapper.Builder jsonMapperBuilder = jsonMapperBuilder();
        RestLoggerAdapter loggerAdapter = restLoggerAdapter();
        ConversionService conversionService = conversionService();

        return JavaRestCallFactory.create(loggerAdapter, jsonMapperBuilder.build())
            .withUserAgent(PnetDataApiUtils.getUserAgent("Java's HttpClient"))
            .withFormatter(new SpringRestFormatter(conversionService));
    }
}
