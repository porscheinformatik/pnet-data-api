package pnet.data.api.person;

import java.util.List;

import pnet.data.api.util.AbstractScrollableFind;
import pnet.data.api.util.CompanyMergable;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.IncludeAllFunctions;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Orderable;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictAdvisorAssignmentCompany;
import pnet.data.api.util.RestrictAdvisorAssignmentCompanyId;
import pnet.data.api.util.RestrictAdvisorAssignmentCompanyNumber;
import pnet.data.api.util.RestrictAdvisorAssignmentDivision;
import pnet.data.api.util.RestrictAdvisorAssignmentType;
import pnet.data.api.util.RestrictApproved;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompany;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictControllingArea;
import pnet.data.api.util.RestrictCostCenter;
import pnet.data.api.util.RestrictCredentialsAvailable;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictEmail;
import pnet.data.api.util.RestrictExternalId;
import pnet.data.api.util.RestrictFunction;
import pnet.data.api.util.RestrictGuid;
import pnet.data.api.util.RestrictId;
import pnet.data.api.util.RestrictNumber;
import pnet.data.api.util.RestrictNumberType;
import pnet.data.api.util.RestrictNumbersType;
import pnet.data.api.util.RestrictPersonnelDepartment;
import pnet.data.api.util.RestrictPersonnelNumber;
import pnet.data.api.util.RestrictPreferredUserId;
import pnet.data.api.util.RestrictRole;
import pnet.data.api.util.RestrictSupervisorPersonnelNumber;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictUpdatedAfter;
import pnet.data.api.util.Scrollable;

/**
 * Find interface for persons.
 *
 * @author ham
 */
public class PersonDataFind extends AbstractScrollableFind<PersonItemDTO, PersonDataFind>
    implements RestrictId<Integer, PersonDataFind>, RestrictExternalId<PersonDataFind>, RestrictTenant<PersonDataFind>,
    RestrictGuid<PersonDataFind>, RestrictPreferredUserId<PersonDataFind>, RestrictEmail<PersonDataFind>,
    RestrictCostCenter<PersonDataFind>, RestrictPersonnelNumber<PersonDataFind>,
    RestrictSupervisorPersonnelNumber<PersonDataFind>, RestrictControllingArea<PersonDataFind>,
    RestrictPersonnelDepartment<PersonDataFind>, RestrictCompanyId<PersonDataFind>,
    RestrictCompanyNumber<PersonDataFind>, RestrictCompany<PersonDataFind>, RestrictBrand<PersonDataFind>,
    RestrictFunction<PersonDataFind>, RestrictActivity<PersonDataFind>, RestrictRole<PersonDataFind>,
    RestrictNumberType<PersonDataFind>, RestrictNumbersType<PersonDataFind>, RestrictNumber<PersonDataFind>,
    RestrictAdvisorAssignmentCompanyId<PersonDataFind>, RestrictAdvisorAssignmentCompanyNumber<PersonDataFind>,
    RestrictAdvisorAssignmentCompany<PersonDataFind>, RestrictAdvisorAssignmentType<PersonDataFind>,
    RestrictAdvisorAssignmentDivision<PersonDataFind>, RestrictCredentialsAvailable<PersonDataFind>,
    RestrictApproved<PersonDataFind>, RestrictUpdatedAfter<PersonDataFind>, RestrictDatedBackUntil<PersonDataFind>,
    IncludeInactive<PersonDataFind>, IncludeAllFunctions<PersonDataFind>, CompanyMergable<PersonDataFind>,
    Orderable<PersonDataFind, PersonOrderBy>, Scrollable<PersonDataFind>
{
    public PersonDataFind(FindFunction<PersonItemDTO> findFunction, List<Pair<String, Object>> restrictItems)
    {
        super(findFunction, restrictItems);
    }
}