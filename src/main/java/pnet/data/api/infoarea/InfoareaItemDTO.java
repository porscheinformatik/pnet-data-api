package pnet.data.api.infoarea;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.brand.WithTenantsAndBrandLinks;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an infoarea. This object contains only minimal information and is used as result of search operations and
 * reference items.
 *
 * @author ham
 */
public class InfoareaItemDTO implements WithMatchcode<InfoareaMatchcode>, WithTenantsAndBrandLinks, WithLastUpdate
{

    private final InfoareaMatchcode matchcode;
    private final String label;
    private final String description;
    private final Collection<InfoareaBrandLinkDTO> brands;
    private final LocalDateTime lastUpdate;

    public InfoareaItemDTO(@JsonProperty("matchcode") InfoareaMatchcode matchcode, @JsonProperty("label") String label,
        @JsonProperty("description") String description,
        @JsonProperty("brands") Collection<InfoareaBrandLinkDTO> brands,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();

        this.matchcode = Objects.requireNonNull(matchcode, "Matchcode is null");
        this.label = Objects.requireNonNull(label, "Label is null");
        this.description = description;
        this.brands = Collections.unmodifiableCollection(Objects.requireNonNull(brands, "Brands are null"));
        this.lastUpdate = lastUpdate;
    }

    @Override
    public InfoareaMatchcode getMatchcode()
    {
        return matchcode;
    }

    /**
     * @return The label in the requested language.
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @return The description in the requested language.
     */
    public String getDescription()
    {
        return description;
    }

    @Override
    public Collection<InfoareaBrandLinkDTO> getBrands()
    {
        return brands;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format("InfoareaItemDTO [matchcode=%s, label=%s, description=%s, brands=%s, lastUpdate=%s]",
            matchcode, label, description, brands, lastUpdate);
    }

}
