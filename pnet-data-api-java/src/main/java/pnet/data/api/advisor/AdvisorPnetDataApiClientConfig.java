package pnet.data.api.advisor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for Advisor.
 */
@Configuration
@ComponentScan(basePackageClasses = {AdvisorPnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class AdvisorPnetDataApiClientConfig
{
}