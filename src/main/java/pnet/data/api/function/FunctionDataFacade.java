package pnet.data.api.function;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByFuzzySearch;
import pnet.data.api.util.ByMatchcode;

/**
 * API for Functions.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/functions")
public interface FunctionDataFacade
    extends ByMatchcode<FunctionMatchcode, FunctionDataDTO>, ByFuzzySearch<FunctionItemDTO>
{

    /**
     * The maximum number of results per request.
     */
    int MAX_ITEMS = 16;

    /**
     * Searches for the functions with the specified query.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @return a collection of results
     */
    @Override
    @RequestMapping(value = "/search")
    Collection<FunctionItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "" + MAX_ITEMS) int perPage);

    /**
     * Returns the function with the specified matchcode.
     *
     * @param matchcode the famous matchcode
     * @return the function, or null if not found
     */
    @Override
    @RequestMapping(value = "/{mc}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    FunctionDataDTO getByMatchcode(@PathVariable("mc") FunctionMatchcode matchcode);

    /**
     * Returns multiple functions with the specified matchcodes. The method is limited to {@value #MAX_ITEMS} items per
     * request.
     *
     * @param matchcodes the matchcodes
     * @return a collection of all found functions
     */
    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<FunctionDataDTO> getAllByMatchcodes(
        @RequestParam(value = "mc") Collection<FunctionMatchcode> matchcodes);
}
