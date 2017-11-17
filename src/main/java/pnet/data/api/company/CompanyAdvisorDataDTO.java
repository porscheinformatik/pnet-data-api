package pnet.data.api.company;

import java.time.LocalDateTime;
import java.util.Collection;

import pnet.data.api.advisortype.AdvisorTypeMatchcode;
import pnet.data.api.brand.BrandLinkDTO;

/**
 * Holds the advisor data for a combination of company and person.
 *
 * @author ham
 */
public class CompanyAdvisorDataDTO
{

    private Integer personId;
    private AdvisorTypeMatchcode type;
    private String division;
    private Collection<BrandLinkDTO> brands;
    private LocalDateTime lastUpdate;

    public Integer getPersonId()
    {
        return personId;
    }

    public void setPersonId(Integer personId)
    {
        this.personId = personId;
    }

    public AdvisorTypeMatchcode getType()
    {
        return type;
    }

    public void setType(AdvisorTypeMatchcode type)
    {
        this.type = type;
    }

    public String getDivision()
    {
        return division;
    }

    public void setDivision(String division)
    {
        this.division = division;
    }

    public Collection<BrandLinkDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<BrandLinkDTO> brands)
    {
        this.brands = brands;
    }

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
        return String.format("AdvisorDataDTO [personId=%s, type=%s, division=%s, brands=%s, lastUpdate=%s]", personId,
            type, division, brands, lastUpdate);
    }

}
