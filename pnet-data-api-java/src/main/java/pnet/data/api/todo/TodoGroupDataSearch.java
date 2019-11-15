package pnet.data.api.todo;

import java.util.List;

import pnet.data.api.util.AbstractSearchWithAggregations;
import pnet.data.api.util.AggregateNumberPerCategory;
import pnet.data.api.util.AggregateNumberPerState;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictCategory;
import pnet.data.api.util.RestrictPersonId;
import pnet.data.api.util.RestrictQueryField;
import pnet.data.api.util.RestrictReferenceId;
import pnet.data.api.util.RestrictState;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.SearchWithAggregationsFunction;

/**
 * Search options
 *
 * @author HAM
 */
public class TodoGroupDataSearch
    extends AbstractSearchWithAggregations<TodoGroupItemDTO, TodoGroupAggregationsDTO, TodoGroupDataSearch>
    implements RestrictCategory<TodoGroupDataSearch>, RestrictPersonId<TodoGroupDataSearch>,
    RestrictReferenceId<TodoGroupDataSearch>, RestrictState<TodoGroupDataSearch>, RestrictType<TodoGroupDataSearch>,
    AggregateNumberPerCategory<TodoGroupDataSearch>, AggregateNumberPerState<TodoGroupDataSearch>,
    RestrictQueryField<TodoGroupDataSearch>
{

    public TodoGroupDataSearch(
        SearchWithAggregationsFunction<TodoGroupItemDTO, TodoGroupAggregationsDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }
}