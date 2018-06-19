package pnet.data.api.companygrouptype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for CompanyGroupType.
 *
 * @author cet
 */
@Configuration
@ComponentScan(basePackageClasses = {CompanyGroupTypePnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class CompanyGroupTypePnetDataApiClientConfig
{
}
