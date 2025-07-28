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
 * A reference to a company.
 *
 * @author ham
 */
public interface WithCompanyId {
    /**
     * @return the id of the company. The id may vary on different environments.
     */
    Integer getCompanyId();

    /**
     * @return the matchcode of the company, consisting of the administrative tenant and the number, e.g. AT00500.
     */
    String getCompanyMatchcode();

    /**
     * @return the optional, not unique number of the company.
     */
    String getCompanyNumber();
}
