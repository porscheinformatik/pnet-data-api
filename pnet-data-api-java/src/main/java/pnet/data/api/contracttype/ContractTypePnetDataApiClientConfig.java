package pnet.data.api.contracttype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for ContractType.
 *
 * @author cet
 */
@Configuration
@ComponentScan(basePackageClasses = {ContractTypePnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class ContractTypePnetDataApiClientConfig
{
}
