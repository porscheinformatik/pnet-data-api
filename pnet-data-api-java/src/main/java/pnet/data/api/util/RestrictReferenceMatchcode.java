package pnet.data.api.util;

/**
 * Restricts the reference matchcode.
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictReferenceMatchcode<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF referenceMatchcode(String... referenceMatchcode)
    {
        return restrict("referenceMatchcode", (Object[]) referenceMatchcode);
    }

}
