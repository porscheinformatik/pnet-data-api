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
package pnet.data.api.util;

import java.util.Collection;

/**
 * Used for DTOs that support multiple tenants.
 *
 * @author ham
 */
public interface WithTenants
{

    /**
     * @return A list of all tenants that support this item.
     */
    Collection<String> getTenants();

    /**
     * Returns true if the DTOs contains the specified tenant, false otherwise.
     *
     * @param tenant the tenant
     * @return true if present
     */
    default boolean containsTenant(String tenant)
    {
        return getTenants().contains(tenant);
    }

}
