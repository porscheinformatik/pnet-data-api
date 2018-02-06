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

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.GeoDistance;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.ResultCollection;
import pnet.data.api.ResultPage;
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
     * Returns multiple {@link CompanyDataDTO}s each matching all specified restrictions. If one or more restrictions
     * are set each restriction will be applied (AND) and one of the values of each restriction must match (OR). It is
     * not possible to call this method without any restriction. The number of results is limited. The
     * {@link ResultCollection} may contain a call for more results.
     *
     * @param ids the ids for filtering, optional
     * @param uidNumbers one or more UID numbers for filtering, optional
     * @param sapNumbers one or more SAP numbers for filtering, optional
     * @param companyNumbers one or more company numbers for filtering, optional
     * @param ibans one or more IBANs for filtering, optional
     * @param email one or more emails for filtering, optional
     * @param dvrNumber one or more DVR numbers for filtering, optional
     * @param fbNumber one or more FB numbers for filtering, optional
     * @return a collection of all found items, never null
     * @throws PnetDataApiException on occasion
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    // CHECKSTYLE:OFF
    ResultCollection<CompanyDataDTO> getAll(@RequestParam(value = "id", required = false) List<Integer> ids,
        @RequestParam(value = "uidNumber", required = false) List<String> uidNumbers,
        @RequestParam(value = "sapNumber", required = false) List<String> sapNumbers,
        @RequestParam(value = "companyNumber", required = false) List<String> companyNumbers,
        @RequestParam(value = "iban", required = false) List<String> ibans,
        @RequestParam(value = "email", required = false) List<String> email,
        @RequestParam(value = "dvrNumber", required = false) List<String> dvrNumber,
        @RequestParam(value = "fbNumber", required = false) List<String> fbNumber) throws PnetDataApiException;
    // CHECKSTYLE:ON

    /**
     * Searches for {@link CompanyItemDTO} with the specified query. If one or more filters are set each filter will be
     * applied (AND) and one of the values of each filter must match (OR). The method returns a stripped down company
     * with only a few properties.
     *
     * @param language the language
     * @param query the query
     * @param pageIndex the number of the page, 0-based
     * @param itemsPerPage the number of items per page
     * @param companyIds one or more company ids for filtering, optional
     * @param brandMatchcodes one or more brand matchcodes for filtering, optional
     * @return a page of the results, never null
     * @throws PnetDataApiException on occasion
     */
    @RequestMapping(value = "/search")
    ResultPage<CompanyItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "0") int pageIndex,
        @RequestParam(value = "pp", defaultValue = "10") int itemsPerPage,
        @RequestParam(value = "companyId", required = false) List<Integer> companyIds,
        @RequestParam(value = "b", required = false) List<String> brandMatchcodes) throws PnetDataApiException;

    /**
     * Finds multiple {@link CompanyItemDTO}s each matching all specified restrictions. If one or more restrictions are
     * set each restriction will be applied (AND) and one of the values of each restriction must match (OR).
     *
     * @param language the language
     * @param ids one or more ids for filtering, optional
     * @param brandMatchcodes one or more brand matchcodes for filtering, optional
     * @param zips one or more ZIPs for filtering, optional
     * @param countryCodes one or more country codes for filtering, optional
     * @param typeMatchcodes one or more type matchcodes for filtering, optional
     * @param contractTypeMatchcodes one or more contract type matchcodes for filtering, optional
     * @param distances one or more GEO distances for filtering, optional
     * @param externalBrandMatchcodes one or more external brand matchcodes for filtering, optional
     * @param headquaterCompanyIds headquaters for filtering, optional
     * @param updatedAfter updated after the specified date and time, optional
     * @param pageIndex the number of the page, 0-based
     * @param itemsPerPage the number of items per page
     * @return a page of the results, never null
     * @throws PnetDataApiException on occasion
     */
    // CHECKSTYLE:OFF
    @RequestMapping(value = "/find")
    ResultPage<CompanyItemDTO> find(@RequestParam(value = "l") String language,
        @RequestParam(value = "id", required = false) List<Integer> ids,
        @RequestParam(value = "b", required = false) List<String> brandMatchcodes,
        @RequestParam(value = "zip", required = false) List<String> zips,
        @RequestParam(value = "countryCode", required = false) List<String> countryCodes,
        @RequestParam(value = "type", required = false) List<String> typeMatchcodes,
        @RequestParam(value = "contractType", required = false) List<String> contractTypeMatchcodes,
        @RequestParam(value = "distance", required = false) List<GeoDistance> distances,
        @RequestParam(value = "externalBrands", required = false) List<String> externalBrandMatchcodes,
        @RequestParam(value = "headquater", required = false) List<Integer> headquaterCompanyIds,
        @RequestParam(value = "up", required = false) LocalDateTime updatedAfter,
        @RequestParam(value = "p", defaultValue = "0") int pageIndex,
        @RequestParam(value = "pp", defaultValue = "10") int itemsPerPage) throws PnetDataApiException;
    // CHECKSTYLE:ON

}
