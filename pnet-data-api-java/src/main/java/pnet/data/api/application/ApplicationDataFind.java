package pnet.data.api.application;

import java.util.List;
import pnet.data.api.util.AbstractScrollableFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find for applications
 */
public class ApplicationDataFind
    extends AbstractScrollableFind<ApplicationItemDTO, ApplicationDataFind>
    implements RestrictMatchcode<ApplicationDataFind>, RestrictUpdatedAfter<ApplicationDataFind> {

    public ApplicationDataFind(FindFunction<ApplicationItemDTO> searchFunction, List<Pair<String, Object>> restricts) {
        super(searchFunction, restricts);
    }
}
