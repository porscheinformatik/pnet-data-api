package pnet.data.api.client;

import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import pnet.data.api.GeoDistance;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import tools.jackson.databind.json.JsonMapper;

public abstract class PnetDataRestCallFactoryConfig {

    private final Optional<PnetDataRestCallFactoryConfigurer> configurer;
    private final Optional<RestLoggerAdapter> loggerAdapter;
    private final Optional<Set<? extends Converter<?, ?>>> attributeConverters;

    protected PnetDataRestCallFactoryConfig(
        Optional<PnetDataRestCallFactoryConfigurer> configurer,
        Optional<RestLoggerAdapter> loggerAdapter,
        Optional<Set<? extends Converter<?, ?>>> attributeConverters
    ) {
        this.configurer = configurer;
        this.loggerAdapter = loggerAdapter;
        this.attributeConverters = attributeConverters;
    }

    protected RestLoggerAdapter restLoggerAdapter() {
        RestLoggerAdapter adapter = configurer.map(PnetDataRestCallFactoryConfigurer::restLoggerAdapter).orElse(null);

        if (adapter == null) {
            adapter = loggerAdapter.orElse(null);
        }

        if (adapter == null) {
            adapter = createDefaultRestLoggerAdapter();
        }

        return adapter;
    }

    private RestLoggerAdapter createDefaultRestLoggerAdapter() {
        if (Slf4jRestLoggerAdapter.isSlf4jAvailable()) {
            return Slf4jRestLoggerAdapter.getDefault();
        }

        return SystemRestLoggerAdapter.INSTANCE;
    }

    protected JsonMapper.Builder jsonMapperBuilder() {
        return configurer
            .map(PnetDataRestCallFactoryConfigurer::jsonMapperBuilder)
            .orElseGet(this::createDefaultJsonMapperBuilder);
    }

    private JsonMapper.Builder createDefaultJsonMapperBuilder() {
        return JacksonPnetDataApiModule.buildJsonMapper();
    }

    protected ConversionService conversionService() {
        return configurer
            .map(PnetDataRestCallFactoryConfigurer::conversionService)
            .orElseGet(this::createDefaultConversionService);
    }

    private ConversionService createDefaultConversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();

        Set<Converter<?, ?>> converters = new HashSet<>(attributeConverters.orElseGet(Set::of));

        converters.add(localDateTimeToStringConverter());
        converters.add(geoDistanceToStringConverter());

        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();

        return conversionServiceFactoryBean.getObject();
    }

    private Converter<LocalDateTime, String> localDateTimeToStringConverter() {
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

    private Converter<GeoDistance, String> geoDistanceToStringConverter() {
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
}
