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

import java.io.Serial;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.WithBrandMatchcode;
import pnet.data.api.util.WithCompanyId;

/**
 * Holds the advisor assignment of a person for one company, one brand and one advisor type.
 *
 * @author ham
 */
@Schema(description = "Holds an advisor assignment of the person.")
public class PersonAdvisorAssignmentLinkDTO extends AbstractLinkDTO implements WithCompanyId, WithBrandMatchcode
{
    @Serial
    private static final long serialVersionUID = 4247336068734009775L;

    @Schema(description = "The unique id of the company the person is advisor for.")
    private final Integer companyId;

    @Schema(description = "The matchcode of the company the person has/had is advisor for.")
    private final String companyMatchcode;

    @Schema(description = "The number of the company the person has/had is advisor for.")
    private final String companyNumber;

    @Schema(description = "The matchcode of the brand where the person is advisor for. "
        + "NOTE: Do not use. Value will be any brand matchcode that this division and advisor type "
        + "matchcode combination can be registered for. Because of that, it may return a brand matchcode "
        + "that is not even active for the company of this assignment.")
    @Deprecated(since = "2.5.0")
    private final String brandMatchcode;

    @Schema(description = "The matchcode of the advisor division.")
    private final String divisionMatchcode;

    @Schema(description = "The label of the advisor division.")
    private final String divisionLabel;

    @SuppressWarnings("java:S107")
    public PersonAdvisorAssignmentLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode, @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode, @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("brandMatchcode") @Deprecated(since = "2.5.0") String brandMatchcode,
        @JsonProperty("divisionMatchcode") String divisionMatchcode,
        @JsonProperty("divisionLabel") String divisionLabel)
    {
        super(tenant, matchcode);

        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.brandMatchcode = brandMatchcode;
        this.divisionMatchcode = divisionMatchcode;
        this.divisionLabel = divisionLabel;
    }

    @Schema(description = "A tenant where the advisor assignment is valid.")
    @Override
    public String getTenant()
    {
        return super.getTenant();
    }

    @Schema(description = "The matchcode of the advisor type.")
    @Override
    public String getMatchcode()
    {
        return super.getMatchcode();
    }

    /**
     * @deprecated Do not use. Value will be any brand matchcode that this division and advisor type matchcode
     * combination can be registered for. Because of that, it may return a brand matchcode that is not even
     * active for the company of this assignment.
     */
    @Override
    @Deprecated(since = "2.5.0")
    public String getBrandMatchcode()
    {
        return brandMatchcode;
    }

    @Override
    public Integer getCompanyId()
    {
        return companyId;
    }

    @Override
    public String getCompanyMatchcode()
    {
        return companyMatchcode;
    }

    @Override
    public String getCompanyNumber()
    {
        return companyNumber;
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
        if (!(obj instanceof PersonAdvisorAssignmentLinkDTO other))
        {
            return false;
        }
        if (!Objects.equals(brandMatchcode, other.brandMatchcode))
        {
            return false;
        }
        return Objects.equals(companyId, other.companyId);
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "PersonAdvisorAssignmentLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s, "
                    + "brandMatchcode=%s, divisionMatchcode=%s, divisionLabel=%s]",
                companyId, companyMatchcode, companyNumber, brandMatchcode, divisionMatchcode, divisionLabel);
    }
}
