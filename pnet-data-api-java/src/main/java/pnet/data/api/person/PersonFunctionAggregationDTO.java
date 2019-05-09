package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractCountAggregationDTO;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class PersonFunctionAggregationDTO extends AbstractCountAggregationDTO
{

    public PersonFunctionAggregationDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("count") long count)
    {
        super(matchcode, count);
    }

}
