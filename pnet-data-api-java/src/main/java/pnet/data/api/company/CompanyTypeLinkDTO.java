package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Objects;
import pnet.data.api.util.WithMatchcode;

/**
 * A link to a company type
 *
 * @author HAM
 */
@Schema(
    description = "Holds minimal information about a type of the company. The matchcode fits the matchcodes of " +
    "the company types interface."
)
public class CompanyTypeLinkDTO implements WithMatchcode, Serializable {

    private static final long serialVersionUID = -6736388715804866171L;

    @Schema(description = "The matchcode of the type (fits the matchcodes of the company types interface).")
    private final String matchcode;

    public CompanyTypeLinkDTO(@JsonProperty("matchcode") String matchcode) {
        super();
        this.matchcode = matchcode;
    }

    @Override
    public String getMatchcode() {
        return matchcode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchcode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CompanyTypeLinkDTO other)) {
            return false;
        }
        if (!Objects.equals(matchcode, other.matchcode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s", matchcode);
    }
}
