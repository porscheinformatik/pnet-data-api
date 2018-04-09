package pnet.data.api.externalbrand;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for ExternalBrand.
 */
@Configuration
@ComponentScan(basePackageClasses = {ExternalBrandPnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class ExternalBrandPnetDataApiClientConfig
{
}
