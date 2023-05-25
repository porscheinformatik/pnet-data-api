package pnet.data.api.todo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithScore;

/**
 * A group of to-dos
 *
 * @author HAM
 */
@Schema(description = "Holds to-dos with the same category and reference id.")
public class TodoGroupItemDTO implements WithLastUpdate, WithScore, Serializable
{

    private static final long serialVersionUID = 725799282920048805L;

    @Schema(description = "The category of to-dos, this groups is holding.")
    private final TodoCategory category;

    @Schema(description = "The label of the category.")
    private final String categoryLabel;

    @Schema(description = "The reference ID of all to-dos in this group.")
    private final Integer referenceId;

    @Schema(description = "The reference matchcode of all to-dos in this group.")
    private final String referenceMatchcode;

    @Schema(description = "The label of the group, decribing the referenced item.")
    private final String label;

    @Schema(description = "The persons that are references in the various to-do entries.")
    private final Collection<TodoGroupPersonLinkDTO> persons;

    @Schema(description = "All to-do entries in this group.")
    private final Collection<TodoGroupEntryLinkDTO> entries;

    @Schema(description = "The time and date when this item has been changed recently.")
    private final LocalDateTime lastUpdate;

    @Schema(description = "The score this item accomplished in the search operation.")
    private final double score;

    public TodoGroupItemDTO(@JsonProperty("category") TodoCategory category,
        @JsonProperty("categoryLabel") String categoryLabel, @JsonProperty("referenceId") Integer referenceId,
        @JsonProperty("referenceMatchcode") String referenceMatchcode, @JsonProperty("label") String label,
        @JsonProperty("persons") Collection<TodoGroupPersonLinkDTO> persons,
        @JsonProperty("entries") Collection<TodoGroupEntryLinkDTO> entries,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate, @JsonProperty("score") double score)
    {
        super();
        this.category = category;
        this.categoryLabel = categoryLabel;
        this.referenceId = referenceId;
        this.referenceMatchcode = referenceMatchcode;
        this.label = label;
        this.persons = persons;
        this.entries = entries;
        this.lastUpdate = lastUpdate;
        this.score = score;
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

    public String getReferenceMatchcode()
    {
        return referenceMatchcode;
    }

    public String getLabel()
    {
        return label;
    }

    public Collection<TodoGroupPersonLinkDTO> getPersons()
    {
        return persons;
    }

    public Optional<TodoGroupPersonLinkDTO> findPerson(Predicate<? super TodoGroupPersonLinkDTO> predicate)
    {
        return persons == null ? Optional.empty() : persons.stream().filter(predicate).findFirst();
    }

    public Collection<TodoGroupEntryLinkDTO> getEntries()
    {
        return entries;
    }

    public Optional<TodoGroupEntryLinkDTO> findEntry(Predicate<? super TodoGroupEntryLinkDTO> predicate)
    {
        return entries == null ? Optional.empty() : entries.stream().filter(predicate).findFirst();
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public double getScore()
    {
        return score;
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "TodoGroupItemDTO [category=%s, categoryLabel=%s, referenceId=%s, referenceMatchcode=%s, label=%s, "
                    + "persons=%s, entries=%s, lastUpdate=%s, score=%s]",
                category, categoryLabel, referenceId, referenceMatchcode, label, persons, entries, lastUpdate, score);
    }

}
