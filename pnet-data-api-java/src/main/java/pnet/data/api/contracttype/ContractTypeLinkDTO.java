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
package pnet.data.api.contracttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.AbstractLinkDTO;

/**
 * Holds a contract type.
 *
 * @author ham
 */
@Schema(description = "Holds minimal information about a contract type")
public class ContractTypeLinkDTO extends AbstractLinkDTO {

    private static final long serialVersionUID = 7156531727995281244L;

    public ContractTypeLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode) {
        super(tenant, matchcode);
    }

    @JsonPropertyDescription("A tenant where the contract type is valid")
    @Override
    public String getTenant() {
        return super.getTenant();
    }

    @JsonPropertyDescription("The unique matchcode of the contract type")
    @Override
    public String getMatchcode() {
        return super.getMatchcode();
    }
}
