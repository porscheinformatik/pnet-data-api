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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.GeoPoint;
import pnet.data.api.util.PnetDataApiUtils;
import pnet.data.api.util.WithCompanyId;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithScore;
import pnet.data.api.util.WithTenants;

/**
 * Holds companydata.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about one company.")
public class CompanyItemDTO
    implements WithCompanyId, WithTenants, WithMatchcode, WithLastUpdate, WithScore, Serializable
{

    private static final long serialVersionUID = 8371146988245636569L;

    @ApiModelProperty(notes = "The unique id of the company (also known as GP-ID).")
    private final Integer companyId;

    @ApiModelProperty(
        notes = "The matchcode of the company (a combination of administrative tenant and company number).")
    private final String matchcode;

    @ApiModelProperty(notes = "The tenant (Portal-ID), in which this company gets administrated.")
    private final String administrativeTenant;

    @ApiModelProperty(
        notes = "The label of the company (either the marketing name or a combination of name and affix).")
    private final String label;

    /**
     * @deprecated use label instead
     */
    @ApiModelProperty(notes = "The name of the company. Deprecated: use label instead.")
    @Deprecated
    private final String name;

    /**
     * @deprecated use label instead
     */
    @ApiModelProperty(notes = "The name affix of the company. Deprecated: use label instead.")
    @Deprecated
    private final String nameAffix;

    /**
     * @deprecated use label instead
     */
    @ApiModelProperty(notes = "The marketing name of the company. Deprecated: use label instead.")
    @Deprecated
    private final String marketingName;

    @ApiModelProperty(notes = "Valid tenants of the company (also known as Portal-ID).")
    private final Collection<String> tenants;

    @ApiModelProperty(
        notes = "All brands assigned to the company. The matchcode of each item fits to the matchcodes of the brands "
            + "interface.")
    private final Collection<CompanyBrandLinkDTO> brands;

    @ApiModelProperty(notes = "The company number. In most cases, this is the same as the SAP number.")
    private final String companyNumber;

    @ApiModelProperty(notes = "The name of the street as defined in the address of the company.")
    private final String street;

    @ApiModelProperty(notes = "The name of the city as defined in the address of the company.")
    private final String city;

    @ApiModelProperty(notes = "The postal code of the city as defined in the address of the company.")
    private final String postalCode;

    @ApiModelProperty(notes = "The code of the country as defined in the address of the company.")
    private final String countryCode;

    @ApiModelProperty(notes = "The name of the country as defined in the address of the company.")
    private final String country;

    @ApiModelProperty(notes = "The region as defined in the address of the company.")
    private final String region;

    @ApiModelProperty(notes = "All company types assigned to the company. The matchcode of each item fits to the "
        + "matchcodes of the company types interface.")
    private final Collection<CompanyTypeLinkDTO> types;

    @ApiModelProperty(notes = "The logitude and latitude of the companies location.")
    private final GeoPoint location;

    @ApiModelProperty(notes = "The time and date when this item has been changed recently.")
    private final LocalDateTime lastUpdate;

    @ApiModelProperty(notes = "The score this item accomplished in the search operation.")
    private final double score;

    public CompanyItemDTO(@JsonProperty("companyId") Integer companyId, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("administrativeTenant") String administrativeTenant, @JsonProperty("label") String label,
        @JsonProperty("name") String name, @JsonProperty("nameAffix") String nameAffix,
        @JsonProperty("marketingName") String marketingName, @JsonProperty("tenants") Collection<String> tenants,
        @JsonProperty("brands") Collection<CompanyBrandLinkDTO> brands,
        @JsonProperty("companyNumber") String companyNumber, @JsonProperty("street") String street,
        @JsonProperty("city") String city, @JsonProperty("postalCode") String postalCode,
        @JsonProperty("countryCode") String countryCode, @JsonProperty("country") String country,
        @JsonProperty("region") String region, @JsonProperty("types") Collection<CompanyTypeLinkDTO> types,
        @JsonProperty("location") GeoPoint location, @JsonProperty("lastUpdate") LocalDateTime lastUpdate,
        @JsonProperty("score") double score)
    {
        super();
        this.companyId = companyId;
        this.matchcode = matchcode;
        this.administrativeTenant = administrativeTenant;
        this.label = label;
        this.name = name;
        this.nameAffix = nameAffix;
        this.marketingName = marketingName;
        this.tenants = tenants;
        this.brands = brands;
        this.companyNumber = companyNumber;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
        this.country = country;
        this.region = region;
        this.types = types;
        this.location = location;
        this.lastUpdate = lastUpdate;
        this.score = score;
    }

    @Override
    public Integer getCompanyId()
    {
        return companyId;
    }

    @Override
    public String getMatchcode()
    {
        return getCompanyMatchcode();
    }

    @Override
    public String getCompanyMatchcode()
    {
        return matchcode;
    }

    public String getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public String getLabel()
    {
        return label;
    }

    public String getLabelWithNumber()
    {
        return PnetDataApiUtils.toCompanyLabelWithNumber(companyNumber, label);
    }

    /**
     * @return the name of the company
     * @deprecated use the label instead
     */
    @Deprecated
    public String getName()
    {
        return name;
    }

    /**
     * @return the name affix of the company
     * @deprecated use the label instead
     */
    @Deprecated
    public String getNameAffix()
    {
        return nameAffix;
    }

    /**
     * @return the marketing name of the company
     * @deprecated use the label instead
     */
    @Deprecated
    public String getMarketingName()
    {
        return marketingName;
    }

    @Override
    public Collection<String> getTenants()
    {
        return tenants;
    }

    public Collection<CompanyBrandLinkDTO> getBrands()
    {
        return brands;
    }

    @Override
    public String getCompanyNumber()
    {
        return companyNumber;
    }

    public String getStreet()
    {
        return street;
    }

    public String getCity()
    {
        return city;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public String getCountry()
    {
        return country;
    }

    public String getRegion()
    {
        return region;
    }

    public Collection<CompanyTypeLinkDTO> getTypes()
    {
        return types;
    }

    public GeoPoint getLocation()
    {
        return location;
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
        return String
            .format(
                "CompanyItemDTO [companyId=%s, matchcode=%s, administrativeTenant=%s, name=%s, nameAffix=%s, "
                    + "marketingName=%s, tenants=%s, brands=%s, companyNumber=%s, street=%s, city=%s, postalCode=%s, "
                    + "countryCode=%s, country=%s, region=%s, types=%s, location=%s, lastUpdate=%s, score=%s]",
                companyId, matchcode, administrativeTenant, name, nameAffix, marketingName, tenants, brands,
                companyNumber, street, city, postalCode, countryCode, country, region, types, location, lastUpdate,
                score);
    }

}
