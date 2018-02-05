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
package pnet.data.api;

import java.io.Serializable;
import java.util.Collection;

/**
 * A matchcode is an alpha-numberic key.
 *
 * @author ham
 */
public abstract class Matchcode implements CharSequence, Serializable
{

    private static final long serialVersionUID = 2878108101706073855L;

    private final String matchcode;

    protected Matchcode(String matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    public String getMatchcode()
    {
        return matchcode;
    }

    @Override
    public int length()
    {
        return matchcode.length();
    }

    @Override
    public char charAt(int index)
    {
        return matchcode.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end)
    {
        return matchcode.subSequence(start, end);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof Matchcode))
        {
            return false;
        }
        Matchcode other = (Matchcode) obj;
        if (matchcode == null)
        {
            if (other.matchcode != null)
            {
                return false;
            }
        }
        else if (!matchcode.equals(other.matchcode))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return matchcode;
    }

    /**
     * Converts the matchcodes to a string array.
     *
     * @param matchcodes the matchcodes
     * @return the array, never null
     */
    public static String[] toStringArray(Collection<? extends Matchcode> matchcodes)
    {
        return matchcodes != null ? matchcodes.stream().map(Matchcode::getMatchcode).toArray(size -> new String[size])
            : new String[0];
    }
}
