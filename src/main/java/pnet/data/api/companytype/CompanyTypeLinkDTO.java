package pnet.data.api.companytype;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.link.AbstractLinkDTO;
import pnet.data.api.tenant.Tenant;

/**
 * A link to a company type for a specified tenant.
 *
 * @author ham
 */
public class CompanyTypeLinkDTO extends AbstractLinkDTO<CompanyTypeMatchcode>
{

    public CompanyTypeLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("matchcode") CompanyTypeMatchcode matchcode)
    {
        super(tenant, matchcode);
    }

}
