package pnet.data.api.externalbrand;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * <<<<<<< Updated upstream Search interface for the {@link ExternalBrandDataClient}. =======
 *
 * @author cet
 *
 *         >>>>>>> Stashed changes
 */
public class ExternalBrandDataSearch extends AbstractSearch<ExternalBrandItemDTO, ExternalBrandDataSearch>
{
    public ExternalBrandDataSearch(SearchFunction<ExternalBrandItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }
}
