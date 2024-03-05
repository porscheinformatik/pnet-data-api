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
package pnet.data.api.contracttype;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.WithLabel;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithScore;
import pnet.data.api.util.WithTenants;

/**
 * Holds a contract type. A company is linked to one or more contract types. Functions and activities need contract
 * types as prerequisite.
 *
 * @author ham
 */
@Schema(description = "Holds basic information about a contract type")
public class ContractTypeItemDTO
    implements WithMatchcode, WithTenants, WithLabel, WithLastUpdate, WithScore, Serializable
{
    @Serial
    private static final long serialVersionUID = -6345795957251172952L;

    @Schema(description = "The unique matchcode of the contract type.")
    private final String matchcode;

    @Schema(description = "The label of the contract type in the requested language.")
    private final String label;

    @Schema(description = "The tenants where the contract type is valid.")
    private final Collection<String> tenants;

    @Schema(description = "The brands where the contract type is valid.")
    private final Collection<ContractTypeBrandLinkDTO> brands;

    @Schema(description = "The type of the contract.")
    private final String type;

    @Schema(description = "The time and date when this item has been changed recently.")
    private final LocalDateTime lastUpdate;

    @Schema(description = "The score this item accomplished in the search operation.")
    private final double score;

    public ContractTypeItemDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("tenants") Collection<String> tenants,
        @JsonProperty("brands") Collection<ContractTypeBrandLinkDTO> brands, @JsonProperty("type") String type,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate, @JsonProperty("score") double score)
    {
        super();

        this.matchcode = matchcode;
        this.label = label;
        this.tenants = tenants;
        this.brands = brands;
        this.type = type;
        this.lastUpdate = lastUpdate;
        this.score = score;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    @Override
    public String getLabel()
    {
        return label;
    }

    @Override
    public Collection<String> getTenants()
    {
        return tenants;
    }

    public Collection<ContractTypeBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public Optional<ContractTypeBrandLinkDTO> findBrand(Predicate<? super ContractTypeBrandLinkDTO> predicate)
    {
        return brands == null ? Optional.empty() : brands.stream().filter(predicate).findFirst();
    }

    public String getType()
    {
        return type;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public double getScore()
    {
        return score;
    }

    @Override
    public String toString()
    {
        return String.format(
            "ContractTypeItemDTO [matchcode=%s, label=%s, tenants=%s, brands=%s, type=%s, lastUpdate=%s, score=%s]",
            matchcode, label, tenants, brands, type, lastUpdate, score);
    }

}
