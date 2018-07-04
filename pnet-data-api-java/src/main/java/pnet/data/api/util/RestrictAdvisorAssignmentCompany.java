package pnet.data.api.util;

/**
 * Restricts the companies of the advisor assigments.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictAdvisorAssignmentCompany<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF advisorAssignmentCompany(Integer... advisorAssignmentCompanyIds)
    {
        return restrict("advisorAssignmentCompany", (Object[]) advisorAssignmentCompanyIds);
    }

}
