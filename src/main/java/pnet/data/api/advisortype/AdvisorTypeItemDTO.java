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

import java.time.LocalDateTime;

import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an advisor type.
 *
 * @author ham
 */
public class AdvisorTypeItemDTO implements WithMatchcode<AdvisorTypeMatchcode>, WithLastUpdate
{

    private final AdvisorTypeMatchcode matchcode;
    private final String label;
    private final String description;
    private LocalDateTime lastUpdate;

    public AdvisorTypeItemDTO(AdvisorTypeMatchcode matchcode, String label, String description,
        LocalDateTime lastUpdate)
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
    public AdvisorTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    public String getLabel()
    {
        return label;
    }

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