package at.porscheinformatik.happyrest.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestUtils;

/**
 * A factory for REST calls using spring
 *
 * @author ham
 */
@Service
public class Spring4RestCallFactory implements RestCallFactory
{

    private static final RestTemplate REST_TEMPLATE;

    /**
     * Use {@link #getDefault()} instead
     */
    @Deprecated
    public static final Spring4RestCallFactory DEFAULT;

    private static Spring4RestCallFactory defaultFactory = null;

    static
    {
        REST_TEMPLATE = new RestTemplate();

        REST_TEMPLATE.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("user-agent", RestUtils.AGENT);

            return execution.execute(request, body);
        });

        DEFAULT = new Spring4RestCallFactory(REST_TEMPLATE, null);
    }

    public static Spring4RestCallFactory getDefault()
    {
        Spring4RestCallFactory factory = defaultFactory;

        if (factory == null)
        {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().add("user-agent", RestUtils.AGENT);

                return execution.execute(request, body);
            });

            factory = new Spring4RestCallFactory(restTemplate, null);
        }

        return factory;
    }

    private final RestTemplate restTemplate;
    private final ConversionService conversionService;

    @Autowired
    public Spring4RestCallFactory(RestTemplate restTemplate, ConversionService conversionService)
    {
        super();

        this.restTemplate = restTemplate;
        this.conversionService = conversionService;
    }

    @Override
    public RestCall url(String url)
    {
        return new Spring4RestCall(restTemplate, conversionService, url);
    }

}
