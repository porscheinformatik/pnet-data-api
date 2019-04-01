package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractCountAggregateDTO;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class CompanyBrandAggregateDTO extends AbstractCountAggregateDTO
{

    public CompanyBrandAggregateDTO(@JsonProperty("matchcode") String matchcode,
        @JsonProperty("count") long count)
    {
        super(matchcode, count);
    }

}
