package at.porscheinformatik.happyrest.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestFormatterException;
import at.porscheinformatik.happyrest.RestUtils;

/**
 * Formats JSON using Jackson
 *
 * @author HAM
 */
public class JacksonBasedFormatter implements RestFormatter
{

    private final ObjectMapper mapper;

    public JacksonBasedFormatter(ObjectMapper mapper)
    {
        super();

        this.mapper = mapper;
    }

    @Override
    public boolean isContentTypeSupported(String contentType)
    {
        return RestCall.MEDIA_TYPE_APPLICATION_JSON.equals(RestUtils.extractContentType(contentType));
    }

    @Override
    public String format(String contentType, Object value) throws RestFormatterException
    {
        if (!isContentTypeSupported(contentType))
        {
            throw new RestFormatterException("Content type not supported: %s", contentType);
        }

        try
        {
            return mapper.writeValueAsString(value);
        }
        catch (JsonProcessingException e)
        {
            throw new RestFormatterException("Conversion failed", e);
        }
    }

}
