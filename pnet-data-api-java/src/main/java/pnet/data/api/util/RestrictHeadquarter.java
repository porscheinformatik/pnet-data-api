package pnet.data.api.util;

/**
 * Restricts headquarters.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictHeadquarter<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF headquarter(Integer... headquarterCompanyIds)
    {
        return restrict("headquarter", (Object[]) headquarterCompanyIds);
    }

}
