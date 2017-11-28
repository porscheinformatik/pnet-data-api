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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Holding results of a search or find operation with paging information.
 *
 * @author ham
 * @param <T> the type of item
 */
public class ResultPage<T> implements Iterable<T>
{

    private final List<T> items;
    private final int itemsPerPage;
    private final int numberOfItems;
    private final int pageNumber;
    private final int numberOfPages;

    public ResultPage(List<T> items, int itemsPerPage, int numberOfItems, int pageNumber, int numberOfPages)
    {
        super();

        this.items = Collections.unmodifiableList(Objects.requireNonNull(items, "Items is null"));
        this.itemsPerPage = itemsPerPage;
        this.numberOfItems = numberOfItems;
        this.pageNumber = pageNumber;
        this.numberOfPages = numberOfPages;
    }

    /**
     * @return the list of items, never null.
     */
    public List<T> getItems()
    {
        return items;
    }

    /**
     * @return the number of items on this page.
     */
    public int size()
    {
        return items.size();
    }

    @Override
    public Iterator<T> iterator()
    {
        return items.iterator();
    }

    /**
     * @return the number of items per page, which must not be the same a the number of items in this page.
     */
    public int getItemsPerPage()
    {
        return itemsPerPage;
    }

    /**
     * @return the total number of items.
     */
    public int getNumberOfItems()
    {
        return numberOfItems;
    }

    /**
     * @return the number of this page, 1-based.
     */
    public int getPageNumber()
    {
        return pageNumber;
    }

    /**
     * @return the total number of pages.
     */
    public int getNumberOfPages()
    {
        return numberOfPages;
    }

}
