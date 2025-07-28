package pnet.data.api.settings;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;

public abstract class AbstractQualificationSettingsLinkDTO extends AbstractSettingsLinkDTO {

    @Serial
    private static final long serialVersionUID = 9056664712077208202L;

    protected final Visibility visibility;
    protected final boolean assignableToRegular;
    protected final boolean assignableToPrivileged;
    protected final boolean approvalNeeded;

    protected AbstractQualificationSettingsLinkDTO(
        @JsonProperty(value = "tenant") String tenant,
        @JsonProperty(value = "visibility") Visibility visibility,
        @JsonProperty(value = "assignableToRegular") boolean assignableToRegular,
        @JsonProperty(value = "assignableToPrivileged") boolean assignableToPrivileged,
        @JsonProperty(value = "approvalNeeded") boolean approvalNeeded
    ) {
        super(tenant);
        this.visibility = visibility;
        this.assignableToRegular = assignableToRegular;
        this.assignableToPrivileged = assignableToPrivileged;
        this.approvalNeeded = approvalNeeded;
    }

    public Visibility getVisibility() {
        return this.visibility;
    }

    public boolean isAssignableToRegular() {
        return this.assignableToRegular;
    }

    public boolean isAssignableToPrivileged() {
        return this.assignableToPrivileged;
    }

    public boolean isApprovalNeeded() {
        return this.approvalNeeded;
    }

    @Override
    public String toString() {
        return String.format(
            "AbstractQualificationSettingsLinkDTO [tenant=%s, visibility=%s, assignableToRegular=%s, assignableToPrivileged=%s, approvalNeeded=%s]",
            this.tenant,
            this.visibility,
            this.assignableToRegular,
            this.assignableToPrivileged,
            this.approvalNeeded
        );
    }
}
