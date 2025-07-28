package at.porscheinformatik.happyrest.util;

import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestFormatterException;

public class TextPlainFormatter implements RestFormatter {

    @Override
    public boolean isContentTypeSupported(MediaType contentType) {
        return MediaType.ANY.isCompatible(contentType);
    }

    @Override
    public String format(MediaType contentType, Object value) throws RestFormatterException {
        return value == null ? null : String.valueOf(value);
    }
}
