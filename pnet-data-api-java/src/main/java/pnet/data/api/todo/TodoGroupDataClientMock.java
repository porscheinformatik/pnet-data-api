package pnet.data.api.todo;

import static pnet.data.api.util.MockFilters.*;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregates;
import pnet.data.api.client.context.PnetDataApiContextMock;
import pnet.data.api.util.ItemClientMock;
import pnet.data.api.util.MockStore;
import pnet.data.api.util.MockUtils;
import pnet.data.api.util.Pair;

/**
 * A mock, that can be used for testing.
 *
 * @author HAM
 */
public class TodoGroupDataClientMock extends TodoGroupDataClient
    implements ItemClientMock<TodoGroupItemDTO, TodoGroupDataClientMock>
{
    public TodoGroupDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<TodoGroupItemDTO> itemStore = getItemStore();

        itemStore.addFilter("q", whenMatches(TodoGroupItemDTO::getReferenceMatchcode));
        itemStore
            .addFilter("q", withCollection(TodoGroupItemDTO::getEntries,
                whenMatches(TodoGroupEntryLinkDTO::getHeadline, TodoGroupEntryLinkDTO::getDescription)));
        itemStore.addFilter("category", whenEquals(TodoGroupItemDTO::getCategory));
        itemStore.addFilter("referenceId", whenEquals(TodoGroupItemDTO::getReferenceId));
        itemStore.addFilter("referenceMatchcode", whenEquals(TodoGroupItemDTO::getReferenceMatchcode));
        itemStore
            .addFilter("personId",
                withCollection(TodoGroupItemDTO::getPersons, whenEquals(TodoGroupPersonLinkDTO::getPersonId)));
        itemStore
            .addFilter("type",
                withCollection(TodoGroupItemDTO::getEntries, whenEquals(TodoGroupEntryLinkDTO::getType)));
        itemStore
            .addFilter("source",
                withCollection(TodoGroupItemDTO::getEntries, whenEquals(TodoGroupEntryLinkDTO::getSource)));
        itemStore
            .addFilter("state",
                withCollection(TodoGroupItemDTO::getEntries, whenEquals(TodoGroupEntryLinkDTO::getState)));
        itemStore
            .addFilter("todoId",
                withCollection(TodoGroupItemDTO::getEntries, whenEquals(TodoGroupEntryLinkDTO::getId)));

        addDefaultLastUpdateFilter(itemStore);
        addDefaultScrollDummy(itemStore);
    }

    @Override
    protected PnetDataClientResultPage<TodoGroupItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        List<TodoGroupItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }

    @Override
    protected PnetDataClientResultPageWithAggregates<TodoGroupItemDTO, TodoGroupAggregatesDTO> search(Locale language,
        String query, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        List<TodoGroupItemDTO> entries = findItems(restricts);
        List<TodoGroupCategoryAggregateDTO> aggregatedCategories =
            MockUtils.aggregate(entries, TodoGroupItemDTO::getCategory, TodoGroupCategoryAggregateDTO::new);
        TodoGroupAggregatesDTO aggregates = new TodoGroupAggregatesDTO(aggregatedCategories);

        return MockUtils.mockResultPageWithAggregates(entries, aggregates, pageIndex, itemsPerPage);
    }

}
