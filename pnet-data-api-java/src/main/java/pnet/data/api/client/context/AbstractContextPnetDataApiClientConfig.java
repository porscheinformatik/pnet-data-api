package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.client.RestTemplate;
import pnet.data.api.resttemplate.RestTemplateBasedRestCallFactoryConfig;

/**
 * Abstract base class for context-based PnetDataApiClient configurations using Spring's RestTemplate.
 *
 * @deprecated since 2.13.x use {@link pnet.data.api.resttemplate.RestTemplateBasedRestCallFactoryConfig} instead
 */
@Configuration
@ComponentScan(basePackageClasses = { AbstractContextPnetDataApiClientConfig.class })
@Import(RestTemplateBasedRestCallFactoryConfig.class)
@Deprecated(since = "2.13.x")
public abstract class AbstractContextPnetDataApiClientConfig extends PnetDataApiClientContextConfig {

    protected abstract RestCallFactory createSpringRestCallFactory(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        ConversionService conversionService
    );
}
