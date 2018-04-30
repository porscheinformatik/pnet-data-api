package pnet.data.api.externalbrand;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
<<<<<<< Updated upstream
 * Find interface for the {@link ExternalBrandDataClient}.
=======
 * @author cet
 *
>>>>>>> Stashed changes
 */
public class ExternalBrandDataFind extends AbstractFind<ExternalBrandItemDTO, ExternalBrandDataFind>
    implements RestrictMatchcode<ExternalBrandDataFind>, RestrictUpdatedAfter<ExternalBrandDataFind>
{

    public ExternalBrandDataFind(ObjectMapper mapper, FindFunction<ExternalBrandItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, findFunction, restricts);
    }

}
