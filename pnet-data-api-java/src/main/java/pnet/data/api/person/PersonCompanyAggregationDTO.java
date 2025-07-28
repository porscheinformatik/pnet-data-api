package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class PersonCompanyAggregationDTO implements Serializable {

    private static final long serialVersionUID = -2588191893345417781L;

    private final Integer companyId;
    private final String companyMatchcode;
    private final String companyNumber;
    private final String companyLabel;
    private final long count;

    public PersonCompanyAggregationDTO(
        @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("companyLabel") String companyLabel,
        @JsonProperty("count") long count
    ) {
        super();
        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.companyLabel = companyLabel;

        this.count = count;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyMatchcode() {
        return companyMatchcode;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public String getCompanyLabel() {
        return companyLabel;
    }

    public String getCompanyLabelWithNumber() {
        return PnetDataApiUtils.toCompanyLabelWithNumber(companyNumber, companyLabel);
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getCompanyLabelWithNumber(), count);
    }
}
