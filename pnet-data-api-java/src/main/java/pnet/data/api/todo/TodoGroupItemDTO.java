package pnet.data.api.todo;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A group of to-dos
 *
 * @author HAM
 */
@ApiModel(description = "Holds to-dos with the same category and reference id.")
public class TodoGroupItemDTO
{

    @ApiModelProperty(notes = "The category of to-dos, this groups is holding.")
    private final TodoCategory category;

    @ApiModelProperty(notes = "The label of the category.")
    private final String categoryLabel;

    @ApiModelProperty(notes = "The reference ID of all to-dos in this group.")
    private final Integer referenceId;

    @ApiModelProperty(notes = "The label of the group, decribing the referenced item.")
    private final String label;

    @ApiModelProperty(notes = "The persons that are references in the various to-do entries.")
    private final Collection<TodoGroupPersonLinkDTO> persons;

    @ApiModelProperty(notes = "All to-do entries in this group.")
    private final Collection<TodoGroupEntryLinkDTO> entries;

    @ApiModelProperty(
        notes = "The exact date and time of the last time this group or one of the to-do entries have been modified.")
    private final LocalDateTime lastUpdate;

    public TodoGroupItemDTO(@JsonProperty("category") TodoCategory category,
        @JsonProperty("categoryLabel") String categoryLabel, @JsonProperty("referenceId") Integer referenceId,
        @JsonProperty("label") String label, @JsonProperty("persons") Collection<TodoGroupPersonLinkDTO> persons,
        @JsonProperty("entries") Collection<TodoGroupEntryLinkDTO> entries,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.category = category;
        this.categoryLabel = categoryLabel;
        this.referenceId = referenceId;
        this.label = label;
        this.persons = persons;
        this.entries = entries;
        this.lastUpdate = lastUpdate;
    }

    public TodoCategory getCategory()
    {
        return category;
    }

    public String getCategoryLabel()
    {
        return categoryLabel;
    }

    public Integer getReferenceId()
    {
        return referenceId;
    }

    public String getLabel()
    {
        return label;
    }

    public Collection<TodoGroupPersonLinkDTO> getPersons()
    {
        return persons;
    }

    public Collection<TodoGroupEntryLinkDTO> getEntries()
    {
        return entries;
    }

    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "TodoGroupItemDTO [category=%s, categoryLabel=%s, referenceId=%s, label=%s, persons=%s, entries=%s, "
                    + "lastUpdate=%s]",
                category, categoryLabel, referenceId, label, persons, entries, lastUpdate);
    }
}
