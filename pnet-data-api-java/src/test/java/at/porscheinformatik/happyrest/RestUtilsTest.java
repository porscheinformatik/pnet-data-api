package at.porscheinformatik.happyrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class RestUtilsTest {

    private static final JsonMapper JSON_MAPPER = new JsonMapper();

    @Test
    void encodePathSegmentTest() {
        assertThat(RestUtils.encodePathSegment(null, StandardCharsets.UTF_8, false, false), nullValue());
        assertThat(RestUtils.encodePathSegment("", StandardCharsets.UTF_8, false, false), is(""));
        assertThat(RestUtils.encodePathSegment("azAZ09", StandardCharsets.UTF_8, false, false), is("azAZ09"));
        assertThat(
            RestUtils.encodePathSegment("-._~!$&'()*+,;=:@", StandardCharsets.UTF_8, false, false),
            is("-._~!$&'()*+,;=:@")
        );

        assertThat(RestUtils.encodePathSegment("sp ace", StandardCharsets.UTF_8, false, false), is("sp%20ace"));
        assertThat(RestUtils.encodePathSegment("slash/", StandardCharsets.UTF_8, false, false), is("slash%2F"));
        assertThat(
            RestUtils.encodePathSegment("ignore/slash", StandardCharsets.UTF_8, true, false),
            is("ignore/slash")
        );
        assertThat(RestUtils.encodePathSegment("per%cent", StandardCharsets.UTF_8, false, false), is("per%25cent"));

        assertThat(RestUtils.encodePathSegment("{/x}", StandardCharsets.UTF_8, false, false), is("%7B%2Fx%7D"));
        assertThat(RestUtils.encodePathSegment("{/x}", StandardCharsets.UTF_8, false, true), is("{/x}"));
    }

    @Test
    void appendPathTest() {
        assertThat(RestUtils.appendPath("https://example.com", "path/"), is("https://example.com/path/"));
        assertThat(RestUtils.appendPath("https://example.com/", "path/"), is("https://example.com/path/"));
        assertThat(RestUtils.appendPath("https://example.com", "/path/"), is("https://example.com/path/"));
        assertThat(RestUtils.appendPath("https://example.com/", "/path/"), is("https://example.com/path/"));

        assertThat(RestUtils.appendPath("https://example.com", "path/file"), is("https://example.com/path/file"));
        assertThat(RestUtils.appendPath("https://example.com/", "path/file"), is("https://example.com/path/file"));
        assertThat(RestUtils.appendPath("https://example.com", "/path/file"), is("https://example.com/path/file"));
        assertThat(RestUtils.appendPath("https://example.com/", "/path/file"), is("https://example.com/path/file"));

        assertThat(
            RestUtils.appendPath("https://example.com", "path/{file}"),
            is("https://example.com/path/%7Bfile%7D")
        );
        assertThat(
            RestUtils.appendPathWithPlaceholders("https://example.com", "path/{file}"),
            is("https://example.com/path/{file}")
        );
    }
}
