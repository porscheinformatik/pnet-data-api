package pnet.data.api.person;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.WithTenant;

/**
 * Known settings of the user.
 *
 * @author HAM
 */
@Schema(description = "Holds the settings of the person per tenant.")
public class PersonSettingsLinkDTO implements WithTenant, Serializable
{
    @Serial
    private static final long serialVersionUID = -8062595231537086982L;

    @Schema(description = "The tenant these settings are defined for.")
    private final String tenant;

    @Schema(description = "The id of the preferred company.")
    private final Integer preferredCompanyId;

    @Schema(description = "The id of the preferred brand.")
    private final String preferredBrandId;

    public PersonSettingsLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("preferredCompanyId") Integer preferredCompanyId,
        @JsonProperty("preferredBrandId") String preferredBrandId)
    {
        super();
        this.tenant = tenant;
        this.preferredCompanyId = preferredCompanyId;
        this.preferredBrandId = preferredBrandId;
    }

    @Override
    public String getTenant()
    {
        return tenant;
    }

    public Integer getPreferredCompanyId()
    {
        return preferredCompanyId;
    }

    public String getPreferredBrandId()
    {
        return preferredBrandId;
    }

    @Override
    public String toString()
    {
        return String.format("PersonSettingsLinkDTO [tenant=%s, preferredCompanyId=%s, preferredBrandId=%s]", tenant,
            preferredCompanyId, preferredBrandId);
    }
}
