package pnet.data.api.advisortype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for AdvisorType.
 * 
 * @author cet
 */
@Configuration
@ComponentScan(basePackageClasses = {AdvisorTypePnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class AdvisorTypePnetDataApiClientConfig
{
}
