package pnet.data.api.companygroup;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for CompanyGroup.
 *
 * @author cet
 */
@Configuration
@ComponentScan(basePackageClasses = {CompanyGroupPnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class CompanyGroupPnetDataApiClientConfig
{
    // intentionally left blank
}
