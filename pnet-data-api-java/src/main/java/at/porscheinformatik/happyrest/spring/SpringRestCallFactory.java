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
public class SpringRestCallFactory implements RestCallFactory
{

    public static final SpringRestCallFactory DEFAULT = new SpringRestCallFactory(new RestTemplate(), null);

    private final RestTemplate restTemplate;
    private final ConversionService conversionService;

    @Autowired
    public SpringRestCallFactory(RestTemplate restTemplate, ConversionService conversionService)
    {
        super();

        this.restTemplate = restTemplate;
        this.conversionService = conversionService;
    }

    @Override
    public RestCall url(String url)
    {
        return new SpringRestCall(restTemplate, conversionService, url);
    }

}
