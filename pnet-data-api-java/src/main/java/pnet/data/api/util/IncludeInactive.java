package pnet.data.api.util;

/**
 * When set, the result contains inactive persons/companies
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface IncludeInactive<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF includeInactive()
    {
        return restrict("includeInactive", true);
    }

}
