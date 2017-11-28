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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.Tenant;
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
public class ContractTypeDataDTO
    implements WithMatchcode<ContractTypeMatchcode>, WithTenants, WithLabels, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -1947283275602928634L;

    private final ContractTypeMatchcode matchcode;

    private Map<Locale, String> labels;
    private Collection<Tenant> tenants;
    private Collection<ContractTypeBrandDataDTO> brands;
    private String type;
    private LocalDateTime lastUpdate;

    public ContractTypeDataDTO(@JsonProperty("matchcode") ContractTypeMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public ContractTypeMatchcode getMatchcode()
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
    public Collection<Tenant> getTenants()
    {
        return tenants;
    }

    public void setTenants(Collection<Tenant> tenants)
    {
        this.tenants = tenants;
    }

    public Collection<ContractTypeBrandDataDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<ContractTypeBrandDataDTO> brands)
    {
        this.brands = brands;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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
        return String.format("ContractTypeDataDTO [matchcode=%s, labels=%s, tenants=%s, brands=%s, type=%s]", matchcode,
            labels, tenants, brands, type);
    }

}
