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
 * @deprecated will be removed soon. Switch to the java-11 branch, if you need this feature!
 */
@Deprecated
@Service
public class Spring4RestCallFactory implements RestCallFactory
{

    private static Spring4RestCallFactory defaultFactory = null;

    public static Spring4RestCallFactory getDefault()
    {
        Spring4RestCallFactory factory = defaultFactory;

        if (factory == null)
        {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().add("user-agent", RestUtils.getUserAgent("Spring 4's RestTemplate"));

                return execution.execute(request, body);
            });

            factory =
                new Spring4RestCallFactory(restTemplate, Slf4jRestLoggerAdapter.getDefault(), new TextPlainFormatter());

            defaultFactory = factory;
        }

        return factory;
    }

    protected final RestTemplate restTemplate;
    protected final RestLoggerAdapter loggerAdapter;
    protected final RestFormatter formatter;

    @Autowired
    public Spring4RestCallFactory(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, RestFormatter formatter)
    {
        super();

        this.restTemplate = restTemplate;
        this.loggerAdapter = loggerAdapter;
        this.formatter = formatter;
    }

    @Override
    public RestCall url(String url)
    {
        return new Spring4RestCall(restTemplate, loggerAdapter, formatter, url);
    }

}
