package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.Objects;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.WithCompanyId;

/**
 * Abstract base class for number references
 *
 * @author ham
 */
public abstract class AbstractNumberTypeLinkDTO extends AbstractLinkDTO implements WithCompanyId {

    @Serial
    private static final long serialVersionUID = 5799256161583182636L;

    @Schema(description = "The id of the company the person has the number at.")
    protected final Integer companyId;

    @Schema(description = "The matchcode of the company the person has the number at.")
    protected final String companyMatchcode;

    @Schema(description = "The number of the company the person has the number at.")
    protected final String companyNumber;

    @Schema(description = "The actual number, that fits the number type.")
    protected final String number;

    public AbstractNumberTypeLinkDTO(
        @JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode,
        @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("number") String number
    ) {
        super(tenant, matchcode);
        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.number = number;
    }

    @JsonPropertyDescription("A tenant where the number type is valid")
    @Override
    public String getTenant() {
        return super.getTenant();
    }

    @JsonPropertyDescription("The unique matchcode of the number type")
    @Override
    public String getMatchcode() {
        return super.getMatchcode();
    }

    @Override
    public Integer getCompanyId() {
        return companyId;
    }

    @Override
    public String getCompanyMatchcode() {
        return companyMatchcode;
    }

    @Override
    public String getCompanyNumber() {
        return companyNumber;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(companyId);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof AbstractNumberTypeLinkDTO other)) {
            return false;
        }
        return Objects.equals(companyId, other.companyId);
    }

    @Override
    public String toString() {
        return String.format(
            "AbstractNumberTypeLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s, number=%s, tenant=%s, " +
            "matchcode=%s]",
            companyId,
            companyMatchcode,
            companyNumber,
            number,
            tenant,
            matchcode
        );
    }
}
