package pnet.data.api.company;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.companynumbertype.CompanyNumberTypeMatchcode;

/**
 * Holds an additional company number.
 *
 * @author ham
 */
public class CompanyNumberDataDTO
{

    private final CompanyNumberTypeMatchcode matchcode;
    private final String number;
    private final LocalDateTime lastUpdated;

    public CompanyNumberDataDTO(@JsonProperty("matchcode") CompanyNumberTypeMatchcode matchcode,
        @JsonProperty("number") String number, @JsonProperty("lastUpdated") LocalDateTime lastUpdated)
    {
        super();

        this.matchcode = matchcode;
        this.number = number;
        this.lastUpdated = lastUpdated;
    }

    public CompanyNumberTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    public String getNumber()
    {
        return number;
    }

    public LocalDateTime getLastUpdated()
    {
        return lastUpdated;
    }

    @Override
    public String toString()
    {
        return String.format("%s [number=%s, lastUpdated=%s]", super.toString(), number, lastUpdated);
    }

}
