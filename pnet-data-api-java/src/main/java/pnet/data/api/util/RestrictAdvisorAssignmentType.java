package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the types of the advisor assignments.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictAdvisorAssignmentType<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF advisorAssignmentType(String... advisorAssignmentTypeMatchcodes) {
        return restrict("advisorAssignmentType", (Object[]) advisorAssignmentTypeMatchcodes);
    }

    default SELF advisorAssignmentTypes(Collection<String> advisorAssignmentTypeMatchcodes) {
        return advisorAssignmentType(advisorAssignmentTypeMatchcodes.toArray(new String[0]));
    }

    @Deprecated
    default SELF advisorType(String... advisorAssignmentTypeMatchcodes) {
        return restrict("advisorAssignmentType", (Object[]) advisorAssignmentTypeMatchcodes);
    }

    @Deprecated
    default SELF advisorTypes(Collection<String> advisorAssignmentTypeMatchcodes) {
        return advisorType(advisorAssignmentTypeMatchcodes.toArray(new String[0]));
    }
}
