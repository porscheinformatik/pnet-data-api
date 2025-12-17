package pnet.data.api.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import pnet.data.api.resttemplate.RestTemplateBasedRestCallFactoryConfig;

/**
 * Enables the PnetDataApiClient module in a Spring context.
 *
 * @deprecated since 2.13.x use specific configurations like {@link pnet.data.api.resttemplate.RestTemplateBasedRestCallFactoryConfig} or
 * {@link pnet.data.api.webclient.WebClientBasedRestCallFactoryConfig} instead
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ RestTemplateBasedRestCallFactoryConfig.class, PnetDataClientConfig.class })
@Deprecated(since = "2.13.x")
public @interface EnablePnetDataClient {
    // intentionally left blank
}
