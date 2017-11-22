package pnet.data.api.util;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Used by facades that returns items by id.
 *
 * @author ham
 * @param <ResultT> the type of the result
 */
public interface ById<ResultT>
{

    /**
     * Returns the item with the specified id
     *
     * @param id the id
     * @return the item, or null if not found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultT getByMatchcode(@PathVariable("id") Integer id);

    /**
     * Returns multiple items with the specified ids. The method is limited to a maximum number of items per request. If
     * no id is specified, it tries to return all items but may fail due to the maximum number of items.
     *
     * @param ids the ids
     * @return a collection of all found items
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<ResultT> getAll(@RequestParam(value = "id", required = false) Collection<Integer> ids);

}
