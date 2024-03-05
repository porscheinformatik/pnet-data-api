package pnet.data.api.util;

/**
 * Defines the sort order.
 *
 * @param <SELF> the type of the restrict for chaining
 * @param <AnySortOrder> the type of object defining the order
 * @author ham
 */
public interface Orderable<SELF extends Restrict<SELF>, AnySortOrder> extends Restrict<SELF>
{
    default SELF orderBy(AnySortOrder... sortOrder)
    {
        return restrict("orderBy", (Object[]) sortOrder);
    }
}
