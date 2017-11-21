package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * Provides a last update timestamp
 *
 * @author ham
 */
public interface WithLastUpdate
{

    /**
     * @return The date/time of the last update to this item.
     */
    LocalDateTime getLastUpdate();
}
