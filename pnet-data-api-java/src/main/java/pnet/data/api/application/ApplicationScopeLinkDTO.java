package pnet.data.api.application;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class ApplicationScopeLinkDTO
{
    @ApiModelProperty(notes = "The unique matchcode of the scope.")
    private final String matchcode;

    @ApiModelProperty(notes = "The label of the scope.")
    private final Map<Locale, String> labels;

    @ApiModelProperty(notes = "The description of the scope.")
    private final Map<Locale, String> descriptions;

    @ApiModelProperty(notes = "The matchcodes of all categories, this scope belongs to.")
    private final Collection<String> categoryMatchcodes;

    public ApplicationScopeLinkDTO(@JsonProperty("matchcode") String matchcode,
        @JsonProperty("labels") Map<Locale, String> labels,
        @JsonProperty("descriptions") Map<Locale, String> descriptions,
        @JsonProperty("categoryMatchcodes") Collection<String> categoryMatchcodes)
    {
        super();
        this.matchcode = matchcode;
        this.labels = labels;
        this.descriptions = descriptions;
        this.categoryMatchcodes = categoryMatchcodes;
    }

    public String getMatchcode()
    {
        return matchcode;
    }

    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    public Map<Locale, String> getDescriptions()
    {
        return descriptions;
    }

    public Collection<String> getCategoryMatchcodes()
    {
        return categoryMatchcodes;
    }

    @Override
    public String toString()
    {
        return String
            .format("ApplicationScopeLinkDTO [matchcode=%s, labels=%s, descriptions=%s, categoryMatchcodes=%s]",
                matchcode, labels, descriptions, categoryMatchcodes);
    }
}
