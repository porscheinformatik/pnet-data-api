package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts matchcodes.
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictMatchcode<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF matchcode(String... matchcode)
    {
        return restrict("mc", (Object[]) matchcode);
    }

    default SELF matchcodes(Collection<String> matchcodes)
    {
        return matchcode(matchcodes.toArray(new String[matchcodes.size()]));
    }

}
