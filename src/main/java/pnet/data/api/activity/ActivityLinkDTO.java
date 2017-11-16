package pnet.data.api.activity;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.link.AbstractLinkDTO;
import pnet.data.api.tenant.Tenant;

/**
 * A link to an activity for a specified tenant.
 *
 * @author ham
 */
public class ActivityLinkDTO extends AbstractLinkDTO<ActivityMatchcode>
{

    public ActivityLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("matchcode") ActivityMatchcode matchcode)
    {
        super(tenant, matchcode);
    }

}
