package pnet.data.api.util;

import java.util.List;
import java.util.function.BiPredicate;

import at.porscheinformatik.happyrest.GenericType;

/**
 * A client mock that handles items
 *
 * @author HAM
 *
 * @param <ItemT> the type of item
 * @param <SELF> the class for self-references
 */
public interface ItemClientMock<ItemT, SELF extends ItemClientMock<ItemT, SELF>> extends Self<SELF>
{

    default Class<ItemT> getItemType()
    {
        return GenericType.build(ItemClientMock.class).instancedBy(this).getArgumentClass(0);
    }

    default MockStore<ItemT> getItemStore()
    {
        return MockStore.get(this, getItemType());
    }

    default SELF addItem(ItemT dto)
    {
        getItemStore().add(dto);

        return self();
    }

    /**
     * Adds a filter function to this mock, that may filter according to restricts with the specified key.
     *
     * @param <ValueT> the type of value or the restrict
     * @param key the key
     * @param predicate the filter function, the item will be kept, if this function returns true
     * @return the mock itself
     */
    default <ValueT> SELF addItemFilter(String key, BiPredicate<ItemT, ValueT> predicate)
    {
        getItemStore().addFilter(key, predicate);

        return self();
    }

    default List<ItemT> findItems(List<Pair<String, Object>> restricts)
    {
        return getItemStore().find(restricts);
    }
}
