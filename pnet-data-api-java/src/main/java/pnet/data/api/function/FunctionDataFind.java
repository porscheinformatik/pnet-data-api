package pnet.data.api.function;

import java.util.List;
import pnet.data.api.util.AbstractScrollableFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictApprovalNeeded;
import pnet.data.api.util.RestrictAssignableToPrivileged;
import pnet.data.api.util.RestrictAssignableToRegular;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictNumberType;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictUpdatedAfter;
import pnet.data.api.util.RestrictVisibility;

/**
 * Find interface for {@link FunctionItemDTO}s
 *
 * @author HAM
 */
public class FunctionDataFind
    extends AbstractScrollableFind<FunctionItemDTO, FunctionDataFind>
    implements
        RestrictMatchcode<FunctionDataFind>,
        RestrictTenant<FunctionDataFind>,
        RestrictBrand<FunctionDataFind>,
        RestrictCompanyType<FunctionDataFind>,
        RestrictContractType<FunctionDataFind>,
        RestrictActivity<FunctionDataFind>,
        RestrictNumberType<FunctionDataFind>,
        RestrictVisibility<FunctionDataFind>,
        RestrictAssignableToRegular<FunctionDataFind>,
        RestrictAssignableToPrivileged<FunctionDataFind>,
        RestrictApprovalNeeded<FunctionDataFind>,
        RestrictUpdatedAfter<FunctionDataFind>,
        IncludeInactive<FunctionDataFind> {

    public FunctionDataFind(FindFunction<FunctionItemDTO> findFunction, List<Pair<String, Object>> restricts) {
        super(findFunction, restricts);
    }
}
