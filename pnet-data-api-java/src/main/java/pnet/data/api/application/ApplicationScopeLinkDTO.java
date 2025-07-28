package pnet.data.api.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import pnet.data.api.util.WithDescriptions;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithMatchcode;

public class ApplicationScopeLinkDTO implements WithMatchcode, WithLabels, WithDescriptions, Serializable {

    @Serial
    private static final long serialVersionUID = -8772509501184236662L;

    @Schema(description = "The unique matchcode of the scope.")
    private final String matchcode;

    @Schema(description = "The label of the scope.")
    private final Map<Locale, String> labels;

    @Schema(description = "The description of the scope.")
    private final Map<Locale, String> descriptions;

    @Schema(description = "The matchcodes of all categories, this scope belongs to.")
    private final Collection<String> categoryMatchcodes;

    public ApplicationScopeLinkDTO(
        @JsonProperty("matchcode") String matchcode,
        @JsonProperty("labels") Map<Locale, String> labels,
        @JsonProperty("descriptions") Map<Locale, String> descriptions,
        @JsonProperty("categoryMatchcodes") Collection<String> categoryMatchcodes
    ) {
        super();
        this.matchcode = matchcode;
        this.labels = labels;
        this.descriptions = descriptions;
        this.categoryMatchcodes = categoryMatchcodes;
    }

    @Override
    public String getMatchcode() {
        return matchcode;
    }

    @Override
    public Map<Locale, String> getLabels() {
        return labels;
    }

    @Override
    public Map<Locale, String> getDescriptions() {
        return descriptions;
    }

    public Collection<String> getCategoryMatchcodes() {
        return categoryMatchcodes;
    }

    @Override
    public String toString() {
        return String.format(
            "ApplicationScopeLinkDTO [matchcode=%s, labels=%s, descriptions=%s, categoryMatchcodes=%s]",
            matchcode,
            labels,
            descriptions,
            categoryMatchcodes
        );
    }
}
