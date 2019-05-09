package pnet.data.api.person;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds aggregations for a search result
 *
 * @author HAM
 */
public class PersonAggregationsDTO
{

    private final List<PersonTenantAggregationDTO> tenants;
    private final List<PersonCompanyAggregationDTO> companies;
    private final List<PersonFunctionAggregationDTO> functions;
    private final List<PersonActivityAggregationDTO> activities;

    public PersonAggregationsDTO(@JsonProperty("tenants") List<PersonTenantAggregationDTO> tenants,
        @JsonProperty("companies") List<PersonCompanyAggregationDTO> companies,
        @JsonProperty("functions") List<PersonFunctionAggregationDTO> functions,
        @JsonProperty("activities") List<PersonActivityAggregationDTO> activities)
    {
        super();
        this.companies = companies;
        this.tenants = tenants;
        this.functions = functions;
        this.activities = activities;
    }

    public List<PersonTenantAggregationDTO> getTenants()
    {
        return tenants;
    }

    public List<PersonCompanyAggregationDTO> getCompanies()
    {
        return companies;
    }

    public List<PersonFunctionAggregationDTO> getFunctions()
    {
        return functions;
    }

    public List<PersonActivityAggregationDTO> getActivities()
    {
        return activities;
    }

    @Override
    public String toString()
    {
        return String
            .format("PersonAggregationsDTO [tenants=%s, companies=%s, functions=%s, activities=%s]", tenants, companies,
                functions, activities);
    }
}
