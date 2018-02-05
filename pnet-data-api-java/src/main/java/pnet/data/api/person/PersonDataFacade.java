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
package pnet.data.api.person;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.ResultCollection;
import pnet.data.api.ResultPage;
import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.util.ById;

/**
 * API for persons.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/persons")
public interface PersonDataFacade extends ById<PersonDataDTO>
{

    /**
     * Returns multiple {@link PersonDataDTO}s each matching all specified restrictions. If one or more restrictions are
     * set each restriction will be applied (AND) and one of the values of each restriction must match (OR). It is not
     * possible to call this method without any restriction. The number of results is limited. The
     * {@link ResultCollection} may contain a call for more results.
     *
     * @param ids the ids for filtering, optional
     * @param guids one or more guids for filtering, optional
     * @param preferredUserIds one or more preferredUserIds for filtering, optional
     * @param emails one or more emails for filtering, optional
     * @param personnelNumbers one or more personnelNumbers for filtering, optional
     * @return a collection of all found items, never null
     * @throws PnetDataApiException on occasion
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultCollection<PersonDataDTO> getAll(@RequestParam(value = "id", required = false) Collection<Integer> ids,
        @RequestParam(value = "guid", required = false) Collection<String> guids,
        @RequestParam(value = "preferredUserId", required = false) Collection<String> preferredUserIds,
        @RequestParam(value = "email", required = false) Collection<String> emails,
        @RequestParam(value = "personnelNumber", required = false) Collection<String> personnelNumbers)
        throws PnetDataApiException;

    /**
     * Searches for {@link PersonItemDTO} with the specified query. If one or more filters are set each filter will be
     * applied (AND) and one of the values of each filter must match (OR). The method returns a stripped down person
     * with only a few properties.
     *
     * @param language the language
     * @param query the query
     * @param pageIndex the number of the page, 0-based
     * @param itemsPerPage the number of items per page
     * @param companyIds one or more company ids for filtering, optional
     * @param brandMatchcodes one or more brand matchcodes for filtering, optional
     * @param functionMatchcodes one or more function matchcodes for filtering, optional
     * @param activityMatchcodes one or more activity matchcodes for filtering, optional
     * @return a page of the results, never null
     * @throws PnetDataApiException on occasion
     */
    // CHECKSTYLE:OFF
    @RequestMapping(value = "/search")
    ResultPage<PersonItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "0") int pageIndex,
        @RequestParam(value = "pp", defaultValue = "10") int itemsPerPage,
        @RequestParam(value = "companyId", required = false) Collection<Integer> companyIds,
        @RequestParam(value = "b", required = false) Collection<BrandMatchcode> brandMatchcodes,
        @RequestParam(value = "function", required = false) Collection<BrandMatchcode> functionMatchcodes,
        @RequestParam(value = "activity", required = false) Collection<BrandMatchcode> activityMatchcodes)
        throws PnetDataApiException;
    // CHECKSTYLE:ON

    /**
     * Finds multiple {@link PersonItemDTO}s each matching all specified restrictions. If one or more restrictions are
     * set each restriction will be applied (AND) and one of the values of each restriction must match (OR).
     *
     * @param language the language
     * @param ids one or more ids for filtering, optional
     * @param guids one or more guids for filtering, optional
     * @param preferredUserIds one or more preferredUserIds for filtering, optional
     * @param emails one or more emails for filtering, optional
     * @param costCenters one or more costCenters for filtering, optional
     * @param personnelNumbers one or more personnelNumbers for filtering, optional
     * @param supervisorPersonnelNumbesr one or more supervisorPersonnelNumbesr for filtering, optional
     * @param controllingAreas one or more controllingAreas for filtering, optional
     * @param personnelDepartments one or more personnelDepartments for filtering, optional
     * @param companyIds one or more company ids for filtering, optional
     * @param brandMatchcodes one or more brand matchcodes for filtering, optional
     * @param functionMatchcodes one or more function matchcodes for filtering, optional
     * @param activityMatchcodes one or more activity matchcodes for filtering, optional
     * @param updatedAfter updated after the specified date and time, optional
     * @param pageIndex the number of the page, 0-based
     * @param itemsPerPage the number of items per page
     * @return a page of the results, never null
     * @throws PnetDataApiException on occasion
     */
    // CHECKSTYLE:OFF
    @RequestMapping(value = "/find")
    Collection<PersonItemDTO> find(@RequestParam(value = "l") String language,
        @RequestParam(value = "id", required = false) Collection<Integer> ids,
        @RequestParam(value = "guid", required = false) Collection<String> guids,
        @RequestParam(value = "preferredUserId", required = false) Collection<String> preferredUserIds,
        @RequestParam(value = "email", required = false) Collection<String> emails,
        @RequestParam(value = "costCenter", required = false) Collection<String> costCenters,
        @RequestParam(value = "personnelNumber", required = false) Collection<String> personnelNumbers,
        @RequestParam(value = "supervisorPersonnelNumber",
            required = false) Collection<String> supervisorPersonnelNumbesr,
        @RequestParam(value = "controllingArea", required = false) Collection<String> controllingAreas,
        @RequestParam(value = "personnelDepartment", required = false) Collection<String> personnelDepartments,
        @RequestParam(value = "companyId", required = false) Collection<Integer> companyIds,
        @RequestParam(value = "b", required = false) Collection<BrandMatchcode> brandMatchcodes,
        @RequestParam(value = "function", required = false) Collection<BrandMatchcode> functionMatchcodes,
        @RequestParam(value = "activity", required = false) Collection<BrandMatchcode> activityMatchcodes,
        @RequestParam(value = "up", required = false) LocalDateTime updatedAfter,
        @RequestParam(value = "p", defaultValue = "0") int pageIndex,
        @RequestParam(value = "pp", defaultValue = "10") int itemsPerPage) throws PnetDataApiException;
    // CHECKSTYLE:ON

}
