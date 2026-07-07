package pnet.data.api.companygroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Objects;
import pnet.data.api.util.WithCompanyId;
import pnet.data.api.util.WithTenant;

/**
 * Holds minimal information about a company group member.
 *
 * @author cet
 */
@Schema(description = "Holds minimal information about a company group member")
public class CompanyGroupMemberLinkDTO implements WithCompanyId, WithTenant, Serializable {

    private static final long serialVersionUID = 1202524288584163184L;

    @Schema(description = "The unique id of the company that is in the company group.")
    private final Integer companyId;

    @Schema(description = "The (almost-unique) matchcode of the company that is in the company group.")
    private final String companyMatchcode;

    @Schema(description = "The (non-unique) number of the company that is in the company group.")
    private final String companyNumber;

    @Schema(description = "The unique matchcode of the company group type of the company group")
    private final String groupType;

    @Schema(description = "The tenant (Portal-ID) of the company that is in the company group.")
    private final String tenant;

    public CompanyGroupMemberLinkDTO(
        @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("groupType") String groupType,
        @JsonProperty("tenant") String tenant
    ) {
        super();
        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.groupType = groupType;
        this.tenant = tenant;
    }

    @Override
    public Integer getCompanyId() {
        return companyId;
    }

    @Override
    public String getCompanyMatchcode() {
        return companyMatchcode;
    }

    @Override
    public String getCompanyNumber() {
        return companyNumber;
    }

    public String getGroupType() {
        return groupType;
    }

    @Override
    public String getTenant() {
        return tenant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();

        result = prime * result + ((groupType == null) ? 0 : groupType.hashCode());
        result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
        result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof CompanyGroupMemberLinkDTO other)) {
            return false;
        }

        if (!Objects.equals(groupType, other.groupType)) {
            return false;
        }
        if (!Objects.equals(companyId, other.companyId)) {
            return false;
        }
        if (!Objects.equals(tenant, other.tenant)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", groupType, companyId, tenant);
    }
}
