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
package pnet.data.api.contractstate;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a contract state used for contract types.
 *
 * @author ham
 */
public class ContractStateItemDTO implements WithMatchcode<ContractStateMatchcode>, WithLastUpdate
{

    private final ContractStateMatchcode matchcode;
    private final String label;
    private final LocalDateTime lastUpdate;

    public ContractStateItemDTO(@JsonProperty("matchcode") ContractStateMatchcode matchcode,
        @JsonProperty("label") String label, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();

        this.matchcode = matchcode;
        this.label = label;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public ContractStateMatchcode getMatchcode()
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
        return String.format("ContractStateItemDTO [matchcode=%s, label=%s, lastUpdate=%s]", matchcode, label,
            lastUpdate);
    }

}
