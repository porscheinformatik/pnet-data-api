package at.porscheinformatik.happyrest.util;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestParserException;
import at.porscheinformatik.happyrest.RestUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringParser implements RestParser {

    public static final StringParser INSTANCE = new StringParser();

    private static final GenericType<String> STRING_TYPE = GenericType.of(String.class);

    protected StringParser() {
        super();
    }

    @Override
    public boolean isContentTypeSupported(MediaType contentType, GenericType<?> type) {
        return type.isAssignableFrom(STRING_TYPE);
    }

    @Override
    public String parse(MediaType contentType, GenericType<?> type, String s) throws RestParserException {
        return s;
    }

    @Override
    public String parse(MediaType contentType, GenericType<?> type, byte[] bytes) throws RestParserException {
        Charset charset = contentType != null ? contentType.getCharset(StandardCharsets.UTF_8) : StandardCharsets.UTF_8;

        return new String(bytes, charset);
    }

    @Override
    public String parse(MediaType contentType, GenericType<?> type, InputStream in) throws RestParserException {
        Charset charset = contentType != null ? contentType.getCharset(StandardCharsets.UTF_8) : StandardCharsets.UTF_8;

        try (Reader reader = new InputStreamReader(in, charset)) {
            return RestUtils.readFully(reader);
        } catch (IOException e) {
            throw new RestParserException("Failed to read chars", e);
        }
    }
}
