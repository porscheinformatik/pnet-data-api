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
package pnet.data.api.advisortype;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.util.ByMatchcode;

/**
 * API for advisor types.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/advisortypes")
public interface AdvisorTypeDataFacade extends ByMatchcode<AdvisorTypeMatchcode, AdvisorTypeDataDTO>
{

    /**
     * Returns multiple {@link AdvisorTypeDataDTO}s each matching all specified filters. The method is limited to a
     * maximum number of items per request. If no values are specified, it tries to return all items but may fail due to
     * the maximum number of items.
     *
     * @param matchcodes the matchcodes for filtering, optional
     * @return a collection of all found items
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<AdvisorTypeDataDTO> getAll(
        @RequestParam(value = "matchcode", required = false) Collection<AdvisorTypeMatchcode> matchcodes);

    /**
     * Searches for {@link AdvisorTypeItemDTO} with the specified query.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @return a collection of results
     */
    @RequestMapping(value = "/search")
    Collection<AdvisorTypeItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage);

}
