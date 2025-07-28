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
import java.io.Serial;
import java.time.LocalDateTime;
import pnet.data.api.util.ApprovalState;
import pnet.data.api.util.PnetDataApiUtils;
import pnet.data.api.util.WithValidPeriod;

/**
 * Holds one employment of a person.
 *
 * @author ham
 */
@Schema(description = "Holds minimal information about a employment the person has/had.")
public class PersonCompanyLinkDTO extends ActivePersonCompanyLinkDTO implements WithValidPeriod {

    @Serial
    private static final long serialVersionUID = 7199829304360405636L;

    @Schema(
        description = "The date and time from when this person has/had an employment at the company. " +
        "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information."
    )
    private final LocalDateTime validFrom;

    @Schema(
        description = "The date and time till when this brand has/had an employment at the company. " +
        "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information."
    )
    private final LocalDateTime validTo;

    @SuppressWarnings("java:S107")
    public PersonCompanyLinkDTO(
        @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("companyLabel") String companyLabel,
        @JsonProperty("approved") boolean approved,
        @JsonProperty("approvalState") ApprovalState approvalState,
        @JsonProperty("currentlyActive") boolean currentlyActive,
        @JsonProperty("validFrom") LocalDateTime validFrom,
        @JsonProperty("validTo") LocalDateTime validTo
    ) {
        super(companyId, companyMatchcode, companyNumber, companyLabel, approved, approvalState, currentlyActive);
        this.validFrom = validFrom;
        this.validTo = validTo;
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
        result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        PersonCompanyLinkDTO other = (PersonCompanyLinkDTO) obj;

        return PnetDataApiUtils.equals(validFrom, other.validFrom);
    }

    @Override
    public String toString() {
        return String.format(
            "PersonCompanyLinkDTO [validFrom=%s, validTo=%s, companyId=%s, companyMatchcode=%s, companyNumber=%s, " +
            "companyLabel=%s, approved=%s, approvalState=%s, currentlyActive=%s]",
            validFrom,
            validTo,
            companyId,
            companyMatchcode,
            companyNumber,
            companyLabel,
            approved,
            approvalState,
            currentlyActive
        );
    }
}
