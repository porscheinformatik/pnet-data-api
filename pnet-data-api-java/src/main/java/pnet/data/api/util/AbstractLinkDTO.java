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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract implementation of a link.
 *
 * @author ham
 */
public abstract class AbstractLinkDTO implements WithTenant, WithMatchcode, Serializable {

    private static final long serialVersionUID = -2028835160784471478L;

    protected final String tenant;
    protected final String matchcode;

    public AbstractLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode) {
        super();
        this.tenant = tenant;
        this.matchcode = matchcode;
    }

    @Override
    public String getTenant() {
        return tenant;
    }

    @Override
    public String getMatchcode() {
        return matchcode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchcode, tenant);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AbstractLinkDTO other)) {
            return false;
        }
        if (!Objects.equals(matchcode, other.matchcode)) {
            return false;
        }
        if (!Objects.equals(tenant, other.tenant)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", matchcode, tenant);
    }
}
