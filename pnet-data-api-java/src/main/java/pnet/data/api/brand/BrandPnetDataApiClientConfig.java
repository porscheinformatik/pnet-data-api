package pnet.data.api.brand;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for Brand.
 */
@Configuration
@ComponentScan(basePackageClasses = {BrandPnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class BrandPnetDataApiClientConfig
{

    // intentionally left blank

}