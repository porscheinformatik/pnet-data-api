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
package pnet.data.api.companygroup;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.companygrouptype.CompanyGroupTypeMatchcode;

/**
 * API for company groups.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/companygroups")
public interface CompanyGroupLinkFacade
{

    /**
     * Returns multiple {@link CompanyGroupDataDTO}s each matching all specified filters. If one or more filters are set
     * each filter will be applied (AND) and one of the values of each filter must match (OR). It is not possible to
     * call this method without any filter and the maximum number of filter items is limited.
     *
     * @param leadingCompanyIds the id of the leading company, optional
     * @param types the type, optional
     * @param companyIds the id of a participating company (the leading company is participating, too), optional
     * @return a collection of all found items, never null
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<CompanyGroupDataDTO> getAll(
        @RequestParam(value = "leadingCompanyId", required = false) Collection<Integer> leadingCompanyIds,
        @RequestParam(value = "type", required = false) Collection<CompanyGroupTypeMatchcode> types,
        @RequestParam(value = "companyId", required = false) Collection<Integer> companyIds);

}
