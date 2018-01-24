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
package pnet.data.api.brand;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.ResultCollection;
import pnet.data.api.ResultPage;
import pnet.data.api.Tenant;
import pnet.data.api.util.ByMatchcode;

/**
 * API for brands.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/brands")
public interface BrandDataFacade extends ByMatchcode<BrandMatchcode, BrandDataDTO>
{

    /**
     * Returns multiple {@link BrandDataDTO}s each matching all specified restrictions. If one or more restrictions are
     * set each restriction will be applied (AND) and one of the values of each restriction must match (OR). It is not
     * possible to call this method without any restriction. The number of results is limited. The
     * {@link ResultCollection} may contain a call for more results.
     *
     * @param matchcodes the matchcodes for filtering, optional
     * @return a collection of all found items, never null
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultCollection<BrandDataDTO> getAll(
        @RequestParam(value = "mc", required = false) Collection<BrandMatchcode> matchcodes);

    /**
     * Searches for {@link BrandItemDTO} with the specified query. If one or more filters are set each filter will be
     * applied (AND) and one of the values of each filter must match (OR). The method returns a stripped down item with
     * only a few properties.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @param tenants the tenants for filtering, optional
     * @return a page of the results, never null
     */
    @RequestMapping(value = "/search")
    ResultPage<BrandItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage,
        @RequestParam(value = "t", required = false) Collection<Tenant> tenants);

    /**
     * Finds multiple {@link BrandItemDTO}s each matching all specified restrictions. If one or more restrictions are
     * set each restriction will be applied (AND) and one of the values of each restriction must match (OR).
     *
     * @param language the language
     * @param matchcodes the matchcodes for filtering, optional
     * @param tenants the tenants for filtering, optional
     * @param updatedAfter updated after the specified date and time, optional
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @return a page of the results, never null
     */
    @RequestMapping(value = "/find")
    ResultPage<BrandItemDTO> find(@RequestParam(value = "l") String language,
        @RequestParam(value = "mc", required = false) Collection<BrandMatchcode> matchcodes,
        @RequestParam(value = "t", required = false) Collection<Tenant> tenants,
        @RequestParam(value = "up", required = false) LocalDateTime updatedAfter,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage);

}
