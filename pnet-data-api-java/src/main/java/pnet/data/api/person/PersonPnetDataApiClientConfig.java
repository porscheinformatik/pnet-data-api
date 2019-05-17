package pnet.data.api.person;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Client for Person.
 */
@Configuration
@ComponentScan(basePackageClasses = {PersonPnetDataApiClientConfig.class})
public class PersonPnetDataApiClientConfig
{
    // intentionally left blank
}