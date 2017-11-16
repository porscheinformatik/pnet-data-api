package pnet.data.api.contracttypes;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.link.AbstractLinkDTO;
import pnet.data.api.tenant.Tenant;

/**
 * A link to a contract type for a specified tenant.
 *
 * @author ham
 */
public class ContractTypeLinkDTO extends AbstractLinkDTO<ContractTypeMatchcode>
{

    public ContractTypeLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("matchcode") ContractTypeMatchcode matchcode)
    {
        super(tenant, matchcode);
    }

}
