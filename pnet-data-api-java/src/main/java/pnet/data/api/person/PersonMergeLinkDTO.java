package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "A link to a person that has been merged into another person.")
public class PersonMergeLinkDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "The GP-ID of the person that was deleted by the merge.")
    private final Integer deletedPersonId;

    @Schema(description = "The external ID of the person that was deleted by the merge.")
    private final String deletedPersonExternalId;

    @Schema(description = "The time and date when the merge was last changed.")
    private final LocalDateTime lastUpdate;

    public PersonMergeLinkDTO(
        @JsonProperty("deletedPersonId") Integer deletedPersonId,
        @JsonProperty("deletedPersonExternalId") String deletedPersonExternalId,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate
    ) {
        this.deletedPersonId = deletedPersonId;
        this.deletedPersonExternalId = deletedPersonExternalId;
        this.lastUpdate = lastUpdate;
    }

    public Integer getDeletedPersonId() {
        return deletedPersonId;
    }

    public String getDeletedPersonExternalId() {
        return deletedPersonExternalId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
