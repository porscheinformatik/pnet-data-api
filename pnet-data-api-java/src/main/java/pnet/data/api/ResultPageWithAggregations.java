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

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import pnet.data.api.util.WithAggregations;

/**
 * Holding results of a search or find operation with paging information.
 *
 * @param <T> the type of item
 * @param <AggregationsT> the type of aggregations
 * @author ham
 */
@Schema(description = "Holds results of a search or find operation with paging information and aggregations.")
public interface ResultPageWithAggregations<T, AggregationsT> extends ResultPage<T>, WithAggregations<AggregationsT> {
    static <T, AggregationsT> ResultPageWithAggregations<T, AggregationsT> of(
        List<T> items,
        AggregationsT aggregations,
        int itemsPerPage,
        int totalNumberOfItems,
        int pageIndex,
        int numberOfPages,
        SearchAfter searchAfter,
        String scrollId,
        boolean complete
    ) {
        return new ResultPageWithAggregations<>() {
            @Override
            public List<T> getItems() {
                return items;
            }

            @Override
            public AggregationsT getAggregations() {
                return aggregations;
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

            @Override
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
}
