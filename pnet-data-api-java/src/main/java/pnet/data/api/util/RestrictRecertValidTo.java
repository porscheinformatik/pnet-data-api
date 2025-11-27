package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * Restricts recertification date
 *
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictRecertValidTo<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF recertInvalidAfter(LocalDateTime recertificationDate) {
        return restrict("recertInvalidAfter", recertificationDate);
    }
}
