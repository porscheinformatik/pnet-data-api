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
import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.GeoDistance;
import pnet.data.api.ResultPage;
import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.companytype.CompanyTypeMatchcode;
import pnet.data.api.contracttype.ContractTypeMatchcode;
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
     * Returns multiple {@link CompanyDataDTO}s each matching all specified filters. If one or more filters are set each
     * filter will be applied (AND) and one of the values of each filter must match (OR). It is not possible to call
     * this method without any filter and the maximum number of filter items is limited.
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
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    // CHECKSTYLE:OFF
    Collection<CompanyDataDTO> getAll(@RequestParam(value = "id", required = false) Collection<Integer> ids,
        @RequestParam(value = "uidNumber", required = false) Collection<String> uidNumbers,
        @RequestParam(value = "sapNumber", required = false) Collection<String> sapNumbers,
        @RequestParam(value = "companyNumber", required = false) Collection<String> companyNumbers,
        @RequestParam(value = "iban", required = false) Collection<String> ibans,
        @RequestParam(value = "email", required = false) Collection<String> email,
        @RequestParam(value = "dvrNumber", required = false) Collection<String> dvrNumber,
        @RequestParam(value = "fbNumber", required = false) Collection<String> fbNumber);
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
     * @return a page of the results, never null
     */
    @RequestMapping(value = "/search")
    ResultPage<CompanyItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage,
        @RequestParam(value = "companyId", required = false) Collection<Integer> companyIds,
        @RequestParam(value = "brand", required = false) Collection<BrandMatchcode> brandMatchcodes);

    /**
     * Searches for {@link CompanyItemDTO} using the specified terms. If one or more filters are set each filter will be
     * applied (AND) and one of the values of each filter must match (OR). The method returns a stripped down company
     * with only a few properties.
     *
     * @param language the language
     * @param brandMatchcodes one or more brand matchcodes for filtering, optional
     * @param zips one or more ZIPs for filtering, optional
     * @param countryCodes one or more country codes for filtering, optional
     * @param typeMatchcodes one or more type matchcodes for filtering, optional
     * @param contractTypeMatchcodes one or more contract type matchcodes for filtering, optional
     * @param distances one or more GEO distances for filtering, optional
     * @param externalBrandMatchcodes one or more external brand matchcodes for filtering, optional
     * @param updatedAfter updated after the specified date and time, optional
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @return a page of the results, never null
     */
    // CHECKSTYLE:OFF
    @RequestMapping(value = "/find")
    ResultPage<CompanyItemDTO> findAll(@RequestParam(value = "l") String language,
        @RequestParam(value = "brand", required = false) Collection<BrandMatchcode> brandMatchcodes,
        @RequestParam(value = "zip", required = false) Collection<String> zips,
        @RequestParam(value = "countryCode", required = false) Collection<String> countryCodes,
        @RequestParam(value = "type", required = false) Collection<CompanyTypeMatchcode> typeMatchcodes,
        @RequestParam(value = "contractType",
            required = false) Collection<ContractTypeMatchcode> contractTypeMatchcodes,
        @RequestParam(value = "distance", required = false) Collection<GeoDistance> distances,
        @RequestParam(value = "externalBrands",
            required = false) Collection<ExternalBrandMatchcode> externalBrandMatchcodes,
        @RequestParam(value = "updatedAfter", required = false) LocalDateTime updatedAfter,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage);
    // CHECKSTYLE:ON

}
