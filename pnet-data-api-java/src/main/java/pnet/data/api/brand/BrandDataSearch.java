package pnet.data.api.brand;

import java.util.List;
import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.SearchFunction;

/**
 * Search options
 *
 * @author ham
 */
public class BrandDataSearch
    extends AbstractSearch<BrandItemDTO, BrandDataSearch>
    implements RestrictTenant<BrandDataSearch> {

    public BrandDataSearch(SearchFunction<BrandItemDTO> searchFunction, List<Pair<String, Object>> restricts) {
        super(searchFunction, restricts);
    }
}
