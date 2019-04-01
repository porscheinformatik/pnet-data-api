package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractCountAggregateDTO;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class PersonFunctionAggregateDTO extends AbstractCountAggregateDTO
{

    public PersonFunctionAggregateDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("count") long count)
    {
        super(matchcode, count);
    }

}
