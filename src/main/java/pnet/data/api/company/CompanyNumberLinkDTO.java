package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.companynumbertype.CompanyNumberTypeMatchcode;

/**
 * Holds an additional company number.
 *
 * @author ham
 */
public class CompanyNumberLinkDTO
{

    private final CompanyNumberTypeMatchcode companyNumberTypeMatchcode;
    private final String number;

    public CompanyNumberLinkDTO(@JsonProperty("numberType") CompanyNumberTypeMatchcode companyNumberTypeMatchcode,
        @JsonProperty("number") String number)
    {
        super();

        this.companyNumberTypeMatchcode = companyNumberTypeMatchcode;
        this.number = number;
    }

    public CompanyNumberTypeMatchcode getCompanyNumberTypeMatchcode()
    {
        return companyNumberTypeMatchcode;
    }

    public String getNumber()
    {
        return number;
    }

    @Override
    public String toString()
    {
        return String.format("%s [number=%s]", super.toString(), number);
    }

}
