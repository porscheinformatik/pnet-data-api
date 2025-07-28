package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts todo ids.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictTodoId<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF todoId(Integer... todoIds) {
        return restrict("todoId", (Object[]) todoIds);
    }

    default SELF todoIds(Collection<Integer> todoIds) {
        return todoId(todoIds.toArray(new Integer[0]));
    }
}
