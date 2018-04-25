package pnet.data.api.companynumbertype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for CompanyNumberType.
 */
@Configuration
@ComponentScan(basePackageClasses = {CompanyNumberTypePnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class CompanyNumberTypePnetDataApiClientConfig
{
}
