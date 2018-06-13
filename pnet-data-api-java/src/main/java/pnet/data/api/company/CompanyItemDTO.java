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

/**
 * Holds companydata.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about a company")
public class CompanyItemDTO implements Serializable
{

    private static final long serialVersionUID = 4397773907429688509L;

    @ApiModelProperty(notes = "The unique id of the company")
    private final Integer companyId;
    @ApiModelProperty(notes = "The tenant, in which this company is administrated")
    private final String administrativeTenant;
    @ApiModelProperty(notes = "The name of the company")
    private final String name;
    @ApiModelProperty(notes = "The name affix of the company")
    private final String nameAffix;
    @ApiModelProperty(notes = "The marketing name of the company")
    private final String marketingName;
    @ApiModelProperty(notes = "The tenants where the company is valid")
    private final Collection<String> tenants;
    @ApiModelProperty(notes = "The brands where the company is valid")
    private final Collection<CompanyBrandLinkDTO> brands;
    @ApiModelProperty(notes = "The company number")
    private final String companyNumber;
    @ApiModelProperty(notes = "The street in which the company is located")
    private final String street;
    @ApiModelProperty(notes = "The city in which the company is located")
    private final String city;
    @ApiModelProperty(notes = "The postal code of the city in which the company is located")
    private final String postalCode;
    @ApiModelProperty(notes = "The code of the country in which the company is located")
    private final String countryCode;
    @ApiModelProperty(notes = "The name of the country the company is located in")
    private final String country;
    @ApiModelProperty(notes = "The region of the country the company is in")
    private final String region;
    @ApiModelProperty(notes = "The company types the company has")
    private final Collection<CompanyTypeLinkDTO> types;
    @ApiModelProperty(notes = "The location of the company in form of a GeoPoint")
    private final GeoPoint location;
    @ApiModelProperty(notes = "The time and date when the company was last changed")
    private final LocalDateTime lastUpdate;

    public CompanyItemDTO(@JsonProperty("companyId") Integer companyId,
        @JsonProperty("administrativeTenant") String administrativeTenant, @JsonProperty("name") String name,
        @JsonProperty("nameAffix") String nameAffix, @JsonProperty("marketingName") String marketingName,
        @JsonProperty("tenants") Collection<String> tenants,
        @JsonProperty("brands") Collection<CompanyBrandLinkDTO> brands,
        @JsonProperty("companyNumber") String companyNumber, @JsonProperty("street") String street,
        @JsonProperty("city") String city, @JsonProperty("postalCode") String postalCode,
        @JsonProperty("countryCode") String countryCode, @JsonProperty("country") String country,
        @JsonProperty("region") String region, @JsonProperty("types") Collection<CompanyTypeLinkDTO> types,
        @JsonProperty("location") GeoPoint location, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.companyId = companyId;
        this.administrativeTenant = administrativeTenant;
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
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public String getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public String getName()
    {
        return name;
    }

    public String getNameAffix()
    {
        return nameAffix;
    }

    public String getMarketingName()
    {
        return marketingName;
    }

    public Collection<String> getTenants()
    {
        return tenants;
    }

    public Collection<CompanyBrandLinkDTO> getBrands()
    {
        return brands;
    }

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

    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format(
            "CompanyItemDTO [companyId=%s, administrativeTenant=%s, name=%s, nameAffix=%s, marketingName=%s, "
                + "tenants=%s, brands=%s, companyNumber=%s, street=%s, city=%s, postalCode=%s, countryCode=%s, country=%s, region=%s, "
                + "types=%s, location=%s, lastUpdate=%s]",
            companyId, administrativeTenant, name, nameAffix, marketingName, tenants, brands, companyNumber, street,
            city, postalCode, countryCode, country, region, types, location, lastUpdate);
    }

}
