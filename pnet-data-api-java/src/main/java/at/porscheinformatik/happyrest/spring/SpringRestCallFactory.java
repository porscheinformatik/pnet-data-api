package at.porscheinformatik.happyrest.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestUtils;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;

/**
 * A factory for REST calls using spring
 *
 * @author ham
 */
@Service
public class SpringRestCallFactory implements RestCallFactory
{

    private static SpringRestCallFactory defaultFactory = null;

    public static SpringRestCallFactory getDefault()
    {
        SpringRestCallFactory factory = defaultFactory;

        if (factory == null)
        {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().add("user-agent", RestUtils.getUserAgent("Spring's RestTemplate"));

                return execution.execute(request, body);
            });

            factory =
                new SpringRestCallFactory(restTemplate, Slf4jRestLoggerAdapter.getDefault(), new TextPlainFormatter());

            defaultFactory = factory;
        }

        return factory;
    }

    private final RestTemplate restTemplate;
    private final RestLoggerAdapter loggerAdapter;
    private final RestFormatter formatter;

    @Autowired
    public SpringRestCallFactory(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, RestFormatter formatter)
    {
        super();

        this.restTemplate = restTemplate;
        this.loggerAdapter = loggerAdapter;
        this.formatter = formatter;
    }

    @Override
    public RestCall url(String url)
    {
        return new SpringRestCall(restTemplate, loggerAdapter, formatter, url);
    }

}
