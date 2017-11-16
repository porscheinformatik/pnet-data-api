package pnet.data.api.infoarea;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.link.AbstractLinkDTO;
import pnet.data.api.tenant.Tenant;

/**
 * A link to a infoarea for a specified tenant.
 *
 * @author ham
 */
public class InfoareaLinkDTO extends AbstractLinkDTO<InfoareaMatchcode>
{

    public InfoareaLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("matchcode") InfoareaMatchcode matchcode)
    {
        super(tenant, matchcode);
    }

}
