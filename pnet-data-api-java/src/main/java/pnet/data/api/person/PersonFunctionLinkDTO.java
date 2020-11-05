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

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithValidPeriod;

/**
 * The assignment of a function to a person, for a specific company, tenant and brand.
 *
 * @author ham
 */
@ApiModel(description = "The assignment of a function to a person, for a specific company, tenant and brand.")
public class PersonFunctionLinkDTO extends AbstractPersonFunctionLinkDTO implements WithValidPeriod
{

    private static final long serialVersionUID = -5572016715722241376L;

    @ApiModelProperty(notes = "The starting date and time of this function assignment. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validFrom;

    @ApiModelProperty(notes = "The ending date and time of this function assignment. "
        + "A missing value indicates an ongoing assignment. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validTo;

    public PersonFunctionLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("companyId") Integer companyId, @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber, @JsonProperty("brandMatchcode") String brandMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("mainFunction") boolean mainFunction)
    {
        super(tenant, matchcode, companyId, companyMatchcode, companyNumber, brandMatchcode, mainFunction);

        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Override
    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    @Override
    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(validFrom);
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
        if (!(obj instanceof PersonFunctionLinkDTO))
        {
            return false;
        }
        PersonFunctionLinkDTO other = (PersonFunctionLinkDTO) obj;
        return Objects.equals(validFrom, other.validFrom);
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "PersonFunctionLinkDTO [tenant=%s, matchcode=%s, companyId=%s, companyMatchcode=%s, companyNumber=%s, "
                    + "brandMatchcode=%s, validFrom=%s, validTo=%s, mainFunction=%s]",
                tenant, matchcode, companyId, companyMatchcode, companyNumber, brandMatchcode, validFrom, validTo,
                mainFunction);
    }

}
