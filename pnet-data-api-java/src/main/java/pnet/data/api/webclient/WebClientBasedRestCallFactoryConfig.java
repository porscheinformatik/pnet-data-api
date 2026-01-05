package pnet.data.api.webclient;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import at.porscheinformatik.happyrest.spring.WebClientRestCallFactory;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.codec.json.JacksonJsonDecoder;
import org.springframework.http.codec.json.JacksonJsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import pnet.data.api.client.PnetDataRestCallFactoryConfig;
import pnet.data.api.util.PnetDataApiUtils;
import tools.jackson.databind.json.JsonMapper;

@Configuration
@Import({ PnetDataRestCallFactoryConfig.class })
public class WebClientBasedRestCallFactoryConfig {

    @Bean
    public RestCallFactory webClientBasedRestCallFactory(
        @Qualifier("pnetDataApiWebClient") WebClient webClient,
        @Qualifier("pnetDataApiConversionService") ConversionService conversionService,
        @Qualifier("pnetDataApiRestLoggerAdapter") Optional<RestLoggerAdapter> optionalLoggerAdapter
    ) {
        RestLoggerAdapter loggerAdapter = optionalLoggerAdapter.orElseGet(() -> {
            if (Slf4jRestLoggerAdapter.isSlf4jAvailable()) {
                return Slf4jRestLoggerAdapter.getDefault();
            }

            return SystemRestLoggerAdapter.INSTANCE;
        });

        return new WebClientRestCallFactory(webClient, loggerAdapter, new SpringRestFormatter(conversionService));
    }

    @Bean
    public WebClient pnetDataApiWebClient(
        @Qualifier("pnetDataApiJsonMapperBuilder") JsonMapper.Builder jsonMapperBuilder
    ) {
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
}
