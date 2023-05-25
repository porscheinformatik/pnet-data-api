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

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.WithDescription;
import pnet.data.api.util.WithLabel;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithScore;

/**
 * Holds an advisor type.
 *
 * @author ham
 */
@Schema(description = "Holds basic information about an advisor type")
public class AdvisorTypeItemDTO
    implements WithMatchcode, WithLabel, WithDescription, WithLastUpdate, WithScore, Serializable
{

    private static final long serialVersionUID = -5385814018859561810L;

    @Schema(description = "The unique matchcode of the advisor type.")
    private final String matchcode;

    @Schema(description = "The label of the advisor type in the requested language.")
    private final String label;

    @Schema(description = "The description of the advisor type in the requested language.")
    private final String description;

    @Schema(description = "The time and date when this item has been changed recently.")
    private final LocalDateTime lastUpdate;

    @Schema(description = "The score this item accomplished in the search operation.")
    private final double score;

    public AdvisorTypeItemDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("description") String description, @JsonProperty("lastUpdate") LocalDateTime lastUpdate,
        @JsonProperty("score") double score)
    {
        super();
        this.matchcode = matchcode;
        this.label = label;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.score = score;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
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
    public double getScore()
    {
        return score;
    }

    @Override
    public String toString()
    {
        return String
            .format("AdvisorTypeItemDTO [matchcode=%s, label=%s, description=%s, lastUpdate=%s, score=%s]", matchcode,
                label, description, lastUpdate, score);
    }

}