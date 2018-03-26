package pnet.data.api.function;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for Function.
 */
@Configuration
@ComponentScan(basePackageClasses = {FunctionPnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class FunctionPnetDataApiClientConfig
{

    // intentionally left blank

}