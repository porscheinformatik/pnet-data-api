package pnet.data.api.client.companytype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * <pre>
 *           ___
 *         O(o o)O
 *           (_)     - Ook!
 * </pre>
 */
@Configuration
@ComponentScan(basePackageClasses = {CompanyTypePnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class CompanyTypePnetDataApiClientConfig
{

    // intentionally left blank

}
