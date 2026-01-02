package at.porscheinformatik.happyrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

class MediaTypeTest {

    @Test
    void testToString() {
        assertThat(MediaType.ANY.toString(), is("*/*"));

        assertThat(MediaType.APPLICATION.toString(), is("application/*"));
        assertThat(MediaType.APPLICATION_ATOM_XML.toString(), is("application/atom+xml"));
        assertThat(MediaType.APPLICATION_FORM.toString(), is("application/x-www-form-urlencoded"));
        assertThat(MediaType.APPLICATION_JSON.toString(), is("application/json"));
        assertThat(MediaType.APPLICATION_JSON_UTF8.toString(), is("application/json;charset=UTF-8"));
        assertThat(MediaType.APPLICATION_ANY_JSON.toString(), is("application/*+json"));
        assertThat(MediaType.APPLICATION_OCTET_STREAM.toString(), is("application/octet-stream"));
        assertThat(MediaType.APPLICATION_PDF.toString(), is("application/pdf"));
        assertThat(MediaType.APPLICATION_RSS_XML.toString(), is("application/rss+xml"));
        assertThat(MediaType.APPLICATION_XHTML_XML.toString(), is("application/xhtml+xml"));
        assertThat(MediaType.APPLICATION_ANY_XML.toString(), is("application/*+json"));
        assertThat(MediaType.APPLICATION_XML.toString(), is("application/xml"));

        assertThat(MediaType.IMAGE.toString(), is("image/*"));
        assertThat(MediaType.IMAGE_GIF.toString(), is("image/gif"));
        assertThat(MediaType.IMAGE_JPEG.toString(), is("image/jpeg"));
        assertThat(MediaType.IMAGE_PNG.toString(), is("image/png"));

        assertThat(MediaType.TEXT.toString(), is("text/*"));
        assertThat(MediaType.TEXT_HTML.toString(), is("text/html"));
        assertThat(MediaType.TEXT_MARKDOWN.toString(), is("text/markdown"));
        assertThat(MediaType.TEXT_PLAIN.toString(), is("text/plain"));
        assertThat(MediaType.TEXT_XML.toString(), is("text/xml"));
    }

    @Test
    void testCompatibility() {
        assertThat(MediaType.ANY.isCompatible(MediaType.ANY), is(true));
        assertThat(MediaType.ANY.isCompatible(MediaType.APPLICATION), is(true));
        assertThat(MediaType.ANY.isCompatible(MediaType.APPLICATION_JSON), is(true));
        assertThat(MediaType.ANY.isCompatible(MediaType.APPLICATION_JSON_UTF8), is(true));
        assertThat(MediaType.ANY.isCompatible(MediaType.APPLICATION_ANY_JSON), is(true));

        assertThat(MediaType.APPLICATION.isCompatible(MediaType.ANY), is(false));
        assertThat(MediaType.APPLICATION.isCompatible(MediaType.APPLICATION), is(true));
        assertThat(MediaType.APPLICATION.isCompatible(MediaType.APPLICATION_JSON), is(true));
        assertThat(MediaType.APPLICATION.isCompatible(MediaType.APPLICATION_JSON_UTF8), is(true));
        assertThat(MediaType.APPLICATION.isCompatible(MediaType.APPLICATION_ANY_JSON), is(true));
        assertThat(MediaType.APPLICATION.isCompatible(MediaType.TEXT), is(false));

        assertThat(MediaType.APPLICATION_JSON.isCompatible(MediaType.APPLICATION_JSON), is(true));
        assertThat(MediaType.APPLICATION_JSON_UTF8.isCompatible(MediaType.APPLICATION_JSON_UTF8), is(true));
        assertThat(MediaType.APPLICATION_ANY_JSON.isCompatible(MediaType.APPLICATION_ANY_JSON), is(true));

        assertThat(MediaType.APPLICATION_JSON.isCompatible(MediaType.APPLICATION_JSON_UTF8), is(true));
        assertThat(MediaType.APPLICATION_JSON_UTF8.isCompatible(MediaType.APPLICATION_JSON), is(true));

        assertThat(MediaType.APPLICATION_ANY_JSON.isCompatible(MediaType.APPLICATION_JSON), is(false));
        assertThat(MediaType.APPLICATION_ANY_JSON.isCompatible(MediaType.APPLICATION_JSON_UTF8), is(false));
        assertThat(MediaType.APPLICATION_JSON.isCompatible(MediaType.APPLICATION_JSON_UTF8), is(true));
        assertThat(MediaType.APPLICATION_JSON_UTF8.isCompatible(MediaType.APPLICATION_JSON_UTF8), is(true));
    }
}
