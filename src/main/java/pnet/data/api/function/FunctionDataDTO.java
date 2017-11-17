package pnet.data.api.function;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.activity.ActivityLinkDTO;
import pnet.data.api.brand.BrandLinkBasedMultiTenancy;
import pnet.data.api.brand.TimedBrandLinkDTO;
import pnet.data.api.companytype.CompanyTypeLinkDTO;
import pnet.data.api.contracttypes.ContractTypeLinkDTO;
import pnet.data.api.infoarea.InfoareaLinkDTO;
import pnet.data.api.numbertype.NumberTypeLinkDTO;
import pnet.data.api.util.Utils;

/**
 * Holds a Function.
 *
 * @author ham
 */
public class FunctionDataDTO implements BrandLinkBasedMultiTenancy
{

    private FunctionMatchcode matchcode;
    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;
    private Collection<TimedBrandLinkDTO> brands;
    private Collection<CompanyTypeLinkDTO> companyTypes;
    private Collection<ContractTypeLinkDTO> contractTypes;
    private Collection<NumberTypeLinkDTO> numberTypes;
    private Collection<ActivityLinkDTO> activities;
    private Collection<InfoareaLinkDTO> infoareas;
    private LocalDateTime lastUpdate;

    public FunctionDataDTO()
    {
        super();
    }

    /**
     * @return The unique, alpha-numeric key of the item. The key is the same on all environments.
     */
    public FunctionMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(FunctionMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    /**
     * @return A map of strings by locale holding the label of the item in multiple languages.
     */
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    /**
     * @param language the language, may be null
     * @return The label in the specified language, null if not found.
     */
    public String getLabel(Locale language)
    {
        return Utils.getText(language, labels);
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

    /**
     * @return A map of strings by locale holding the description of the item in multiple languages.
     */
    public Map<Locale, String> getDescriptions()
    {
        return descriptions;
    }

    /**
     * @param language the language, may be null
     * @return The description in the specified language, null if not found.
     */
    public String getDescriptions(Locale language)
    {
        return Utils.getText(language, descriptions);
    }

    public void setDescriptions(Map<Locale, String> descriptions)
    {
        this.descriptions = descriptions;
    }

    /**
     * @return This function is only available, if the company has one of these brands.
     */
    @Override
    public Collection<TimedBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<TimedBrandLinkDTO> brands)
    {
        this.brands = brands;
    }

    /**
     * @return This function is only available, if the company has one of these types.
     */
    public Collection<CompanyTypeLinkDTO> getCompanyTypes()
    {
        return companyTypes;
    }

    public void setCompanyTypes(Collection<CompanyTypeLinkDTO> companyTypes)
    {
        this.companyTypes = companyTypes;
    }

    /**
     * @return This function is only available, if the company has one of these contracts. This collection is only
     *         relevant, if the company type of the company says so.
     */
    public Collection<ContractTypeLinkDTO> getContractTypes()
    {
        return contractTypes;
    }

    public void setContractTypes(Collection<ContractTypeLinkDTO> contractTypes)
    {
        this.contractTypes = contractTypes;
    }

    /**
     * @return The number types necessary for this function.
     */
    public Collection<NumberTypeLinkDTO> getNumberTypes()
    {
        return numberTypes;
    }

    public void setNumberTypes(Collection<NumberTypeLinkDTO> numberTypes)
    {
        this.numberTypes = numberTypes;
    }

    /**
     * @return The activities, that are linked to this function.
     */
    public Collection<ActivityLinkDTO> getActivities()
    {
        return activities;
    }

    public void setActivities(Collection<ActivityLinkDTO> activities)
    {
        this.activities = activities;
    }

    /**
     * @return The inforareas, that are linked to this function.
     */
    public Collection<InfoareaLinkDTO> getInfoareas()
    {
        return infoareas;
    }

    public void setInfoareas(Collection<InfoareaLinkDTO> infoareas)
    {
        this.infoareas = infoareas;
    }

    /**
     * @return The date/time of the last update to this item.
     */
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

}
