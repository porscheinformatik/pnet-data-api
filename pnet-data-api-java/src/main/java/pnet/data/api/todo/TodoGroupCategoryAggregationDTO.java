package pnet.data.api.todo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The aggregation of one category
 * 
 * @author HAM
 */
public class TodoGroupCategoryAggregationDTO
{

    private final TodoCategory category;
    private final long count;

    public TodoGroupCategoryAggregationDTO(@JsonProperty("category") TodoCategory category,
        @JsonProperty("count") long count)
    {
        super();

        this.category = category;
        this.count = count;
    }

    public TodoCategory getCategory()
    {
        return category;
    }

    public long getCount()
    {
        return count;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s", category, count);
    }

}
