package pnet.data.api.companynumbertype;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    private final CompanyNumberTypeMatchcode matchcode;

    private Map<Locale, String> labels;
    private LocalDateTime lastUpdate;

    public CompanyNumberTypeDataDTO(@JsonProperty("matchcode") CompanyNumberTypeMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public CompanyNumberTypeMatchcode getMatchcode()
    {
        return matchcode;
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
