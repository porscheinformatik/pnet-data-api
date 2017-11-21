package pnet.data.api.activity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.brand.WithTenantsAndBrandLinks;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a activity. This object contains only minimal information and is used as result of search operations and
 * reference items.
 *
 * @author ham
 */
// FIXME the tenants should be bases on company types!
public class ActivityItemDTO implements WithMatchcode<ActivityMatchcode>, WithTenantsAndBrandLinks, WithLastUpdate
{

    private final ActivityMatchcode matchcode;
    private final String label;
    private final String description;
    private final Collection<ActivityBrandLinkDTO> brands;
    private final LocalDateTime lastUpdate;

    public ActivityItemDTO(@JsonProperty("matchcode") ActivityMatchcode matchcode, @JsonProperty("label") String label,
        @JsonProperty("description") String description,
        @JsonProperty("brands") Collection<ActivityBrandLinkDTO> brands,
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
    public ActivityMatchcode getMatchcode()
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
    public Collection<ActivityBrandLinkDTO> getBrands()
    {
        return brands;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

}
