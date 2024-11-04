package pnet.data.api.util;

/**
 * Restricts whether the item is assignable to privileged users.
 *
 * @param <SELF> the type of the restrict for chaining
 * @author TechScar
 */
public interface RestrictAssignableToPrivileged<SELF extends Restrict<SELF>> extends Restrict<SELF>
{
    default public SELF assignableToPrivileged(Boolean assignableToPrivileged)
    {
        return this.restrict("assignableToPrivileged", assignableToPrivileged);
    }
}
