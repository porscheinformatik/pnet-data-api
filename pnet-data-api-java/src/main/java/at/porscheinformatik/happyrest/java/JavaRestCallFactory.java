package at.porscheinformatik.happyrest.java;

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
 * A {@link RestCall} using the Java Client
 *
 * @author HAM
 */
public class JavaRestCallFactory implements RestCallFactory
{

    public static JavaRestCallFactory create(RestLoggerAdapter loggerAdapter, ObjectMapper mapper)
    {
        RestFormatter formatter = RestFormatter.of(new JacksonBasedFormatter(mapper), new TextPlainFormatter());
        RestParser parser = RestParser.of(new JacksonBasedParser(mapper));

        return new JavaRestCallFactory(loggerAdapter, formatter, parser);
    }

    private final RestLoggerAdapter loggerAdapter;
    private final RestFormatter formatter;
    private final RestParser parser;

    public JavaRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser)
    {
        super();

        this.loggerAdapter = loggerAdapter;
        this.formatter = formatter;
        this.parser = parser;
    }

    @Override
    public RestCall url(String url)
    {
        return new JavaRestCall(loggerAdapter, url, null, MediaType.APPLICATION_JSON_UTF8_VALUE, null, formatter,
            parser, null);
    }

}