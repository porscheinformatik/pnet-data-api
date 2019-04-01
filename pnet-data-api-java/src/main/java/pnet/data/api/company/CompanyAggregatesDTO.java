package pnet.data.api.company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds aggregations for a search result
 *
 * @author HAM
 */
public class CompanyAggregatesDTO
{

    private final List<CompanyTenantAggregateDTO> tenants;
    private final List<CompanyBrandAggregateDTO> brands;
    private final List<CompanyTypeAggregateDTO> types;
    private final List<CompanyContractTypeAggregateDTO> contractTypes;

    public CompanyAggregatesDTO(@JsonProperty("tenants") List<CompanyTenantAggregateDTO> tenants,
        @JsonProperty("brands") List<CompanyBrandAggregateDTO> brands,
        @JsonProperty("types") List<CompanyTypeAggregateDTO> types,
        @JsonProperty("contractTypes") List<CompanyContractTypeAggregateDTO> contractTypes)
    {
        super();
        this.tenants = tenants;
        this.brands = brands;
        this.types = types;
        this.contractTypes = contractTypes;
    }

    public List<CompanyTenantAggregateDTO> getTenants()
    {
        return tenants;
    }

    public List<CompanyBrandAggregateDTO> getBrands()
    {
        return brands;
    }

    public List<CompanyTypeAggregateDTO> getTypes()
    {
        return types;
    }

    public List<CompanyContractTypeAggregateDTO> getContractTypes()
    {
        return contractTypes;
    }

    @Override
    public String toString()
    {
        return String
            .format("CompanyAggregatesDTO [tenants=%s, brands=%s, types=%s, contractTypes=%s]", tenants, brands, types,
                contractTypes);
    }

}