package pnet.data.api.about;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds information about the Partner.Net Data API.
 *
 * @author ham
 */
public class AboutDataDTO
{

    private final String partnerNetVersion;
    private final String dataApiVersion;

    public AboutDataDTO(@JsonProperty("partnerNetVersion") String partnerNetVersion,
        @JsonProperty("dataApiVersion") String dataApiVersion)
    {
        super();

        this.partnerNetVersion = partnerNetVersion;
        this.dataApiVersion = dataApiVersion;
    }

    /**
     * @return the version of the Partner.Net
     */
    public String getPartnerNetVersion()
    {
        return partnerNetVersion;
    }

    /**
     * @return the version of the Partner.Net Data API
     */
    public String getDataApiVersion()
    {
        return dataApiVersion;
    }

}
