package at.porscheinformatik.happyrest.spring;

import org.springframework.beans.factory.annotation.Autowired;
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

    public static final SpringRestCallFactory DEFAULT = new SpringRestCallFactory(new RestTemplate());

    private final RestTemplate restTemplate;

    @Autowired
    public SpringRestCallFactory(RestTemplate restTemplate)
    {
        super();

        this.restTemplate = restTemplate;
    }

    @Override
    public RestCall url(String url)
    {
        return new SpringRestCall(restTemplate, url);
    }

}
