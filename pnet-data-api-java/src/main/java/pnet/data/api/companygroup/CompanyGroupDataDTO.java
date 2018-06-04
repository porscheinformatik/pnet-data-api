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

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds a group of companies.
 *
 * @author ham
 */
public class CompanyGroupDataDTO implements Serializable
{

    private static final long serialVersionUID = -260656363547122718L;

    private final Integer leadingCompanyId;
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
