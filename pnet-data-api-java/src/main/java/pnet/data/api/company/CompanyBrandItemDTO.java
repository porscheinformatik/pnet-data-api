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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.AbstractLinkDTO;

/**
 * Holds the brand of a company with all contracts for the brand.
 *
 * @author ham
 */
@ApiModel(description = "Holds the refrence to a brand of a company. The matchcode fits the matchcodes of the brands "
    + "interface.")
public class CompanyBrandItemDTO extends AbstractLinkDTO
{

    private static final long serialVersionUID = 3718076879527390575L;

    public CompanyBrandItemDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode)
    {
        super(tenant, matchcode);
    }

    @ApiModelProperty(notes = "The tenant (Portal-ID) where this brand is valid.")
    @Override
    public String getTenant()
    {
        return super.getTenant();
    }

    @ApiModelProperty(notes = "The matchcode of the brand (fits the matchcodes of the brands interface)")
    @Override
    public String getMatchcode()
    {
        return super.getMatchcode();
    }

}
