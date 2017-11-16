package pnet.data.api.numbertype;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.link.AbstractLinkDTO;
import pnet.data.api.tenant.Tenant;

/**
 * A link to a number type for a specified tenant.
 *
 * @author ham
 */
public class NumberTypeLinkDTO extends AbstractLinkDTO<NumberTypeMatchcode>
{

    public NumberTypeLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("matchcode") NumberTypeMatchcode matchcode)
    {
        super(tenant, matchcode);
    }

}
