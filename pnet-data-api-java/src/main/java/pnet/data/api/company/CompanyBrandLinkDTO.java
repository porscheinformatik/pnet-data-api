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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.PnetDataApiUtils;
import pnet.data.api.util.WithValidPeriod;

/**
 * Holds the brand of a company with all contracts for the brand.
 *
 * @author ham
 */
@Schema(
    description = "Holds minimal information about the brand of a company. The matchcode fits the matchcodes of " +
    "the brands interface."
)
public class CompanyBrandLinkDTO extends AbstractLinkDTO implements WithValidPeriod {

    private static final long serialVersionUID = 7506202638418892087L;

    @Schema(
        description = "The date and time from when this brand is/was valid for the company. " +
        "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information."
    )
    private final LocalDateTime validFrom;

    @Schema(
        description = "The date and time till when this brand is/was valid for the company. " +
        "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information."
    )
    private final LocalDateTime validTo;

    public CompanyBrandLinkDTO(
        @JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom,
        @JsonProperty("validTo") LocalDateTime validTo
    ) {
        super(tenant, matchcode);
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Schema(description = "The tenant (Portal-ID) where this brand is valid.")
    @Override
    public String getTenant() {
        return super.getTenant();
    }

    @Schema(description = "The matchcode of the brand (fits the matchcodes of the brands interface)")
    @Override
    public String getMatchcode() {
        return super.getMatchcode();
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
        if (!(obj instanceof CompanyBrandLinkDTO other)) {
            return false;
        }
        if (!PnetDataApiUtils.equals(validFrom, other.validFrom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s(%s) [validFrom=%s, validTo=%s]", getMatchcode(), getTenant(), validFrom, validTo);
    }
}
