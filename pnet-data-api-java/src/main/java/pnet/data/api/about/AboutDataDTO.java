/* Copyright 2017 Porsche Informatik GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pnet.data.api.about;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Holds information about the Partner.Net Data API and the connected user.
 *
 * @author ham
 */
@Schema(description = "Holds information about the Partner.Net Data API and the connected user.")
public class AboutDataDTO implements Serializable
{
    private static final long serialVersionUID = -6140129628280468919L;

    @Schema(description = "The version of the Partner.Net.")
    private final String partnerNetVersion;

    @Schema(description = "The supported version Partner.Net Data API.")
    private final String dataApiVersion;

    @Schema(description = "The id of the system user used for this request.")
    private final Integer userId;

    @Schema(description = "All available tenants of the system user used for this request.")
    private final Collection<String> tenants;

    @Schema(description = "All rights of the system user used for this request.")
    private final Collection<String> authorities;

    public AboutDataDTO(@JsonProperty("partnerNetVersion") String partnerNetVersion,
        @JsonProperty("dataApiVersion") String dataApiVersion, @JsonProperty("userId") Integer userId,
        @JsonProperty("tenants") Collection<String> tenants,
        @JsonProperty("authorities") Collection<String> authorities)
    {
        super();

        this.partnerNetVersion = partnerNetVersion;
        this.dataApiVersion = dataApiVersion;
        this.userId = userId;
        this.tenants = tenants;
        this.authorities = authorities;
    }

    /**
     * @return the version of the Partner.Net
     */
    public String getPartnerNetVersion()
    {
        return partnerNetVersion;
    }

    /**
     * @return the supported version Partner.Net Data API
     */
    public String getDataApiVersion()
    {
        return dataApiVersion;
    }

    /**
     * @return the id of the system user used for this request
     */
    public Integer getUserId()
    {
        return userId;
    }

    /**
     * @return all available tenants of the system user used for this request
     */
    public Collection<String> getTenants()
    {
        return tenants;
    }

    /**
     * @return all rights of the system user used for this request
     */
    public Collection<String> getAuthorities()
    {
        return authorities;
    }

    @Override
    public String toString()
    {
        return String
            .format("AboutDataDTO [partnerNetVersion=%s, dataApiVersion=%s, userId=%s, tenants=%s, authorities=%s]",
                partnerNetVersion, dataApiVersion, userId, tenants, authorities);
    }

}
