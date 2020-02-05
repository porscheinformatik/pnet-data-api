package at.porscheinformatik.happyrest.apache;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.jackson.JacksonBasedFormatter;
import at.porscheinformatik.happyrest.jackson.JacksonBasedParser;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;

/**
 * A {@link RestCall} using the Apache HTTP Client
 *
 * @author HAM
 */
public class ApacheRestCallFactory implements RestCallFactory
{

    public static ApacheRestCallFactory create(RestLoggerAdapter loggerAdapter, ObjectMapper mapper)
    {
        RestFormatter formatter = RestFormatter.of(new JacksonBasedFormatter(mapper), new TextPlainFormatter());
        RestParser parser = RestParser.of(new JacksonBasedParser(mapper));

        return new ApacheRestCallFactory(loggerAdapter, formatter, parser);
    }

    private final RestLoggerAdapter loggerAdapter;
    private final RestFormatter formatter;
    private final RestParser parser;

    public ApacheRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser)
    {
        super();

        this.loggerAdapter = loggerAdapter;
        this.formatter = formatter;
        this.parser = parser;
    }

    @Override
    public RestCall url(String url)
    {
        return new ApacheRestCall(loggerAdapter, url, null, MediaType.APPLICATION_JSON_UTF8_VALUE, null, formatter,
            parser, null);
    }

}
