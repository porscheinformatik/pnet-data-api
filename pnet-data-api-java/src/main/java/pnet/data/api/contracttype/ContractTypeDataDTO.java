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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenants;

/**
 * Holds a contract type. A company is linked to one or more contract types. Functions and activities need contract
 * types as prerequisite.
 *
 * @author ham
 */
@Schema(description = "Holds all information about a contract type")
public class ContractTypeDataDTO implements WithMatchcode, WithTenants, WithLabels, WithLastUpdate, Serializable {

    @Serial
    private static final long serialVersionUID = -1947283275602928634L;

    @Schema(description = "The unique matchcode of the contract type")
    private final String matchcode;

    @Schema(description = "The label of the contract type with all existing translations")
    private Map<Locale, String> labels;

    @Schema(description = "The tenants where the contract type is valid")
    private Collection<String> tenants;

    @Schema(description = "The brands where the contract type is valid")
    private Collection<ContractTypeBrandLinkDTO> brands;

    @Schema(description = "The type of the contract")
    private String type;

    @Schema(description = "The time and date when the contract type was last changed")
    private LocalDateTime lastUpdate;

    public ContractTypeDataDTO(@JsonProperty("matchcode") String matchcode) {
        super();
        this.matchcode = matchcode;
    }

    @Override
    public String getMatchcode() {
        return matchcode;
    }

    @Override
    public Map<Locale, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels) {
        this.labels = labels;
    }

    @Override
    public Collection<String> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<String> tenants) {
        this.tenants = tenants;
    }

    public Collection<ContractTypeBrandLinkDTO> getBrands() {
        return brands;
    }

    public Optional<ContractTypeBrandLinkDTO> findBrand(Predicate<? super ContractTypeBrandLinkDTO> predicate) {
        return brands == null ? Optional.empty() : brands.stream().filter(predicate).findFirst();
    }

    public Collection<ContractTypeBrandLinkDTO> getBrandsOfTenant(String tenant) {
        return getBrands()
            .stream()
            .filter($ -> Objects.equals(tenant, $.getTenant()))
            .toList();
    }

    public void setBrands(Collection<ContractTypeBrandLinkDTO> brands) {
        this.brands = brands;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return String.format(
            "ContractTypeDataDTO [matchcode=%s, labels=%s, tenants=%s, brands=%s, type=%s]",
            matchcode,
            labels,
            tenants,
            brands,
            type
        );
    }
}
