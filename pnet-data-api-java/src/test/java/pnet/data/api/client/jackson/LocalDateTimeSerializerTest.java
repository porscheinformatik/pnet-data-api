package pnet.data.api.client.jackson;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;

@SuppressWarnings("resource")
public class LocalDateTimeSerializerTest
{

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");

    @Test
    public void testNull() throws JsonGenerationException, IOException
    {
        LocalDateTimeSerializer serializer = new LocalDateTimeSerializer(UTC);
        JsonGenerator jgen = mock(JsonGenerator.class);

        serializer.serialize(null, jgen, null);

        verify(jgen).writeNull();
    }

    @Test
    public void testUtc() throws JsonGenerationException, IOException
    {
        LocalDateTimeSerializer serializer = new LocalDateTimeSerializer(UTC);
        JsonGenerator jgen = mock(JsonGenerator.class);

        serializer.serialize(LocalDateTime.of(2000, 01, 01, 12, 00), jgen, null);

        verify(jgen).writeString("2000-01-01T12:00:00Z");
    }

    @Test
    public void testCet() throws JsonGenerationException, IOException
    {
        LocalDateTimeSerializer serializer = new LocalDateTimeSerializer(CET);
        JsonGenerator jgen = mock(JsonGenerator.class);

        serializer.serialize(LocalDateTime.of(2000, 01, 01, 12, 00), jgen, null);

        verify(jgen).writeString("2000-01-01T11:00:00Z");
    }
}
