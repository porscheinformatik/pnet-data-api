package at.porscheinformatik.happyrest.spring;

import org.springframework.core.convert.ConversionService;

import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestFormatterException;

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
    public boolean isContentTypeSupported(MediaType contentType)
    {
        return MediaType.ANY.isCompatible(contentType);
    }

    @Override
    public String format(MediaType contentType, Object value) throws RestFormatterException
    {
        if (!isContentTypeSupported(contentType))
        {
            throw new RestFormatterException("Unsupported content type: " + contentType);
        }

        return conversionService.convert(value, String.class);
    }

}
