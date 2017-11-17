package pnet.data.api.advisortype;

import java.util.Locale;
import java.util.Map;

/**
 * Holds an advisor type.
 *
 * @author ham
 */
public class AdvisorTypeDataDTO
{

    private AdvisorTypeMatchcode matchcode;
    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;

    public AdvisorTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(AdvisorTypeMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

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
