package pnet.data.api.util;

import java.util.List;
import java.util.function.BiPredicate;

import at.porscheinformatik.happyrest.GenericType;

/**
 * A client mock that handles datas
 *
 * @param <DataT> the type of data
 * @param <SELF> the class for self-references
 * @author HAM
 */
public interface DataClientMock<DataT, SELF extends DataClientMock<DataT, SELF>> extends Self<SELF>
{

    default Class<DataT> getDataType()
    {
        return GenericType.build(DataClientMock.class).instancedBy(this).getArgumentClass(0);
    }

    default MockStore<DataT> getDataStore()
    {
        return MockStore.get(this, getDataType());
    }

    default SELF addData(DataT dto)
    {
        getDataStore().add(dto);

        return self();
    }

    /**
     * Adds a filter function to this mock, that may filter according to restricts with the specified key.
     *
     * @param <ValueT> the type of value or the restrict
     * @param key the key
     * @param predicate the filter function, the data will be kept, if this function returns true
     * @return the mock itself
     */
    default <ValueT> SELF addDataFilter(String key, BiPredicate<DataT, ValueT> predicate)
    {
        getDataStore().addFilter(key, predicate);

        return self();
    }

    default List<DataT> findDatas(List<Pair<String, Object>> restricts)
    {
        return getDataStore().find(restricts);
    }
}
