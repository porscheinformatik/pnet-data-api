package pnet.data.api.contracttypes;

import java.util.Collection;

import pnet.data.api.brand.BrandLinkDTO;
import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * A link to a brand for a specified tenant.
 *
 * @author ham
 */
public class ContractTypeBrandLinkDTO extends BrandLinkDTO
{

    private final Collection<String> states;

    public ContractTypeBrandLinkDTO(Tenant tenant, BrandMatchcode brand, Collection<String> states)
    {
        super(tenant, brand);
        this.states = states;
    }

    public Collection<String> getStates()
    {
        return states;
    }

    @Override
    public String toString()
    {
        return String.format("%s [states=%s]", super.toString(), states);
    }

}
