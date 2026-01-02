/* Copyright 2022 Porsche Informatik GmbH
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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import pnet.data.api.util.PnetDataApiUtils;
import pnet.data.api.util.WithBrandMatchcode;
import pnet.data.api.util.WithCompanyId;
import pnet.data.api.util.WithContractTypeMatchcode;
import pnet.data.api.util.WithTenant;
import pnet.data.api.util.WithValidPeriod;

/**
 * A contract distribution structure
 *
 * @author ham
 */
@Schema(description = "Holds minimal information about the contract distribution structure of a company.")
public class CompanyContractDistributionStructureLinkDTO
    implements WithTenant, WithCompanyId, WithBrandMatchcode, WithContractTypeMatchcode, WithValidPeriod, Serializable {

    @Serial
    private static final long serialVersionUID = 5212128752932674480L;

    @Schema(description = "The tenant (Portal-ID) where this contract state is valid.")
    private final String tenant;

    @Schema(description = "The company that is linked with this contract distribution structure.")
    private final Integer companyId;

    @Schema(description = "The company that is linked with this contract distribution structure.")
    private final String companyNumber;

    @Schema(description = "The company that is linked with this contract distribution structure.")
    private final String companyMatchcode;

    @Schema(description = "The matchcode of the brand this contract distribution structure is assigned to.")
    private final String brandMatchcode;

    @Schema(description = "The matchcode of the contract type this distribution structure state is assigned to.")
    private final String contractTypeMatchcode;

    @Schema(
        description = "The date and time from when this contract state is/was valid for the company. " +
            "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information."
    )
    private final LocalDateTime validFrom;

    @Schema(
        description = "The date and time till when this contract state is/was valid for the company. " +
            "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information."
    )
    private final LocalDateTime validTo;

    @SuppressWarnings("java:S107")
    public CompanyContractDistributionStructureLinkDTO(
        @JsonProperty("tenant") String tenant,
        @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("brand") String brandMatchcode,
        @JsonProperty("contractType") String contractTypeMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom,
        @JsonProperty("validTo") LocalDateTime validTo
    ) {
        this.tenant = tenant;
        this.companyId = companyId;
        this.companyNumber = companyNumber;
        this.companyMatchcode = companyMatchcode;
        this.brandMatchcode = brandMatchcode;
        this.contractTypeMatchcode = contractTypeMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Override
    public String getTenant() {
        return tenant;
    }

    @Override
    public Integer getCompanyId() {
        return companyId;
    }

    @Override
    public String getCompanyNumber() {
        return companyNumber;
    }

    @Override
    public String getCompanyMatchcode() {
        return companyMatchcode;
    }

    @Override
    public String getBrandMatchcode() {
        return brandMatchcode;
    }

    @Override
    public String getContractTypeMatchcode() {
        return contractTypeMatchcode;
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
        return Objects.hash(
            brandMatchcode,
            companyId,
            companyNumber,
            contractTypeMatchcode,
            tenant,
            validFrom,
            validTo
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompanyContractDistributionStructureLinkDTO other)) {
            return false;
        }
        return (
            Objects.equals(brandMatchcode, other.brandMatchcode) &&
            Objects.equals(companyId, other.companyId) &&
            Objects.equals(companyNumber, other.companyNumber) &&
            Objects.equals(contractTypeMatchcode, other.contractTypeMatchcode) &&
            Objects.equals(tenant, other.tenant) &&
            PnetDataApiUtils.equals(validFrom, other.validFrom) &&
            PnetDataApiUtils.equals(validTo, other.validTo)
        );
    }

    @Override
    public String toString() {
        return String.format(
            "CompanyContractDistributionStructureLinkDTO [tenant=%s, companyId=%s, companyNumber=%s, " +
                "companyMatchcode=%s, brandMatchcode=%s, contractTypeMatchcode=%s, validFrom=%s, validTo=%s]",
            tenant,
            companyId,
            companyNumber,
            companyMatchcode,
            brandMatchcode,
            contractTypeMatchcode,
            validFrom,
            validTo
        );
    }
}
