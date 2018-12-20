package pnet.data.api.util;

import pnet.data.api.todo.TodoCategory;

/**
 * Restricts categories
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictCategory<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF category(TodoCategory... categories)
    {
        return restrict("category", (Object[]) categories);
    }

}
