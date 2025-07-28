package at.porscheinformatik.happyrest;

import java.io.InputStream;

/**
 * Parses a rest response
 *
 * @author HAM
 */
public interface RestParser {
    static RestParser of(RestParser... parsers) {
        return new RestParser() {
            @Override
            public boolean isContentTypeSupported(MediaType contentType, GenericType<?> type) {
                for (RestParser parser : parsers) {
                    if (parser.isContentTypeSupported(contentType, type)) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public Object parse(MediaType contentType, GenericType<?> type, String s) throws RestParserException {
                for (RestParser parser : parsers) {
                    if (parser.isContentTypeSupported(contentType, type)) {
                        return parser.parse(contentType, type, s);
                    }
                }

                throw new RestParserException("Cannot convert " + contentType + " to " + type);
            }

            @Override
            public Object parse(MediaType contentType, GenericType<?> type, byte[] bytes) throws RestParserException {
                for (RestParser parser : parsers) {
                    if (parser.isContentTypeSupported(contentType, type)) {
                        return parser.parse(contentType, type, bytes);
                    }
                }

                throw new RestParserException("Cannot convert " + contentType + " to " + type);
            }

            @Override
            public Object parse(MediaType contentType, GenericType<?> type, InputStream in) throws RestParserException {
                for (RestParser parser : parsers) {
                    if (parser.isContentTypeSupported(contentType, type)) {
                        return parser.parse(contentType, type, in);
                    }
                }

                throw new RestParserException("Cannot convert " + contentType + " to " + type);
            }
        };
    }

    boolean isContentTypeSupported(MediaType contentType, GenericType<?> type);

    Object parse(MediaType contentType, GenericType<?> type, String s) throws RestParserException;

    Object parse(MediaType contentType, GenericType<?> type, byte[] bytes) throws RestParserException;

    Object parse(MediaType contentType, GenericType<?> type, InputStream in) throws RestParserException;
}
