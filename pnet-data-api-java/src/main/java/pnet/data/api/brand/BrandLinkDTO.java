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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import io.swagger.annotations.ApiModel;
import pnet.data.api.util.AbstractLinkDTO;

/**
 * Holds a brand.
 *
 * @author ham
 */
@ApiModel(description = "Holds minimal information about a brand")
public class BrandLinkDTO extends AbstractLinkDTO
{

    private static final long serialVersionUID = -5756014789178869166L;

    public BrandLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode)
    {
        super(tenant, matchcode);
    }

    @Override
    @JsonPropertyDescription("A tenant where the brand is valid")
    public String getTenant()
    {
        return super.getTenant();
    }

    @Override
    @JsonPropertyDescription("The unique matchcode of the brand")
    public String getMatchcode()
    {
        return super.getMatchcode();
    }

}
