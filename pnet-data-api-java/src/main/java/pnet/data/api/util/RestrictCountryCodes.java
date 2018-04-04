package pnet.data.api.util;

/**
 * Restricts country codes.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictCountryCodes<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF countryCodes(String... countryCodes)
    {
        return restrict("countryCode", (Object[]) countryCodes);
    }

}
