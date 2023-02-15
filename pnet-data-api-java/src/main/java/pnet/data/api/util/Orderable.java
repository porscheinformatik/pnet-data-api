package pnet.data.api.util;

/**
 * Defines the sort order.
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 * @param <AnySortOrder> the type of object defining the order
 */
public interface Orderable<SELF extends Restrict<SELF>, AnySortOrder> extends Restrict<SELF>
{
    default SELF orderBy(@SuppressWarnings("unchecked") AnySortOrder... sortOrder)
    {
        return restrict("orderBy", (Object[]) sortOrder);
    }
}
