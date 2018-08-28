package pnet.data.api.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for Application.
 *
 * @author cet
 */
@Configuration
@ComponentScan(basePackageClasses = {ApplicationPnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class ApplicationPnetDataApiClientConfig
{
    // intentionally left blank
}
