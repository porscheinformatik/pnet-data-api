package pnet.data.api.numbertype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * <<<<<<< Updated upstream Configuration for {@link NumberTypeDataClient}. =======
 *
 * @author cet
 *
 *         >>>>>>> Stashed changes
 */
@Configuration
@ComponentScan(basePackageClasses = {NumberTypePnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class NumberTypePnetDataApiClientConfig
{
}
