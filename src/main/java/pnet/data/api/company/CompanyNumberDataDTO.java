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

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.companynumbertype.CompanyNumberTypeMatchcode;

/**
 * Holds an additional company number.
 *
 * @author ham
 */
public class CompanyNumberDataDTO
{

    private final CompanyNumberTypeMatchcode companyNumberTypeMatchcode;
    private final String number;

    public CompanyNumberDataDTO(
        @JsonProperty("companyNumberTypeMatchcode") CompanyNumberTypeMatchcode companyNumberTypeMatchcode,
        @JsonProperty("number") String number)
    {
        super();

        this.companyNumberTypeMatchcode = companyNumberTypeMatchcode;
        this.number = number;
    }

    public CompanyNumberTypeMatchcode getCompanyNumberTypeMatchcode()
    {
        return companyNumberTypeMatchcode;
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

        result = prime * result + ((companyNumberTypeMatchcode == null) ? 0 : companyNumberTypeMatchcode.hashCode());

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

        if (companyNumberTypeMatchcode == null)
        {
            if (other.companyNumberTypeMatchcode != null)
            {
                return false;
            }
        }
        else if (!companyNumberTypeMatchcode.equals(other.companyNumberTypeMatchcode))
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
