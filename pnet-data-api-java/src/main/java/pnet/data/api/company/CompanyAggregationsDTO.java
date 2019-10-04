package pnet.data.api.company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds aggregations for a search result
 *
 * @author HAM
 */
public class CompanyAggregationsDTO
{

    private final List<CompanyTenantAggregationDTO> tenants;
    private final List<CompanyBrandAggregationDTO> brands;
    private final List<CompanyTypeAggregationDTO> types;
    private final List<CompanyContractTypeAggregationDTO> contractTypes;

    public CompanyAggregationsDTO(@JsonProperty("tenants") List<CompanyTenantAggregationDTO> tenants,
        @JsonProperty("brands") List<CompanyBrandAggregationDTO> brands,
        @JsonProperty("types") List<CompanyTypeAggregationDTO> types,
        @JsonProperty("contractTypes") List<CompanyContractTypeAggregationDTO> contractTypes)
    {
        super();
        this.tenants = tenants;
        this.brands = brands;
        this.types = types;
        this.contractTypes = contractTypes;
    }

    public List<CompanyTenantAggregationDTO> getTenants()
    {
        return tenants;
    }

    public List<CompanyBrandAggregationDTO> getBrands()
    {
        return brands;
    }

    public List<CompanyTypeAggregationDTO> getTypes()
    {
        return types;
    }

    public List<CompanyContractTypeAggregationDTO> getContractTypes()
    {
        return contractTypes;
    }

    @Override
    public String toString()
    {
        return String
            .format("CompanyAggregationsDTO [tenants=%s, brands=%s, types=%s, contractTypes=%s]", tenants, brands,
                types, contractTypes);
    }

}