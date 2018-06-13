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
package pnet.data.api.externalbrand;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an external brand.
 *
 * @author ham
 */
@ApiModel(description = "Holds all information about an external brand")
public class ExternalBrandDataDTO implements WithMatchcode, WithLabels, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -5466024519780832525L;

    @ApiModelProperty(notes = "The unique matchcode of the external brand")
    private final String matchcode;

    @ApiModelProperty(notes = "The unique id of the external brand")
    private String id;
    @ApiModelProperty(notes = "The label of the external brand with all existing translations")
    private Map<Locale, String> labels;
    @ApiModelProperty(notes = "The time and date when the external brand was last changed")
    private LocalDateTime lastUpdate;

    public ExternalBrandDataDTO(@JsonProperty("matchcode") String matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
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
    public String toString()
    {
        return String.format("ExternalBrandDataDTO [matchcode=%s, id=%s, labels=%s, lastUpdate=%s]", matchcode, id,
            labels, lastUpdate);
    }

}
