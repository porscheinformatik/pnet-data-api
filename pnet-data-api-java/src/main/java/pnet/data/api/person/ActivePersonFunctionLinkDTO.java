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
import pnet.data.api.util.WithLabel;

/**
 * The assignment of a function to a person, for a specific company, tenant and brand.
 *
 * @author ham
 */
@Schema(
    description = "The assignment of a function to a person, for a specific company, tenant and brand. " +
    "Related to the datedBackUnitl parameter."
)
public class ActivePersonFunctionLinkDTO extends AbstractPersonFunctionLinkDTO implements WithLabel {

    @Serial
    private static final long serialVersionUID = -5572016715722241376L;

    @Schema(description = "The label of the function that is assigned to the person.")
    private final String label;

    @Schema(description = "True if currently active (ignores the datedBackUntil parameter).")
    protected final boolean currentlyActive;

    @SuppressWarnings("java:S107")
    public ActivePersonFunctionLinkDTO(
        @JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode,
        @JsonProperty("label") String label,
        @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("brandMatchcode") String brandMatchcode,
        @JsonProperty("mainFunction") boolean mainFunction,
        @JsonProperty("currentlyActive") boolean currentlyActive
    ) {
        super(tenant, matchcode, companyId, companyMatchcode, companyNumber, brandMatchcode, mainFunction);
        this.label = label;
        this.currentlyActive = currentlyActive;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public boolean isCurrentlyActive() {
        return currentlyActive;
    }

    @Override
    public String toString() {
        return String.format(
            "ActivePersonFunctionLinkDTO [tenant=%s, matchcode=%s, companyId=%s, companyMatchcode=%s, " +
            "companyNumber=%s, brandMatchcode=%s, mainFunction=%s, currentlyActive=%s]",
            tenant,
            matchcode,
            companyId,
            companyMatchcode,
            companyNumber,
            brandMatchcode,
            mainFunction,
            currentlyActive
        );
    }
}
