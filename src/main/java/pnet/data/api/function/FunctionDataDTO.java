package pnet.data.api.function;

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
 * Holds a Function.
 *
 * @author ham
 */
public class FunctionDataDTO implements WithMatchcode<FunctionMatchcode>, WithLabels, WithDescriptions, WithBrandLinks,
    WithTenantsAndCompanyTypeLinks, WithLastUpdate
{

    private FunctionMatchcode matchcode;
    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;
    private Collection<FunctionBrandLinkDTO> brands;
    private Collection<FunctionCompanyTypeLinkDTO> companyTypes;
    private Collection<FunctionContractTypeLinkDTO> contractTypes;
    private Collection<FunctionNumberTypeLinkDTO> numberTypes;
    private Collection<FunctionActivityLinkDTO> activities;
    private Collection<FunctionInfoareaLinkDTO> infoareas;
    private LocalDateTime lastUpdate;

    public FunctionDataDTO()
    {
        super();
    }

    @Override
    public FunctionMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(FunctionMatchcode matchcode)
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
    public Collection<FunctionBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<FunctionBrandLinkDTO> brands)
    {
        this.brands = brands;
    }

    @Override
    public Collection<FunctionCompanyTypeLinkDTO> getCompanyTypes()
    {
        return companyTypes;
    }

    public void setCompanyTypes(Collection<FunctionCompanyTypeLinkDTO> companyTypes)
    {
        this.companyTypes = companyTypes;
    }

    /**
     * @return This function is only available, if the company has one of these contracts. This collection is only
     *         relevant, if the company type of the company says so.
     */
    public Collection<FunctionContractTypeLinkDTO> getContractTypes()
    {
        return contractTypes;
    }

    public void setContractTypes(Collection<FunctionContractTypeLinkDTO> contractTypes)
    {
        this.contractTypes = contractTypes;
    }

    /**
     * @return The number types necessary for this function.
     */
    public Collection<FunctionNumberTypeLinkDTO> getNumberTypes()
    {
        return numberTypes;
    }

    public void setNumberTypes(Collection<FunctionNumberTypeLinkDTO> numberTypes)
    {
        this.numberTypes = numberTypes;
    }

    /**
     * @return The activities, that are linked to this function.
     */
    public Collection<FunctionActivityLinkDTO> getActivities()
    {
        return activities;
    }

    public void setActivities(Collection<FunctionActivityLinkDTO> activities)
    {
        this.activities = activities;
    }

    /**
     * @return The inforareas, that are linked to this function.
     */
    public Collection<FunctionInfoareaLinkDTO> getInfoareas()
    {
        return infoareas;
    }

    public void setInfoareas(Collection<FunctionInfoareaLinkDTO> infoareas)
    {
        this.infoareas = infoareas;
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

}
