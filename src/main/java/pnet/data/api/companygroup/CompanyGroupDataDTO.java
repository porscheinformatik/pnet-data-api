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
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.companygrouptype.CompanyGroupTypeMatchcode;

/**
 * Holds a group of companies.
 *
 * @author ham
 */
public class CompanyGroupDataDTO implements Serializable
{

    private static final long serialVersionUID = -260656363547122718L;
    
    private final Integer leadingCompanyId;
    private final CompanyGroupTypeMatchcode type;
    private final Collection<Integer> companyIds;

    public CompanyGroupDataDTO(@JsonProperty("leadingCompanyId") Integer leadingCompanyId,
        @JsonProperty("type") CompanyGroupTypeMatchcode type,
        @JsonProperty("companyIds") Collection<Integer> companyIds)
    {
        super();

        this.leadingCompanyId = leadingCompanyId;
        this.type = type;
        this.companyIds = Collections.unmodifiableCollection(companyIds);
    }

    public Integer getLeadingCompanyId()
    {
        return leadingCompanyId;
    }

    public CompanyGroupTypeMatchcode getType()
    {
        return type;
    }

    public Collection<Integer> getCompanyIds()
    {
        return companyIds;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((leadingCompanyId == null) ? 0 : leadingCompanyId.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        CompanyGroupDataDTO other = (CompanyGroupDataDTO) obj;

        if (leadingCompanyId == null)
        {
            if (other.leadingCompanyId != null)
            {
                return false;
            }
        }
        else if (!leadingCompanyId.equals(other.leadingCompanyId))
        {
            return false;
        }

        if (type == null)
        {
            if (other.type != null)
            {
                return false;
            }
        }
        else if (!type.equals(other.type))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("CompanyGroupDataDTO [leadingCompanyId=%s, type=%s, companyIds=%s]", leadingCompanyId,
            type, companyIds);
    }

}
