package pnet.data.api.numbertype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Updated upstream Configuration for {@link NumberTypeDataClient}.
 *
 * @author cet
 */
@Configuration
@ComponentScan(basePackageClasses = { NumberTypePnetDataApiClientConfig.class })
public class NumberTypePnetDataApiClientConfig {
    // intentionally left blank
}
