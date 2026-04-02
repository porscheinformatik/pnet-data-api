package pnet.data.api.webclient;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pnet.data.api.client.PnetDataRestCallFactoryConfigurer;
import pnet.data.api.client.context.PnetDataApiLoginMethod;

class EnableWebClientBasedPnetDataClientTest {

    @Configuration
    @EnableWebClientBasedPnetDataClient
    static class TestConfigWithLoginMethod {

        @Bean
        public PnetDataApiLoginMethod loginMethod() throws Exception {
            PnetDataApiLoginMethod loginMethod = mock(PnetDataApiLoginMethod.class);
            RestCall restCall = mock(RestCall.class);

            when(loginMethod.performLogin(any(RestCallFactory.class))).thenReturn(restCall);

            return loginMethod;
        }
    }

    @Configuration
    @EnableWebClientBasedPnetDataClient
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
            assertThat("RestCallFactory should not be null", context.getBean(RestCallFactory.class), notNullValue());
            assertThat("LoginMethod should not be null", context.getBean(PnetDataApiLoginMethod.class), notNullValue());
        }
    }

    @Test
    void test_withConfigurer_restCallFactoryIsInitialized() {
        try (
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                TestConfigWithConfigurer.class
            )
        ) {
            assertThat("RestCallFactory should not be null", context.getBean(RestCallFactory.class), notNullValue());
            assertThat("LoginMethod should not be null", context.getBean(PnetDataApiLoginMethod.class), notNullValue());
        }
    }
}
