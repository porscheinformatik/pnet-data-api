package at.porscheinformatik.happyrest.spring;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestUtils;
import at.porscheinformatik.happyrest.slf4j.Slf4jRestLoggerAdapter;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * A factory for REST calls using Spring WebClient
 *
 * @author ham
 */
@Service
public class WebClientRestCallFactory implements RestCallFactory {

    private static WebClientRestCallFactory defaultFactory = null;

    public static WebClientRestCallFactory getDefault() {
        WebClientRestCallFactory factory = defaultFactory;

        if (factory == null) {
            WebClient webClient = WebClient.builder()
                .defaultHeader("user-agent", RestUtils.getUserAgent("Spring's WebClient"))
                .build();

            factory = new WebClientRestCallFactory(
                webClient,
                Slf4jRestLoggerAdapter.getDefault(),
                new TextPlainFormatter()
            );

            defaultFactory = factory;
        }

        return factory;
    }

    protected final WebClient webClient;
    protected final RestLoggerAdapter loggerAdapter;
    protected final RestFormatter formatter;

    @Autowired
    public WebClientRestCallFactory(WebClient webClient, RestLoggerAdapter loggerAdapter, RestFormatter formatter) {
        super();
        this.webClient = webClient;
        this.loggerAdapter = loggerAdapter;
        this.formatter = formatter;
    }

    @Override
    public RestCall url(String url) {
        return new WebClientRestCall(webClient, loggerAdapter, formatter, url);
    }
}
