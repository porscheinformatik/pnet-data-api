package at.porscheinformatik.happyrest.jackson;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestParserException;
import at.porscheinformatik.happyrest.RestUtils;
import java.io.IOException;
import java.io.InputStream;
import tools.jackson.databind.json.JsonMapper;

/**
 * Parses a JSON response using the Jackson mapper
 *
 * @author HAM
 */
public class JacksonBasedParser implements RestParser {

    private final JsonMapper mapper;

    public JacksonBasedParser(JsonMapper mapper) {
        super();
        this.mapper = mapper;
    }

    @Override
    public boolean isContentTypeSupported(MediaType contentType, GenericType<?> type) {
        if (contentType == null) {
            return true;
        }

        return (
            MediaType.APPLICATION_JSON.isCompatible(contentType) ||
            MediaType.APPLICATION_ANY_JSON.isCompatible(contentType)
        );
    }

    @Override
    public Object parse(MediaType contentType, GenericType<?> type, String s) throws RestParserException {
        if (!isContentTypeSupported(contentType, type)) {
            throw new RestParserException("Cannot convert " + contentType + " to " + type);
        }

        try {
            return mapper.readValue(s, JacksonTypeReference.of(type));
        } catch (Exception e) {
            throw new RestParserException(
                "Failed to parse JSON for type " + type + ": " + RestUtils.abbreviate(s, 2048),
                e
            );
        }
    }

    @Override
    public Object parse(MediaType contentType, GenericType<?> type, byte[] bytes) throws RestParserException {
        if (!isContentTypeSupported(contentType, type)) {
            throw new RestParserException("Cannot convert " + contentType + " to " + type);
        }

        try {
            return mapper.readValue(bytes, JacksonTypeReference.of(type));
        } catch (Exception e) {
            throw new RestParserException(
                "Failed to parse JSON for type " + type + ": " + RestUtils.abbreviate(new String(bytes), 2048),
                e
            );
        }
    }

    @Override
    public Object parse(MediaType contentType, GenericType<?> type, InputStream in) throws RestParserException {
        byte[] bytes;

        try {
            bytes = RestUtils.readAllBytes(in);

            if (bytes.length == 0) {
                return null;
            }
        } catch (IOException e) {
            throw new RestParserException("Failed to read JSON", e);
        }

        return parse(contentType, type, bytes);
    }
}
