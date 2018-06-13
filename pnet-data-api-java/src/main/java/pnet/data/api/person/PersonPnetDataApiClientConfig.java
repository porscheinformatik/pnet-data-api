package pnet.data.api.person;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.client.context.ContextPnetDataApiClientConfig;

/**
 * Client for Person.
 */
@Configuration
@ComponentScan(basePackageClasses = {PersonPnetDataApiClientConfig.class})
@Import(ContextPnetDataApiClientConfig.class)
public class PersonPnetDataApiClientConfig
{
    // intentionally left blank
}