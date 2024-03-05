package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the companies of the advisor assignments.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictAdvisorAssignmentCompany<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF advisorAssignmentCompany(String... advisorAssignmentCompanyMatchcodes)
    {
        return restrict("advisorAssignmentCompany", (Object[]) advisorAssignmentCompanyMatchcodes);
    }

    default SELF advisorAssignmentCompanies(Collection<String> advisorAssignmentCompanyMatchcodes)
    {
        return advisorAssignmentCompany(
            advisorAssignmentCompanyMatchcodes.toArray(new String[0]));
    }

}
