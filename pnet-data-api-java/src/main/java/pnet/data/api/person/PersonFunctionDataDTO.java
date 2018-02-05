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

import pnet.data.api.function.FunctionMatchcode;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds the function of a person for one company and brand.
 *
 * @author ham
 */
public class PersonFunctionDataDTO implements WithMatchcode<FunctionMatchcode>, Serializable
{

    private static final long serialVersionUID = -5572016715722241376L;

    private final FunctionMatchcode matchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final boolean mainFunction;

    public PersonFunctionDataDTO(@JsonProperty("matchcode") FunctionMatchcode matchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("mainFunction") boolean mainFunction)
    {
        super();

        this.matchcode = matchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.mainFunction = mainFunction;
    }

    @Override
    public FunctionMatchcode getMatchcode()
    {
        return matchcode;
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

        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());
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

        PersonFunctionDataDTO other = (PersonFunctionDataDTO) obj;

        if (matchcode == null)
        {
            if (other.matchcode != null)
            {
                return false;
            }
        }
        else if (!matchcode.equals(other.matchcode))
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
        return String.format("%s [validFrom=%s, validTo=%s, mainFunction=%s]", matchcode, validFrom, validTo,
            mainFunction);
    }

}
