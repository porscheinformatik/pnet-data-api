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
package pnet.data.api.companytype;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.Tenant;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenants;

/**
 * Holds a company type. A company is linked to one or more company types. Functions and activities need company types
 * as prerequisite.
 *
 * @author ham
 */
public class CompanyTypeDataDTO
    implements WithMatchcode<CompanyTypeMatchcode>, WithTenants, WithLabels, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -5837538753457547027L;

    private final CompanyTypeMatchcode matchcode;

    private Collection<Tenant> tenants;
    private Map<Locale, String> labels;
    private int level;
    private boolean contractSpecific;
    private LocalDateTime lastUpdate;

    public CompanyTypeDataDTO(CompanyTypeMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public CompanyTypeMatchcode getMatchcode()
    {
        return matchcode;
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

    @Override
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

    /**
     * @return The level of the company type.
     */
    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * @return True, if contract types have to be checked as prerequisite along with this company type. False, if it's
     *         enough, that company has this company type.
     */
    public boolean isContractSpecific()
    {
        return contractSpecific;
    }

    public void setContractSpecific(boolean contractSpecific)
    {
        this.contractSpecific = contractSpecific;
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
        return String.format("CompanyTypeDataDTO [matchcode=%s, tenants=%s, labels=%s, level=%s, contractSpecific=%s]",
            matchcode, tenants, labels, level, contractSpecific);
    }

}
