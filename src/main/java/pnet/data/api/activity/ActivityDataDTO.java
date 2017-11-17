package pnet.data.api.activity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.brand.BrandLinkBasedMultiTenancy;
import pnet.data.api.brand.TimedBrandLinkDTO;
import pnet.data.api.companytype.CompanyTypeLinkDTO;
import pnet.data.api.contracttypes.ContractTypeLinkDTO;
import pnet.data.api.infoarea.InfoareaLinkDTO;
import pnet.data.api.numbertype.NumberTypeLinkDTO;
import pnet.data.api.util.Utils;

/**
 * Holds an activity.
 *
 * @author ham
 */
public class ActivityDataDTO implements BrandLinkBasedMultiTenancy
{

    private ActivityMatchcode matchcode;
    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;
    private Collection<TimedBrandLinkDTO> brands;
    private Collection<CompanyTypeLinkDTO> companyTypes;
    private Collection<ContractTypeLinkDTO> contractTypes;
    private Collection<NumberTypeLinkDTO> numberTypes;
    private Collection<InfoareaLinkDTO> infoareas;
    private LocalDateTime lastUpdate;

    public ActivityDataDTO()
    {
        super();
    }

    /**
     * @return The unique, alpha-numeric key of the item. The key is the same on all environments.
     */
    public ActivityMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(ActivityMatchcode matchcode)
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
     * @return This activity is only available, if the company has one of these brands.
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
     * @return This activity is only available, if the company has one of these types.
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
     * @return This activity is only available, if the company has one of these contracts. This collection is only
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
     * @return The number types necessary for this activity.
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
     * @return The inforareas, that are linked to this activity.
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
