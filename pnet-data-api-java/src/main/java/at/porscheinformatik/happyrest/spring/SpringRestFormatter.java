package at.porscheinformatik.happyrest.spring;

import org.springframework.core.convert.ConversionService;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestFormatterException;
import at.porscheinformatik.happyrest.RestUtils;

/**
 * A formatter using Spring's {@link ConversionService}.
 *
 * @author HAM
 */
public class SpringRestFormatter implements RestFormatter
{

    private final ConversionService conversionService;

    public SpringRestFormatter(ConversionService conversionService)
    {
        super();

        this.conversionService = conversionService;
    }

    @Override
    public boolean isContentTypeSupported(String contentType)
    {
        return RestCall.MEDIA_TYPE_TEXT_PLAIN.equals(RestUtils.extractContentType(contentType));
    }

    @Override
    public String format(String contentType, Object value) throws RestFormatterException
    {
        if (!isContentTypeSupported(contentType))
        {
            throw new RestFormatterException("Unsupported content type: " + contentType);
        }

        return conversionService.convert(value, String.class);
    }

}
