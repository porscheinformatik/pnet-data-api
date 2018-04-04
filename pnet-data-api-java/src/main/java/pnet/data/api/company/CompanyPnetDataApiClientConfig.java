package pnet.data.api.company;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for Company.
 */
@Configuration
@ComponentScan(basePackageClasses = {CompanyPnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class CompanyPnetDataApiClientConfig
{

    // intentionally left blank

}