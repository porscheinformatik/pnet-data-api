package at.porscheinformatik.happyrest.jackson;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestParserException;

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
    public boolean isContentTypeSupported(MediaType contentType, GenericType<?> type)
    {
        return MediaType.APPLICATION_JSON.isCompatible(contentType)
            || MediaType.APPLICATION_ANY_JSON.isCompatible(contentType);
    }

    @Override
    public <T> Object parse(MediaType contentType, GenericType<?> type, InputStream in) throws RestParserException
    {
        if (!isContentTypeSupported(contentType, type))
        {
            throw new RestParserException("Cannot convert " + contentType + " to " + type);
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
