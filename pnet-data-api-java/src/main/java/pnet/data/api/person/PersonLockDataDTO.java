package pnet.data.api.person;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a lock on a person's login that was created either manually or automatically. This record does not
 * represent softlocks or hardlocks resulting from failed login attempts. Only explicit locks are captured by this DTO.
 */
public record PersonLockDataDTO(PersonLockType type, LocalDateTime lockedAt, Integer lockedByGpId)
    implements Serializable
{
}
