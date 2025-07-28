package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the reference matchcode.
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface RestrictReferenceMatchcode<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF referenceMatchcode(String... referenceMatchcode) {
        return restrict("referenceMatchcode", (Object[]) referenceMatchcode);
    }

    default SELF referenceMatchcodes(Collection<String> referenceMatchcodes) {
        return referenceMatchcode(referenceMatchcodes.toArray(new String[0]));
    }
}
