package pnet.data.api.activity;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.SearchFunction;

/**
 * Search for {@link ActivityDataClient}.
 *
 * @author ham
 */
public class ActivityDataSearch extends AbstractSearch<ActivityItemDTO, ActivityDataSearch>
    implements RestrictTenant<ActivityDataSearch>, RestrictBrand<ActivityDataSearch>,
    RestrictCompanyType<ActivityDataSearch>, RestrictContractType<ActivityDataSearch>
{

    public ActivityDataSearch(ObjectMapper mapper, SearchFunction<ActivityItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }

}
