package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.SpringRestCallFactory;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration for context-based PnetDataApiClient using Spring's RestTemplate.
 *
 * @deprecated since 2.13.x use {@link pnet.data.api.resttemplate.RestTemplateBasedRestCallFactoryConfig} instead
 */
@Configuration
@ComponentScan(basePackageClasses = { ContextPnetDataApiClientConfig.class })
@Deprecated(since = "2.13.x")
public class ContextPnetDataApiClientConfig extends AbstractContextPnetDataApiClientConfig {

    @Override
    protected RestCallFactory createSpringRestCallFactory(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        ConversionService conversionService
    ) {
        return new SpringRestCallFactory(restTemplate, loggerAdapter, new SpringRestFormatter(conversionService));
    }
}
