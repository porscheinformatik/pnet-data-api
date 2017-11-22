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

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.brand.WithTenantsAndBrandLinks;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a activity. This object contains only minimal information and is used as result of search operations and
 * reference items.
 *
 * @author ham
 */
// FIXME the tenants should be bases on company types!
public class ActivityItemDTO implements WithMatchcode<ActivityMatchcode>, WithTenantsAndBrandLinks, WithLastUpdate
{

    private final ActivityMatchcode matchcode;
    private final String label;
    private final String description;
    private final Collection<ActivityBrandLinkDTO> brands;
    private final LocalDateTime lastUpdate;

    public ActivityItemDTO(@JsonProperty("matchcode") ActivityMatchcode matchcode, @JsonProperty("label") String label,
        @JsonProperty("description") String description,
        @JsonProperty("brands") Collection<ActivityBrandLinkDTO> brands,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();

        this.matchcode = Objects.requireNonNull(matchcode, "Matchcode is null");
        this.label = Objects.requireNonNull(label, "Label is null");
        this.description = description;
        this.brands = Collections.unmodifiableCollection(Objects.requireNonNull(brands, "Brands are null"));
        this.lastUpdate = lastUpdate;
    }

    @Override
    public ActivityMatchcode getMatchcode()
    {
        return matchcode;
    }

    /**
     * @return The label in the requested language.
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @return The description in the requested language.
     */
    public String getDescription()
    {
        return description;
    }

    @Override
    public Collection<ActivityBrandLinkDTO> getBrands()
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
        return String.format("ActivityItemDTO [matchcode=%s, label=%s, description=%s, brands=%s, lastUpdate=%s]",
            matchcode, label, description, brands, lastUpdate);
    }

}
