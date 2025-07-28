package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * A valid-from, valid-to period.
 *
 * @author ham
 */
public interface WithValidPeriod {
    LocalDateTime getValidFrom();

    LocalDateTime getValidTo();

    default boolean isValidAt(LocalDateTime dateTime) {
        LocalDateTime validFrom = getValidFrom();
        LocalDateTime validTo = getValidTo();

        if (validTo == null) {
            if (validFrom == null) {
                return true;
            }

            return !validFrom.isAfter(dateTime);
        }

        if (validFrom == null) {
            return !validTo.isBefore(dateTime);
        }

        return !validFrom.isAfter(dateTime) && !validTo.isBefore(dateTime);
    }
}
