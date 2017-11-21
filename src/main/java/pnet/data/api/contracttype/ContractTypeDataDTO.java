package pnet.data.api.contracttype;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.brand.BrandLinkBasedMultiTenancy;
import pnet.data.api.util.Utils;

/**
 * Holds a contract type. A company is linked to one or more contract types. Functions and activities need contract
 * types as prerequisite.
 *
 * @author ham
 */
public class ContractTypeDataDTO implements BrandLinkBasedMultiTenancy
{

    private ContractTypeMatchcode matchcode;
    private Map<Locale, String> labels;
    private Collection<ContractTypeBrandLinkDTO> brands;
    private String type;
    private LocalDateTime lastUpdate;

    public ContractTypeDataDTO()
    {
        super();
    }

    /**
     * @return The unique, alpha-numeric key of the contract type. The key is the same in all environments.
     */
    public ContractTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(ContractTypeMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    /**
     * @return A map of strings by locale, holding the label of the contract type in multiple languages.
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

    @Override
    public Collection<ContractTypeBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<ContractTypeBrandLinkDTO> brands)
    {
        this.brands = brands;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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

    @Override
    public String toString()
    {
        return String.format("ContractTypeDataDTO [matchcode=%s, labels=%s, brands=%s, type=%s]", matchcode, labels,
            brands, type);
    }

}
