package at.porscheinformatik.happyrest.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;

/**
 * A factory for REST calls using spring
 *
 * @author ham
 */
@Service
public class Spring4RestCallFactory implements RestCallFactory
{

    public static final Spring4RestCallFactory DEFAULT = new Spring4RestCallFactory(new RestTemplate(), null);

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
