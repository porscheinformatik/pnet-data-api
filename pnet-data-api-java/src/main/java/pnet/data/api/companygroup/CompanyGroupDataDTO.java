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
package pnet.data.api.companygroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Holds a group of companies.
 *
 * @author ham
 */
@Schema(description = "Holds all information about a company group.")
public class CompanyGroupDataDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 9149727074935636956L;

    @Schema(description = "The unique id of the company that is leading the company group.")
    private final Integer leadingCompanyId;

    @Schema(description = "The (almost-unique) matchcode of the company that is leading the company group.")
    private final String leadingCompanyMatchcode;

    @Schema(description = "The (non-unique) number of the company that is leading the company group.")
    private final String leadingCompanyNumber;

    @Schema(description = "The members of the company group")
    private Collection<CompanyGroupMemberLinkDTO> members;

    public CompanyGroupDataDTO(
        @JsonProperty("leadingCompanyId") Integer leadingCompanyId,
        @JsonProperty("leadingCompanyMatchcode") String leadingCompanyMatchcode,
        @JsonProperty("leadingCompanyNumber") String leadingCompanyNumber
    ) {
        super();
        this.leadingCompanyId = leadingCompanyId;
        this.leadingCompanyMatchcode = leadingCompanyMatchcode;
        this.leadingCompanyNumber = leadingCompanyNumber;
    }

    public Integer getLeadingCompanyId() {
        return leadingCompanyId;
    }

    public String getLeadingCompanyMatchcode() {
        return leadingCompanyMatchcode;
    }

    public String getLeadingCompanyNumber() {
        return leadingCompanyNumber;
    }

    public Collection<CompanyGroupMemberLinkDTO> getMembers() {
        return members;
    }

    public Optional<CompanyGroupMemberLinkDTO> findMember(Predicate<? super CompanyGroupMemberLinkDTO> predicate) {
        return members == null ? Optional.empty() : members.stream().filter(predicate).findFirst();
    }

    public void setMembers(Collection<CompanyGroupMemberLinkDTO> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return String.format(
            "CompanyGroupDataDTO [leadingCompanyId=%s, leadingCompanyMatchcode=%s, leadingCompanyNumber=%s, " +
            "members=%s]",
            leadingCompanyId,
            leadingCompanyMatchcode,
            leadingCompanyNumber,
            members
        );
    }
}
