package pnet.data.api.util;

import pnet.data.api.todo.TodoSource;

/**
 * Restricts sources
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictSource<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF source(TodoSource... sources)
    {
        return restrict("source", (Object[]) sources);
    }

}
