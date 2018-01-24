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
import java.util.Iterator;
import java.util.List;

/**
 * Holding results of a search or find operation with paging information.
 *
 * @author ham
 * @param <T> the type of item
 */
public interface ResultPage<T> extends Iterable<T>, Serializable
{

    /**
     * @return the list of items, never null.
     */
    List<T> getItems();

    /**
     * @return the number of items on this page.
     */
    default int size()
    {
        return getItems().size();
    }

    @Override
    default Iterator<T> iterator()
    {
        return getItems().iterator();
    }

    /**
     * @return the number of items per page, which must not be the same a the number of items in this page.
     */
    int getItemsPerPage();

    /**
     * @return the total number of items.
     */
    int getNumberOfItems();

    /**
     * @return the number of this page, 1-based.
     */
    int getPageNumber();

    /**
     * @return the total number of pages.
     */
    int getNumberOfPages();

    /**
     * @return true if there is another page
     */
    default boolean hasNextPage()
    {
        return getPageNumber() <= getNumberOfPages();
    }

    /**
     * @return the next page. Executes a call if there is another page. Null otherwise.
     */
    ResultPage<T> getNextPage();

}
