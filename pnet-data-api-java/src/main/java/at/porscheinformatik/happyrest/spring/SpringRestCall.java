package at.porscheinformatik.happyrest.spring;

import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestAttribute;
import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 * A REST call. This implementation is thread-safe!
 *
 * @deprecated use RestTemplateRestCall instead
 * @author ham
 */
@Deprecated(since = "2.13.x")
public class SpringRestCall extends RestTemplateRestCall {

    protected SpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, RestFormatter formatter) {
        this(restTemplate, loggerAdapter, null, null, MediaType.APPLICATION_JSON, null, formatter, null);
    }

    protected SpringRestCall(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter,
        String url
    ) {
        this(restTemplate, loggerAdapter, url, null, MediaType.APPLICATION_JSON, null, formatter, null);
    }

    @SuppressWarnings("java:S107") // As usual, the high number of parameters is necessary
    protected SpringRestCall(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        String url,
        List<MediaType> acceptableMediaTypes,
        MediaType contentType,
        List<RestAttribute> attributes,
        RestFormatter formatter,
        Object body
    ) {
        super(restTemplate, loggerAdapter, url, acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    @Override
    protected RestCall copy(
        RestLoggerAdapter loggerAdapter,
        String url,
        List<MediaType> acceptableMediaTypes,
        MediaType contentType,
        List<RestAttribute> attributes,
        RestFormatter formatter,
        Object body
    ) {
        return new SpringRestCall(
            restTemplate,
            loggerAdapter,
            url,
            acceptableMediaTypes,
            contentType,
            attributes,
            formatter,
            body
        );
    }
}
