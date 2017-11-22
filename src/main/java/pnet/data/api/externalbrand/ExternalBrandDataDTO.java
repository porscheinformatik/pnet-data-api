package pnet.data.api.externalbrand;

import java.time.LocalDateTime;

import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an external brand.
 *
 * @author ham
 */
public class ExternalBrandDataDTO implements WithMatchcode<ExternalBrandMatchcode>, WithLastUpdate
{

    private final ExternalBrandMatchcode matchcode;

    private String id;
    private String label;
    private LocalDateTime lastUpdate;

    public ExternalBrandDataDTO(ExternalBrandMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public ExternalBrandMatchcode getMatchcode()
    {
        return matchcode;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    @Override
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
