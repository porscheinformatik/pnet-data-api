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
import java.io.Serializable;
import java.util.Objects;
import pnet.data.api.util.WithPersonId;

/**
 * Holds person-person references, e.g. responsible persons for bots and test users.
 *
 * @author ham
 */
@Schema(description = "Holds person-person references, e.g. responsible persons for bots and test users.")
public class PersonHierarchyLinkDTO implements WithPersonId, Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "The unique id of the referenced person.")
    private final Integer referencedPersonId;

    @Schema(description = "The type of hierarchy.")
    private final PersonHierarchyType type;

    @Schema(description = "The name of the referenced person.")
    private final String referencedPersonName;

    public PersonHierarchyLinkDTO(
        @JsonProperty("personId") Integer personId,
        @JsonProperty("type") PersonHierarchyType type,
        @JsonProperty("personName") String personName
    ) {
        this.referencedPersonId = personId;
        this.type = type;
        this.referencedPersonName = personName;
    }

    @Override
    public Integer getPersonId() {
        return referencedPersonId;
    }

    public PersonHierarchyType getType() {
        return type;
    }

    public String getReferencedPersonName() {
        return referencedPersonName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonHierarchyLinkDTO that = (PersonHierarchyLinkDTO) o;
        return (
            Objects.equals(referencedPersonId, that.referencedPersonId) &&
            type == that.type &&
            Objects.equals(referencedPersonName, that.referencedPersonName)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(referencedPersonId, type, referencedPersonName);
    }

    @Override
    public String toString() {
        return String.format(
            "PersonHierarchyLinkDTO [referencedPersonId=%s, type=%s, referencedPersonName=%s]",
            referencedPersonId,
            type,
            referencedPersonName
        );
    }
}
