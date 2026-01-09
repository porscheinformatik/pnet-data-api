package pnet.data.api.resttemplate;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.RestTemplateRestCallFactory;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pnet.data.api.client.PnetDataRestCallFactoryConfig;
import pnet.data.api.util.PnetDataApiUtils;
import tools.jackson.databind.json.JsonMapper;

@Configuration
@Import({ PnetDataRestCallFactoryConfig.class })
public class RestTemplateBasedRestCallFactoryConfig {

    @Bean
    public RestCallFactory restTemplateBasedRestCallFactory(
        @Qualifier("pnetDataApiRestTemplate") RestTemplate restTemplate,
        @Qualifier("pnetDataApiConversionService") ConversionService conversionService,
        @Qualifier("pnetDataApiRestLoggerAdapter") Optional<RestLoggerAdapter> optionalLoggerAdapter
    ) {
        RestLoggerAdapter loggerAdapter = optionalLoggerAdapter.orElseGet(() -> {
            if (Slf4jRestLoggerAdapter.isSlf4jAvailable()) {
                return Slf4jRestLoggerAdapter.getDefault();
            }

            return SystemRestLoggerAdapter.INSTANCE;
        });

        return new RestTemplateRestCallFactory(restTemplate, loggerAdapter, new SpringRestFormatter(conversionService));
    }

    @Bean
    public RestTemplate pnetDataApiRestTemplate(
        @Qualifier("pnetDataApiJsonMapperBuilder") JsonMapper.Builder jsonMapperBuilder
    ) {
        HttpMessageConverters messageConverters = HttpMessageConverters.forClient()
            .registerDefaults()
            .withJsonConverter(new JacksonJsonHttpMessageConverter(jsonMapperBuilder.build()))
            .build();

        RestTemplate restTemplate = new RestTemplate(messageConverters);

        SimpleClientHttpRequestFactory requestFactory =
            (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();

        requestFactory.setReadTimeout(30000);
        requestFactory.setConnectTimeout(2000);

        restTemplate
            .getInterceptors()
            .add((request, body, execution) -> {
                request.getHeaders().add("user-agent", PnetDataApiUtils.getUserAgent("Spring's RestTemplate"));

                return execution.execute(request, body);
            });

        return restTemplate;
    }
}
