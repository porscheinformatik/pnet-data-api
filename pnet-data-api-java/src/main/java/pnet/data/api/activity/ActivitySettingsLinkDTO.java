package pnet.data.api.activity;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.settings.AbstractQualificationSettingsLinkDTO;
import pnet.data.api.settings.Visibility;

public class ActivitySettingsLinkDTO extends AbstractQualificationSettingsLinkDTO
{

    private static final long serialVersionUID = -1305175682271191556L;

    public ActivitySettingsLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("visibility") Visibility visibility, @JsonProperty("approvalNeeded") boolean approvalNeeded)
    {
        super(tenant, visibility, approvalNeeded);
    }

}
