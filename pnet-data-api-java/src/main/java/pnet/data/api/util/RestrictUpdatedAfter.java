package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * A restriction for the update timestamp
 *
 * @author ham
 * @param <T> the type of the restrict for chaining
 */
public interface RestrictUpdatedAfter<T extends Restrict<T>> extends Restrict<T>
{

    default T restrictUpdatedAfter(LocalDateTime updatedAfter)
    {
        return restrict("up", updatedAfter);
    }

}
