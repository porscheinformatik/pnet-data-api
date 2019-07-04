package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractCountAggregationDTO;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class PersonActivityAggregationDTO extends AbstractCountAggregationDTO
{

    private static final long serialVersionUID = -423997707872307217L;

    public PersonActivityAggregationDTO(@JsonProperty("activity") String matchcode, @JsonProperty("label") String label,
        @JsonProperty("count") long count)
    {
        super(matchcode, label, count);
    }

}
