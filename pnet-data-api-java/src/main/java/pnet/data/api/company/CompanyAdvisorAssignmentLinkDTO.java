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
package pnet.data.api.company;

import java.io.Serial;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.WithPersonId;

/**
 * Holds the advisor assignment of a person to a company.
 *
 * @author ham
 */
@Schema(description = "Holds an advisor assignment to the company.")
public class CompanyAdvisorAssignmentLinkDTO extends AbstractLinkDTO implements WithPersonId
{
    @Serial
    private static final long serialVersionUID = 4247336068734009775L;

    @Schema(description = "The unique id of the person.")
    private final Integer personId;

    @Schema(description = "The name of the person.")
    private final String personName;

    @Schema(description = "The matchcode of the advisor division.")
    private final String divisionMatchcode;

    @Schema(description = "The label of the advisor division.")
    private final String divisionLabel;

    public CompanyAdvisorAssignmentLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode, @JsonProperty("personId") Integer personId,
        @JsonProperty("personName") String personName, @JsonProperty("divisionMatchcode") String divisionMatchcode,
        @JsonProperty("divisionLabel") String divisionLabel)
    {
        super(tenant, matchcode);

        this.personId = personId;
        this.personName = personName;
        this.divisionMatchcode = divisionMatchcode;
        this.divisionLabel = divisionLabel;
    }

    @JsonPropertyDescription("A tenant where the advisor assignment is valid.")
    @Override
    public String getTenant()
    {
        return super.getTenant();
    }

    @JsonPropertyDescription("The matchcode of the advisor type.")
    @Override
    public String getMatchcode()
    {
        return super.getMatchcode();
    }

    @JsonPropertyDescription("The id of the person.")
    @Override
    public Integer getPersonId()
    {
        return personId;
    }

    @JsonPropertyDescription("The name of the person.")
    public String getPersonName()
    {
        return personName;
    }

    public String getDivisionMatchcode()
    {
        return divisionMatchcode;
    }

    public String getDivisionLabel()
    {
        return divisionLabel;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
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
        if (!(obj instanceof CompanyAdvisorAssignmentLinkDTO other))
        {
            return false;
        }
        return Objects.equals(personId, other.personId);
    }

    @Override
    public String toString()
    {
        return String.format(
            "CompanyAdvisorAssignmentLinkDTO [personId=%s, personName=%s, divisionMatchcode=%s, divisionLabel=%s, "
                + "tenant=%s, matchcode=%s]", this.personId, this.personName, this.divisionMatchcode,
            this.divisionLabel, this.tenant, this.matchcode);
    }
}
