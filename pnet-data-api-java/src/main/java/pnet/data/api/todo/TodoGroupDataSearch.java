package pnet.data.api.todo;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.AggregateNumberPerCategory;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictCategory;
import pnet.data.api.util.RestrictPersonId;
import pnet.data.api.util.RestrictReferenceId;
import pnet.data.api.util.RestrictState;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.SearchFunction;

/**
 * Search options
 *
 * @author HAM
 */
public class TodoGroupDataSearch extends AbstractSearch<TodoGroupItemDTO, TodoGroupDataSearch>
    implements RestrictCategory<TodoGroupDataSearch>, RestrictPersonId<TodoGroupDataSearch>,
    RestrictReferenceId<TodoGroupDataSearch>, RestrictState<TodoGroupDataSearch>, RestrictType<TodoGroupDataSearch>,
    AggregateNumberPerCategory<TodoGroupDataSearch>
{

    public TodoGroupDataSearch(SearchFunction<TodoGroupItemDTO> searchFunction, List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }
}