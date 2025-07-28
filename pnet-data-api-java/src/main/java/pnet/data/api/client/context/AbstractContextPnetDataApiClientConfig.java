package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pnet.data.api.GeoDistance;
import pnet.data.api.client.PnetDataClientPrefs;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.PnetDataApiUtils;

@Configuration
@ComponentScan(basePackageClasses = { AbstractContextPnetDataApiClientConfig.class })
@SuppressWarnings("deprecation")
public abstract class AbstractContextPnetDataApiClientConfig {

    @Bean
    public PnetDataApiContext pnetDataApiContext(
        RestCallFactory restCallFactory,
        Optional<PnetDataApiLoginMethod> loginMethod,
        Optional<PnetDataApiTokenRepository> tokenRepository,
        Optional<PnetDataClientPrefs> prefs
    ) {
        if (loginMethod.isPresent()) {
            return new SimplePnetDataApiContext(restCallFactory, loginMethod.get());
        }

        if (prefs.isPresent()) {
            // both variants are deprecated

            if (tokenRepository.isPresent()) {
                return new PrefsBasedPnetDataApiContext(tokenRepository.get(), prefs.get());
            }

            return new SimplePnetDataApiContext(
                restCallFactory,
                new UsernamePasswordPnetDataApiLoginMethod(prefs.get().getPnetDataApiUrl(), () ->
                    new UsernamePasswordCredentials(
                        prefs.get().getPnetDataApiUsername(),
                        prefs.get().getPnetDataApiPassword()
                    )
                )
            );
        }

        throw new IllegalArgumentException(
            "Please provide a PnetDataApiLoginMethod bean. " +
            "See https://github.com/porscheinformatik/pnet-data-api/tree/master/pnet-data-api-java#readme " +
            "for more inforation."
        );
    }

    @Bean
    public Converter<LocalDateTime, String> localDateTimeToStringConverter() {
        // do not convert this to a Lambda operation, otherwise Spring get's confused!
        return new Converter<>() {
            private final ZoneId systemDefault = ZoneId.systemDefault();
            private final ZoneId utc = ZoneId.of("UTC");
            private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

            @Override
            public String convert(LocalDateTime source) {
                ZonedDateTime zonedDate = source.atZone(systemDefault).withZoneSameInstant(utc);

                return zonedDate.toLocalDateTime().format(formatter) + "Z";
            }
        };
    }

    @Bean
    public Converter<GeoDistance, String> geoDistanceToStringConverter() {
        // do not convert this to a Lambda operation, otherwise Spring get's confused!
        return new Converter<>() {
            private final Locale locale = Locale.ENGLISH;

            @Override
            public String convert(GeoDistance source) {
                return String.format(
                    locale,
                    "{\"lat\":%.6f,\"lon\":%.6f,\"distance\":%.6f}",
                    source.getLat(),
                    source.getLon(),
                    source.getDistance()
                );
            }
        };
    }

    @Bean
    public RestCallFactory restCallFactory(
        Optional<Set<? extends Converter<?, ?>>> attributeConverters,
        Optional<RestLoggerAdapter> optionalLoggerAdapter
    ) {
        RestTemplate restTemplate = createRestTemplate();
        ConversionService conversionService = createConversionService(attributeConverters);

        RestLoggerAdapter loggerAdapter = optionalLoggerAdapter.orElseGet(() -> {
            if (Slf4jRestLoggerAdapter.isSlf4jAvailable()) {
                return Slf4jRestLoggerAdapter.getDefault();
            }

            return SystemRestLoggerAdapter.INSTANCE;
        });

        return createSpringRestCallFactory(restTemplate, loggerAdapter, conversionService);
    }

    protected RestTemplate createRestTemplate() {
        ObjectMapper objectMapper = createObjectMapper();

        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory =
            (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();

        requestFactory.setReadTimeout(30000);
        requestFactory.setConnectTimeout(2000);

        for (HttpMessageConverter<?> elem : restTemplate.getMessageConverters()) {
            if (elem.getClass().equals(MappingJackson2HttpMessageConverter.class)) {
                ((MappingJackson2HttpMessageConverter) elem).setObjectMapper(objectMapper);
                break;
            }
        }

        restTemplate
            .getInterceptors()
            .add((request, body, execution) -> {
                request.getHeaders().add("user-agent", PnetDataApiUtils.getUserAgent("Spring's RestTemplate"));

                return execution.execute(request, body);
            });

        return restTemplate;
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

    protected abstract RestCallFactory createSpringRestCallFactory(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        ConversionService conversionService
    );
}
