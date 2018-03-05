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
package pnet.data.api.advisordivision;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractLinkDTO;

/**
 * A brand for an advisor division.
 *
 * @author ham
 */
public class AdvisorDivisionBrandLinkDTO extends AbstractLinkDTO
{

    private static final long serialVersionUID = 4906679956173547375L;
    private final Collection<String> types;

    public AdvisorDivisionBrandLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode, @JsonProperty("types") Collection<String> types)
    {
        super(tenant, matchcode);

        this.types = types;
    }

    public Collection<String> getTypes()
    {
        return types;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((types == null) ? 0 : types.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        if (!(obj instanceof AdvisorDivisionBrandLinkDTO))
        {
            return false;
        }
        AdvisorDivisionBrandLinkDTO other = (AdvisorDivisionBrandLinkDTO) obj;
        if (types == null)
        {
            if (other.types != null)
            {
                return false;
            }
        }
        else if (!types.equals(other.types))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s(%s) [types=%s]", getMatchcode(), getTenant(), types);
    }

}
