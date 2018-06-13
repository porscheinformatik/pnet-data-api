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
package pnet.data.api.advisortype;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithDescription;
import pnet.data.api.util.WithLabel;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an advisor type.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about an advisor type")
public class AdvisorTypeItemDTO implements WithMatchcode, WithLabel, WithDescription, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -5385814018859561810L;
    
    @ApiModelProperty(notes = "The unique matchcode of the advisor type")
    private final String matchcode;
    @ApiModelProperty(notes = "The label of the advisor type in the requested language")
    private final String label;
    @ApiModelProperty(notes = "The description of the advisor type in the requested language")
    private final String description;
    @ApiModelProperty(notes = "The time and date when the advisor type was last changed")
    private LocalDateTime lastUpdate;

    public AdvisorTypeItemDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("description") String description, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.matchcode = matchcode;
        this.label = label;
        this.description = description;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    @Override
    public String getLabel()
    {
        return label;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        return String.format("AdvisorTypeItemDTO [matchcode=%s, label=%s, description=%s, lastUpdate=%s]", matchcode,
            label, description, lastUpdate);
    }

}