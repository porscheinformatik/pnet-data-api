package pnet.data.api.todo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds aggregations for a search result
 *
 * @author HAM
 */
public class TodoGroupAggregationsDTO
{

    private final List<TodoGroupCategoryAggregationDTO> categories;
    private final List<TodoGroupStateAggregationDTO> states;

    public TodoGroupAggregationsDTO(@JsonProperty("categories") List<TodoGroupCategoryAggregationDTO> categories,
        @JsonProperty("categories") List<TodoGroupStateAggregationDTO> states)
    {
        super();

        this.categories = categories;
        this.states = states;
    }

    public List<TodoGroupCategoryAggregationDTO> getCategories()
    {
        return categories;
    }

    public List<TodoGroupStateAggregationDTO> getStates()
    {
        return states;
    }

    @Override
    public String toString()
    {
        return String.format("TodoGroupAggregationsDTO [categories=%s, states=%s]", categories, states);
    }

}
