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
package pnet.data.api.companytype;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithLabel;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithScore;
import pnet.data.api.util.WithTenants;

/**
 * Holds a company type. A company is linked to one or more company types. Functions and activities need company types
 * as prerequisite.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about a company type")
public class CompanyTypeItemDTO
    implements WithMatchcode, WithLabel, WithTenants, WithLastUpdate, WithScore, Serializable
{

    private static final long serialVersionUID = 1943888464506455363L;

    @ApiModelProperty(notes = "The unique matchcode of the company type.")
    private final String matchcode;

    @ApiModelProperty(notes = "The tenants where the company type is valid.")
    private final Collection<String> tenants;

    @ApiModelProperty(notes = "The label of the company type in the requested language.")
    private final String label;

    @ApiModelProperty(notes = "The time and date when this item has been changed recently.")
    private final LocalDateTime lastUpdate;

    @ApiModelProperty(notes = "The score this item accomplished in the search operation.")
    private final double score;

    public CompanyTypeItemDTO(@JsonProperty("matchcode") String matchcode,
        @JsonProperty("tenants") Collection<String> tenants, @JsonProperty("label") String label,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate, @JsonProperty("score") double score)
    {
        super();
        this.matchcode = matchcode;
        this.tenants = tenants;
        this.label = label;
        this.lastUpdate = lastUpdate;
        this.score = score;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    @Override
    public Collection<String> getTenants()
    {
        return tenants;
    }

    @Override
    public String getLabel()
    {
        return label;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public double getScore()
    {
        return score;
    }

    @Override
    public String toString()
    {
        return String
            .format("CompanyTypeItemDTO [matchcode=%s, tenants=%s, label=%s, lastUpdate=%s, score=%s]", matchcode,
                tenants, label, lastUpdate, score);
    }

}
