package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts personnel department.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictPersonnelDepartment<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF personnelDepartment(String... personnelDepartments)
    {
        return restrict("personnelDepartment", (Object[]) personnelDepartments);
    }

    default SELF personnelDepartments(Collection<String> personnelDepartments)
    {
        return personnelDepartment(personnelDepartments.toArray(new String[personnelDepartments.size()]));
    }

}
