package pnet.data.api.resttemplate;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.RestTemplateRestCallFactory;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pnet.data.api.client.PnetDataRestCallFactoryConfig;
import pnet.data.api.client.PnetDataRestCallFactoryConfigurer;
import pnet.data.api.util.PnetDataApiUtils;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class RestTemplateBasedRestCallFactoryConfig extends PnetDataRestCallFactoryConfig {

    protected RestTemplateBasedRestCallFactoryConfig(
        Optional<PnetDataRestCallFactoryConfigurer> configurer,
        Optional<RestLoggerAdapter> loggerAdapter,
        Optional<Set<? extends Converter<?, ?>>> attributeConverters
    ) {
        super(configurer, loggerAdapter, attributeConverters);
    }

    @Bean
    public RestCallFactory restTemplateBasedRestCallFactory() {
        RestTemplate restTemplate = restTemplate();
        RestLoggerAdapter loggerAdapter = restLoggerAdapter();
        ConversionService conversionService = conversionService();

        return new RestTemplateRestCallFactory(restTemplate, loggerAdapter, new SpringRestFormatter(conversionService));
    }

    protected RestTemplate restTemplate() {
        JsonMapper.Builder jsonMapperBuilder = jsonMapperBuilder();
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
