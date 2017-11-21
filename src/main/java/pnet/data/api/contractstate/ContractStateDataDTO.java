package pnet.data.api.contractstate;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a contract state used for contract types
 *
 * @author ham
 */
public class ContractStateDataDTO implements WithMatchcode<ContractStateMatchcode>, WithLabels, WithLastUpdate
{

    private ContractStateMatchcode matchcode;
    private Map<Locale, String> labels;
    private LocalDateTime lastUpdate;

    @Override
    public ContractStateMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(ContractStateMatchcode matchcode)
    {
        this.matchcode = matchcode;
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
        return String.format("ContractStateDataDTO [matchcode=%s, labels=%s, lastUpdate=%s]", matchcode, labels,
            lastUpdate);
    }

}
