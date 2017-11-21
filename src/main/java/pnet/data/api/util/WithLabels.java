package pnet.data.api.util;

import java.util.Locale;
import java.util.Map;

/**
 * Used to mark DTOs with labels
 *
 * @author ham
 */
public interface WithLabels
{

    /**
     * @return A map of strings by locale, holding the label of the item in multiple languages.
     */
    Map<Locale, String> getLabels();

    /**
     * @param language the language, may be null
     * @return The label in the specified language, null if not found.
     */
    default String getLabel(Locale language)
    {
        return Utils.getText(language, getLabels());
    }

}
