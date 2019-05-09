package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the companies of the advisor assignments.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictAdvisorAssignmentCompanyNumber<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF advisorAssignmentCompanyNumber(String... advisorAssignmentCompanyNumbers)
    {
        return restrict("advisorAssignmentCompanyNumber", (Object[]) advisorAssignmentCompanyNumbers);
    }

    default SELF advisorAssignmentCompanyNumbers(Collection<String> advisorAssignmentCompanyNumberMatchcodes)
    {
        return advisorAssignmentCompanyNumber(advisorAssignmentCompanyNumberMatchcodes
            .toArray(new String[advisorAssignmentCompanyNumberMatchcodes.size()]));
    }

}
