package pnet.data.api.util;

/**
 * Restricts the BPCM manager flag.
 *
 * @param <SELF> the state of the filter for chaining
 * @author ham
 */
public interface RestrictBpcmManaged<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF bpcmManagedOnly()
    {
        return bpcmManaged(Boolean.TRUE);
    }

    default SELF notBpcmManagedOnly()
    {
        return bpcmManaged(Boolean.FALSE);
    }

    default SELF bpcmManaged(Boolean bpcmManaged)
    {
        return restrict("bpcmManaged", bpcmManaged);
    }

}
