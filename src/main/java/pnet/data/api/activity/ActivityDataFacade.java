package pnet.data.api.activity;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * API for Activitys.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/activitys")
public interface ActivityDataFacade
{

    /**
     * The maximum number of results per request.
     */
    int MAX_ITEMS = 16;

    /**
     * Searches for the activitys with the specified query.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @return a collection of results
     */
    @RequestMapping(value = "/search")
    Collection<ActivityItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "" + MAX_ITEMS) int perPage);

    /**
     * Returns the activity with the specified matchcode.
     *
     * @param matchcode the famous matchcode
     * @return the activity, or null if not found
     */
    @RequestMapping(value = "/{mc}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ActivityDataDTO getByMatchcode(@PathVariable("mc") ActivityMatchcode matchcode);

    /**
     * Returns multiple activitys with the specified matchcodes. The method is limited to {@value #MAX_ITEMS} items per
     * request.
     *
     * @param matchcodes the matchcodes
     * @return a collection of all found activitys
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<ActivityDataDTO> getAllByMatchcodes(
        @RequestParam(value = "mc") Collection<ActivityMatchcode> matchcodes);
}
