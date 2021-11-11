package at.porscheinformatik.happyrest.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestParserException;
import at.porscheinformatik.happyrest.RestUtils;

public class NumberParser implements RestParser
{

    public static final NumberParser INSTANCE = new NumberParser();

    private static final GenericType<String> NUMBER_TYPE = GenericType.of(Number.class);

    private static final GenericType<String> BYTE_TYPE = GenericType.of(Byte.class);
    private static final GenericType<String> SHORT_TYPE = GenericType.of(Short.class);
    private static final GenericType<String> INTEGER_TYPE = GenericType.of(Integer.class);
    private static final GenericType<String> LONG_TYPE = GenericType.of(Long.class);
    private static final GenericType<String> FLOAT_TYPE = GenericType.of(Float.class);
    private static final GenericType<String> DOUBLE_TYPE = GenericType.of(Double.class);
    private static final GenericType<String> BIG_INTEGER_TYPE = GenericType.of(BigInteger.class);
    private static final GenericType<String> BIG_DECIMAL_TYPE = GenericType.of(BigDecimal.class);

    protected NumberParser()
    {
        super();
    }

    @Override
    public boolean isContentTypeSupported(MediaType contentType, GenericType<?> type)
    {
        return type.isAssignableFrom(NUMBER_TYPE);
    }

    @Override
    public <T> Object parse(MediaType contentType, GenericType<?> type, InputStream in) throws RestParserException
    {
        Charset charset = contentType.getCharset(StandardCharsets.UTF_8);

        try (Reader reader = new InputStreamReader(in, charset))
        {
            String s = RestUtils.readFully(reader);

            try
            {
                if (type.isAssignableFrom(BYTE_TYPE))
                {
                    return Byte.decode(s);
                }

                if (type.isAssignableFrom(SHORT_TYPE))
                {
                    return Short.decode(s);
                }

                if (type.isAssignableFrom(INTEGER_TYPE))
                {
                    return Integer.decode(s);
                }

                if (type.isAssignableFrom(LONG_TYPE))
                {
                    return Long.decode(s);
                }

                if (type.isAssignableFrom(FLOAT_TYPE))
                {
                    return Float.parseFloat(s);
                }

                if (type.isAssignableFrom(DOUBLE_TYPE))
                {
                    return Double.parseDouble(s);
                }

                if (type.isAssignableFrom(BIG_INTEGER_TYPE))
                {
                    return new BigInteger(s);
                }

                if (type.isAssignableFrom(BIG_DECIMAL_TYPE))
                {
                    return new BigDecimal(s);
                }

                throw new IllegalArgumentException("Unsupported type: " + type);
            }
            catch (NumberFormatException e)
            {
                throw new RestParserException("Failed to parse number: " + s, e);
            }
        }
        catch (IOException e)
        {
            throw new RestParserException("Failed to read chars", e);
        }
    }
}
