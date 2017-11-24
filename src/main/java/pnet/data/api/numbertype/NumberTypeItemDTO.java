package pnet.data.api.numbertype;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a number type for the search.
 *
 * @author ham
 */
public class NumberTypeItemDTO implements WithMatchcode<NumberTypeMatchcode>, WithLastUpdate
{

    private final NumberTypeMatchcode matchcode;
    private final String label;
    private final LocalDateTime lastUpdate;

    public NumberTypeItemDTO(@JsonProperty("matchcode") NumberTypeMatchcode matchcode,
        @JsonProperty("label") String label, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.matchcode = matchcode;
        this.label = label;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public NumberTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

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
    public String toString()
    {
        return String.format("NumberTypeItemDTO [matchcode=%s, label=%s, lastUpdate=%s]", matchcode, label, lastUpdate);
    }

}
