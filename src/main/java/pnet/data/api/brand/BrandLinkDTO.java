package pnet.data.api.brand;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.link.AbstractLinkDTO;
import pnet.data.api.tenant.Tenant;

/**
 * A link to a brand for a specified tenant.
 *
 * @author ham
 */
public class BrandLinkDTO extends AbstractLinkDTO<BrandMatchcode>
{

    public BrandLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("matchcode") BrandMatchcode matchcode)
    {
        super(tenant, matchcode);
    }

}
