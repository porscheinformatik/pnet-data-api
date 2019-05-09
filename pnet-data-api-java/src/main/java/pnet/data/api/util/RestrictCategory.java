package pnet.data.api.util;

import java.util.Collection;

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

    default SELF categories(Collection<TodoCategory> categories)
    {
        return category(categories.toArray(new TodoCategory[categories.size()]));
    }

}
