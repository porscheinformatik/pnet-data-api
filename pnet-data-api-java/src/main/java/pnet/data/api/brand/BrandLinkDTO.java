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
package pnet.data.api.brand;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithMatchcodes;
import pnet.data.api.util.WithTenant;

/**
 * Holds a brand.
 *
 * @author ham
 */
public class BrandLinkDTO implements WithTenant, WithMatchcodes, Serializable
{

    private static final long serialVersionUID = 6759967646557769422L;

    private final String tenant;
    private final boolean brandIndependent;
    private final Collection<String> matchcodes;

    public BrandLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("brandIndependent") boolean brandIndependent,
        @JsonProperty("matchcodes") Collection<String> matchcodes)
    {
        super();

        this.tenant = tenant;
        this.brandIndependent = brandIndependent;
        this.matchcodes = matchcodes;
    }

    @Override
    public String getTenant()
    {
        return tenant;
    }

    public boolean isBrandIndependent()
    {
        return brandIndependent;
    }

    @Override
    public Collection<String> getMatchcodes()
    {
        return matchcodes;
    }

    @Override
    public String toString()
    {
        return String.format("BrandLinkDTO [tenant=%s, brandIndependent=%s, matchcodes=%s]", tenant, brandIndependent,
            matchcodes);
    }

}
