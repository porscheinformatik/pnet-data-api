package pnet.data.api.client.jackson;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Converter module for Jackson.
 *
 * @author ham
 */
public class JacksonPnetDataApiModule extends SimpleModule
{

    private static final long serialVersionUID = 460900059584008887L;

    public JacksonPnetDataApiModule()
    {
        super("pnet-data-api", new Version(1, 0, 0, null, "at.porscheinformatik.pnet", "pnet-data-api"));

        addSerializer(new LocalDateTimeSerializer());
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    }

    public static ObjectMapper createObjectMapper()
    {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModules(new JacksonPnetDataApiModule());

        return objectMapper;
    }
}
