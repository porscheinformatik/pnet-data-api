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
package pnet.data.api.contracttype;

import java.util.Collection;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractLinkDTO;

/**
 * A brand for a specified tenant.
 *
 * @author ham
 */
public class ContractTypeBrandLinkDTO extends AbstractLinkDTO
{

    private static final long serialVersionUID = -4749964162640876397L;

    private final Collection<String> states;

    public ContractTypeBrandLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("states") Collection<String> states)
    {
        super(tenant, matchcode);

        this.states = states;
    }

    public Collection<String> getStates()
    {
        return states;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((states == null) ? 0 : states.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        if (!(obj instanceof ContractTypeBrandLinkDTO other))
        {
            return false;
        }
        if (!Objects.equals(states, other.states))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s(%s) [states=%s]", getMatchcode(), getTenant(), states);
    }

}
