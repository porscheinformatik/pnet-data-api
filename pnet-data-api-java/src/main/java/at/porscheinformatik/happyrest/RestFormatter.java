package at.porscheinformatik.happyrest;

/**
 * A formatter for parameters, attributes and header values
 *
 * @author ham
 */
public interface RestFormatter
{

    static RestFormatter of(RestFormatter... formatters)
    {
        return new RestFormatter()
        {

            @Override
            public boolean isContentTypeSupported(String contentType)
            {
                for (RestFormatter formatter : formatters)
                {
                    if (formatter.isContentTypeSupported(contentType))
                    {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public String format(String contentType, Object value) throws RestFormatterException
            {
                for (RestFormatter formatter : formatters)
                {
                    if (formatter.isContentTypeSupported(contentType))
                    {
                        return formatter.format(contentType, value);
                    }
                }

                throw new RestFormatterException("Unsupported content type: " + contentType);
            }

        };
    }

    boolean isContentTypeSupported(String contentType);

    String format(String contentType, Object value) throws RestFormatterException;

}
