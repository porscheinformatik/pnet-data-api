package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.time.LocalDateTime;

/**
 * A restriction for the update timestamp
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface RestrictUpdatedAfter<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF updatedAfter(LocalDateTime updatedAfter)
    {
        return restrict(UPDATED_AFTER_KEY, updatedAfter);
    }

}
