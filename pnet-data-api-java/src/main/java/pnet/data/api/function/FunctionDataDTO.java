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
package pnet.data.api.function;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.activity.ActivityLinkDTO;
import pnet.data.api.brand.BrandLinkDTO;
import pnet.data.api.brand.WithBrandLinks;
import pnet.data.api.companytype.CompanyTypeLinkDTO;
import pnet.data.api.companytype.WithCompanyTypeLinks;
import pnet.data.api.contracttype.ContractTypeLinkDTO;
import pnet.data.api.contracttype.WithContractTypeLinks;
import pnet.data.api.numbertype.NumberTypeLinkDTO;
import pnet.data.api.numbertype.WithNumberTypeLinks;
import pnet.data.api.util.WithDescriptions;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenants;

/**
 * Holds a Function.
 *
 * @author ham
 */
@ApiModel(description = "Holds all information about a function")
public class FunctionDataDTO implements WithMatchcode, WithLabels, WithDescriptions, WithTenants, WithBrandLinks,
    WithCompanyTypeLinks, WithContractTypeLinks, WithNumberTypeLinks, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -3654140715367585861L;

    @ApiModelProperty(notes = "The unique matchcode of the function")
    private final String matchcode;

    @ApiModelProperty(notes = "The label of the function with all existing translations")
    private Map<Locale, String> labels;
    @ApiModelProperty(notes = "The description of the function with all existing translations")
    private Map<Locale, String> descriptions;
    @ApiModelProperty(notes = "The tenants where the function is valid")
    private Collection<String> tenants;
    @ApiModelProperty(notes = "The brands where the function is valid")
    private Collection<BrandLinkDTO> brands;
    @ApiModelProperty(notes = "The company types where the function is valid")
    private Collection<CompanyTypeLinkDTO> companyTypes;
    @ApiModelProperty(notes = "The contract types where the function is valid")
    private Collection<ContractTypeLinkDTO> contractTypes;
    @ApiModelProperty(notes = "The contract number where the function is valid")
    private Collection<NumberTypeLinkDTO> numberTypes;
    @ApiModelProperty(notes = "The activities the function has")
    private Collection<ActivityLinkDTO> activities;
    @ApiModelProperty(notes = "The time and date when the function was last changed")
    private LocalDateTime lastUpdate;

    public FunctionDataDTO(@JsonProperty("matchcode") String matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    @Override
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

    @Override
    public Map<Locale, String> getDescriptions()
    {
        return descriptions;
    }

    public void setDescriptions(Map<Locale, String> descriptions)
    {
        this.descriptions = descriptions;
    }

    @Override
    public Collection<BrandLinkDTO> getBrands()
    {
        return brands;
    }

    @Override
    public Collection<String> getTenants()
    {
        return tenants;
    }

    public void setTenants(Collection<String> tenants)
    {
        this.tenants = tenants;
    }

    public void setBrands(Collection<BrandLinkDTO> brands)
    {
        this.brands = brands;
    }

    @Override
    public Collection<CompanyTypeLinkDTO> getCompanyTypes()
    {
        return companyTypes;
    }

    public void setCompanyTypes(Collection<CompanyTypeLinkDTO> companyTypes)
    {
        this.companyTypes = companyTypes;
    }

    /**
     * @return This function is only available, if the company has one of these contracts. This collection is only
     *         relevant, if the company type of the company says so.
     */
    @Override
    public Collection<ContractTypeLinkDTO> getContractTypes()
    {
        return contractTypes;
    }

    public void setContractTypes(Collection<ContractTypeLinkDTO> contractTypes)
    {
        this.contractTypes = contractTypes;
    }

    /**
     * @return The number types necessary for this function.
     */
    @Override
    public Collection<NumberTypeLinkDTO> getNumberTypes()
    {
        return numberTypes;
    }

    public void setNumberTypes(Collection<NumberTypeLinkDTO> numberTypes)
    {
        this.numberTypes = numberTypes;
    }

    /**
     * @return The activities, that are linked to this function.
     */
    public Collection<ActivityLinkDTO> getActivities()
    {
        return activities;
    }

    public void setActivities(Collection<ActivityLinkDTO> activities)
    {
        this.activities = activities;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "FunctionDataDTO [matchcode=%s, labels=%s, descriptions=%s, tenants=%s, brands=%s, companyTypes=%s, contractTypes=%s, numberTypes=%s, activities=%s, lastUpdate=%s]",
                matchcode, labels, descriptions, tenants, brands, companyTypes, contractTypes, numberTypes, activities,
                lastUpdate);
    }

}
