package pnet.data.api.role;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.link.AbstractLinkDTO;
import pnet.data.api.tenant.Tenant;

/**
 * A link to a role for a specified tenant.
 *
 * @author ham
 */
public class RoleLinkDTO extends AbstractLinkDTO<RoleMatchcode>
{

    public RoleLinkDTO(@JsonProperty("tenant") Tenant tenant, @JsonProperty("matchcode") RoleMatchcode matchcode)
    {
        super(tenant, matchcode);
    }

}
