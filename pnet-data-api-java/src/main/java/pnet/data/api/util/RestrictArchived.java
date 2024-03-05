package pnet.data.api.util;

/**
 * Restricts archivation flags.
 *
 * @param <SELF> the state of the filter for chaining
 * @author ham
 */
public interface RestrictArchived<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF archivedOnly()
    {
        return archived(Boolean.TRUE);
    }

    default SELF notArchivedOnly()
    {
        return archived(Boolean.FALSE);
    }

    default SELF archived(Boolean archived)
    {
        return restrict("archived", archived);
    }

}
