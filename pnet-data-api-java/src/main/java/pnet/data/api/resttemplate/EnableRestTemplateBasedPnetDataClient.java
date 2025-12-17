package pnet.data.api.resttemplate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import pnet.data.api.client.PnetDataClientConfig;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ RestTemplateBasedRestCallFactoryConfig.class, PnetDataClientConfig.class })
public @interface EnableRestTemplateBasedPnetDataClient {
    // intentionally left blank
}
