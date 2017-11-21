package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * Provides a last update timestamp
 * 
 * @author ham
 */
public interface Traceable
{

    LocalDateTime getLastUpdate();
}
