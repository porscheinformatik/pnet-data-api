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
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenants;

/**
 * Holds a brand as specified in the Partner.Net.
 *
 * @author ham
 */
@Schema(description = "Holds all information about a brand")
public class BrandDataDTO implements WithMatchcode, WithLabels, WithTenants, WithLastUpdate, Serializable {

    @Serial
    private static final long serialVersionUID = -5392033900534170882L;

    @Schema(description = "The unique matchcode of the brand")
    private final String matchcode;

    @Schema(description = "The tenants where the brand is valid")
    private Collection<String> tenants;

    @Schema(description = "The label of the brand with all existing translations")
    private Map<Locale, String> labels;

    @Schema(description = "The ordinal of the brand for sorting")
    private int ordinal;

    @Schema(description = "The simplified name of the brand when used as path, e.g. 'vw', 'audi', 'vwlnf'")
    private String path;

    @Schema(description = "The time and date when the brand was last changed")
    private LocalDateTime lastUpdate;

    public BrandDataDTO(@JsonProperty("matchcode") String matchcode) {
        super();
        this.matchcode = matchcode;
    }

    @Override
    public String getMatchcode() {
        return matchcode;
    }

    @Override
    public Collection<String> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<String> tenants) {
        this.tenants = tenants;
    }

    @Override
    public Map<Locale, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels) {
        this.labels = labels;
    }

    /**
     * @return The ordinal of the brand for sorting.
     */
    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    /**
     * @return The simplified name when used as path, e.g. "vw", "audi", "vwlnf".
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
        return String.format(
            "BrandDataDTO [matchcode=%s, tenants=%s, labels=%s, ordinal=%s, path=%s]",
            matchcode,
            tenants,
            labels,
            ordinal,
            path
        );
    }
}
