package pnet.data.api.contractstate;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for ContractState.
 *
 * @author cet
 */
@Configuration
@ComponentScan(basePackageClasses = {ContractStatePnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class ContractStatePnetDataApiClientConfig
{
}
