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

    public TodoGroupAggregationsDTO(@JsonProperty("categories") List<TodoGroupCategoryAggregationDTO> categories)
    {
        super();

        this.categories = categories;
    }

    public List<TodoGroupCategoryAggregationDTO> getCategories()
    {
        return categories;
    }

    @Override
    public String toString()
    {
        return String.format("TodoGroupAggregationDTO [categories=%s]", categories);
    }

}
