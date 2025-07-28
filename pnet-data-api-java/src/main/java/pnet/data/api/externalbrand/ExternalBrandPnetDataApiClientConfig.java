package pnet.data.api.externalbrand;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Client for ExternalBrand.
 *
 * @author cet
 */
@Configuration
@ComponentScan(basePackageClasses = { ExternalBrandPnetDataApiClientConfig.class })
public class ExternalBrandPnetDataApiClientConfig {
    // intentionally left blank
}
