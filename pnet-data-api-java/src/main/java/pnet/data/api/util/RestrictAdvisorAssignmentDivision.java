package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the divisions of the advisor assignments.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictAdvisorAssignmentDivision<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF advisorAssignmentDivision(String... advisorAssignmentDivisionMatchcodes)
    {
        return restrict("advisorAssignmentDivision", (Object[]) advisorAssignmentDivisionMatchcodes);
    }

    default SELF advisorAssignmentDivisions(Collection<String> advisorAssignmentDivisionMatchcodes)
    {
        return advisorAssignmentDivision(
            advisorAssignmentDivisionMatchcodes.toArray(new String[0]));
    }

}
