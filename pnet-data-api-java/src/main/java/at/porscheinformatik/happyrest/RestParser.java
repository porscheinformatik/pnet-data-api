package at.porscheinformatik.happyrest;

import java.io.InputStream;

/**
 * Parses a rest response
 *
 * @author HAM
 */
public interface RestParser
{

    static RestParser of(RestParser... parsers)
    {
        return new RestParser()
        {
            @Override
            public boolean isContentTypeSupported(String contentType, GenericType<?> type)
            {
                for (RestParser parser : parsers)
                {
                    if (parser.isContentTypeSupported(contentType, type))
                    {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public <T> Object parse(String contentType, GenericType<?> type, InputStream in) throws RestParserException
            {
                for (RestParser parser : parsers)
                {
                    if (parser.isContentTypeSupported(contentType, type))
                    {
                        return parser.<T> parse(contentType, type, in);
                    }
                }

                throw new RestParserException("Cannot convert " + contentType + " to " + type);
            }
        };
    }

    boolean isContentTypeSupported(String contentType, GenericType<?> type);

    <T> Object parse(String contentType, GenericType<?> type, InputStream in) throws RestParserException;

}
