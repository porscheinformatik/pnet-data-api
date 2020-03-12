package pnet.data.api.proposal;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithDescription;
import pnet.data.api.util.WithLabel;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithScore;

/**
 * Proposals.
 */
@ApiModel(description = "Holds all information about a proposal")
public class ProposalItemDTO
    implements WithMatchcode, WithLabel, WithDescription, WithLastUpdate, WithScore, Serializable
{

    private static final long serialVersionUID = -2521027633935213916L;

    private final Integer proposalId;
    private final String qualifiedId;
    private final String matchcode;
    private final ProposalType type;
    private final String label;
    private final String description;
    private final String url;
    private final String urlQa;
    private final ProposalState state;
    private final boolean rejected;
    private final boolean archived;
    private final LocalDateTime targetDate;
    private final ProposalTargetDateType targetDateType;
    private final LocalDateTime assignedDate;
    private final LocalDateTime testedDate;
    private final List<ProposalPersonLinkDTO> persons;
    private final LocalDateTime lastUpdate;

    @ApiModelProperty(notes = "The score this item accomplished in the search operation.")
    private final double score;

    public ProposalItemDTO(@JsonProperty("proposalId") Integer proposalId,
        @JsonProperty("qualifiedId") String qualifiedId, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("type") ProposalType type, @JsonProperty("label") String label,
        @JsonProperty("description") String description, @JsonProperty("url") String url,
        @JsonProperty("urlQa") String urlQa, @JsonProperty("state") ProposalState state,
        @JsonProperty("rejected") boolean rejected, @JsonProperty("archived") boolean archived,
        @JsonProperty("targetDate") LocalDateTime targetDate,
        @JsonProperty("targetDateType") ProposalTargetDateType targetDateType,
        @JsonProperty("assignedDate") LocalDateTime assignedDate, @JsonProperty("testedDate") LocalDateTime testedDate,
        @JsonProperty("persons") List<ProposalPersonLinkDTO> persons,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate, @JsonProperty("score") double score)
    {
        super();
        this.proposalId = proposalId;
        this.qualifiedId = qualifiedId;
        this.matchcode = matchcode;
        this.type = type;
        this.label = label;
        this.description = description;
        this.url = url;
        this.urlQa = urlQa;
        this.state = state;
        this.rejected = rejected;
        this.archived = archived;
        this.targetDate = targetDate;
        this.targetDateType = targetDateType;
        this.assignedDate = assignedDate;
        this.testedDate = testedDate;
        this.persons = persons;
        this.lastUpdate = lastUpdate;
        this.score = score;
    }

    public Integer getProposalId()
    {
        return proposalId;
    }

    public String getQualifiedId()
    {
        return qualifiedId;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    public ProposalType getType()
    {
        return type;
    }

    @Override
    public String getLabel()
    {
        return label;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    public String getUrl()
    {
        return url;
    }

    public String getUrlQa()
    {
        return urlQa;
    }

    public ProposalState getState()
    {
        return state;
    }

    public boolean isRejected()
    {
        return rejected;
    }

    public boolean isArchived()
    {
        return archived;
    }

    public LocalDateTime getTargetDate()
    {
        return targetDate;
    }

    public ProposalTargetDateType getTargetDateType()
    {
        return targetDateType;
    }

    public LocalDateTime getAssignedDate()
    {
        return assignedDate;
    }

    public LocalDateTime getTestedDate()
    {
        return testedDate;
    }

    public List<ProposalPersonLinkDTO> getPersons()
    {
        return persons;
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
            .format("ProposalItemDTO [proposalId=%s, qualifiedId=%s, matchcode=%s, type=%s, label=%s, description=%s, "
                + "url=%s, urlQa=%s, state=%s, rejected=%s, archived=%s, targetDate=%s, targetDateType=%s, assignedDate=%s, "
                + "testedDate=%s, persons=%s, lastUpdate=%s, score=%s]", proposalId, qualifiedId, matchcode, type,
                label, description, url, urlQa, state, rejected, archived, targetDate, targetDateType, assignedDate,
                testedDate, persons, lastUpdate, score);
    }

}
