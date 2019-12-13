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
package pnet.data.api.legalform;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithLabel;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithScore;

/**
 * Holds a legal form. The legal form is used for company groups.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about a legal form as used with companies.")
public class LegalFormItemDTO implements WithMatchcode, WithLabel, WithLastUpdate, WithScore, Serializable
{

    private static final long serialVersionUID = -3404509259945335365L;

    @ApiModelProperty(notes = "The unique matchcode of the legal form.")
    private final String matchcode;

    @ApiModelProperty(notes = "The label of the legal form in the requested language.")
    private final String label;

    @ApiModelProperty(notes = "The time and date when this item has been changed recently.")
    private final LocalDateTime lastUpdate;

    @ApiModelProperty(notes = "The score this item accomplished in the search operation.")
    private final double score;

    public LegalFormItemDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate, @JsonProperty("score") double score)
    {
        super();
        this.matchcode = matchcode;
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
            .format("LegalFormItemDTO [matchcode=%s, label=%s, lastUpdate=%s, score=%s]", matchcode, label, lastUpdate,
                score);
    }

}
