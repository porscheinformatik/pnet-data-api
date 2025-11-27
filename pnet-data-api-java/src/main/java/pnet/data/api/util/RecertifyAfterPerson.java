package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * Restricts person types.
 *
 * @param <SELF> the type of the filter for chaining
 */
public interface RecertifyAfterPerson<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF recertificationInvalidAfter(LocalDateTime recertificationDate) {
        return restrict("recertInvalidAfter", recertificationDate);
    }
}
