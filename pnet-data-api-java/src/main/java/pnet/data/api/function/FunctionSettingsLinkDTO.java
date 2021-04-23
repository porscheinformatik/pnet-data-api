package pnet.data.api.function;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.settings.AbstractQualificationSettingsLinkDTO;
import pnet.data.api.settings.Visibility;

public class FunctionSettingsLinkDTO extends AbstractQualificationSettingsLinkDTO
{

    private static final long serialVersionUID = -1305175682271191556L;

    public FunctionSettingsLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("visibility") Visibility visibility, @JsonProperty("approvalNeeded") boolean approvalNeeded)
    {
        super(tenant, visibility, approvalNeeded);
    }

}
