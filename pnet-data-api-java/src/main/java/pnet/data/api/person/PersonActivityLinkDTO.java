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
package pnet.data.api.person;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.WithBrandMatchcode;
import pnet.data.api.util.WithCompanyId;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds the activity of a person for one company and brand.
 *
 * @author ham
 */
@ApiModel(description = "Holds minimal information about a activity of the person")
public class PersonActivityLinkDTO extends AbstractLinkDTO
    implements WithMatchcode, WithCompanyId, WithBrandMatchcode, Serializable
{

    private static final long serialVersionUID = 4247336068734009775L;

    @ApiModelProperty(notes = "The unique id of the company the person has the activity at")
    private final Integer companyId;
    @ApiModelProperty(notes = "A unique matchcode of the brand where the activity is valid")
    private final String brandMatchcode;
    @ApiModelProperty(
        notes = "The flag that declares, whether this activity is assigned to the person due to a function or not")
    private final boolean dueToFunction;

    public PersonActivityLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("companyId") Integer companyId, @JsonProperty("brandMatchcode") String brandMatchcode,
        @JsonProperty("dueToFunction") boolean dueToFunction)
    {
        super(tenant, matchcode);

        this.companyId = companyId;
        this.brandMatchcode = brandMatchcode;
        this.dueToFunction = dueToFunction;
    }

    @JsonPropertyDescription("A tenant where the activity is valid")
    @Override
    public String getTenant()
    {
        return super.getTenant();
    }

    @JsonPropertyDescription("The unique matchcode of the activity")
    @Override
    public String getMatchcode()
    {
        return super.getMatchcode();
    }

    @Override
    public String getBrandMatchcode()
    {
        return brandMatchcode;
    }

    @Override
    public Integer getCompanyId()
    {
        return companyId;
    }

    public boolean isDueToFunction()
    {
        return dueToFunction;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((brandMatchcode == null) ? 0 : brandMatchcode.hashCode());
        result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
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
        if (!(obj instanceof PersonActivityLinkDTO))
        {
            return false;
        }
        PersonActivityLinkDTO other = (PersonActivityLinkDTO) obj;
        if (brandMatchcode == null)
        {
            if (other.brandMatchcode != null)
            {
                return false;
            }
        }
        else if (!brandMatchcode.equals(other.brandMatchcode))
        {
            return false;
        }
        if (companyId == null)
        {
            if (other.companyId != null)
            {
                return false;
            }
        }
        else if (!companyId.equals(other.companyId))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String
            .format("%s [companyId=%s, brandMatchcode=%s, dueToFunction=%s]", super.toString(), companyId,
                brandMatchcode, dueToFunction);
    }

}
