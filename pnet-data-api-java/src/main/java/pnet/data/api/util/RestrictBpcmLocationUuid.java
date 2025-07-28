package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts BPCM location uuid.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictBpcmLocationUuid<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF bpcmLocationUuid(String... bpcmLocationUuids) {
        return restrict("bpcmLocationUuid", (Object[]) bpcmLocationUuids);
    }

    default SELF bpcmLocationUuids(Collection<String> bpcmLocationUuids) {
        return bpcmLocationUuid(bpcmLocationUuids.toArray(new String[0]));
    }
}
