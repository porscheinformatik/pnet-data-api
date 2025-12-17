package pnet.data.api.resttemplate;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.RestTemplateRestCallFactory;
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
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pnet.data.api.client.PnetDataRestCallFactoryConfig;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.PnetDataApiUtils;

@Configuration
@Import({ PnetDataRestCallFactoryConfig.class })
public class RestTemplateBasedRestCallFactoryConfig {

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

        return new RestTemplateRestCallFactory(restTemplate, loggerAdapter, new SpringRestFormatter(conversionService));
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
}
