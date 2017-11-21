package pnet.data.api.advisordivision;

import java.util.Locale;
import java.util.Map;

/**
 * Holds an advisor type.
 *
 * @author ham
 */
public class AdvisorDivisonDataDTO
{

    private AdvisorDivisionMatchcode matchcode;
    private Map<Locale, String> labels;
    private Map<Locale, String> descriptions;

    public AdvisorDivisionMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(AdvisorDivisionMatchcode matchcode)
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
