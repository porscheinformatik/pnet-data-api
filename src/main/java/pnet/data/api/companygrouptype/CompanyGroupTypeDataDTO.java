package pnet.data.api.companygrouptype;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a company group type. The company group type is used for company groups.
 *
 * @author ham
 */
public class CompanyGroupTypeDataDTO implements WithMatchcode<CompanyGroupTypeMatchcode>, WithLabels, WithLastUpdate
{

    private final CompanyGroupTypeMatchcode matchcode;

    private Map<Locale, String> labels;
    private LocalDateTime lastUpdate;

    public CompanyGroupTypeDataDTO(@JsonProperty("matchcode") CompanyGroupTypeMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public CompanyGroupTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    @Override
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
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
        return String.format("CompanyTypeDataDTO [matchcode=%s, labels=%s]", matchcode, labels);
    }

}
