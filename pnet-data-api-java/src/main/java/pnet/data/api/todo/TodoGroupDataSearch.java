package pnet.data.api.todo;

import java.util.List;

import pnet.data.api.util.AbstractSearchWithAggregates;
import pnet.data.api.util.AggregateNumberPerCategory;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictCategory;
import pnet.data.api.util.RestrictPersonId;
import pnet.data.api.util.RestrictReferenceId;
import pnet.data.api.util.RestrictState;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.SearchWithAggregatesFunction;

/**
 * Search options
 *
 * @author HAM
 */
public class TodoGroupDataSearch
    extends AbstractSearchWithAggregates<TodoGroupItemDTO, TodoGroupAggregatesDTO, TodoGroupDataSearch>
    implements RestrictCategory<TodoGroupDataSearch>, RestrictPersonId<TodoGroupDataSearch>,
    RestrictReferenceId<TodoGroupDataSearch>, RestrictState<TodoGroupDataSearch>, RestrictType<TodoGroupDataSearch>,
    AggregateNumberPerCategory<TodoGroupDataSearch>
{

    public TodoGroupDataSearch(SearchWithAggregatesFunction<TodoGroupItemDTO, TodoGroupAggregatesDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }
}