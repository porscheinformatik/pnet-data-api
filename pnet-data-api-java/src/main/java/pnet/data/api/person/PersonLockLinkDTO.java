package pnet.data.api.person;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a lock on a person's login that was created either manually or automatically. This record does not
 * represent softlocks or hardlocks resulting from failed login attempts. Only explicit locks are captured by this DTO.
 */
public class PersonLockLinkDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final PersonLockType type;
    private final LocalDateTime lockedAt;
    private final Integer lockedByGpId;

    public PersonLockLinkDTO(@JsonProperty("type") PersonLockType type,
        @JsonProperty("lockedAt") LocalDateTime lockedAt, @JsonProperty("lockedByGpId") Integer lockedByGpId)
    {
        this.type = type;
        this.lockedAt = lockedAt;
        this.lockedByGpId = lockedByGpId;
    }

    public PersonLockType getType()
    {
        return type;
    }

    public LocalDateTime getLockedAt()
    {
        return lockedAt;
    }

    public Integer getLockedByGpId()
    {
        return lockedByGpId;
    }

    @Override
    public String toString()
    {
        return String
            .format("%s[type=%s, lockedAt=%s, lockedByGpId=%s]", getClass().getSimpleName(), type, lockedAt,
                lockedByGpId);
    }
}
