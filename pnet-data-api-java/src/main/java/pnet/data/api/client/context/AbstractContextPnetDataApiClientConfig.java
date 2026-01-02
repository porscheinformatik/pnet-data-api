package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
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
import org.springframework.web.client.RestTemplate;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import pnet.data.api.util.PnetDataApiUtils;
import tools.jackson.databind.json.JsonMapper;

/**
 * Abstract base class for context-based PnetDataApiClient configurations using Spring's RestTemplate.
 *
 * @deprecated since 2.13.x use {@link pnet.data.api.resttemplate.RestTemplateBasedRestCallFactoryConfig} instead
 */
@Configuration
@ComponentScan(basePackageClasses = { AbstractContextPnetDataApiClientConfig.class })
@Deprecated(since = "2.13.x")
public abstract class AbstractContextPnetDataApiClientConfig extends PnetDataApiClientContextConfig {

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
        JsonMapper jsonMapper = createJsonMapper();

        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory =
            (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();

        requestFactory.setReadTimeout(30000);
        requestFactory.setConnectTimeout(2000);

        for (HttpMessageConverter<?> elem : restTemplate.getMessageConverters()) {
            System.err.println("DEBUG HttpMessageConverter: " + elem.getClass());
            // if (elem.getClass().equals(MappingJackson2HttpMessageConverter.class)) {
            //     ((MappingJackson2HttpMessageConverter) elem).setJsonMapper(jsonMapper);
            //     break;
            // }
        }

        restTemplate
            .getInterceptors()
            .add((request, body, execution) -> {
                request.getHeaders().add("user-agent", PnetDataApiUtils.getUserAgent("Spring's RestTemplate"));

                return execution.execute(request, body);
            });

        return restTemplate;
    }

    protected JsonMapper createJsonMapper() {
        return JacksonPnetDataApiModule.createJsonMapper();
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
