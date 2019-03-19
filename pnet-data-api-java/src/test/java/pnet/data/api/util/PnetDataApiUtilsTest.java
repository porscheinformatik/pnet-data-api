package pnet.data.api.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

/**
 * Tests for utilities
 *
 * @author HAM
 */
public class PnetDataApiUtilsTest
{

    private static final LocalDateTime TIMESTAMP = LocalDateTime.of(2019, 03, 21, 13, 42);

    @Test
    public void dateTimeTransformation()
    {
        LocalDateTime timestamp = TIMESTAMP.truncatedTo(ChronoUnit.SECONDS);
        String formattedTimestamp = PnetDataApiUtils.formatISODateTime(timestamp);

        assertThat(formattedTimestamp, endsWith("Z"));

        LocalDateTime parsedTimestamp = PnetDataApiUtils.parseISODateTime(formattedTimestamp);

        assertThat(parsedTimestamp, is(timestamp));
    }

    @Test
    public void dateTimeTransformationWithoutZone()
    {
        LocalDateTime timestamp = TIMESTAMP.truncatedTo(ChronoUnit.SECONDS);
        String formattedTimestamp = PnetDataApiUtils.formatISODateTime(timestamp);

        // strip the Z
        formattedTimestamp = formattedTimestamp.substring(0, formattedTimestamp.length() - 1);

        LocalDateTime parsedTimestamp = PnetDataApiUtils.parseISODateTime(formattedTimestamp);

        parsedTimestamp = PnetDataApiUtils.convertUTCToDefault(parsedTimestamp);

        assertThat(parsedTimestamp, is(timestamp));
    }

    @Test
    public void dateTimeTransformationWithoutTime()
    {
        LocalDateTime timestamp = TIMESTAMP.truncatedTo(ChronoUnit.SECONDS);
        String formattedTimestamp = PnetDataApiUtils.formatISODateTime(timestamp);

        // strip the time
        formattedTimestamp = formattedTimestamp.substring(0, formattedTimestamp.indexOf("T"));

        LocalDateTime parsedTimestamp = PnetDataApiUtils.parseISODateTime(formattedTimestamp);

        assertThat(parsedTimestamp, is(timestamp.truncatedTo(ChronoUnit.DAYS)));
    }

}
