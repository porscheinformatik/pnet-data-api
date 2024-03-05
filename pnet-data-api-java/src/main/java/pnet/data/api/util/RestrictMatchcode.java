package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.util.Collection;

/**
 * Restricts matchcodes.
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface RestrictMatchcode<SELF extends Restrict<SELF>> extends Restrict<SELF>
{
    default SELF matchcode(String... matchcode)
    {
        return restrict(MATCHCODE_KEY, (Object[]) matchcode);
    }

    default SELF matchcodes(Collection<String> matchcodes)
    {
        return matchcode(matchcodes.toArray(new String[0]));
    }
}
