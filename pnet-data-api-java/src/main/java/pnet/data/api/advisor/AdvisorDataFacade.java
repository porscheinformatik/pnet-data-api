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
package pnet.data.api.advisor;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.ResultCollection;

/**
 * Provides access to advisors.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/advisors")
public interface AdvisorDataFacade
{

    /**
     * Returns multiple {@link AdvisorDataDTO}s each matching all specified restrictions. If one or more restrictions
     * are set each restriction will be applied (AND) and one of the values of each restriction must match (OR). It is
     * not possible to call this method without any restriction. The number of results is limited. The
     * {@link ResultCollection} may contain a call for more results.
     *
     * @param companyIds the ids of the companies, optional
     * @param personIds the ids of the persons, optional
     * @param advisorTypeMatchcodes the advisor types, optional
     * @param advisorDivisionMatchcodes the advisor divisions, optional
     * @return a collection of all found items, never null
     * @throws PnetDataApiException on occasion
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultCollection<AdvisorDataDTO> getAll(
        @RequestParam(value = "companyId", required = false) List<Integer> companyIds,
        @RequestParam(value = "personId", required = false) List<Integer> personIds,
        @RequestParam(value = "advisorType", required = false) List<String> advisorTypeMatchcodes,
        @RequestParam(value = "advisorDivision", required = false) List<String> advisorDivisionMatchcodes)
        throws PnetDataApiException;

}
