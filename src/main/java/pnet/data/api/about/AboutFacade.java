package pnet.data.api.about;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Facade for requesting details about the Data API.
 * 
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/about")
public class AboutFacade
{

    /**
     * Returns global information about the Partner.Net Data API.
     *
     * @return some information
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AboutDataDTO about()
    {
        return null;
    }

}
