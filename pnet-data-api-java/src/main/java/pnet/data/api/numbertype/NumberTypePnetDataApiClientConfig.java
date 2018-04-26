package pnet.data.api.numbertype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Configuration for {@link NumberTypeDataClient}.
 */
@Configuration
@ComponentScan(basePackageClasses = {NumberTypePnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class NumberTypePnetDataApiClientConfig
{

}
