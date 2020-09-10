package at.porscheinformatik.happyrest.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestParserException;
import at.porscheinformatik.happyrest.RestUtils;

public class BinaryParser implements RestParser
{

    public static final BinaryParser INSTANCE = new BinaryParser();

    private static final GenericType<byte[]> BINARY_ARRAY_TYPE =
        GenericType.of(Array.newInstance(Byte.TYPE, 0).getClass());

    protected BinaryParser()
    {
        super();
    }

    @Override
    public boolean isContentTypeSupported(String contentType, GenericType<?> type)
    {

        return type.isAssignableFrom(BINARY_ARRAY_TYPE);
    }

    @Override
    public <T> Object parse(String contentType, GenericType<?> type, InputStream in) throws RestParserException
    {
        try
        {
            return RestUtils.readAllBytes(in);
        }
        catch (IOException e)
        {
            throw new RestParserException("Failed to read bytes", e);
        }
    }

}
