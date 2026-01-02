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

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Holding results of a search or find operation with paging information.
 *
 * @param <T> the type of item
 * @author ham
 */
@Schema(description = "Holds results of a search or find operation with paging information")
public interface ResultPage<T> extends Iterable<T> {
    static <T> ResultPage<T> of(
        List<T> items,
        int itemsPerPage,
        int totalNumberOfItems,
        int pageIndex,
        int numberOfPages,
        SearchAfter searchAfter,
        String scrollId,
        boolean complete
    ) {
        return new ResultPage<>() {
            @Override
            public List<T> getItems() {
                return items;
            }

            @Override
            public int getItemsPerPage() {
                return itemsPerPage;
            }

            @Override
            public int getTotalNumberOfItems() {
                return totalNumberOfItems;
            }

            @Override
            public int getPageIndex() {
                return pageIndex;
            }

            @Override
            public int getNumberOfPages() {
                return numberOfPages;
            }

            public SearchAfter getSearchAfter() {
                return searchAfter;
            }

            @Override
            public String getScrollId() {
                return scrollId;
            }

            @Override
            public boolean isComplete() {
                return complete;
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
    default Stream<T> stream() {
        List<T> items = getItems();

        return items != null ? items.stream() : Stream.<T>empty();
    }

    /**
     * @return the first item of this page, null if there isn't one
     */
    @JsonIgnore
    default T first() {
        List<T> items = getItems();

        return items != null && !items.isEmpty() ? items.get(0) : null;
    }

    /**
     * @return the first item of this page, null if there isn't one
     * @throws IllegalStateException if there are more than one items
     */
    @JsonIgnore
    default T unique() {
        int totalNumberOfItems = getTotalNumberOfItems();

        if (totalNumberOfItems > 1) {
            throw new IllegalStateException("Multiple items found");
        }

        return first();
    }

    /**
     * @param index the index
     * @return the item at the specified index of this page, null if there isn't one
     */
    default T get(int index) {
        List<T> items = getItems();

        return items != null && index < items.size() ? items.get(index) : null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return the number of items of this page
     */
    @JsonIgnore
    default int size() {
        List<T> items = getItems();

        return items != null ? items.size() : 0;
    }

    /**
     * @return an iterator of the items of this page, never null
     */
    @Override
    @JsonIgnore
    default Iterator<T> iterator() {
        List<T> items = getItems();

        return items != null ? items.iterator() : Collections.emptyIterator();
    }

    /**
     * @return the number of items per page, which must not be the same a the number of items in this page.
     */
    @Schema(description = "The amount of items that each page can hold.")
    int getItemsPerPage();

    /**
     * @return the total number of items.
     */
    @Schema(description = "The total amount of items that the search/find operation found.")
    int getTotalNumberOfItems();

    /**
     * @return the index of this page, 0-based.
     * @deprecated Depending on how the page was loaded, this value may be missing and contain 0 despite the fact, that
     * it isn't the first page. This property will be removed to avoid confusion, to conform our document store, that
     * does not provide this value anymore and you should be encourage to use other forms of iterating over results.
     */
    @Schema(description = "The index of the current page if paging was used (the index is 0-based).")
    @Deprecated
    int getPageIndex();

    /**
     * @return the total number of pages.
     * @deprecated This value is a simple calculation of the totalNumberOfItems / itemsPerPage, because out document
     * store, that does not provide this value anymore. By removing this property, you should be encourage to use other
     * forms of iterating over results.
     */
    @Deprecated
    @Schema(description = "The total amount of pages, that the search/find operation found.")
    int getNumberOfPages();

    /**
     * @return the searchAfter parameter that can be passed to the next request to skip all previous results
     */
    @Schema(description = "The searchAfter parameter, that can be used with subsequent requests to implement paging.")
    SearchAfter getSearchAfter();

    /**
     * Returns the scroll id, if available
     *
     * @return the scroll id
     */
    @Schema(description = "The id for scrolling results (scroll=true). Can be used together with /next requests.")
    String getScrollId();

    /**
     * @return true if there is another page
     * @deprecated The result of this method depends on two values, that are not available anymore and should not be
     * used anymore. Depending on how the results were loaded, this method may report the wrong result (see
     * {@link #getPageIndex()} and {@link #getNumberOfPages()}).
     */
    @Deprecated(since = "2.x")
    default boolean hasNextPage() {
        return !isEmpty() && getPageIndex() + 1 < getNumberOfPages();
    }

    /**
     * @return false if there may be more results on a subsequent page. If this properity retrurns true, it has been
     * verified, that subsequent pages will not contain any more results. If this propertys return false, the next page
     * may contain more results but it may also be empty. This property is of special intrest when scrolling, in order
     * to prevent a final call that results in an empty page.
     */
    boolean isComplete();
}
