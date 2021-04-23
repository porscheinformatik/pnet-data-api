package pnet.data.api.settings;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractQualificationSettingsLinkDTO extends AbstractSettingsLinkDTO
{

    private static final long serialVersionUID = 9056664712077208202L;

    protected final Visibility visibility;
    protected final boolean approvalNeeded;

    public AbstractQualificationSettingsLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("visibility") Visibility visibility, @JsonProperty("approvalNeeded") boolean approvalNeeded)
    {
        super(tenant);

        this.visibility = visibility;
        this.approvalNeeded = approvalNeeded;
    }

    public Visibility getVisibility()
    {
        return visibility;
    }

    public boolean isApprovalNeeded()
    {
        return approvalNeeded;
    }

    @Override
    public String toString()
    {
        return String
            .format("AbstractQualificationSettingsLinkDTO [tenant=%s, visibility=%s, approvalNeeded=%s]", visibility,
                approvalNeeded);
    }

}
