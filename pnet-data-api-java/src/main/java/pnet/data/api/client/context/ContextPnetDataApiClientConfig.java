package pnet.data.api.client.context;

import java.util.Iterator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.spring.SpringRestCallFactory;
import pnet.data.api.client.jackson.JacksonPnetDataApiModule;

/**
 * <pre>
 *           ___
 *         O(o o)O
 *           (_)     - Ook!
 * </pre>
 */
@Configuration
@ComponentScan(basePackageClasses = {ContextPnetDataApiClientConfig.class})
public class ContextPnetDataApiClientConfig
{

    @Bean
    public RestCallFactory restCallFactory()
    {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModules(new JacksonPnetDataApiModule());

        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory =
            (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();

        requestFactory.setReadTimeout(30000);
        requestFactory.setConnectTimeout(2000);

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        messageConverter.setPrettyPrint(false);
        messageConverter.setObjectMapper(objectMapper);

        Iterator<HttpMessageConverter<?>> iterator = restTemplate.getMessageConverters().iterator();
        while (iterator.hasNext())
        {
            HttpMessageConverter<?> elem = iterator.next();
            if (elem.getClass().equals(MappingJackson2HttpMessageConverter.class))
            {
                ((MappingJackson2HttpMessageConverter) elem).setObjectMapper(objectMapper);
                break;
            }
        }

        return new SpringRestCallFactory(restTemplate);
    }
}
