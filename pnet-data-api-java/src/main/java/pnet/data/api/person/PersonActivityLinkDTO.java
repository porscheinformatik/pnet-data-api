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
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.ApprovalState;
import pnet.data.api.util.PnetDataApiUtils;
import pnet.data.api.util.WithBrandMatchcode;
import pnet.data.api.util.WithCompanyId;

/**
 * Holds the activity of a person for one company and brand.
 *
 * @author ham
 */
@ApiModel(description = "Holds minimal information about a activity of the person.")
public class PersonActivityLinkDTO extends AbstractLinkDTO implements WithCompanyId, WithBrandMatchcode
{

    private static final long serialVersionUID = 4247336068734009775L;

    @ApiModelProperty(notes = "The unique id of the company the person has the activity at.")
    private final Integer companyId;

    @ApiModelProperty(notes = "The matchcode of the company the person has/had an activity at.")
    private final String companyMatchcode;

    @ApiModelProperty(notes = "The number of the company the person has/had an activity at.")
    private final String companyNumber;

    @ApiModelProperty(notes = "A unique matchcode of the brand where the activity is valid.")
    private final String brandMatchcode;

    @ApiModelProperty(notes = "True, if the employment has been approved already, false otherwise.")
    protected final boolean approved;

    @ApiModelProperty(notes = "The current state of the audit process.")
    protected final ApprovalState approvalState;

    @ApiModelProperty(notes = "The date and time from when this person has/had an actvitiy. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validFrom;

    @ApiModelProperty(notes = "The date and time till when this brand has/had an actvitiy. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validTo;

    @ApiModelProperty(
        notes = "The flag that declares, whether this activity is assigned to the person due to a function or not.")
    private final boolean dueToFunction;

    public PersonActivityLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("companyId") Integer companyId, @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber, @JsonProperty("brandMatchcode") String brandMatchcode,
        @JsonProperty("approved") boolean approved, @JsonProperty("approvalState") ApprovalState approvalState,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("dueToFunction") boolean dueToFunction)
    {
        super(tenant, matchcode);

        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.brandMatchcode = brandMatchcode;
        this.approved = approved;
        this.approvalState = approvalState;
        this.validFrom = validFrom;
        this.validTo = validTo;
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

    public boolean isApproved()
    {
        return approved;
    }

    public ApprovalState getApprovalState()
    {
        return approvalState;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
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
        result = prime * result + Objects.hash(brandMatchcode, companyId, validFrom);
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
        return Objects.equals(brandMatchcode, other.brandMatchcode)
            && Objects.equals(companyId, other.companyId)
            && PnetDataApiUtils.equals(validFrom, other.validFrom);
    }

    @Override
    public String toString()
    {
        return String
            .format("PersonActivityLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s, brandMatchcode=%s, "
                + "approved=%s, approvalState=%s, validFrom=%s, validTo=%s, dueToFunction=%s, tenant=%s, matchcode=%s]",
                companyId, companyMatchcode, companyNumber, brandMatchcode, approved, approvalState, validFrom, validTo,
                dueToFunction, tenant, matchcode);
    }

}
