package pnet.data.api.todo;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Holds one to-do.
 *
 * @author HAM
 */
@ApiModel(description = "One to-do within a group of to-dos.")
public class TodoGroupEntryLinkDTO
{

    @ApiModelProperty(notes = "The unique id of the to-do entry.")
    private final Integer id;

    @ApiModelProperty(notes = "The type of the to-do entry.")
    private final String type;

    @ApiModelProperty(notes = "The source of the to-do entry.")
    private final TodoSource source;

    @ApiModelProperty(notes = "The state of the to-do entry.")
    private final TodoState state;

    @ApiModelProperty(notes = "The date and time this to-do entry has been created.")
    private final LocalDateTime created;

    @ApiModelProperty(
        notes = "The date and time the execution of this entry has been started. Null if the execution has not been started, yes.")
    private final LocalDateTime started;

    @ApiModelProperty(notes = "The date and time, this entry was approved. Null if it has not been approved, yet..")
    private final LocalDateTime approved;

    @ApiModelProperty(notes = "The id of the person, that did the approval.")
    private final Integer approvedByPersonId;

    @ApiModelProperty(notes = "The name of the person, that did the approval.")
    private final String approvedByPersonName;

    @ApiModelProperty(notes = "The name of the person, that did the approval.")
    private final Collection<Integer> assignedToPersonIds;

    @ApiModelProperty(notes = "True if the entry has been rejected.")
    private final boolean rejected;

    @ApiModelProperty(notes = "The earliest date and time, the execution of this entry may start.")
    private final LocalDateTime scheduled;

    @ApiModelProperty(notes = "The headline of the entry.")
    private final String headline;

    @ApiModelProperty(notes = "The description of the entry.")
    private final String description;

    public TodoGroupEntryLinkDTO(@JsonProperty("id") Integer id, @JsonProperty("type") String type,
        @JsonProperty("source") TodoSource source, @JsonProperty("state") TodoState state,
        @JsonProperty("created") LocalDateTime created, @JsonProperty("started") LocalDateTime started,
        @JsonProperty("approved") LocalDateTime approved,
        @JsonProperty("approvedByPersonId") Integer approvedByPersonId,
        @JsonProperty("approvedByPersonName") String approvedByPersonName,
        @JsonProperty("assignedToPersonIds") Collection<Integer> assignedToPersonIds,
        @JsonProperty("rejected") boolean rejected, @JsonProperty("scheduled") LocalDateTime scheduled,
        @JsonProperty("headline") String headline, @JsonProperty("description") String description)
    {
        super();

        this.id = id;
        this.type = type;
        this.source = source;
        this.state = state;
        this.created = created;
        this.started = started;
        this.approved = approved;
        this.approvedByPersonId = approvedByPersonId;
        this.approvedByPersonName = approvedByPersonName;
        this.assignedToPersonIds = assignedToPersonIds;
        this.rejected = rejected;
        this.scheduled = scheduled;
        this.headline = headline;
        this.description = description;
    }

    public Integer getId()
    {
        return id;
    }

    public String getType()
    {
        return type;
    }

    public TodoSource getSource()
    {
        return source;
    }

    public TodoState getState()
    {
        return state;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public LocalDateTime getStarted()
    {
        return started;
    }

    public LocalDateTime getApproved()
    {
        return approved;
    }

    public Integer getApprovedByPersonId()
    {
        return approvedByPersonId;
    }

    public String getApprovedByPersonName()
    {
        return approvedByPersonName;
    }

    public Collection<Integer> getAssignedToPersonIds()
    {
        return assignedToPersonIds;
    }

    public boolean isRejected()
    {
        return rejected;
    }

    public LocalDateTime getScheduled()
    {
        return scheduled;
    }

    public String getHeadline()
    {
        return headline;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof TodoGroupEntryLinkDTO))
        {
            return false;
        }
        TodoGroupEntryLinkDTO other = (TodoGroupEntryLinkDTO) obj;
        if (id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        }
        else if (!id.equals(other.id))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String
            .format("TodoGroupEntryLinkDTO [id=%s, type=%s, source=%s, state=%s, created=%s, started=%s, approved=%s, "
                + "approvedByPersonId=%s, approvedByPersonName=%s, assignedToPersonIds=%s, rejected=%s, scheduled=%s, "
                + "headline=%s, description=%s]", id, type, source, state, created, started, approved,
                approvedByPersonId, approvedByPersonName, assignedToPersonIds, rejected, scheduled, headline,
                description);
    }

}
