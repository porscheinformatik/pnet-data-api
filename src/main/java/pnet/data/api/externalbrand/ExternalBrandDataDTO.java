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

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an external brand.
 *
 * @author ham
 */
public class ExternalBrandDataDTO implements WithMatchcode<ExternalBrandMatchcode>, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -5466024519780832525L;

    private final ExternalBrandMatchcode matchcode;

    private String id;
    private String label;
    private LocalDateTime lastUpdate;

    public ExternalBrandDataDTO(@JsonProperty("matchcode") ExternalBrandMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public ExternalBrandMatchcode getMatchcode()
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

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
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
        return String.format("ExternalBrandDataDTO [id=%s, matchcode=%s, label=%s, lastUpdate=%s]", id, matchcode,
            label, lastUpdate);
    }

}
