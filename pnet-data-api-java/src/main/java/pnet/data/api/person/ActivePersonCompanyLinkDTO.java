package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import pnet.data.api.util.ApprovalState;
import pnet.data.api.util.PnetDataApiUtils;
import pnet.data.api.util.WithCompanyId;

/**
 * Holds one employment of a person
 *
 * @author HAM
 */
@Schema(
    description = "Holds minimal information about a employment the person has. " +
    "Related to the datedBackUnitl parameter."
)
public class ActivePersonCompanyLinkDTO implements WithCompanyId, Serializable {

    @Serial
    private static final long serialVersionUID = -847403488657179223L;

    @Schema(description = "The unique id of the company the person has an employment at.")
    protected final Integer companyId;

    @Schema(description = "The matchcode of the company the person has an employment at.")
    protected final String companyMatchcode;

    @Schema(description = "The number of the company the person has an employment at.")
    protected final String companyNumber;

    @Schema(description = "The label of the company the person has an employment at.")
    protected final String companyLabel;

    @Schema(description = "True, if the employment has been approved already, false otherwise.")
    protected final boolean approved;

    @Schema(description = "The current state of the audit process.")
    protected final ApprovalState approvalState;

    @Schema(description = "True if currently active (ignores the datedBackUntil parameter).")
    protected final boolean currentlyActive;

    public ActivePersonCompanyLinkDTO(
        @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("companyLabel") String companyLabel,
        @JsonProperty("approved") boolean approved,
        @JsonProperty("approvalState") ApprovalState approvalState,
        @JsonProperty("currentlyActive") boolean currentlyActive
    ) {
        super();
        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.companyLabel = companyLabel;
        this.approved = approved;
        this.approvalState = approvalState;
        this.currentlyActive = currentlyActive;
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

    public String getCompanyLabel() {
        return companyLabel;
    }

    public String getCompanyLabelWithNumber() {
        return PnetDataApiUtils.toCompanyLabelWithNumber(companyNumber, companyLabel);
    }

    public boolean isApproved() {
        return approved;
    }

    public ApprovalState getApprovalState() {
        return approvalState;
    }

    public boolean isCurrentlyActive() {
        return currentlyActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ActivePersonCompanyLinkDTO other)) {
            return false;
        }
        return Objects.equals(companyId, other.companyId);
    }

    @Override
    public String toString() {
        return String.format(
            "ActivePersonCompanyLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s, companyLabel=%s, " +
            "approved=%s, approvalState=%s,currentlyActive=%s]",
            companyId,
            companyMatchcode,
            companyNumber,
            companyLabel,
            approved,
            approvalState,
            currentlyActive
        );
    }
}
