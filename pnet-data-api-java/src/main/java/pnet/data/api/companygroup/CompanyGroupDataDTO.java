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

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Holds a group of companies.
 *
 * @author ham
 */
@ApiModel(description = "Holds all information about a company group")
public class CompanyGroupDataDTO
{

    @ApiModelProperty(notes = "The unique id of the company that is leading the company group")
    private final Integer leadingCompanyId;

    @ApiModelProperty(notes = "The members of the company group")
    private Collection<CompanyGroupMemberLinkDTO> members;

    public CompanyGroupDataDTO(@JsonProperty("leadingCompanyId") Integer leadingCompanyId)
    {
        super();

        this.leadingCompanyId = leadingCompanyId;
    }

    public Integer getLeadingCompanyId()
    {
        return leadingCompanyId;
    }

    public Collection<CompanyGroupMemberLinkDTO> getMembers()
    {
        return members;
    }

    public void setMembers(Collection<CompanyGroupMemberLinkDTO> members)
    {
        this.members = members;
    }

}
