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

import java.util.Locale;
import java.util.Map;

/**
 * Used to mark DTOs with labels
 *
 * @author ham
 */
public interface WithLabels
{

    /**
     * @return A map of strings by locale, holding the label of the item in multiple languages.
     */
    Map<Locale, String> getLabels();

    /**
     * @param language the language, may be null
     * @return The label in the specified language, null if not found.
     */
    default String getLabel(Locale language)
    {
        return PnetDataApiUtils.getText(language, getLabels());
    }

}
