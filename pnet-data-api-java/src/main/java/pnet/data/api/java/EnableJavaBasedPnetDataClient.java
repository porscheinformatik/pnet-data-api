package pnet.data.api.java;

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
@Import({ JavaClientBasedRestCallFactoryConfig.class, PnetDataClientConfig.class })
public @interface EnableJavaBasedPnetDataClient {
    // intentionally left blank
}
