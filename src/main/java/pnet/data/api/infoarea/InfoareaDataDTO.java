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

    private InfoareaMatchcode matchcode;
    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;
    private Collection<InfoareaBrandLinkDTO> brands;
    private Collection<InfoareaCompanyTypeLinkDTO> companyTypes;
    private Collection<InfoareaContractTypeLinkDTO> contractTypes;
    private LocalDateTime lastUpdate;

    public InfoareaDataDTO()
    {
        super();
    }

    @Override
    public InfoareaMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(InfoareaMatchcode matchcode)
    {
        this.matchcode = matchcode;
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
