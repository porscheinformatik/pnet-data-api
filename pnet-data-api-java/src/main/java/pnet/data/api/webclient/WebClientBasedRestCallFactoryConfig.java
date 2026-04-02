package pnet.data.api.webclient;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import at.porscheinformatik.happyrest.spring.WebClientRestCallFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import pnet.data.api.client.PnetDataRestCallFactoryConfig;
import pnet.data.api.client.PnetDataRestCallFactoryConfigurer;
import pnet.data.api.util.PnetDataApiUtils;

@Configuration
public class WebClientBasedRestCallFactoryConfig extends PnetDataRestCallFactoryConfig {

    protected WebClientBasedRestCallFactoryConfig(
        Optional<PnetDataRestCallFactoryConfigurer> configurer,
        Optional<RestLoggerAdapter> loggerAdapter,
        Optional<Set<? extends Converter<?, ?>>> attributeConverters
    ) {
        super(configurer, loggerAdapter, attributeConverters);
    }

    @Bean
    public RestCallFactory webClientBasedRestCallFactory() {
        WebClient webClient = webClient();
        RestLoggerAdapter loggerAdapter = restLoggerAdapter();
        ConversionService conversionService = conversionService();

        return new WebClientRestCallFactory(webClient, loggerAdapter, new SpringRestFormatter(conversionService));
    }

    protected WebClient webClient() {
        ObjectMapper objectMapper = objectMapper();
        ExchangeStrategies strategies = ExchangeStrategies.builder()
            .codecs(configurer -> {
                configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
            })
            .build();

        return WebClient.builder()
            .exchangeStrategies(strategies)
            .defaultHeader("user-agent", PnetDataApiUtils.getUserAgent("Spring's WebClient"))
            .build();
    }
}
