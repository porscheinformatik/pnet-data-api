package pnet.data.api.numbertype;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithLabel;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithScore;

/**
 * Holds a number type for the search.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about a number type")
public class NumberTypeItemDTO implements WithMatchcode, WithLabel, WithLastUpdate, WithScore, Serializable
{

    private static final long serialVersionUID = -902938475261504086L;

    @ApiModelProperty(notes = "The unique matchcode of the number type.")
    private final String matchcode;

    @ApiModelProperty(notes = "The label of the number type in the requested language.")
    private final String label;

    @ApiModelProperty(notes = "The time and date when this item has been changed recently.")
    private final LocalDateTime lastUpdate;

    @ApiModelProperty(notes = "The score this item accomplished in the search operation.")
    private final double score;

    public NumberTypeItemDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate, @JsonProperty("score") double score)
    {
        super();
        this.matchcode = matchcode;
        this.label = label;
        this.lastUpdate = lastUpdate;
        this.score = score;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    @Override
    public String getLabel()
    {
        return label;
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
            .format("NumberTypeItemDTO [matchcode=%s, label=%s, lastUpdate=%s, score=%s]", matchcode, label, lastUpdate,
                score);
    }

}
