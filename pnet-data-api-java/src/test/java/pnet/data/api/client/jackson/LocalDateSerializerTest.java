package pnet.data.api.client.jackson;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;

@SuppressWarnings("resource")
public class LocalDateSerializerTest
{

    @Test
    public void testNull() throws JsonGenerationException, IOException
    {
        LocalDateSerializer serializer = new LocalDateSerializer();
        JsonGenerator jgen = mock(JsonGenerator.class);

        serializer.serialize(null, jgen, null);

        verify(jgen).writeNull();
    }

    @Test
    public void testUtc() throws JsonGenerationException, IOException
    {
        LocalDateSerializer serializer = new LocalDateSerializer();
        JsonGenerator jgen = mock(JsonGenerator.class);

        serializer.serialize(LocalDate.of(2000, 01, 01), jgen, null);

        verify(jgen).writeString("2000-01-01");
    }
}
