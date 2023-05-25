package pnet.data.api.todo;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.WithPersonId;

/**
 * One referenced person of a to-do group.
 *
 * @author HAM
 */
@Schema(description = "One referenced person of a to-do group.")
public class TodoGroupPersonLinkDTO implements WithPersonId, Serializable
{

    private static final long serialVersionUID = -4823204025294192488L;

    @Schema(description = "The id of the person.")
    private final Integer personId;

    @Schema(description = "The name of the person.")
    private final String name;

    @Schema(description = "True, if the person has some open work to do in this group.")
    private final boolean assigned;

    public TodoGroupPersonLinkDTO(@JsonProperty("personId") Integer personId, @JsonProperty("name") String name,
        @JsonProperty("assigned") boolean assigned)
    {
        super();

        this.personId = personId;
        this.name = name;
        this.assigned = assigned;
    }

    @Override
    public Integer getPersonId()
    {
        return personId;
    }

    public String getName()
    {
        return name;
    }

    public boolean isAssigned()
    {
        return assigned;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(assigned, name, personId);
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
        if (!(obj instanceof TodoGroupPersonLinkDTO))
        {
            return false;
        }
        TodoGroupPersonLinkDTO other = (TodoGroupPersonLinkDTO) obj;
        if (assigned != other.assigned)
        {
            return false;
        }
        if (!Objects.equals(name, other.name))
        {
            return false;
        }
        if (!Objects.equals(personId, other.personId))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("TodoGroupPersonLinkDTO [personId=%s, name=%s, assigned=%s]", personId, name, assigned);
    }
}
