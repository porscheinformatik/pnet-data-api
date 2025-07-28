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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a legal form. The legal form is used for company groups.
 *
 * @author ham
 */
@Schema(description = "Holds all information about a legal form as used with companies.")
public class LegalFormDataDTO implements WithMatchcode, WithLabels, WithLastUpdate, Serializable {

    private static final long serialVersionUID = 7656177578952551086L;

    @Schema(description = "The unique matchcode of the legal form.")
    private final String matchcode;

    @Schema(description = "The label of the legal form with all existing translations.")
    private Map<Locale, String> labels;

    @Schema(description = "The time and date when the legal form was last changed.")
    private LocalDateTime lastUpdate;

    public LegalFormDataDTO(@JsonProperty("matchcode") String matchcode) {
        super();
        this.matchcode = matchcode;
    }

    @Override
    public String getMatchcode() {
        return matchcode;
    }

    @Override
    public Map<Locale, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels) {
        this.labels = labels;
    }

    @Override
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return String.format("CompanyTypeDataDTO [matchcode=%s, labels=%s]", matchcode, labels);
    }
}
