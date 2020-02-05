package at.porscheinformatik.happyrest.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestUtils;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;

/**
 * A factory for REST calls using spring
 *
 * @author ham
 */
@Service
public class SpringRestCallFactory implements RestCallFactory
{

    private static final RestTemplate REST_TEMPLATE;

    /**
     * @deprecated use {@link #getDefault()} instead
     */
    @Deprecated
    public static final SpringRestCallFactory DEFAULT;

    private static SpringRestCallFactory defaultFactory = null;

    static
    {
        REST_TEMPLATE = new RestTemplate();

        REST_TEMPLATE.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("user-agent", RestUtils.getAgent());

            return execution.execute(request, body);
        });

        DEFAULT = new SpringRestCallFactory(REST_TEMPLATE, Slf4jRestLoggerAdapter.getDefault(), null);
    }

    public static SpringRestCallFactory getDefault()
    {
        SpringRestCallFactory factory = defaultFactory;

        if (factory == null)
        {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().add("user-agent", RestUtils.getAgent());

                return execution.execute(request, body);
            });

            factory = new SpringRestCallFactory(restTemplate, Slf4jRestLoggerAdapter.getDefault(), null);

            defaultFactory = factory;
        }

        return factory;
    }

    private final RestTemplate restTemplate;
    private final RestLoggerAdapter loggerAdapter;
    private final ConversionService conversionService;

    @Autowired
    public SpringRestCallFactory(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter,
        ConversionService conversionService)
    {
        super();

        this.restTemplate = restTemplate;
        this.loggerAdapter = loggerAdapter;
        this.conversionService = conversionService;
    }

    @Override
    public RestCall url(String url)
    {
        return new SpringRestCall(restTemplate, loggerAdapter, new SpringRestFormatter(conversionService), url);
    }

}
