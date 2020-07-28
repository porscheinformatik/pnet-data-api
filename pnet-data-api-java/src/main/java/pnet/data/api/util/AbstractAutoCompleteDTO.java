package pnet.data.api.util;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Holds the result of an autoComplete query.
 *
 * @author HAM
 */
@ApiModel(description = "Holds the result of an autoComplete query.")
public abstract class AbstractAutoCompleteDTO implements Serializable
{

    private static final long serialVersionUID = -6206196144565929843L;

    @ApiModelProperty(notes = "The context dependent label of the item.")
    private final String label;

    @ApiModelProperty(notes = "The context dependent description of the item.")
    private final String description;

    @ApiModelProperty(notes = "The score this item accomplished in the search operation.")
    private final double score;

    public AbstractAutoCompleteDTO(String label, String description, double score)
    {
        super();
        this.label = label;
        this.description = description;
        this.score = score;
    }

    public String getLabel()
    {
        return label;
    }

    public String getDescription()
    {
        return description;
    }

    public double getScore()
    {
        return score;
    }

}
