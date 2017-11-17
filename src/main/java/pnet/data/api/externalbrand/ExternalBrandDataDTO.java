package pnet.data.api.externalbrand;

import java.time.LocalDateTime;

/**
 * Holds an external brand.
 *
 * @author ham
 */
public class ExternalBrandDataDTO
{

    private String id;
    private ExternalBrandMatchcode matchcode;
    private String label;
    private LocalDateTime lastUpdate;

    public ExternalBrandDataDTO()
    {
        super();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return The unique, alpha-numeric key of the company type. The key is the same in all environments.
     */
    public ExternalBrandMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(ExternalBrandMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    /**
     * @return The date/time of the last update to this item.
     */
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format("ExternalBrandDataDTO [id=%s, matchcode=%s, label=%s, lastUpdate=%s]", id, matchcode,
            label, lastUpdate);
    }

}
