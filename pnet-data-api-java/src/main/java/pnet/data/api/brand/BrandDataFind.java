package pnet.data.api.brand;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcodes;
import pnet.data.api.util.RestrictTenants;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find options
 *
 * @author ham
 */
public class BrandDataFind extends AbstractFind<BrandItemDTO, BrandDataFind>
    implements RestrictTenants<BrandDataFind>, RestrictMatchcodes<BrandDataFind>, RestrictUpdatedAfter<BrandDataFind>
{

    public BrandDataFind(ObjectMapper mapper, FindFunction<BrandItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, findFunction, restricts);
    }

}
