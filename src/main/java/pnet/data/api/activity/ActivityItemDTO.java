package pnet.data.api.activity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.brand.BrandLinkBasedMultiTenancy;
import pnet.data.api.brand.BrandLinkDTO;

/**
 * Holds a activity. This object contains only minimal information and is used as result of search operations and
 * reference items.
 *
 * @author ham
 */
public class ActivityItemDTO implements BrandLinkBasedMultiTenancy
{

    private final ActivityMatchcode matchcode;
    private final String label;
    private final String description;
    private final Collection<BrandLinkDTO> brands;
    private final LocalDateTime lastUpdate;

    public ActivityItemDTO(@JsonProperty("matchcode") ActivityMatchcode matchcode, @JsonProperty("label") String label,
        @JsonProperty("description") String description, @JsonProperty("brands") Collection<BrandLinkDTO> brands,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();

        this.matchcode = Objects.requireNonNull(matchcode, "Matchcode is null");
        this.label = Objects.requireNonNull(label, "Label is null");
        this.description = description;
        this.brands = Collections.unmodifiableCollection(Objects.requireNonNull(brands, "Brands are null"));
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return The unique, alpha-numeric key of the item. This matchcode is the same on all environments.
     */
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

    /**
     * @return The brands for this item. These brands are used for calculating the tenants, too.
     */
    @Override
    public Collection<BrandLinkDTO> getBrands()
    {
        return brands;
    }

    /**
     * @return The date/time of the last update to this item.
     */
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

}
