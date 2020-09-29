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
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Holding results of a search or find operation with paging information.
 *
 * @author ham
 * @param <T> the type of item
 */
@ApiModel(description = "Holds results of a search or find operation with paging information")
public interface ResultPage<T> extends Iterable<T>
{

    static <T> ResultPage<T> of(List<T> items, int itemsPerPage, int totalNumberOfItems, int pageIndex,
        int numberOfPages, String scrollId)
    {
        return new ResultPage<T>() //
        {
            @Override
            public List<T> getItems()
            {
                return items;
            }

            @Override
            public int getItemsPerPage()
            {
                return itemsPerPage;
            }

            @Override
            public int getTotalNumberOfItems()
            {
                return totalNumberOfItems;
            }

            @Override
            public int getPageIndex()
            {
                return pageIndex;
            }

            @Override
            public int getNumberOfPages()
            {
                return numberOfPages;
            }

            @Override
            public String getScrollId()
            {
                return scrollId;
            }
        };
    }

    /**
     * @return the list of items of this page, never null
     */
    List<T> getItems();

    /**
     * @return a stream of the items of this page, never null
     */
    @JsonIgnore
    default Stream<T> stream()
    {
        List<T> items = getItems();

        return items != null ? items.stream() : Collections.<T> emptyList().stream();
    }

    /**
     * @return the first item of this page, null if there isn't one
     */
    @JsonIgnore
    default T first()
    {
        List<T> items = getItems();

        return items != null && items.size() > 0 ? items.get(0) : null;
    }

    /**
     * @return the first item of this page, null if there isn't one
     * @throws IllegalStateException if there are more than one items
     */
    @JsonIgnore
    default T unique()
    {
        int totalNumberOfItems = getTotalNumberOfItems();

        if (totalNumberOfItems > 1)
        {
            throw new IllegalStateException("Multiple items found");
        }

        return first();
    }

    /**
     * @param index the index
     * @return the item at the specified index of this page, null if there isn't one
     */
    default T get(int index)
    {
        List<T> items = getItems();

        return items != null && index < items.size() ? items.get(index) : null;
    }

    /**
     * @return the number of items of this page
     */
    @JsonIgnore
    default int size()
    {
        List<T> items = getItems();

        return items != null ? items.size() : 0;
    }

    /**
     * @return an iterator of the items of this page, never null
     */
    @Override
    @JsonIgnore
    default Iterator<T> iterator()
    {
        List<T> items = getItems();

        return items != null ? items.iterator() : Collections.emptyIterator();
    }

    /**
     * @return the number of items per page, which must not be the same a the number of items in this page.
     */
    @ApiModelProperty(notes = "The amount of items that each page can hold")
    int getItemsPerPage();

    /**
     * @return the total number of items.
     */
    @ApiModelProperty(notes = "The total amount of items that the search/find operation found")
    int getTotalNumberOfItems();

    /**
     * @return the index of this page, 0-based.
     */
    @ApiModelProperty(notes = "The index of the current page (the index is 0-based)")
    int getPageIndex();

    /**
     * @return the total number of pages.
     */
    @ApiModelProperty(notes = "The total amount of pages, that the search/find operation found")
    int getNumberOfPages();

    /**
     * Returns the scroll id, if available
     *
     * @return the scroll id
     */
    @ApiModelProperty(notes = "The id for scrolling results (scroll=true). Can be used together with /next requests.")
    String getScrollId();

    /**
     * @return true if there is another page
     */
    default boolean hasNextPage()
    {
        return getPageIndex() + 1 < getNumberOfPages();
    }

}
