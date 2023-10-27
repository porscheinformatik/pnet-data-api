package at.porscheinformatik.happyrest.jackson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
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
    public boolean isContentTypeSupported(Optional<MediaType> contentType, GenericType<?> type)
    {
        if (!contentType.isPresent())
        {
            return true;
        }

        return MediaType.APPLICATION_JSON.isCompatible(contentType.get())
            || MediaType.APPLICATION_ANY_JSON.isCompatible(contentType.get());
    }

    @Override
    public <T> Object parse(Optional<MediaType> contentType, GenericType<?> type, InputStream in)
        throws RestParserException
    {
        if (!isContentTypeSupported(contentType, type))
        {
            throw new RestParserException("Cannot convert " + contentType + " to " + type);
        }

        byte[] bytes;

        try
        {
            bytes = RestUtils.readAllBytes(in);

            if (bytes.length == 0)
            {
                return null;
            }
        }
        catch (IOException e)
        {
            throw new RestParserException("Failed to read JSON", e);
        }

        try
        {
            return mapper.readValue(bytes, JacksonTypeReference.of(type));
        }
        catch (Exception e)
        {
            throw new RestParserException(
                "Failed to parse JSON for type " + type + ": " + RestUtils.abbreviate(new String(bytes), 2048), e);
        }
    }
}
