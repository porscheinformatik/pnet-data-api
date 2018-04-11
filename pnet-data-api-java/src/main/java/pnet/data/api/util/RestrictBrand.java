package pnet.data.api.util;

/**
 * Restricts brands
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictBrand<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF brand(String... brandMatchcodes)
    {
        return restrict("b", (Object[]) brandMatchcodes);
    }

}
