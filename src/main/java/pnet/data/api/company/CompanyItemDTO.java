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

import pnet.data.api.GeoPoint;
import pnet.data.api.Tenant;
import pnet.data.api.companytype.CompanyTypeMatchcode;

/**
 * Holds companydata.
 *
 * @author ham
 */
public class CompanyItemDTO implements Serializable
{

    private static final long serialVersionUID = 4397773907429688509L;

    private final Integer companyId;
    private final Tenant administrativeTenant;
    private final String name;
    private final String nameAffix;
    private final String marketingName;
    private final Collection<CompanyBrandItemDTO> brands;
    private final String companyNumber;
    private final String street;
    private final String city;
    private final String zip;
    private final String countryCode;
    private final String country;
    private final String region;
    private final Collection<CompanyTypeMatchcode> types;
    private final GeoPoint location;
    private final LocalDateTime lastUpdate;

    public CompanyItemDTO(@JsonProperty("companyId") Integer companyId,
        @JsonProperty("administrativeTenant") Tenant administrativeTenant, @JsonProperty("name") String name,
        @JsonProperty("nameAffix") String nameAffix, @JsonProperty("marketingName") String marketingName,
        @JsonProperty("brands") Collection<CompanyBrandItemDTO> brands,
        @JsonProperty("companyNumber") String companyNumber, @JsonProperty("street") String street,
        @JsonProperty("city") String city, @JsonProperty("zip") String zip,
        @JsonProperty("countryCode") String countryCode, @JsonProperty("country") String country,
        @JsonProperty("region") String region, @JsonProperty("types") Collection<CompanyTypeMatchcode> types,
        @JsonProperty("location") GeoPoint location, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.companyId = companyId;
        this.administrativeTenant = administrativeTenant;
        this.name = name;
        this.nameAffix = nameAffix;
        this.marketingName = marketingName;
        this.brands = brands;
        this.companyNumber = companyNumber;
        this.street = street;
        this.city = city;
        this.zip = zip;
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

    public Tenant getAdministrativeTenant()
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

    public Collection<CompanyBrandItemDTO> getBrands()
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

    public String getZip()
    {
        return zip;
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

    public Collection<CompanyTypeMatchcode> getTypes()
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
                + "brands=%s, companyNumber=%s, street=%s, city=%s, zip=%s, countryCode=%s, country=%s, region=%s, "
                + "types=%s, location=%s, lastUpdate=%s]",
            companyId, administrativeTenant, name, nameAffix, marketingName, brands, companyNumber, street, city, zip,
            countryCode, country, region, types, location, lastUpdate);
    }

}
