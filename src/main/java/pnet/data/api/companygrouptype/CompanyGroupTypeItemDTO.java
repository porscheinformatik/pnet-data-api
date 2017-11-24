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
package pnet.data.api.companygrouptype;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a company group type. The company group type is used for company groups.
 *
 * @author ham
 */
public class CompanyGroupTypeItemDTO implements WithMatchcode<CompanyGroupTypeMatchcode>, WithLastUpdate
{

    private final CompanyGroupTypeMatchcode matchcode;
    private final String label;
    private final LocalDateTime lastUpdate;

    public CompanyGroupTypeItemDTO(@JsonProperty("matchcode") CompanyGroupTypeMatchcode matchcode,
        @JsonProperty("label") String label, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.matchcode = matchcode;
        this.label = label;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public CompanyGroupTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

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
        return String.format("CompanyGroupTypeItemDTO [matchcode=%s, label=%s, lastUpdate=%s]", matchcode, label,
            lastUpdate);
    }

}
