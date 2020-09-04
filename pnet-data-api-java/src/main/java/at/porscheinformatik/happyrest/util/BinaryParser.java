package at.porscheinformatik.happyrest.util;

import java.io.IOException;
import java.io.InputStream;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestParserException;

public class BinaryParser implements RestParser
{

    public static final BinaryParser INSTANCE = new BinaryParser();

    private static final GenericType<byte[]> BINARY_ARRAY_TYPE = GenericType.of(Byte.TYPE.arrayType());

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
            return in.readAllBytes();
        }
        catch (IOException e)
        {
            throw new RestParserException("Failed to read bytes", e);
        }
    }

}
