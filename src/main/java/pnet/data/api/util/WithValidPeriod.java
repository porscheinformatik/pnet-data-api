package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * A valid-from, valid-to period.
 *
 * @author ham
 */
public interface WithValidPeriod
{

    LocalDateTime getValidFrom();

    LocalDateTime getValidTo();

}
