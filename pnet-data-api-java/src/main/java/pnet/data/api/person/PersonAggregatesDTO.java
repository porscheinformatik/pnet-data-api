package pnet.data.api.person;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds aggregations for a search result
 *
 * @author HAM
 */
public class PersonAggregatesDTO
{

    private final List<PersonTenantAggregateDTO> tenants;
    private final List<PersonCompanyAggregateDTO> companies;
    private final List<PersonFunctionAggregateDTO> functions;
    private final List<PersonActivityAggregateDTO> activities;

    public PersonAggregatesDTO(@JsonProperty("tenants") List<PersonTenantAggregateDTO> tenants,
        @JsonProperty("companies") List<PersonCompanyAggregateDTO> companies,
        @JsonProperty("functions") List<PersonFunctionAggregateDTO> functions,
        @JsonProperty("activities") List<PersonActivityAggregateDTO> activities)
    {
        super();
        this.companies = companies;
        this.tenants = tenants;
        this.functions = functions;
        this.activities = activities;
    }

    public List<PersonTenantAggregateDTO> getTenants()
    {
        return tenants;
    }

    public List<PersonCompanyAggregateDTO> getCompanies()
    {
        return companies;
    }

    public List<PersonFunctionAggregateDTO> getFunctions()
    {
        return functions;
    }

    public List<PersonActivityAggregateDTO> getActivities()
    {
        return activities;
    }

    @Override
    public String toString()
    {
        return String
            .format("PersonAggregatesDTO [tenants=%s, companies=%s, functions=%s, activities=%s]", tenants, companies,
                functions, activities);
    }
}
