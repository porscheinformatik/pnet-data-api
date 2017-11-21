package pnet.data.api.person;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Holds one employment of a person.
 * 
 * @author ham
 */
public class PersonCompanyLinkDTO
{

    private final Integer companyId;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final Collection<PersonBrandLinkDTO> brands;
    private final Collection<PersonNumberTypeLinkDTO> numbers;

    public PersonCompanyLinkDTO(Integer companyId, LocalDateTime validFrom, LocalDateTime validTo,
        Collection<PersonBrandLinkDTO> brands, Collection<PersonNumberTypeLinkDTO> numbers)
    {
        super();
        this.companyId = companyId;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.brands = brands;
        this.numbers = numbers;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    public Collection<PersonBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public Collection<PersonNumberTypeLinkDTO> getNumbers()
    {
        return numbers;
    }

    @Override
    public String toString()
    {
        return String.format("PersonCompanyLinkDTO [companyId=%s, validFrom=%s, validTo=%s, brands=%s, numbers=%s]",
            companyId, validFrom, validTo, brands, numbers);
    }

}
