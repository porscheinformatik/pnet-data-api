package pnet.data.api.client.jackson;

import java.io.IOException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author ham
 */
public class LocaleDeserializer extends StdDeserializer<Locale>
{
    private static final long serialVersionUID = 1L;

    public LocaleDeserializer()
    {
        super(Locale.class);
    }

    @Override
    public Locale deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException
    {
        String localeAsString = _parseString(jp, ctxt, NullsConstantProvider.nuller());

        return localeAsString == null ? null : Locale.forLanguageTag(localeAsString);
    }
}
