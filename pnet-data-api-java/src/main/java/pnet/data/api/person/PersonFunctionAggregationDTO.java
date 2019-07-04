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

    private static final long serialVersionUID = 4349997034345067288L;

    public PersonFunctionAggregationDTO(@JsonProperty("matchcode") String matchcode,
        @JsonProperty("label") String label, @JsonProperty("count") long count)
    {
        super(matchcode, label, count);
    }

}
