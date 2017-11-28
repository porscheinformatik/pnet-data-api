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
package pnet.data.api.function;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.Tenant;
import pnet.data.api.numbertype.NumberTypeMatchcode;

/**
 * Holds a number type.
 *
 * @author ham
 */
public class FunctionNumberTypeDataDTO implements Serializable
{

    private static final long serialVersionUID = -5782141662030293391L;
    
    private final Tenant tenant;
    private final NumberTypeMatchcode numberTypeMatchcode;

    public FunctionNumberTypeDataDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("numberTypeMatchcode") NumberTypeMatchcode numberTypeMatchcode)
    {
        super();

        this.tenant = tenant;
        this.numberTypeMatchcode = numberTypeMatchcode;
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public NumberTypeMatchcode getNumberTypeMatchcode()
    {
        return numberTypeMatchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((numberTypeMatchcode == null) ? 0 : numberTypeMatchcode.hashCode());
        result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());

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

        FunctionNumberTypeDataDTO other = (FunctionNumberTypeDataDTO) obj;

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

        if (tenant == null)
        {
            if (other.tenant != null)
            {
                return false;
            }
        }
        else if (!tenant.equals(other.tenant))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s(%s)", numberTypeMatchcode, tenant);
    }

}
