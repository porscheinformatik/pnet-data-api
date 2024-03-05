package at.porscheinformatik.happyrest.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestFormatterException;

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
    public boolean isContentTypeSupported(MediaType contentType)
    {
        return MediaType.APPLICATION_JSON.isCompatible(contentType) || MediaType.APPLICATION_ANY_JSON.isCompatible(
            contentType);
    }

    @Override
    public String format(MediaType contentType, Object value) throws RestFormatterException
    {
        if (!isContentTypeSupported(contentType))
        {
            throw new RestFormatterException("Content type not supported: " + contentType);
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
