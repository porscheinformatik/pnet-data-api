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
package pnet.data.api.activity;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenant;

/**
 * Holds a link to an activity
 *
 * @author ham
 */
@Schema(description = "Holds minimal information about an activity")
public class ActivityLinkDTO implements WithTenant, WithMatchcode, Serializable
{

    private static final long serialVersionUID = -5441503535879450447L;

    @Schema(description = "A tenant where the activity is valid")
    private final String tenant;
    @Schema(description = "The unique matchcode of the activity")
    private final String matchcode;

    public ActivityLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode)
    {
        super();

        this.tenant = tenant;
        this.matchcode = matchcode;
    }

    @Override
    public String getTenant()
    {
        return tenant;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(matchcode, tenant);
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

        ActivityLinkDTO other = (ActivityLinkDTO) obj;

        if (!Objects.equals(matchcode, other.matchcode))
        {
            return false;
        }

        if (!Objects.equals(tenant, other.tenant))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s(%s)", matchcode, tenant);
    }

}
