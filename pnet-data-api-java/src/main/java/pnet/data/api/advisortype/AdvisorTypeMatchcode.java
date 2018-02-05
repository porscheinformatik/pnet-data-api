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
package pnet.data.api.advisortype;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import pnet.data.api.Matchcode;

/**
 * A {@link Matchcode} used for advisor types.
 *
 * @author ham
 */
public final class AdvisorTypeMatchcode extends Matchcode
{

    private static final long serialVersionUID = 8294667591017559079L;

    /**
     * Creates a matchcode from the specified string
     *
     * @param matchcode the matchcode
     * @return the matchcode object
     */
    public static AdvisorTypeMatchcode of(String matchcode)
    {
        return new AdvisorTypeMatchcode(matchcode);
    }

    /**
     * Creates a collection of matchcodes
     *
     * @param matchcodes the matchcodes
     * @return a collection, never null
     */
    public static Collection<AdvisorTypeMatchcode> ofAll(String... matchcodes)
    {
        return ofAll(Arrays.asList(matchcodes));
    }

    /**
     * Creates a collection of matchcodes
     *
     * @param matchcodes the matchcodes, may be null
     * @return a collection, may be null
     */
    public static Collection<AdvisorTypeMatchcode> ofAll(Collection<String> matchcodes)
    {
        return matchcodes == null ? null
            : matchcodes.stream().map(AdvisorTypeMatchcode::of).collect(Collectors.toList());
    }

    protected AdvisorTypeMatchcode(String matchcode)
    {
        super(matchcode);
    }

}
