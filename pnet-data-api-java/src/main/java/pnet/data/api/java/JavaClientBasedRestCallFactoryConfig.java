package pnet.data.api.java;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.java.JavaRestCallFactory;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import pnet.data.api.client.PnetDataRestCallFactoryConfig;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.PnetDataApiUtils;

@Configuration
@Import({ PnetDataRestCallFactoryConfig.class })
public class JavaClientBasedRestCallFactoryConfig {

    @Bean
    public RestCallFactory restCallFactory(
        Optional<Set<? extends Converter<?, ?>>> attributeConverters,
        Optional<RestLoggerAdapter> optionalLoggerAdapter
    ) {
        ConversionService conversionService = createConversionService(attributeConverters);

        RestLoggerAdapter loggerAdapter = optionalLoggerAdapter.orElseGet(() -> {
            if (Slf4jRestLoggerAdapter.isSlf4jAvailable()) {
                return Slf4jRestLoggerAdapter.getDefault();
            }

            return SystemRestLoggerAdapter.INSTANCE;
        });

        return JavaRestCallFactory.create(loggerAdapter, createObjectMapper())
            .withUserAgent(PnetDataApiUtils.getUserAgent("Java's HttpClient"))
            .withFormatter(new SpringRestFormatter(conversionService));
    }

    protected ObjectMapper createObjectMapper() {
        return JacksonPnetDataApiModule.createObjectMapper();
    }

    protected ConversionService createConversionService(Optional<Set<? extends Converter<?, ?>>> attributeConverters) {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();

        attributeConverters.ifPresent(conversionServiceFactoryBean::setConverters);

        conversionServiceFactoryBean.afterPropertiesSet();

        return conversionServiceFactoryBean.getObject();
    }
}
