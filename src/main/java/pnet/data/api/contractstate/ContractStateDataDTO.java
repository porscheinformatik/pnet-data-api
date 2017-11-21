package pnet.data.api.contractstate;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.companytype.CompanyTypeMatchcode;

/**
 * Holds a contract state used for contract types
 *
 * @author ham
 */
public class ContractStateDataDTO
{

    private CompanyTypeMatchcode matchcode;
    private Map<Locale, String> labels;
    private LocalDateTime lastUpdate;

    public CompanyTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(CompanyTypeMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

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
        return String.format("ContractStateDataDTO [matchcode=%s, labels=%s, lastUpdate=%s]", matchcode, labels,
            lastUpdate);
    }

}
