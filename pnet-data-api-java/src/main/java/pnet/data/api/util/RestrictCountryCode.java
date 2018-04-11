package pnet.data.api.util;

/**
 * Restricts country codes.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictCountryCode<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF countryCode(String... countryCodes)
    {
        return restrict("countryCode", (Object[]) countryCodes);
    }

}
