package pnet.data.api.person;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.numbertype.NumberTypeMatchcode;

/**
 * Holds the number of a person for a company.
 *
 * @author ham
 */
public class PersonNumberTypeLinkDTO
{

    private final NumberTypeMatchcode numberTypeMatchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final String number;

    public PersonNumberTypeLinkDTO(@JsonProperty("numberTypeMatchcode") NumberTypeMatchcode numberTypeMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("number") String number)
    {
        super();

        this.numberTypeMatchcode = numberTypeMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.number = number;
    }

    public NumberTypeMatchcode getNumberTypeMatchcode()
    {
        return numberTypeMatchcode;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    public String getNumber()
    {
        return number;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((numberTypeMatchcode == null) ? 0 : numberTypeMatchcode.hashCode());
        result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        PersonNumberTypeLinkDTO other = (PersonNumberTypeLinkDTO) obj;

        if (numberTypeMatchcode == null)
        {
            if (other.numberTypeMatchcode != null)
            {
                return false;
            }
        }
        else if (!numberTypeMatchcode.equals(other.numberTypeMatchcode))
        {
            return false;
        }

        if (validFrom == null)
        {
            if (other.validFrom != null)
            {
                return false;
            }
        }
        else if (!validFrom.equals(other.validFrom))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s [number=%s, validFrom=%s, validTo=%s]", numberTypeMatchcode, number, validFrom,
            validTo);
    }

}
