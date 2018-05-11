package pnet.data.api.application;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find for applications
 */
public class ApplicationDataFind extends AbstractFind<ApplicationItemDTO, ApplicationDataFind>
    implements RestrictMatchcode<ApplicationDataFind>, RestrictUpdatedAfter<ApplicationDataFind>
{

    protected ApplicationDataFind(ObjectMapper mapper, FindFunction<ApplicationItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }

}
