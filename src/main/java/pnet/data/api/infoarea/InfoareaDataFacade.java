package pnet.data.api.infoarea;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * API for infoareas.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/infoareas")
public interface InfoareaDataFacade
{

    /**
     * The maximum number of results per request.
     */
    int MAX_ITEMS = 16;

    /**
     * Searches for the infoareas with the specified query.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @return a collection of results
     */
    @RequestMapping(value = "/search")
    Collection<InfoareaItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "" + MAX_ITEMS) int perPage);

    /**
     * Returns the infoarea with the specified matchcode.
     *
     * @param matchcode the famous matchcode
     * @return the infoarea, or null if not found
     */
    @RequestMapping(value = "/{mc}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    InfoareaDataDTO getByMatchcode(@PathVariable("mc") InfoareaMatchcode matchcode);

    /**
     * Returns multiple infoareas with the specified matchcodes. The method is limited to {@value #MAX_ITEMS} items per
     * request.
     *
     * @param matchcodes the matchcodes
     * @return a collection of all found infoareas
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<InfoareaDataDTO> getAllByMatchcodes(
        @RequestParam(value = "mc") Collection<InfoareaMatchcode> matchcodes);
}
