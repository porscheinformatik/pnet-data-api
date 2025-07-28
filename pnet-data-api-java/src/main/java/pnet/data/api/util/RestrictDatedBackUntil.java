package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * When set, it allows to request "historic" data including anything dating back to the specified date.
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface RestrictDatedBackUntil<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF datedBackUntil(LocalDateTime datedBackUntil) {
        return restrict("datedBackUntil", datedBackUntil);
    }

    default SELF datedBackByDays(int days) {
        return datedBackUntil(LocalDateTime.now().minusDays(days));
    }
}
