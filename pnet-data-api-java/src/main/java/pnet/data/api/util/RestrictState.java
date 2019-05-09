package pnet.data.api.util;

import java.util.Collection;

import pnet.data.api.todo.TodoState;

/**
 * Restricts states
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictState<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF state(TodoState... states)
    {
        return restrict("state", (Object[]) states);
    }

    default SELF states(Collection<TodoState> states)
    {
        return state(states.toArray(new TodoState[states.size()]));
    }

}
