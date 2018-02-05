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
package pnet.data.api.company;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.companynumbertype.CompanyNumberTypeMatchcode;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an additional company number.
 *
 * @author ham
 */
public class CompanyNumberDataDTO implements WithMatchcode<CompanyNumberTypeMatchcode>, Serializable
{

    private static final long serialVersionUID = 2495670532285085314L;

    private final CompanyNumberTypeMatchcode matchcode;
    private final String number;

    public CompanyNumberDataDTO(@JsonProperty("matchcode") CompanyNumberTypeMatchcode matchcode,
        @JsonProperty("number") String number)
    {
        super();

        this.matchcode = matchcode;
        this.number = number;
    }

    @Override
    public CompanyNumberTypeMatchcode getMatchcode()
    {
        return matchcode;
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

        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());

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

        CompanyNumberDataDTO other = (CompanyNumberDataDTO) obj;

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

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s [number=%s]", super.toString(), number);
    }

}
