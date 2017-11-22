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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Used by facades that returns items by a fuzzy search.
 *
 * @author ham
 * @param <ResultT> the type of the result
 */
public interface ByFuzzySearch<ResultT>
{

    /**
     * Searches for the items with the specified query.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @return a collection of results
     */
    @RequestMapping(value = "/search")
    Collection<ResultT> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage);

}
