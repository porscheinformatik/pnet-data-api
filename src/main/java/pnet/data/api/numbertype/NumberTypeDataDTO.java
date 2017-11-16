package pnet.data.api.numbertype;

import java.util.Locale;
import java.util.Map;

import pnet.data.api.util.Utils;

/**
 * Holds a number type.
 *
 * @author ham
 */
public class NumberTypeDataDTO
{

    private NumberTypeMatchcode matchcode;
    private Map<Locale, String> labels;

    public NumberTypeDataDTO()
    {
        super();
    }

    /**
     * @return The unique, alpha-numeric key of the item. The key is the same on all environments.
     */
    public NumberTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(NumberTypeMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    /**
     * @return A map of strings by locale holding the label of the item in multiple languages.
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

}
