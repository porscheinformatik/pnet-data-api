package pnet.data.api.util;

import java.time.LocalDateTime;

/**
 * When set, it allows to request "historic" data including anything dating back to the specified date.
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictDatedBackUntil<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF datedBackUntil(LocalDateTime datedBackUntil)
    {
        return restrict("datedBackUntil", datedBackUntil);
    }

}
