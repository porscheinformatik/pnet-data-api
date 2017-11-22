/* Copyright 2017 Porsche Informatik GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
