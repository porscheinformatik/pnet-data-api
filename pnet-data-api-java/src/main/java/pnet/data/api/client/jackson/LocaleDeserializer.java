package pnet.data.api.client.jackson;

import java.util.Locale;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.impl.NullsConstantProvider;
import tools.jackson.databind.deser.std.StdDeserializer;

/**
 * @author ham
 */
public class LocaleDeserializer extends StdDeserializer<Locale> {

    public LocaleDeserializer() {
        super(Locale.class);
    }

    @Override
    public Locale deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        String localeAsString = _parseString(p, ctxt, NullsConstantProvider.nuller());

        return localeAsString == null ? null : Locale.forLanguageTag(localeAsString);
    }
}
