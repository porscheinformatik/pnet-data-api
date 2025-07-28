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

/**
 * An item, that uses an id as universal identifier.
 *
 * @author ham
 * @deprecated replaced by {@link WithPersonId}
 */
@Deprecated
public interface WithId {
    /**
     * @return The id of the item.
     * @deprecated replaced by {@link WithPersonId#getPersonId()}
     */
    @Deprecated
    Integer getId();
}
