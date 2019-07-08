package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractCountAggregationDTO;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class CompanyBrandAggregationDTO extends AbstractCountAggregationDTO
{

    private static final long serialVersionUID = -4404355723561616580L;

    public CompanyBrandAggregationDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("count") long count)
    {
        super(matchcode, label, count);
    }

}
