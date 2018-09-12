package pnet.data.api.util;

/**
 * Enables scrolling
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictScroll<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF scroll()
    {
        return restrict("scroll", true);
    }

}
