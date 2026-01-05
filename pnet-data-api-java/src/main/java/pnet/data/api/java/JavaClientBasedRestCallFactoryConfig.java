package pnet.data.api.java;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.java.JavaRestCallFactory;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import pnet.data.api.client.PnetDataRestCallFactoryConfig;
import pnet.data.api.util.PnetDataApiUtils;
import tools.jackson.databind.json.JsonMapper;

@Configuration
@Import({ PnetDataRestCallFactoryConfig.class })
public class JavaClientBasedRestCallFactoryConfig {

    @Bean
    public RestCallFactory javaClientBasedRestCallFactory(
        @Qualifier("pnetDataApiJsonMapperBuilder") JsonMapper.Builder jsonMapperBuilder,
        @Qualifier("pnetDataApiConversionService") ConversionService conversionService,
        @Qualifier("pnetDataApiRestLoggerAdapter") Optional<RestLoggerAdapter> optionalLoggerAdapter
    ) {
        RestLoggerAdapter loggerAdapter = optionalLoggerAdapter.orElseGet(() -> {
            if (Slf4jRestLoggerAdapter.isSlf4jAvailable()) {
                return Slf4jRestLoggerAdapter.getDefault();
            }

            return SystemRestLoggerAdapter.INSTANCE;
        });

        return JavaRestCallFactory.create(loggerAdapter, jsonMapperBuilder.build())
            .withUserAgent(PnetDataApiUtils.getUserAgent("Java's HttpClient"))
            .withFormatter(new SpringRestFormatter(conversionService));
    }
}
