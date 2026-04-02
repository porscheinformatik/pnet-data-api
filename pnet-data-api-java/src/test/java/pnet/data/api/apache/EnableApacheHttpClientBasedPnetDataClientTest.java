package pnet.data.api.apache;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pnet.data.api.client.PnetDataRestCallFactoryConfigurer;
import pnet.data.api.client.context.PnetDataApiLoginMethod;

class EnableApacheHttpClientBasedPnetDataClientTest {

    @Configuration
    @EnableApacheHttpClientBasedPnetDataClient
    static class TestConfigWithLoginMethod {

        @Bean
        public PnetDataApiLoginMethod loginMethod() throws Exception {
            PnetDataApiLoginMethod loginMethod = Mockito.mock(PnetDataApiLoginMethod.class);
            RestCall restCall = Mockito.mock(RestCall.class);

            Mockito.when(loginMethod.performLogin(ArgumentMatchers.any(RestCallFactory.class))).thenReturn(restCall);

            return loginMethod;
        }
    }

    @Configuration
    @EnableApacheHttpClientBasedPnetDataClient
    static class TestConfigWithConfigurer extends TestConfigWithLoginMethod {

        @Bean
        public PnetDataRestCallFactoryConfigurer configurer() {
            return new PnetDataRestCallFactoryConfigurer();
        }
    }

    @Test
    void test_withLoginMethod_restCallFactoryIsInitialized() {
        try (
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                TestConfigWithLoginMethod.class
            )
        ) {
            MatcherAssert.assertThat(
                "RestCallFactory should not be null",
                context.getBean(RestCallFactory.class),
                Matchers.notNullValue()
            );
            MatcherAssert.assertThat(
                "LoginMethod should not be null",
                context.getBean(PnetDataApiLoginMethod.class),
                Matchers.notNullValue()
            );
        }
    }

    @Test
    void test_withConfigurer_restCallFactoryIsInitialized() {
        try (
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                TestConfigWithConfigurer.class
            )
        ) {
            MatcherAssert.assertThat(
                "RestCallFactory should not be null",
                context.getBean(RestCallFactory.class),
                Matchers.notNullValue()
            );
            MatcherAssert.assertThat(
                "LoginMethod should not be null",
                context.getBean(PnetDataApiLoginMethod.class),
                Matchers.notNullValue()
            );
        }
    }
}
