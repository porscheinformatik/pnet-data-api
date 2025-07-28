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
package pnet.data.api.function;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.AbstractAutoCompleteDTO;
import pnet.data.api.util.WithMatchcode;

@Schema(description = "Holds the auto-complete results for a function")
public class FunctionAutoCompleteDTO extends AbstractAutoCompleteDTO implements WithMatchcode {

    private static final long serialVersionUID = -8765852009234856747L;

    @Schema(description = "The unique matchcode of the activity.")
    private final String matchcode;

    public FunctionAutoCompleteDTO(
        @JsonProperty("matchcode") String matchcode,
        @JsonProperty("label") String label,
        @JsonProperty("description") String description,
        @JsonProperty("score") double score
    ) {
        super(label, description, score);
        this.matchcode = matchcode;
    }

    @Override
    public String getMatchcode() {
        return matchcode;
    }

    @Override
    public String toString() {
        return String.format(
            "FunctionAutoCompleteDTO [matchcode=%s, getLabel()=%s, getDescription()=%s, getScore()=%s]",
            matchcode,
            getLabel(),
            getDescription(),
            getScore()
        );
    }
}
