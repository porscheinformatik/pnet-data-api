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
package pnet.data.api.brand;

import java.util.Collection;
import java.util.stream.Collectors;

import pnet.data.api.Matchcode;

/**
 * A {@link Matchcode} used for brands.
 *
 * @author ham
 */
public final class BrandMatchcode extends Matchcode
{

    private static final long serialVersionUID = -4814532448455032787L;

    /**
     * Creates a matchcode from the specified string
     *
     * @param matchcode the matchcode
     * @return the matchcode object
     */
    public static BrandMatchcode of(String matchcode)
    {
        return new BrandMatchcode(matchcode);
    }

    /**
     * Creates a collection of matchcodes
     *
     * @param matchcodes the matchcodes, may be null
     * @return a collection, may be null
     */
    public static Collection<BrandMatchcode> ofAll(Collection<String> matchcodes)
    {
        return matchcodes == null ? null : matchcodes.stream().map(BrandMatchcode::of).collect(Collectors.toList());
    }

    protected BrandMatchcode(String matchcode)
    {
        super(matchcode);
    }

}
