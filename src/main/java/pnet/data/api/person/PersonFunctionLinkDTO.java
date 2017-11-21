package pnet.data.api.person;

import java.time.LocalDateTime;

import pnet.data.api.function.FunctionMatchcode;

/**
 * Holds the function of a person for one company and brand.
 *
 * @author ham
 */
public class PersonFunctionLinkDTO
{

    private final FunctionMatchcode functionMatchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final boolean mainFunction;

    public PersonFunctionLinkDTO(FunctionMatchcode functionMatchcode, LocalDateTime validFrom, LocalDateTime validTo,
        boolean mainFunction)
    {
        super();
        this.functionMatchcode = functionMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.mainFunction = mainFunction;
    }

    public FunctionMatchcode getFunctionMatchcode()
    {
        return functionMatchcode;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    public boolean isMainFunction()
    {
        return mainFunction;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((functionMatchcode == null) ? 0 : functionMatchcode.hashCode());
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
        PersonFunctionLinkDTO other = (PersonFunctionLinkDTO) obj;
        if (functionMatchcode == null)
        {
            if (other.functionMatchcode != null)
            {
                return false;
            }
        }
        else if (!functionMatchcode.equals(other.functionMatchcode))
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
        return String.format("%s [validFrom=%s, validTo=%s, mainFunction=%s]", functionMatchcode, validFrom, validTo,
            mainFunction);
    }

}
