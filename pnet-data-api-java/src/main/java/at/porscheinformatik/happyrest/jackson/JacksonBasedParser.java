package at.porscheinformatik.happyrest.jackson;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestParserException;
import at.porscheinformatik.happyrest.RestUtils;

/**
 * Parses a JSON response using the Jackson mapper
 *
 * @author HAM
 */
public class JacksonBasedParser implements RestParser
{

    private final ObjectMapper mapper;

    public JacksonBasedParser(ObjectMapper mapper)
    {
        super();

        this.mapper = mapper;
    }

    @Override
    public boolean isContentTypeSupported(String contentType, GenericType<?> type)
    {
        return RestCall.MEDIA_TYPE_APPLICATION_JSON.equals(RestUtils.extractContentType(contentType));
    }

    @Override
    public <T> Object parse(String contentType, GenericType<?> type, InputStream in) throws RestParserException
    {
        if (!isContentTypeSupported(contentType, type))
        {
            throw new RestParserException("Cannot convert %s to %s", contentType, type);
        }

        try
        {
            return mapper.readValue(in, JacksonTypeReference.of(type));
        }
        catch (JsonParseException e)
        {
            throw new RestParserException("Failed to parse JSON", e);
        }
        catch (JsonMappingException e)
        {
            throw new RestParserException("Failed to parse JSON", e);
        }
        catch (IOException e)
        {
            throw new RestParserException("Failed to read JSON", e);
        }
    }

}
