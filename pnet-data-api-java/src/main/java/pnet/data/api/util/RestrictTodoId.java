package pnet.data.api.util;

/**
 * Restricts todo ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictTodoId<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF todoId(Integer... todoIds)
    {
        return restrict("todoId", (Object[]) todoIds);
    }

}
