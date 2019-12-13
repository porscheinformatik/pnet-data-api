package pnet.data.api.legalform;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * @author ham
 */
public class LegalFormDataSearch extends AbstractSearch<LegalFormItemDTO, LegalFormDataSearch>
{
    public LegalFormDataSearch(SearchFunction<LegalFormItemDTO> searchFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }
}
