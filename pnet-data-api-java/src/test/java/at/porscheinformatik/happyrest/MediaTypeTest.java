package at.porscheinformatik.happyrest;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class MediaTypeTest {

    @Test
    void testToString() {
        MatcherAssert.assertThat(MediaType.ANY.toString(), Matchers.is("*/*"));

        MatcherAssert.assertThat(MediaType.APPLICATION.toString(), Matchers.is("application/*"));
        MatcherAssert.assertThat(MediaType.APPLICATION_ATOM_XML.toString(), Matchers.is("application/atom+xml"));
        MatcherAssert.assertThat(
            MediaType.APPLICATION_FORM.toString(),
            Matchers.is("application/x-www-form-urlencoded")
        );
        MatcherAssert.assertThat(MediaType.APPLICATION_JSON.toString(), Matchers.is("application/json"));
        MatcherAssert.assertThat(
            MediaType.APPLICATION_JSON_UTF8.toString(),
            Matchers.is("application/json;charset=UTF-8")
        );
        MatcherAssert.assertThat(MediaType.APPLICATION_ANY_JSON.toString(), Matchers.is("application/*+json"));
        MatcherAssert.assertThat(
            MediaType.APPLICATION_OCTET_STREAM.toString(),
            Matchers.is("application/octet-stream")
        );
        MatcherAssert.assertThat(MediaType.APPLICATION_PDF.toString(), Matchers.is("application/pdf"));
        MatcherAssert.assertThat(MediaType.APPLICATION_RSS_XML.toString(), Matchers.is("application/rss+xml"));
        MatcherAssert.assertThat(MediaType.APPLICATION_XHTML_XML.toString(), Matchers.is("application/xhtml+xml"));
        MatcherAssert.assertThat(MediaType.APPLICATION_ANY_XML.toString(), Matchers.is("application/*+xml"));
        MatcherAssert.assertThat(MediaType.APPLICATION_XML.toString(), Matchers.is("application/xml"));

        MatcherAssert.assertThat(MediaType.IMAGE.toString(), Matchers.is("image/*"));
        MatcherAssert.assertThat(MediaType.IMAGE_GIF.toString(), Matchers.is("image/gif"));
        MatcherAssert.assertThat(MediaType.IMAGE_JPEG.toString(), Matchers.is("image/jpeg"));
        MatcherAssert.assertThat(MediaType.IMAGE_PNG.toString(), Matchers.is("image/png"));

        MatcherAssert.assertThat(MediaType.TEXT.toString(), Matchers.is("text/*"));
        MatcherAssert.assertThat(MediaType.TEXT_HTML.toString(), Matchers.is("text/html"));
        MatcherAssert.assertThat(MediaType.TEXT_MARKDOWN.toString(), Matchers.is("text/markdown"));
        MatcherAssert.assertThat(MediaType.TEXT_PLAIN.toString(), Matchers.is("text/plain"));
        MatcherAssert.assertThat(MediaType.TEXT_XML.toString(), Matchers.is("text/xml"));
    }

    @Test
    void testCompatibility() {
        MatcherAssert.assertThat(MediaType.ANY.isCompatible(MediaType.ANY), Matchers.is(true));
        MatcherAssert.assertThat(MediaType.ANY.isCompatible(MediaType.APPLICATION), Matchers.is(true));
        MatcherAssert.assertThat(MediaType.ANY.isCompatible(MediaType.APPLICATION_JSON), Matchers.is(true));
        MatcherAssert.assertThat(MediaType.ANY.isCompatible(MediaType.APPLICATION_JSON_UTF8), Matchers.is(true));
        MatcherAssert.assertThat(MediaType.ANY.isCompatible(MediaType.APPLICATION_ANY_JSON), Matchers.is(true));

        MatcherAssert.assertThat(MediaType.APPLICATION.isCompatible(MediaType.ANY), Matchers.is(false));
        MatcherAssert.assertThat(MediaType.APPLICATION.isCompatible(MediaType.APPLICATION), Matchers.is(true));
        MatcherAssert.assertThat(MediaType.APPLICATION.isCompatible(MediaType.APPLICATION_JSON), Matchers.is(true));
        MatcherAssert.assertThat(
            MediaType.APPLICATION.isCompatible(MediaType.APPLICATION_JSON_UTF8),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(MediaType.APPLICATION.isCompatible(MediaType.APPLICATION_ANY_JSON), Matchers.is(true));
        MatcherAssert.assertThat(MediaType.APPLICATION.isCompatible(MediaType.TEXT), Matchers.is(false));

        MatcherAssert.assertThat(
            MediaType.APPLICATION_JSON.isCompatible(MediaType.APPLICATION_JSON),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            MediaType.APPLICATION_JSON_UTF8.isCompatible(MediaType.APPLICATION_JSON_UTF8),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            MediaType.APPLICATION_ANY_JSON.isCompatible(MediaType.APPLICATION_ANY_JSON),
            Matchers.is(true)
        );

        MatcherAssert.assertThat(
            MediaType.APPLICATION_JSON.isCompatible(MediaType.APPLICATION_JSON_UTF8),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            MediaType.APPLICATION_JSON_UTF8.isCompatible(MediaType.APPLICATION_JSON),
            Matchers.is(true)
        );

        MatcherAssert.assertThat(
            MediaType.APPLICATION_ANY_JSON.isCompatible(MediaType.APPLICATION_JSON),
            Matchers.is(false)
        );
        MatcherAssert.assertThat(
            MediaType.APPLICATION_ANY_JSON.isCompatible(MediaType.APPLICATION_JSON_UTF8),
            Matchers.is(false)
        );
        MatcherAssert.assertThat(
            MediaType.APPLICATION_JSON.isCompatible(MediaType.APPLICATION_JSON_UTF8),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            MediaType.APPLICATION_JSON_UTF8.isCompatible(MediaType.APPLICATION_JSON_UTF8),
            Matchers.is(true)
        );
    }
}
