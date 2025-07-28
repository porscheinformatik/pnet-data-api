package pnet.data.api.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Client for Application.
 *
 * @author cet
 */
@Configuration
@ComponentScan(basePackageClasses = { ApplicationPnetDataApiClientConfig.class })
public class ApplicationPnetDataApiClientConfig {
    // intentionally left blank
}
