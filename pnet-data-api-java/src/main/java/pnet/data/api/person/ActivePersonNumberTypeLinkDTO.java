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
package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Holds one employment dependent number of a person.
 *
 * @author ham
 */
@Schema(description = "Holds one employment dependent number of a person.")
public class ActivePersonNumberTypeLinkDTO extends AbstractNumberTypeLinkDTO
{

    private static final long serialVersionUID = -3446430282367218468L;

    @Schema(description = "True if currently active (ignores future settings).")
    protected final boolean currentlyActive;

    public ActivePersonNumberTypeLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode, @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode, @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("number") String number, @JsonProperty("currentlyActive") boolean currentlyActive)
    {
        super(tenant, matchcode, companyId, companyMatchcode, companyNumber, number);

        this.currentlyActive = currentlyActive;
    }

    public boolean isCurrentlyActive()
    {
        return currentlyActive;
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "ActivePersonNumberTypeLinkDTO [currentlyActive=%s, companyId=%s, companyMatchcode=%s, "
                    + "companyNumber=%s, number=%s, tenant=%s, matchcode=%s]",
                currentlyActive, companyId, companyMatchcode, companyNumber, number, tenant, matchcode);
    }

}
