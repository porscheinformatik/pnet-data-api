package pnet.data.api.proposal;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.WithPersonId;

/**
 * One referenced person of a proposal.
 *
 * @author HAM
 */
@Schema(description = "One referenced person of a proposal.")
public class ProposalPersonLinkDTO implements WithPersonId, Serializable
{

    private static final long serialVersionUID = 4810615779489939325L;

    @Schema(description = "The id of the person.")
    private final Integer personId;

    @Schema(description = "The name of the person.")
    private final String name;

    private final ProposalPersonType type;

    public ProposalPersonLinkDTO(@JsonProperty("personId") Integer personId, @JsonProperty("name") String name,
        @JsonProperty("type") ProposalPersonType type)
    {
        super();

        this.personId = personId;
        this.name = name;
        this.type = type;
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

    public ProposalPersonType getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return String.format("ProposalPersonLinkDTO [personId=%s, name=%s, type=%s]", personId, name, type);
    }
}
