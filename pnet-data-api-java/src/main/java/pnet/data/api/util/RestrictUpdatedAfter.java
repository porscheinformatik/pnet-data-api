package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * A restriction for the update timestamp
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictUpdatedAfter<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF restrictUpdatedAfter(LocalDateTime updatedAfter)
    {
        return restrict("up", updatedAfter);
    }

}
