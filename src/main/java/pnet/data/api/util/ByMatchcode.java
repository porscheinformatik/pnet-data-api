package pnet.data.api.util;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Used by facades that returns items by {@link Matchcode}.
 *
 * @author ham
 * @param <MatchcodeT> the type of the matchcode
 * @param <ResultT> the type of the result
 */
public interface ByMatchcode<MatchcodeT extends Matchcode, ResultT extends WithMatchcode<MatchcodeT>>
{

    /**
     * Returns the item with the specified matchcode.
     *
     * @param matchcode the matchcode
     * @return the item, or null if not found
     */
    @RequestMapping(value = "/{mc}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultT getByMatchcode(@PathVariable("mc") MatchcodeT matchcode);

    /**
     * Returns multiple items with the specified matchcodes. The method is limited to a maximum number of items per
     * request. If no matchcode is specified, it tries to return all items but may fail due to the maximum number of
     * items.
     *
     * @param matchcodes the matchcodes
     * @return a collection of all found items
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<ResultT> getAllByMatchcodes(
        @RequestParam(value = "mc", required = false) Collection<MatchcodeT> matchcodes);

}
