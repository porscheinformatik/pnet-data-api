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

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenants;

/**
 * Holds a contract type. A company is linked to one or more contract types. Functions and activities need contract
 * types as prerequisite.
 *
 * @author ham
 */
public class ContractTypeItemDTO implements WithMatchcode, WithTenants, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -6345795957251172952L;

    private final String matchcode;
    private final String label;
    private final Collection<String> tenants;
    private final Collection<ContractTypeBrandItemDTO> brands;
    private final String type;
    private final LocalDateTime lastUpdate;

    public ContractTypeItemDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("tenants") Collection<String> tenants,
        @JsonProperty("brands") Collection<ContractTypeBrandItemDTO> brands, @JsonProperty("type") String type,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();

        this.matchcode = matchcode;
        this.label = label;
        this.tenants = tenants;
        this.brands = brands;
        this.type = type;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    public String getLabel()
    {
        return label;
    }

    @Override
    public Collection<String> getTenants()
    {
        return tenants;
    }

    public Collection<ContractTypeBrandItemDTO> getBrands()
    {
        return brands;
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
    public String toString()
    {
        return String.format(
            "ContractTypeItemDTO [matchcode=%s, label=%s, tenants=%s, brands=%s, type=%s, lastUpdate=%s]", matchcode,
            label, tenants, brands, type, lastUpdate);
    }

}
