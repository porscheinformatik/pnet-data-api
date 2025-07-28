package pnet.data.api.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import pnet.data.api.settings.AbstractQualificationSettingsLinkDTO;
import pnet.data.api.settings.Visibility;

public class ActivitySettingsLinkDTO extends AbstractQualificationSettingsLinkDTO {

    private static final long serialVersionUID = -1305175682271191556L;

    public ActivitySettingsLinkDTO(
        @JsonProperty(value = "tenant") String tenant,
        @JsonProperty(value = "visibility") Visibility visibility,
        @JsonProperty(value = "assignableToRegular") boolean assignableToRegular,
        @JsonProperty(value = "assignableToPrivileged") boolean assignableToPrivileged,
        @JsonProperty(value = "approvalNeeded") boolean approvalNeeded
    ) {
        super(tenant, visibility, assignableToRegular, assignableToPrivileged, approvalNeeded);
    }
}
