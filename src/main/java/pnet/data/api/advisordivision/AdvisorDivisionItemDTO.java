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
package pnet.data.api.advisordivision;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.brand.WithTenantsAndBrandLinks;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an advisor type.
 *
 * @author ham
 */
public class AdvisorDivisionItemDTO
    implements WithMatchcode<AdvisorDivisionMatchcode>, WithTenantsAndBrandLinks, WithLastUpdate
{

    private final AdvisorDivisionMatchcode matchcode;
    private final String label;
    private final String description;
    private final Collection<AdvisorDivisionBrandItemDTO> brands;
    private final LocalDateTime lastUpdate;

    public AdvisorDivisionItemDTO(@JsonProperty("matchcode") AdvisorDivisionMatchcode matchcode,
        @JsonProperty("label") String label, @JsonProperty("description") String description,
        @JsonProperty("brands") Collection<AdvisorDivisionBrandItemDTO> brands,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.matchcode = matchcode;
        this.label = label;
        this.description = description;
        this.brands = brands;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public AdvisorDivisionMatchcode getMatchcode()
    {
        return matchcode;
    }

    public String getLabel()
    {
        return label;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public Collection<AdvisorDivisionBrandItemDTO> getBrands()
    {
        return brands;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format("AdvisorDivisonItemDTO [matchcode=%s, label=%s, description=%s, brands=%s, lastUpdate=%s]",
            matchcode, label, description, brands, lastUpdate);
    }

}
