package pnet.data.api.util;

/**
 * Restricts the companies of the advisor assignments.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictAdvisorAssignmentCompany<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF advisorAssignmentCompany(String... advisorAssignmentCompanyMatchcodes)
    {
        return restrict("advisorAssignmentCompany", (Object[]) advisorAssignmentCompanyMatchcodes);
    }

}
