package pnet.data.api.numbertype;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;

/**
 * @author cet
 *
 */
public class NumberTypeDataGet extends AbstractGet<NumberTypeDataDTO, NumberTypeDataGet>
    implements ByMatchcode<NumberTypeDataDTO, NumberTypeDataGet>
{

    public NumberTypeDataGet(GetFunction<NumberTypeDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

}
