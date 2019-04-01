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

import java.util.List;

import io.swagger.annotations.ApiModel;
import pnet.data.api.util.WithAggregates;

/**
 * Holding results of a search or find operation with paging information.
 *
 * @author ham
 * @param <T> the type of item
 * @param <AggregatesT> the type of aggregates
 */
@ApiModel(description = "Holds results of a search or find operation with paging information and aggregates")
public interface ResultPageWithAggregates<T, AggregatesT> extends ResultPage<T>, WithAggregates<AggregatesT>
{

    static <T, AggregatesT> ResultPageWithAggregates<T, AggregatesT> of(List<T> items, AggregatesT aggregates,
        int itemsPerPage, int totalNumberOfItems, int pageIndex, int numberOfPages, String scrollId)
    {
        return new ResultPageWithAggregates<T, AggregatesT>() //
        {
            private static final long serialVersionUID = -999167833058168881L;

            @Override
            public List<T> getItems()
            {
                return items;
            }

            @Override
            public AggregatesT getAggregates()
            {
                return aggregates;
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
}
