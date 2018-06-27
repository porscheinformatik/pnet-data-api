package pnet.data.api.spring;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.about.AboutDataClient;
import pnet.data.api.about.AboutDataDTO;
import pnet.data.api.activity.ActivityDataClient;
import pnet.data.api.activity.ActivityItemDTO;
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.CLI;
import pnet.data.api.util.CLI.Arguments;

@Service
public class PnetSpringRestClient
{

    private final CLI cli;

    @Autowired
    private MutablePnetDataClientPrefs prefs;

    @Autowired
    private AboutDataClient aboutDataClient;

    @Autowired
    private ActivityDataClient activityDataClient;

    private PnetSpringRestClient()
    {
        super();

        cli = new CLI();

        cli.register(this);
    }

    @CLI.Command(description = "Info about the Partner.Net Data API and the user.")
    public void about() throws PnetDataApiException
    {
        AboutDataDTO about = aboutDataClient.about();

        cli.info(about);
    }

    @CLI.Command(format = "query", description = "Query activities")
    public void activities(String query) throws PnetDataApiException
    {
        PnetDataClientResultPage<ActivityItemDTO> page =
            activityDataClient.search().execute(Locale.getDefault(), query);

        cli.info("Found %d results.", page.getTotalNumberOfItems());

        page.forEach(cli::info);
    }

    @CLI.Command(format = "[url]", description = "Prints or overrides the predefined URL.")
    public void url(String url)
    {
        if (url != null)
        {
            prefs.setPnetDataApiUrl(url);
        }

        cli.info("url = %s", prefs.getPnetDataApiUrl());
    }

    @CLI.Command(format = "[username]", description = "Prints or overrides the username and password.")
    public void user(String username) throws IOException
    {
        if (username != null)
        {
            Arguments arguments = cli.consume("Password: ");
            String password = arguments.consumeString();

            if (password != null)
            {
                prefs.setPnetDataApiUsername(username);
                prefs.setPnetDataApiPassword(password);
            }
            else
            {
                cli.warn("Aborted.");
            }
        }

        cli.info("username = %s", prefs.getPnetDataApiUsername());
    }

    public void consume()
    {
        cli.info("Partner.Net REST Client Sample application");
        cli.info("==========================================");
        cli.info("");
        cli.info("Type \"help\" for help.");
        cli.info("");

        while (true)
        {
            try
            {
                Thread.sleep(500);

                cli.consumeCommand(null);
            }
            catch (Exception e)
            {
                cli.error("Command failed", e);
            }
        }
    }

}
