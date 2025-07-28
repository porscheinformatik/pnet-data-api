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
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CharArrayParser implements RestParser {

    public static final CharArrayParser INSTANCE = new CharArrayParser();

    private static final GenericType<byte[]> CHAR_ARRAY_TYPE = GenericType.of(
        Array.newInstance(Character.TYPE, 0).getClass()
    );

    protected CharArrayParser() {
        super();
    }

    @Override
    public boolean isContentTypeSupported(MediaType contentType, GenericType<?> type) {
        return type.isAssignableFrom(CHAR_ARRAY_TYPE);
    }

    @Override
    public char[] parse(MediaType contentType, GenericType<?> type, String s) throws RestParserException {
        return s.toCharArray();
    }

    @Override
    public char[] parse(MediaType contentType, GenericType<?> type, byte[] bytes) throws RestParserException {
        return new String(
            bytes,
            contentType != null ? contentType.getCharset(StandardCharsets.UTF_8) : StandardCharsets.UTF_8
        ).toCharArray();
    }

    @Override
    public char[] parse(MediaType contentType, GenericType<?> type, InputStream in) throws RestParserException {
        Charset charset = contentType != null ? contentType.getCharset(StandardCharsets.UTF_8) : StandardCharsets.UTF_8;

        try (Reader reader = new InputStreamReader(in, charset)) {
            return RestUtils.readAllChars(reader);
        } catch (IOException e) {
            throw new RestParserException("Failed to read chars", e);
        }
    }
}
