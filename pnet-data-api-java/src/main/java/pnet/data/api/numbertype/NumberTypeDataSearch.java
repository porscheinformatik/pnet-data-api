package pnet.data.api.numbertype;

import java.util.List;
import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * @author cet
 */
public class NumberTypeDataSearch extends AbstractSearch<NumberTypeItemDTO, NumberTypeDataSearch> {

    public NumberTypeDataSearch(
        SearchFunction<NumberTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restricts
    ) {
        super(searchFunction, restricts);
    }
}
