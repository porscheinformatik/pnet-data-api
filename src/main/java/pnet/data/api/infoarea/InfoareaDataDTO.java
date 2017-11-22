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
package pnet.data.api.infoarea;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.brand.WithBrandLinks;
import pnet.data.api.companytype.WithTenantsAndCompanyTypeLinks;
import pnet.data.api.util.WithDescriptions;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an infoarea.
 *
 * @author ham
 */
public class InfoareaDataDTO implements WithMatchcode<InfoareaMatchcode>, WithLabels, WithDescriptions, WithBrandLinks,
    WithTenantsAndCompanyTypeLinks, WithLastUpdate
{

    private final InfoareaMatchcode matchcode;

    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;
    private Collection<InfoareaBrandLinkDTO> brands;
    private Collection<InfoareaCompanyTypeLinkDTO> companyTypes;
    private Collection<InfoareaContractTypeLinkDTO> contractTypes;
    private LocalDateTime lastUpdate;

    public InfoareaDataDTO(InfoareaMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public InfoareaMatchcode getMatchcode()
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
    public Collection<InfoareaBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<InfoareaBrandLinkDTO> brands)
    {
        this.brands = brands;
    }

    @Override
    public Collection<InfoareaCompanyTypeLinkDTO> getCompanyTypes()
    {
        return companyTypes;
    }

    public void setCompanyTypes(Collection<InfoareaCompanyTypeLinkDTO> companyTypes)
    {
        this.companyTypes = companyTypes;
    }

    /**
     * @return This infoarea is only available, if the company has one of these contracts. This collection is only
     *         relevant, if the company type of the company says so.
     */
    public Collection<InfoareaContractTypeLinkDTO> getContractTypes()
    {
        return contractTypes;
    }

    public void setContractTypes(Collection<InfoareaContractTypeLinkDTO> contractTypes)
    {
        this.contractTypes = contractTypes;
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
            "InfoareaDataDTO [matchcode=%s, labels=%s, descriptions=%s, brands=%s, companyTypes=%s, contractTypes=%s, lastUpdate=%s]",
            matchcode, labels, descriptions, brands, companyTypes, contractTypes, lastUpdate);
    }

}
