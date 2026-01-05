package pnet.data.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import pnet.data.api.GeoDistance;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;

@Configuration
public class PnetDataRestCallFactoryConfig {

    @Bean
    public ObjectMapper pnetDataApiObjectMapper() {
        return JacksonPnetDataApiModule.createObjectMapper();
    }

    @Bean
    public ConversionService pnetDataApiConversionService(
        Optional<Set<? extends Converter<?, ?>>> attributeConverters
    ) {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();

        attributeConverters.ifPresent(conversionServiceFactoryBean::setConverters);

        conversionServiceFactoryBean.afterPropertiesSet();

        return conversionServiceFactoryBean.getObject();
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
}
