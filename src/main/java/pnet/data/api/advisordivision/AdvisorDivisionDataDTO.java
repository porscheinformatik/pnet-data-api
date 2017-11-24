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
import java.util.Locale;
import java.util.Map;

import pnet.data.api.brand.WithTenantsAndBrandLinks;
import pnet.data.api.util.WithDescriptions;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an advisor type.
 *
 * @author ham
 */
public class AdvisorDivisionDataDTO implements WithMatchcode<AdvisorDivisionMatchcode>, WithLabels, WithDescriptions,
    WithTenantsAndBrandLinks, WithLastUpdate
{

    private final AdvisorDivisionMatchcode matchcode;

    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;
    private Collection<AdvisorDivisionBrandDataDTO> brands;
    private LocalDateTime lastUpdate;

    public AdvisorDivisionDataDTO(AdvisorDivisionMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public AdvisorDivisionMatchcode getMatchcode()
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
    public Collection<AdvisorDivisionBrandDataDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<AdvisorDivisionBrandDataDTO> brands)
    {
        this.brands = brands;
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
        return String.format(
            "AdvisorDivisonDataDTO [matchcode=%s, labels=%s, descriptions=%s, brands=%s, lastUpdate=%s]", matchcode,
            labels, descriptions, brands, lastUpdate);
    }

}
