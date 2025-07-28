package pnet.data.api.legalform;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Client for legal forms.
 *
 * @author ham
 */
@Configuration
@ComponentScan(basePackageClasses = { LegalFormPnetDataApiClientConfig.class })
public class LegalFormPnetDataApiClientConfig {
    // intentionally left blank
}
