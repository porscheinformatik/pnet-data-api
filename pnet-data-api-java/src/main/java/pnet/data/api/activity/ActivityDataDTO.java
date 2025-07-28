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
package pnet.data.api.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import pnet.data.api.brand.BrandLinkDTO;
import pnet.data.api.brand.WithBrandLinks;
import pnet.data.api.companytype.CompanyTypeLinkDTO;
import pnet.data.api.companytype.WithCompanyTypeLinks;
import pnet.data.api.contracttype.ContractTypeLinkDTO;
import pnet.data.api.contracttype.WithContractTypeLinks;
import pnet.data.api.util.WithDescriptions;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenants;

/**
 * Holds an activity.
 *
 * @author ham
 */
@Schema(description = "Holds all information about an activity")
public class ActivityDataDTO
    implements
        WithMatchcode,
        WithLabels,
        WithDescriptions,
        WithTenants,
        WithBrandLinks,
        WithCompanyTypeLinks,
        WithContractTypeLinks,
        WithLastUpdate,
        Serializable {

    @Serial
    private static final long serialVersionUID = 5133673955487263429L;

    @Schema(description = "The unique matchcode of the activity")
    private final String matchcode;

    @Schema(description = "The label of the activity with all existing translations")
    private Map<Locale, String> labels;

    @Schema(description = "The description of the activity with all existing translations")
    private Map<Locale, String> descriptions;

    @Schema(description = "The tenants where the activity is valid")
    private Collection<String> tenants;

    @Schema(description = "The brands where the activity is valid")
    private Collection<BrandLinkDTO> brands;

    @Schema(description = "The company types where the activity is valid")
    private Collection<CompanyTypeLinkDTO> companyTypes;

    @Schema(description = "The contract types where the activity is valid")
    private Collection<ContractTypeLinkDTO> contractTypes;

    @Schema(description = "The settings for this activity")
    private Collection<ActivitySettingsLinkDTO> settings;

    @Schema(description = "The time and date when the activity was last changed")
    private LocalDateTime lastUpdate;

    public ActivityDataDTO(@JsonProperty("matchcode") String matchcode) {
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
    public Map<Locale, String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Map<Locale, String> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public Collection<String> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<String> tenants) {
        this.tenants = tenants;
    }

    @Override
    public Collection<BrandLinkDTO> getBrands() {
        return brands;
    }

    public Optional<BrandLinkDTO> findBrand(Predicate<? super BrandLinkDTO> predicate) {
        return brands == null ? Optional.empty() : brands.stream().filter(predicate).findFirst();
    }

    public void setBrands(Collection<BrandLinkDTO> brands) {
        this.brands = brands;
    }

    @Override
    public Collection<CompanyTypeLinkDTO> getCompanyTypes() {
        return companyTypes;
    }

    public Optional<CompanyTypeLinkDTO> findCompanyType(Predicate<? super CompanyTypeLinkDTO> predicate) {
        return companyTypes == null ? Optional.empty() : companyTypes.stream().filter(predicate).findFirst();
    }

    public void setCompanyTypes(Collection<CompanyTypeLinkDTO> companyTypes) {
        this.companyTypes = companyTypes;
    }

    /**
     * @return This activity is only available, if the company has one of these contracts. This collection is only
     * relevant, if the company type of the company says so.
     */
    @Override
    public Collection<ContractTypeLinkDTO> getContractTypes() {
        return contractTypes;
    }

    public Optional<ContractTypeLinkDTO> findContractType(Predicate<? super ContractTypeLinkDTO> predicate) {
        return contractTypes == null ? Optional.empty() : contractTypes.stream().filter(predicate).findFirst();
    }

    public void setContractTypes(Collection<ContractTypeLinkDTO> contractTypes) {
        this.contractTypes = contractTypes;
    }

    public Collection<ActivitySettingsLinkDTO> getSettings() {
        return settings;
    }

    public Optional<ActivitySettingsLinkDTO> findSettings(Predicate<? super ActivitySettingsLinkDTO> predicate) {
        return settings == null ? Optional.empty() : settings.stream().filter(predicate).findFirst();
    }

    public void setSettings(Collection<ActivitySettingsLinkDTO> settings) {
        this.settings = settings;
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
            "ActivityDataDTO [matchcode=%s, labels=%s, descriptions=%s, tenants=%s, brands=%s, companyTypes=%s, " +
            "contractTypes=%s, settings=%s, lastUpdate=%s]",
            matchcode,
            labels,
            descriptions,
            tenants,
            brands,
            companyTypes,
            contractTypes,
            settings,
            lastUpdate
        );
    }
}
