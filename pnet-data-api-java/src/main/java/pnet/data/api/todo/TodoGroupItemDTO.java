package pnet.data.api.todo;

import java.time.LocalDateTime;
import java.util.Collection;

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
    private TodoCategory category;

    @ApiModelProperty(notes = "The reference ID of all to-dos in this group.")
    private Integer referenceId;

    @ApiModelProperty(notes = "The label of the group.")
    private String label;

    @ApiModelProperty(notes = "The headline of the group.")
    private String headline;

    @ApiModelProperty(notes = "The persons that are references in the various to-do entries.")
    private Collection<TodoGroupPersonLinkDTO> persons;

    @ApiModelProperty(notes = "All to-do entries in this group.")
    private Collection<TodoGroupEntryLinkDTO> entries;

    @ApiModelProperty(
        notes = "The exact date and time of the last time this group or one of the to-do entries have been modified.")
    private LocalDateTime lastUpdate;

    public TodoCategory getCategory()
    {
        return category;
    }

    public void setCategory(TodoCategory category)
    {
        this.category = category;
    }

    public Integer getReferenceId()
    {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId)
    {
        this.referenceId = referenceId;
    }

    public Collection<TodoGroupPersonLinkDTO> getPersons()
    {
        return persons;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getHeadline()
    {
        return headline;
    }

    public void setHeadline(String headline)
    {
        this.headline = headline;
    }

    public void setPersons(Collection<TodoGroupPersonLinkDTO> persons)
    {
        this.persons = persons;
    }

    public Collection<TodoGroupEntryLinkDTO> getEntries()
    {
        return entries;
    }

    public void setEntries(Collection<TodoGroupEntryLinkDTO> entries)
    {
        this.entries = entries;
    }

    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "TodoGroupDataDTO [category=%s, referenceId=%s, label=%s, headline=%s, persons=%s, entries=%s, lastUpdate=%s]",
                category, referenceId, label, headline, persons, entries, lastUpdate);
    }

}
