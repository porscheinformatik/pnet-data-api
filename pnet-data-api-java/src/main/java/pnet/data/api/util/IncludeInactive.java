package pnet.data.api.util;

/**
 * When set, the result contains inactive persons/companies
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface IncludeInactive<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF includeInactive()
    {
        return restrict("includeInactive", true);
    }

}
