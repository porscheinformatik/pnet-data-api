package pnet.data.api.todo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds aggregations for a search result
 *
 * @author HAM
 */
public class TodoGroupAggregatesDTO
{

    private final List<TodoGroupCategoryAggregateDTO> categories;

    public TodoGroupAggregatesDTO(@JsonProperty("categories") List<TodoGroupCategoryAggregateDTO> categories)
    {
        super();

        this.categories = categories;
    }

    public List<TodoGroupCategoryAggregateDTO> getCategories()
    {
        return categories;
    }

    @Override
    public String toString()
    {
        return String.format("TodoGroupAggregateDTO [categories=%s]", categories);
    }

}
