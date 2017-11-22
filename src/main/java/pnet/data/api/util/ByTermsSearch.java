package pnet.data.api.util;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Used by facades that returns items by a terms search.
 *
 * @author ham
 * @param <ResultT> the type of the result
 */
public interface ByTermsSearch<ResultT>
{

    /**
     * Finds all items with the specified query.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @return a collection of results
     */
    @RequestMapping(value = "/find")
    Collection<ResultT> find(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage);

}
