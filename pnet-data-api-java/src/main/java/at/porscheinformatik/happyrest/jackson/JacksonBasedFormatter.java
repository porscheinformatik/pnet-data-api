package at.porscheinformatik.happyrest.jackson;

import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestFormatterException;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

/**
 * Formats JSON using Jackson
 *
 * @author HAM
 */
public class JacksonBasedFormatter implements RestFormatter {

    private final JsonMapper mapper;

    public JacksonBasedFormatter(JsonMapper mapper) {
        super();
        this.mapper = mapper;
    }

    @Override
    public boolean isContentTypeSupported(MediaType contentType) {
        return (
            MediaType.APPLICATION_JSON.isCompatible(contentType) ||
            MediaType.APPLICATION_ANY_JSON.isCompatible(contentType)
        );
    }

    @Override
    public String format(MediaType contentType, Object value) throws RestFormatterException {
        if (!isContentTypeSupported(contentType)) {
            throw new RestFormatterException("Content type not supported: " + contentType);
        }

        try {
            return mapper.writeValueAsString(value);
        } catch (JacksonException e) {
            throw new RestFormatterException("Conversion failed", e);
        }
    }
}
