/* Copyright 2017 Porsche Informatik GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pnet.data.api.person;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.numbertype.NumberTypeMatchcode;

/**
 * Holds the number of a person for a company.
 *
 * @author ham
 */
public class PersonNumberTypeDataDTO implements Serializable
{

    private static final long serialVersionUID = -3446430282367218468L;
    
    private final NumberTypeMatchcode numberTypeMatchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final String number;

    public PersonNumberTypeDataDTO(@JsonProperty("numberTypeMatchcode") NumberTypeMatchcode numberTypeMatchcode,
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

        PersonNumberTypeDataDTO other = (PersonNumberTypeDataDTO) obj;

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
