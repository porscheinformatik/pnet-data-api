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
package pnet.data.api.application;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.Tenant;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenants;

/**
 * Holds a company type. A company is linked to one or more company types. Functions and activities need company types
 * as prerequisite.
 *
 * @author ham
 */
public class ApplicationItemDTO
    implements WithMatchcode<ApplicationMatchcode>, WithTenants, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = 1943888464506455363L;
    
    private final ApplicationMatchcode matchcode;
    private final Collection<Tenant> tenants;
    private final String label;
    private final LocalDateTime lastUpdate;

    public ApplicationItemDTO(@JsonProperty("matchcode") ApplicationMatchcode matchcode,
        @JsonProperty("tenants") Collection<Tenant> tenants, @JsonProperty("label") String label,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.matchcode = matchcode;
        this.tenants = tenants;
        this.label = label;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public ApplicationMatchcode getMatchcode()
    {
        return matchcode;
    }

    @Override
    public Collection<Tenant> getTenants()
    {
        return tenants;
    }

    public String getLabel()
    {
        return label;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format("CompanyTypeItemDTO [matchcode=%s, tenants=%s, label=%s, lastUpdate=%s]", matchcode,
            tenants, label, lastUpdate);
    }

}
