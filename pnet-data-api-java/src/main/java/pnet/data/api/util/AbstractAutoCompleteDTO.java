package pnet.data.api.util;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;

/**
 * Holds the result of an autoComplete query.
 *
 * @author HAM
 */
@Schema(description = "Holds the result of an autoComplete query.")
public abstract class AbstractAutoCompleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6206196144565929843L;

    @Schema(description = "The context dependent label of the item.")
    private final String label;

    @Schema(description = "The context dependent description of the item.")
    private final String description;

    @Schema(description = "The score this item accomplished in the search operation.")
    private final double score;

    public AbstractAutoCompleteDTO(String label, String description, double score) {
        super();
        this.label = label;
        this.description = description;
        this.score = score;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public double getScore() {
        return score;
    }
}
