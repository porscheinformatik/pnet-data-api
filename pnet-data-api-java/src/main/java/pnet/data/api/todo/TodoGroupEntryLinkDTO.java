package pnet.data.api.todo;

import java.io.Serializable;
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
public class TodoGroupEntryLinkDTO implements Serializable
{
    private static final long serialVersionUID = 7738135458998397744L;

    @ApiModelProperty(notes = "The unique id of the to-do entry.")
    private final Integer id;

    @ApiModelProperty(notes = "The type of the to-do entry.")
    private final String type;

    @ApiModelProperty(notes = "The source of the to-do entry.")
    private final TodoSource source;

    @ApiModelProperty(notes = "The state of the to-do entry.")
    private final TodoState state;

    @ApiModelProperty(notes = "The phase of the to-do entry, that depends directly on the state.")
    private final TodoPhase phase;

    @ApiModelProperty(notes = "The date and time this to-do entry has been created.")
    private final LocalDateTime created;

    @ApiModelProperty(
        notes = "The date and time this to-do entry has been redetected (created again) and possibly updated.")
    private final LocalDateTime refreshed;

    @ApiModelProperty(notes = "The name of the person, that did the approval.")
    private final Collection<Integer> assignedToPersonIds;

    @ApiModelProperty(
        notes = "The date and time, this entry was processed and submitted. Null, if it has not been approved, yet..")
    private final LocalDateTime submitted;

    @ApiModelProperty(notes = "The id of the person, that did the processing and submittion.")
    private final Integer submittedByPersonId;

    @ApiModelProperty(notes = "The name of the person, that did the processing ans submittion.")
    private final String submittedByPersonName;

    @ApiModelProperty(notes = "The earliest date and time, the execution of this entry may start.")
    private final LocalDateTime scheduled;

    @ApiModelProperty(
        notes = "The date and time the execution of this entry has been started. Null, if the execution has not been started, yet.")
    private final LocalDateTime started;

    @ApiModelProperty(
        notes = "The date and time this to-do entry has been finished. Null, it the execution has not been finished, yet.")
    private final LocalDateTime finished;

    @ApiModelProperty(notes = "The headline of the entry.")
    private final String headline;

    @ApiModelProperty(notes = "The description of the entry.")
    private final String description;

    public TodoGroupEntryLinkDTO(@JsonProperty("id") Integer id, @JsonProperty("type") String type,
        @JsonProperty("source") TodoSource source, @JsonProperty("state") TodoState state,
        @JsonProperty("phase") TodoPhase phase, @JsonProperty("created") LocalDateTime created,
        @JsonProperty("refreshed") LocalDateTime refreshed,
        @JsonProperty("assignedToPersonIds") Collection<Integer> assignedToPersonIds,
        @JsonProperty("submitted") LocalDateTime submitted,
        @JsonProperty("submittedByPersonId") Integer submittedByPersonId,
        @JsonProperty("submittedByPersonName") String submittedByPersonName,
        @JsonProperty("scheduled") LocalDateTime scheduled, @JsonProperty("started") LocalDateTime started,
        @JsonProperty("finished") LocalDateTime finished, @JsonProperty("headline") String headline,
        @JsonProperty("description") String description)
    {
        super();

        this.id = id;
        this.type = type;
        this.source = source;
        this.state = state;
        this.phase = phase;
        this.created = created;
        this.refreshed = refreshed;
        this.submitted = submitted;
        this.submittedByPersonId = submittedByPersonId;
        this.submittedByPersonName = submittedByPersonName;
        this.started = started;
        this.finished = finished;
        this.assignedToPersonIds = assignedToPersonIds;
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

    public TodoPhase getPhase()
    {
        return phase;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public LocalDateTime getRefreshed()
    {
        return refreshed;
    }

    public Collection<Integer> getAssignedToPersonIds()
    {
        return assignedToPersonIds;
    }

    public LocalDateTime getSubmitted()
    {
        return submitted;
    }

    public Integer getSubmittedByPersonId()
    {
        return submittedByPersonId;
    }

    public String getSubmittedByPersonName()
    {
        return submittedByPersonName;
    }

    public LocalDateTime getScheduled()
    {
        return scheduled;
    }

    public LocalDateTime getStarted()
    {
        return started;
    }

    public LocalDateTime getFinished()
    {
        return finished;
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
    public String toString()
    {
        return String
            .format(
                "TodoGroupEntryLinkDTO [id=%s, type=%s, source=%s, state=%s, phase=%s, created=%s, refreshed=%s, "
                    + "assignedToPersonIds=%s, submitted=%s, submittedByPersonId=%s, submittedByPersonName=%s, "
                    + "scheduled=%s, started=%s, finished=%s, headline=%s, description=%s]",
                id, type, source, state, phase, created, refreshed, assignedToPersonIds, submitted, submittedByPersonId,
                submittedByPersonName, scheduled, started, finished, headline, description);
    }
}
