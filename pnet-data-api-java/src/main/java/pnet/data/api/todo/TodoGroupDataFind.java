package pnet.data.api.todo;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictCategory;
import pnet.data.api.util.RestrictPersonId;
import pnet.data.api.util.RestrictReferenceId;
import pnet.data.api.util.RestrictReferenceMatchcode;
import pnet.data.api.util.Scrollable;
import pnet.data.api.util.RestrictState;
import pnet.data.api.util.RestrictTodoId;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find options
 *
 * @author HAM
 */
public class TodoGroupDataFind extends AbstractFind<TodoGroupItemDTO, TodoGroupDataFind> implements
    RestrictCategory<TodoGroupDataFind>, RestrictPersonId<TodoGroupDataFind>, RestrictReferenceId<TodoGroupDataFind>,
    RestrictReferenceMatchcode<TodoGroupDataFind>, RestrictState<TodoGroupDataFind>, RestrictType<TodoGroupDataFind>,
    RestrictTodoId<TodoGroupDataFind>, RestrictUpdatedAfter<TodoGroupDataFind>, Scrollable<TodoGroupDataFind>
{

    public TodoGroupDataFind(FindFunction<TodoGroupItemDTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }
}