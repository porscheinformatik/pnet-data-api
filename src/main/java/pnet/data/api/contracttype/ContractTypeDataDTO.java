package pnet.data.api.contracttype;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.brand.WithTenantsAndBrandLinks;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a contract type. A company is linked to one or more contract types. Functions and activities need contract
 * types as prerequisite.
 *
 * @author ham
 */
public class ContractTypeDataDTO
    implements WithMatchcode<ContractTypeMatchcode>, WithTenantsAndBrandLinks, WithLabels, WithLastUpdate
{

    private final ContractTypeMatchcode matchcode;

    private Map<Locale, String> labels;
    private Collection<ContractTypeBrandLinkDTO> brands;
    private String type;
    private LocalDateTime lastUpdate;

    public ContractTypeDataDTO(ContractTypeMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public ContractTypeMatchcode getMatchcode()
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
        return String.format("ContractTypeDataDTO [matchcode=%s, labels=%s, brands=%s, type=%s]", matchcode, labels,
            brands, type);
    }

}
