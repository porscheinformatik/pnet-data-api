package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractCountAggregateDTO;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class PersonActivityAggregateDTO extends AbstractCountAggregateDTO
{

    public PersonActivityAggregateDTO(@JsonProperty("activity") String matchcode, @JsonProperty("count") long count)
    {
        super(matchcode, count);
    }

}
