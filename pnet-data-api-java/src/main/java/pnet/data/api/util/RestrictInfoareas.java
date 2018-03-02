package pnet.data.api.util;

/**
 * Restricts infoareas.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictInfoareas<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF infoarea(String... infoareaMatchcodes)
    {
        return restrict("infoarea", (Object[]) infoareaMatchcodes);
    }

}
