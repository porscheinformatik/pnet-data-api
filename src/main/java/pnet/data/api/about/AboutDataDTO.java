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
