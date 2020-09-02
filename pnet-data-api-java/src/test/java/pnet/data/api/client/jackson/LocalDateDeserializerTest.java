package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

@SuppressWarnings("resource")
public class LocalDateDeserializerTest
{

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");
    private static final ZoneId SGT = ZoneId.of("UTC-6");

    @Test
    public void testNull() throws JsonGenerationException, IOException
    {
        LocalDateDeserializer deserializer = new LocalDateDeserializer(CET);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn(null);

        assertThat(deserializer.deserialize(jp, null), nullValue());
    }

    @Test
    public void testDateUtc() throws JsonGenerationException, IOException
    {
        LocalDateDeserializer deserializer = new LocalDateDeserializer(UTC);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01");

        LocalDate result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    public void testDateCet() throws JsonGenerationException, IOException
    {
        LocalDateDeserializer deserializer = new LocalDateDeserializer(CET);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01");

        LocalDate result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    public void testDateTimeUtc() throws JsonGenerationException, IOException
    {
        LocalDateDeserializer deserializer = new LocalDateDeserializer(UTC);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01T00:00:00");

        LocalDate result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    public void testDateTimeCet() throws JsonGenerationException, IOException
    {
        LocalDateDeserializer deserializer = new LocalDateDeserializer(CET);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01T00:00:00");

        LocalDate result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    public void testZonedDateTimeUtc() throws JsonGenerationException, IOException
    {
        LocalDateDeserializer deserializer = new LocalDateDeserializer(UTC);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01T00:00:00Z");

        LocalDate result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    public void testZonedDateTimeCet() throws JsonGenerationException, IOException
    {
        LocalDateDeserializer deserializer = new LocalDateDeserializer(CET);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01T00:00:00Z");

        LocalDate result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    public void testZonedDateTimeSgt() throws JsonGenerationException, IOException
    {
        LocalDateDeserializer deserializer = new LocalDateDeserializer(SGT);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01T00:00:00Z");

        LocalDate result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(1999));
        assertThat(result.getMonth(), is(Month.DECEMBER));
        assertThat(result.getDayOfMonth(), is(31));
    }
}
