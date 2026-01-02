package pnet.data.api.webclient;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import at.porscheinformatik.happyrest.spring.WebClientRestCallFactory;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.codec.json.JacksonJsonDecoder;
import org.springframework.http.codec.json.JacksonJsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import pnet.data.api.client.PnetDataRestCallFactoryConfig;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.PnetDataApiUtils;
import tools.jackson.databind.json.JsonMapper.Builder;

@Configuration
@Import({ PnetDataRestCallFactoryConfig.class })
public class WebClientBasedRestCallFactoryConfig {

    @Bean
    public RestCallFactory restCallFactory(
        Optional<Set<? extends Converter<?, ?>>> attributeConverters,
        Optional<RestLoggerAdapter> optionalLoggerAdapter
    ) {
        WebClient webClient = createWebClient();
        ConversionService conversionService = createConversionService(attributeConverters);

        RestLoggerAdapter loggerAdapter = optionalLoggerAdapter.orElseGet(() -> {
            if (Slf4jRestLoggerAdapter.isSlf4jAvailable()) {
                return Slf4jRestLoggerAdapter.getDefault();
            }

            return SystemRestLoggerAdapter.INSTANCE;
        });

        return new WebClientRestCallFactory(webClient, loggerAdapter, new SpringRestFormatter(conversionService));
    }

    protected WebClient createWebClient() {
        Builder jsonMapperBuilder = buildJsonMapper();

        ExchangeStrategies strategies = ExchangeStrategies.builder()
            .codecs(configurer -> {
                configurer.defaultCodecs().jacksonJsonEncoder(new JacksonJsonEncoder(jsonMapperBuilder));
                configurer.defaultCodecs().jacksonJsonDecoder(new JacksonJsonDecoder(jsonMapperBuilder));
            })
            .build();

        return WebClient.builder()
            .exchangeStrategies(strategies)
            .defaultHeader("user-agent", PnetDataApiUtils.getUserAgent("Spring's WebClient"))
            .build();
    }

    protected Builder buildJsonMapper() {
        return JacksonPnetDataApiModule.buildJsonMapper();
    }

    protected ConversionService createConversionService(Optional<Set<? extends Converter<?, ?>>> attributeConverters) {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();

        attributeConverters.ifPresent(conversionServiceFactoryBean::setConverters);

        conversionServiceFactoryBean.afterPropertiesSet();

        return conversionServiceFactoryBean.getObject();
    }
}
