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
 * An item, that uses a collection of matchcodes as universal identifiers.
 *
 * @author ham
 */
public interface WithMatchcodes {
    /**
     * @return A collection of unique, alpha-numeric keys. These keys are the same in all environments.
     */
    Collection<String> getMatchcodes();
}
