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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Objects;
import pnet.data.api.util.ApprovalState;
import pnet.data.api.util.PnetDataApiUtils;
import pnet.data.api.util.WithValidPeriod;

/**
 * The assignment of a function to a person, for a specific company, tenant and brand.
 *
 * @author ham
 */
@Schema(description = "The assignment of a function to a person, for a specific company, tenant and brand.")
public class PersonFunctionLinkDTO extends AbstractPersonFunctionLinkDTO implements WithValidPeriod {

    private static final long serialVersionUID = -5572016715722241376L;

    @Schema(description = "True, if the function has been approved already, false otherwise.")
    protected final boolean approved;

    @Schema(description = "The current state of the audit process.")
    protected final ApprovalState approvalState;

    @Schema(
        description = "The starting date and time of this function assignment. " +
            "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information."
    )
    private final LocalDateTime validFrom;

    @Schema(
        description = "The ending date and time of this function assignment. " +
            "A missing value indicates an ongoing assignment. " +
            "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information."
    )
    private final LocalDateTime validTo;

    public PersonFunctionLinkDTO(
        @JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode,
        @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("brandMatchcode") String brandMatchcode,
        @JsonProperty("approved") boolean approved,
        @JsonProperty("approvalState") ApprovalState approvalState,
        @JsonProperty("validFrom") LocalDateTime validFrom,
        @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("mainFunction") boolean mainFunction
    ) {
        super(tenant, matchcode, companyId, companyMatchcode, companyNumber, brandMatchcode, mainFunction);
        this.approved = approved;
        this.approvalState = approvalState;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public boolean isApproved() {
        return approved;
    }

    public ApprovalState getApprovalState() {
        return approvalState;
    }

    @Override
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    @Override
    public LocalDateTime getValidTo() {
        return validTo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(validFrom);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof PersonFunctionLinkDTO other)) {
            return false;
        }
        return PnetDataApiUtils.equals(validFrom, other.validFrom);
    }

    @Override
    public String toString() {
        return String.format(
            "PersonFunctionLinkDTO [approved=%s, approvalState=%s, validFrom=%s, validTo=%s, companyId=%s, " +
                "companyMatchcode=%s, companyNumber=%s, brandMatchcode=%s, mainFunction=%s, tenant=%s, matchcode=%s]",
            approved,
            approvalState,
            validFrom,
            validTo,
            companyId,
            companyMatchcode,
            companyNumber,
            brandMatchcode,
            mainFunction,
            tenant,
            matchcode
        );
    }
}
