package pnet.data.api.numbertype;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * @author cet
 */
public class NumberTypeDataFind extends AbstractFind<NumberTypeItemDTO, NumberTypeDataFind>
    implements RestrictMatchcode<NumberTypeDataFind>, RestrictUpdatedAfter<NumberTypeDataFind>
{

    public NumberTypeDataFind(FindFunction<NumberTypeItemDTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }

}
