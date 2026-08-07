/* Copyright 2026 Porsche Informatik GmbH
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
import java.io.Serializable;
import pnet.data.api.util.WithTenant;

@Schema(description = "Holds information about the commission sharing of a person")
public class PersonCommissionSharingLinkDTO implements WithTenant, Serializable {

    @Serial
    private static final long serialVersionUID = -6849794470754667710L;

    @Schema(description = "The unique id of the company for which the person has the commission sharing.")
    private final Integer companyId;

    @Schema(description = "The tenant for which the person's commission sharing is valid.")
    private final String tenant;

    @Schema(description = "The dealer percentage of the person's commission sharing.")
    private final Integer dealerPercentage;

    @Schema(description = "The seller percentage of the person's commission sharing.")
    private final Integer sellerPercentage;

    public PersonCommissionSharingLinkDTO(
        @JsonProperty("companyId") Integer companyId,
        @JsonProperty("tenant") String tenant,
        @JsonProperty("dealerPercentage") Integer dealerPercentage,
        @JsonProperty("sellerPercentage") Integer sellerPercentage
    ) {
        this.companyId = companyId;
        this.tenant = tenant;
        this.dealerPercentage = dealerPercentage;
        this.sellerPercentage = sellerPercentage;
    }

    @Override
    public String getTenant() {
        return tenant;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public Integer getDealerPercentage() {
        return dealerPercentage;
    }

    public Integer getSellerPercentage() {
        return sellerPercentage;
    }

    @Override
    public String toString() {
        return String.format(
            "PersonCommissionSharingLinkDTO [companyId=%d, tenant=%s, dealerPercentage=%d, sellerPercentage=%d]",
            companyId,
            tenant,
            dealerPercentage,
            sellerPercentage
        );
    }
}
