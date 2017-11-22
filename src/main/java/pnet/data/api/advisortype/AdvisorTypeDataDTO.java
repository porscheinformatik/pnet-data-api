package pnet.data.api.advisortype;

import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithDescriptions;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an advisor type.
 *
 * @author ham
 */
public class AdvisorTypeDataDTO implements WithMatchcode<AdvisorTypeMatchcode>, WithLabels, WithDescriptions
{

    private final AdvisorTypeMatchcode matchcode;

    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;

    public AdvisorTypeDataDTO(@JsonProperty("matchcode") AdvisorTypeMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public AdvisorTypeMatchcode getMatchcode()
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
    public Map<Locale, String> getDescriptions()
    {
        return descriptions;
    }

    public void setDescriptions(Map<Locale, String> descriptions)
    {
        this.descriptions = descriptions;
    }

    @Override
    public String toString()
    {
        return String.format("AdvisorTypeDataDTO [matchcode=%s, labels=%s, descriptions=%s]", matchcode, labels,
            descriptions);
    }

}
