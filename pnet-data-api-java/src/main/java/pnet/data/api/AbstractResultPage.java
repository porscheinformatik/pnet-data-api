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
import java.util.function.Supplier;

/**
 * Default implementation of a result page
 *
 * @author ham
 * @param <T> the type of item
 */
public abstract class AbstractResultPage<T> implements ResultPage<T>
{

    public static final <T> AbstractResultPage<T> of(List<T> items, int itemsPerPage, int numberOfItems, int pageNumber,
        int numberOfPages, Supplier<ResultPage<T>> nextPageSupplier)
    {
        return new AbstractResultPage<T>(items, itemsPerPage, numberOfItems, pageNumber, numberOfPages)
        {
            private static final long serialVersionUID = -8828864758876524528L;

            @Override
            public ResultPage<T> getNextPage()
            {
                return nextPageSupplier.get();
            }
        };
    }

    private static final long serialVersionUID = 254532979510480226L;

    private final List<T> items;
    private final int itemsPerPage;
    private final int numberOfItems;
    private final int pageNumber;
    private final int numberOfPages;

    public AbstractResultPage(List<T> items, int itemsPerPage, int numberOfItems, int pageNumber, int numberOfPages)
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
    @Override
    public List<T> getItems()
    {
        return items;
    }

    /**
     * @return the number of items on this page.
     */
    @Override
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
    @Override
    public int getItemsPerPage()
    {
        return itemsPerPage;
    }

    /**
     * @return the total number of items.
     */
    @Override
    public int getNumberOfItems()
    {
        return numberOfItems;
    }

    /**
     * @return the number of this page, 0-based.
     */
    @Override
    public int getPageNumber()
    {
        return pageNumber;
    }

    /**
     * @return the total number of pages.
     */
    @Override
    public int getNumberOfPages()
    {
        return numberOfPages;
    }

}
