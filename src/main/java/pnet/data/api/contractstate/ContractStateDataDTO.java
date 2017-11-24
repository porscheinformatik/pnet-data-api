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
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a contract state used for contract types.
 *
 * @author ham
 */
public class ContractStateDataDTO implements WithMatchcode<ContractStateMatchcode>, WithLabels, WithLastUpdate
{

    private final ContractStateMatchcode matchcode;

    private Map<Locale, String> labels;
    private LocalDateTime lastUpdate;

    public ContractStateDataDTO(@JsonProperty("matchcode") ContractStateMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public ContractStateMatchcode getMatchcode()
    {
        return matchcode;
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
        return String.format("ContractStateDataDTO [matchcode=%s, labels=%s, lastUpdate=%s]", matchcode, labels,
            lastUpdate);
    }

}
