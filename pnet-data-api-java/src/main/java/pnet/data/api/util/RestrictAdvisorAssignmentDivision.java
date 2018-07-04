package pnet.data.api.util;

/**
 * Restricts the divisions of the advisor assignments.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictAdvisorAssignmentDivision<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF advisorAssignmentDivision(String... advisorAssignmentDivisionMatchcodes)
    {
        return restrict("advisorAssignmentDivision", (Object[]) advisorAssignmentDivisionMatchcodes);
    }

}
