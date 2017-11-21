package pnet.data.api.companynumbertype;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a company number type. The company number type is the type of a company number used to number companies.
 *
 * @author ham
 */
public class CompanyNumberTypeDataDTO implements WithMatchcode<CompanyNumberTypeMatchcode>, WithLabels, WithLastUpdate
{

    private CompanyNumberTypeMatchcode matchcode;
    private Map<Locale, String> labels;
    private LocalDateTime lastUpdate;

    public CompanyNumberTypeDataDTO()
    {
        super();
    }

    @Override
    public CompanyNumberTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(CompanyNumberTypeMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    @Override
    public Map<Locale, String> getLabels()
    {
        return labels;
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
        return String.format("CompanyNumberTypeDataDTO [matchcode=%s, labels=%s, lastUpdate=%s]", matchcode, labels,
            lastUpdate);
    }

}
