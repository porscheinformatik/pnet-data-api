package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the companies of the advisor assignments.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictAdvisorAssignmentCompanyId<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF advisorAssignmentCompany(Integer... advisorAssignmentCompanyIds)
    {
        return restrict("advisorAssignmentCompanyId", (Object[]) advisorAssignmentCompanyIds);
    }

    default SELF advisorAssignmentCompanys(Collection<Integer> advisorAssignmentCompanyMatchcodes)
    {
        return advisorAssignmentCompany(
            advisorAssignmentCompanyMatchcodes.toArray(new Integer[advisorAssignmentCompanyMatchcodes.size()]));
    }

}
