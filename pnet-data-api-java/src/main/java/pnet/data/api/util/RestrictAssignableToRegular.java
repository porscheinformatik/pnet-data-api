package pnet.data.api.util;

/**
 * Restricts whether the item is assignable to regular users.
 *
 * @param <SELF> the type of the restrict for chaining
 * @author TechScar
 */
public interface RestrictAssignableToRegular<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    public default SELF assignableToRegular(Boolean assignableToRegular) {
        return this.restrict("assignableToRegular", assignableToRegular);
    }
}
