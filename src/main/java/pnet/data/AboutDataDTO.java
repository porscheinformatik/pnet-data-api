package pnet.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AboutDataDTO
{

    private final String version;

    public AboutDataDTO(@JsonProperty("version") String version)
    {
        super();

        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }

}
