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

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithValidPeriod;

/**
 * Holds one employment dependent number of a person.
 *
 * @author ham
 */
@ApiModel(description = "Holds one employment dependent number of a person.")
public class PersonNumberTypeLinkDTO extends AbstractNumberTypeLinkDTO implements WithValidPeriod
{

    private static final long serialVersionUID = -3446430282367218468L;

    @ApiModelProperty(notes = "The date and time from when this person has/had an employment at the company. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validFrom;

    @ApiModelProperty(notes = "The date and time till when this brand has/had an employment at the company. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validTo;

    public PersonNumberTypeLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("companyId") Integer companyId, @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber, @JsonProperty("validFrom") LocalDateTime validFrom,
        @JsonProperty("validTo") LocalDateTime validTo, @JsonProperty("number") String number)
    {
        super(tenant, matchcode, companyId, companyMatchcode, companyNumber, number);

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
    public String toString()
    {
        return String
            .format("PersonNumberTypeLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s, validFrom=%s, "
                + "validTo=%s, number=%s]", companyId, companyMatchcode, companyNumber, validFrom, validTo, number);
    }

}
