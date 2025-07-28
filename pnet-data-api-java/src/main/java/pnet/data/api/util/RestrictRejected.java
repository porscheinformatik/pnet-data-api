package pnet.data.api.util;

/**
 * Restricts rejects.
 *
 * @param <SELF> the state of the filter for chaining
 * @author ham
 */
public interface RestrictRejected<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF rejectedOnly() {
        return rejected(Boolean.TRUE);
    }

    default SELF notRejectedOnly() {
        return rejected(Boolean.FALSE);
    }

    default SELF rejected(Boolean rejected) {
        return restrict("rejected", rejected);
    }
}
