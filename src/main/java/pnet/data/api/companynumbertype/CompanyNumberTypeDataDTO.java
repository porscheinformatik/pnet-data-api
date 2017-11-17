package pnet.data.api.companynumbertype;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.util.Utils;

/**
 * Holds a company number type. A company has one or more company number.
 *
 * @author ham
 */
public class CompanyNumberTypeDataDTO
{

    private CompanyNumberTypeMatchcode matchcode;
    private Map<Locale, String> labels;
    private LocalDateTime lastUpdate;

    public CompanyNumberTypeDataDTO()
    {
        super();
    }

    /**
     * @return The unique, alpha-numeric key of the company number type. The key is the same in all environments.
     */
    public CompanyNumberTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(CompanyNumberTypeMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    /**
     * @return A map of strings by locale, holding the label of the company number type in multiple languages.
     */
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    /**
     * @param language the language, may be null
     * @return The label in the specified language, null if not found.
     */
    public String getLabel(Locale language)
    {
        return Utils.getText(language, labels);
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

    /**
     * @return The date/time of the last update to this item.
     */
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
        return String.format("CompanyNumberTypeDataDTO [matchcode=%s, labels=%s, lastUpdate=%s]", matchcode, labels,
            lastUpdate);
    }

}
