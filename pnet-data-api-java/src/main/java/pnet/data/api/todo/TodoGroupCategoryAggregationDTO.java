package pnet.data.api.todo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The aggregation of one category
 *
 * @author HAM
 */
public class TodoGroupCategoryAggregationDTO implements Serializable
{

    private static final long serialVersionUID = 2277177401669237777L;

    private final TodoCategory category;
    private final long count;

    public TodoGroupCategoryAggregationDTO(@JsonProperty("category") TodoCategory category,
        @JsonProperty("count") Long count)
    {
        super();

        this.category = category;
        this.count = count != null ? count.longValue() : 0;
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
