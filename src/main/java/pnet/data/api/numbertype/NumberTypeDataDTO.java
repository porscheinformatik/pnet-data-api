package pnet.data.api.numbertype;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a number type.
 *
 * @author ham
 */
public class NumberTypeDataDTO implements WithMatchcode<NumberTypeMatchcode>, WithLabels, WithLastUpdate
{

    private final NumberTypeMatchcode matchcode;

    private Map<Locale, String> labels;
    private LocalDateTime lastUpdate;

    public NumberTypeDataDTO(NumberTypeMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public NumberTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    @Override
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

    @Override
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
        return String.format("NumberTypeDataDTO [matchcode=%s, labels=%s]", matchcode, labels);
    }

}
