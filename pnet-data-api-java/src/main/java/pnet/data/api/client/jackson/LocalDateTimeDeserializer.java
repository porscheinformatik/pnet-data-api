package pnet.data.api.client.jackson;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import pnet.data.api.util.PnetDataApiUtils;

/**
 * @author Daniel Furtlehner
 */
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime>
{

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public LocalDateTimeDeserializer()
    {
        super(LocalDate.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException
    {
        String dateAsString = _parseString(jp, ctxt);

        return PnetDataApiUtils.parseISODateTime(dateAsString);
    }
}
