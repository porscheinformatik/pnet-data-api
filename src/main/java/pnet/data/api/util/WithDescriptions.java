package pnet.data.api.util;

import java.util.Locale;
import java.util.Map;

/**
 * Used to mark DTOs with descriptions
 *
 * @author ham
 */
public interface WithDescriptions
{

    /**
     * @return A map of strings by locale, holding the description of the item in multiple languages.
     */
    Map<Locale, String> getDescriptions();

    /**
     * @param language the language, may be null
     * @return The description in the specified language, null if not found.
     */
    default String getDescription(Locale language)
    {
        return Utils.getText(language, getDescriptions());
    }

}
