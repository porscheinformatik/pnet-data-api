package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractCountAggregationDTO;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class CompanyContractTypeAggregationDTO extends AbstractCountAggregationDTO
{

    private static final long serialVersionUID = -7503894792809235564L;

    public CompanyContractTypeAggregationDTO(@JsonProperty("matchcode") String matchcode,
        @JsonProperty("label") String label, @JsonProperty("count") long count)
    {
        super(matchcode, label, count);
    }

}
