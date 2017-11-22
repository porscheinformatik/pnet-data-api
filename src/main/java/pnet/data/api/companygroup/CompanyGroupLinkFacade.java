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

import pnet.data.api.advisor.AdvisorLinkDTO;
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

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<AdvisorLinkDTO> getAll(
        @RequestParam(value = "leadingCompanyId", required = false) Integer leadingCompanyId,
        @RequestParam(value = "type", required = false) CompanyGroupTypeMatchcode type);

}
