package at.porscheinformatik.happyrest.util;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestFormatterException;
import at.porscheinformatik.happyrest.RestUtils;

public class TextPlainFormatter implements RestFormatter
{

    @Override
    public boolean isContentTypeSupported(String contentType)
    {
        return RestCall.MEDIA_TYPE_TEXT_PLAIN.equals(RestUtils.extractContentType(contentType));
    }

    @Override
    public String format(String contentType, Object value) throws RestFormatterException
    {
        return value == null ? null : String.valueOf(value);
    }

}
