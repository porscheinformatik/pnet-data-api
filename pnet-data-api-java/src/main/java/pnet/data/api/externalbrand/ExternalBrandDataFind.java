package pnet.data.api.externalbrand;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcodes;
import pnet.data.api.util.RestrictUpdatedAfter;

public class ExternalBrandDataFind extends AbstractFind<ExternalBrandItemDTO, ExternalBrandDataFind>
    implements RestrictMatchcodes<ExternalBrandDataFind>, RestrictUpdatedAfter<ExternalBrandDataFind>
{

    public ExternalBrandDataFind(ObjectMapper mapper, FindFunction<ExternalBrandItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, findFunction, restricts);
    }

}
