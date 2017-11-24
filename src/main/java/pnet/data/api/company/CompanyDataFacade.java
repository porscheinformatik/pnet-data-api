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
package pnet.data.api.company;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.companytype.CompanyTypeMatchcode;
import pnet.data.api.externalbrand.ExternalBrandMatchcode;
import pnet.data.api.util.ById;

/**
 * API for companies.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/companies")
public interface CompanyDataFacade extends ById<CompanyDataDTO>
{

    /**
     * Returns multiple {@link CompanyDataDTO}s each matching all specified filters. The method is limited to a maximum
     * number of items per request. If no values are specified, it tries to return all items but may fail due to the
     * maximum number of items.
     *
     * @param ids the ids for filtering, optional
     * @param uidNumbers one or more uidNumbers for filtering, optional
     * @param sapNumbers one or more sapNumbers for filtering, optional
     * @param companyNumbers one or more companyNumbers for filtering, optional
     * @param zips one or more zips for filtering, optional
     * @param countryCodes one or more countryCodes for filtering, optional
     * @param ibans one or more ibans for filtering, optional
     * @param bics one or more bics for filtering, optional
     * @param types one or more types for filtering, optional
     * @param email one or more email for filtering, optional
     * @param dvrNumber one or more dvrNumber for filtering, optional
     * @param fbNumber one or more fbNumber for filtering, optional
     * @param externalBrandMatchcodes one or more externalBrandMatchcodes for filtering, optional
     * @return a collection of all found items
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    // CHECKSTYLE:OFF
    Collection<CompanyDataDTO> getAll(@RequestParam(value = "id", required = false) Collection<Integer> ids,
        @RequestParam(value = "uidNumber", required = false) Collection<String> uidNumbers,
        @RequestParam(value = "sapNumber", required = false) Collection<String> sapNumbers,
        @RequestParam(value = "companyNumber", required = false) Collection<String> companyNumbers,
        @RequestParam(value = "zip", required = false) Collection<String> zips,
        @RequestParam(value = "countryCode", required = false) Collection<String> countryCodes,
        @RequestParam(value = "iban", required = false) Collection<String> ibans,
        @RequestParam(value = "bics", required = false) Collection<String> bics,
        @RequestParam(value = "types", required = false) Collection<CompanyTypeMatchcode> types,
        @RequestParam(value = "email", required = false) Collection<String> email,
        @RequestParam(value = "dvrNumber", required = false) Collection<String> dvrNumber,
        @RequestParam(value = "fbNumber", required = false) Collection<String> fbNumber,
        @RequestParam(value = "externalBrands",
            required = false) Collection<ExternalBrandMatchcode> externalBrandMatchcodes);
    // CHECKSTYLE:ON

    /**
     * Searches for {@link CompanyItemDTO} with the specified query. If one or more filters are set each filter will be
     * applied (AND) and one of the values of each filter must match (OR). The method returns a stripped down company
     * with only a few properties.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @param companyIds one or more company ids for filtering, optional
     * @param brandMatchcodes one or more brand matchcodes for filtering, optional
     * @param functionMatchcodes one or more function matchcodes for filtering, optional
     * @param activityMatchcodes one or more activity matchcodes for filtering, optional
     * @return a collection of results
     */
    // CHECKSTYLE:OFF
    @RequestMapping(value = "/search")
    Collection<CompanyItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage,
        @RequestParam(value = "companyId", required = false) Collection<Integer> companyIds,
        @RequestParam(value = "brand", required = false) Collection<BrandMatchcode> brandMatchcodes,
        @RequestParam(value = "function", required = false) Collection<BrandMatchcode> functionMatchcodes,
        @RequestParam(value = "activity", required = false) Collection<BrandMatchcode> activityMatchcodes);
    // CHECKSTYLE:ON

    /**
     * Searches for {@link CompanyItemDTO} using the specified terms. If one or more filters are set each filter will be
     * applied (AND) and one of the values of each filter must match (OR). The method returns a stripped down company
     * with only a few properties.
     *
     * @param guids one or more guids for filtering, optional
     * @param preferredUserIds one or more preferredUserIds for filtering, optional
     * @param emails one or more emails for filtering, optional
     * @param costCenters one or more costCenters for filtering, optional
     * @param companynelNumbers one or more companynelNumbers for filtering, optional
     * @param supervisorCompanynelNumbesr one or more supervisorCompanynelNumbesr for filtering, optional
     * @param controllingAreas one or more controllingAreas for filtering, optional
     * @param companynelDepartments one or more companynelDepartments for filtering, optional
     * @param companyIds one or more company ids for filtering, optional
     * @param brandMatchcodes one or more brand matchcodes for filtering, optional
     * @param functionMatchcodes one or more function matchcodes for filtering, optional
     * @param activityMatchcodes one or more activity matchcodes for filtering, optional
     * @return a collection of results
     */
    // CHECKSTYLE:OFF
    @RequestMapping(value = "/find")
    Collection<CompanyItemDTO> findAll(@RequestParam(value = "guid", required = false) Collection<String> guids,
        @RequestParam(value = "preferredUserId", required = false) Collection<String> preferredUserIds,
        @RequestParam(value = "email", required = false) Collection<String> emails,
        @RequestParam(value = "costCenter", required = false) Collection<String> costCenters,
        @RequestParam(value = "companynelNumber", required = false) Collection<String> companynelNumbers,
        @RequestParam(value = "supervisorCompanynelNumber",
            required = false) Collection<String> supervisorCompanynelNumbesr,
        @RequestParam(value = "controllingArea", required = false) Collection<String> controllingAreas,
        @RequestParam(value = "companynelDepartment", required = false) Collection<String> companynelDepartments,
        @RequestParam(value = "companyId", required = false) Collection<Integer> companyIds,
        @RequestParam(value = "brand", required = false) Collection<BrandMatchcode> brandMatchcodes,
        @RequestParam(value = "function", required = false) Collection<BrandMatchcode> functionMatchcodes,
        @RequestParam(value = "activity", required = false) Collection<BrandMatchcode> activityMatchcodes);
    // CHECKSTYLE:ON

}
