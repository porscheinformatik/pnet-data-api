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

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.advisordivision.AdvisorDivisionMatchcode;
import pnet.data.api.advisortype.AdvisorTypeMatchcode;

/**
 * Provides access to advisors.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/advisors")
public interface AdvisorLinkFacade
{

    // FIXME better interface
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<AdvisorDataDTO> getAll(@RequestParam(value = "companyId", required = false) Integer companyId,
        @RequestParam(value = "personId", required = false) Integer personId,
        @RequestParam(value = "advisorType", required = false) AdvisorTypeMatchcode advisorTypeMatchcode,
        @RequestParam(value = "advisorDivision", required = false) AdvisorDivisionMatchcode advisorDivisionMatchcode);

}
