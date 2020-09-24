package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

@SuppressWarnings("resource")
public class LocalDateTimeDeserializerTest
{

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");

    @Test
    public void testNull() throws JsonGenerationException, IOException
    {
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(CET);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn(null);

        assertThat(deserializer.deserialize(jp, null), nullValue());
    }

    @Test
    public void testDateUtc() throws JsonGenerationException, IOException
    {
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(UTC);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01");

        LocalDateTime result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    public void testDateCet() throws JsonGenerationException, IOException
    {
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(CET);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01");

        LocalDateTime result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    public void testDateTimeUtc() throws JsonGenerationException, IOException
    {
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(UTC);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01T00:00:00");

        LocalDateTime result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    public void testDateTimeCet() throws JsonGenerationException, IOException
    {
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(CET);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01T00:00:00");

        LocalDateTime result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    public void testZonedDateTimeUtc() throws JsonGenerationException, IOException
    {
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(UTC);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01T00:00:00Z");

        LocalDateTime result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    public void testZonedDateTimeCet() throws JsonGenerationException, IOException
    {
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(CET);
        JsonParser jp = mock(JsonParser.class);

        when(jp.getCurrentToken()).thenReturn(JsonToken.VALUE_STRING);
        when(jp.getText()).thenReturn("2000-01-01T00:00:00Z");

        LocalDateTime result = deserializer.deserialize(jp, null);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(1));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }
}
