package pnet.data.api.todo;

import java.time.LocalDateTime;

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
        notes = "The date and time the execution of this item has been started. Null if the execution has not been started, yes.")
    private final LocalDateTime started;

    @ApiModelProperty(notes = "True if the item has been rejected.")
    private final boolean rejected;

    @ApiModelProperty(notes = "True if the item has been archived.")
    private final boolean archived;

    @ApiModelProperty(notes = "The earliest date and time, the execution of this item may start.")
    private final LocalDateTime scheduled;

    @ApiModelProperty(notes = "The headline of the item.")
    private final String headline;

    @ApiModelProperty(notes = "The description of the item.")
    private final String description;

    public TodoGroupEntryLinkDTO(@JsonProperty("id") Integer id, @JsonProperty("type") String type,
        @JsonProperty("source") TodoSource source, @JsonProperty("state") TodoState state,
        @JsonProperty("created") LocalDateTime created, @JsonProperty("started") LocalDateTime started,
        @JsonProperty("rejected") boolean rejected, @JsonProperty("archived") boolean archived,
        @JsonProperty("scheduled") LocalDateTime scheduled, @JsonProperty("headline") String headline,
        @JsonProperty("description") String description)
    {
        super();

        this.id = id;
        this.type = type;
        this.source = source;
        this.state = state;
        this.created = created;
        this.started = started;
        this.rejected = rejected;
        this.archived = archived;
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

    public boolean isRejected()
    {
        return rejected;
    }

    public boolean isArchived()
    {
        return archived;
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
            .format(
                "TodoGroupItemLinkDTO [id=%s, type=%s, source=%s, state=%s, created=%s, started=%s, rejected=%s,"
                    + " archived=%s, scheduled=%s, headline=%s, description=%s]",
                id, type, source, state, created, started, rejected, archived, scheduled, headline, description);
    }

}
