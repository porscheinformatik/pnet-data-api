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
public class AdvisorDivisonDataDTO implements WithMatchcode<AdvisorDivisionMatchcode>, WithLabels, WithDescriptions,
    WithTenantsAndBrandLinks, WithLastUpdate
{

    private final AdvisorDivisionMatchcode matchcode;

    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;
    private Collection<AdvisorDivisionBrandLinkDTO> brands;
    private LocalDateTime lastUpdate;

    public AdvisorDivisonDataDTO(AdvisorDivisionMatchcode matchcode)
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
    public Collection<AdvisorDivisionBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<AdvisorDivisionBrandLinkDTO> brands)
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
