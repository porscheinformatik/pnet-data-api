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

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Used by facades that returns items by {@link Matchcode}.
 *
 * @author ham
 * @param <MatchcodeT> the type of the matchcode
 * @param <ResultT> the type of the result
 */
public interface ByMatchcode<MatchcodeT extends Matchcode, ResultT extends WithMatchcode<MatchcodeT>>
{

    /**
     * Returns the item with the specified matchcode.
     *
     * @param matchcode the matchcode
     * @return the item, or null if not found
     */
    @RequestMapping(value = "/{matchcode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultT getByMatchcode(@PathVariable("matchcode") MatchcodeT matchcode);

}
