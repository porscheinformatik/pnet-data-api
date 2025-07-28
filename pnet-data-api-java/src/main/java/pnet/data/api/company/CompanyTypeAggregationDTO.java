package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import pnet.data.api.util.AbstractCountAggregationDTO;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class CompanyTypeAggregationDTO extends AbstractCountAggregationDTO {

    private static final long serialVersionUID = 2298705805398989182L;

    public CompanyTypeAggregationDTO(
        @JsonProperty("matchcode") String matchcode,
        @JsonProperty("label") String label,
        @JsonProperty("count") long count
    ) {
        super(matchcode, label, count);
    }
}
