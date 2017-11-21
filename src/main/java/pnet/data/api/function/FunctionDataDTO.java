package pnet.data.api.function;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.companytype.CompanyTypeLinkBasedMultiTenancy;
import pnet.data.api.util.Traceable;
import pnet.data.api.util.Utils;

/**
 * Holds a Function.
 *
 * @author ham
 */
public class FunctionDataDTO implements CompanyTypeLinkBasedMultiTenancy, Traceable
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
    public Collection<FunctionBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<FunctionBrandLinkDTO> brands)
    {
        this.brands = brands;
    }

    /**
     * @return This function is only available, if the company has one of these types.
     */
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

    /**
     * @return The date/time of the last update to this item.
     */
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
