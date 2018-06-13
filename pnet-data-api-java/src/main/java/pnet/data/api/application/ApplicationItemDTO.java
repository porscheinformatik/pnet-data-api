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
package pnet.data.api.application;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithLabel;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an application.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about an application")
public class ApplicationItemDTO implements WithMatchcode, WithLabel, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = 1943888464506455363L;

    @ApiModelProperty(notes = "The unique matchcode of the application")
    private final String matchcode;
    @ApiModelProperty(notes = "The label of the application in the requested language")
    private final String label;
    @ApiModelProperty(notes = "The time and date when the application was last changed")
    private final LocalDateTime lastUpdate;

    public ApplicationItemDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.matchcode = matchcode;
        this.label = label;
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
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format("CompanyTypeItemDTO [matchcode=%s, label=%s, lastUpdate=%s]", matchcode, label,
            lastUpdate);
    }

}
