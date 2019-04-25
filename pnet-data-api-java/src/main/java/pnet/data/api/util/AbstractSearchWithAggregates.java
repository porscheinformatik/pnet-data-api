package pnet.data.api.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPageWithAggregates;

/**
 * Abstract implementation of a search query.
 *
 * @author ham
 * @param <DTO> the type of the DTO
 * @param <AggregatesDTO> the type of aggregates DTO
 * @param <SELF> the type of the filter itself for fluent interface
 */
public abstract class AbstractSearchWithAggregates<DTO, AggregatesDTO, SELF extends AbstractSearchWithAggregates<DTO, AggregatesDTO, SELF>>
    extends AbstractSearch<DTO, SELF> implements SearchWithAggregates<DTO, AggregatesDTO>
{

    private final SearchWithAggregatesFunction<DTO, AggregatesDTO> searchFunction;

    protected AbstractSearchWithAggregates(SearchWithAggregatesFunction<DTO, AggregatesDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);

        this.searchFunction = searchFunction;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected SELF newInstance(List<Pair<String, Object>> restricts)
    {
        Constructor<?> constructor;
        try
        {
            constructor = getClass().getConstructor(SearchWithAggregatesFunction.class, List.class);
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try
        {
            return (SELF) constructor.newInstance(searchFunction, restricts);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            throw new IllegalArgumentException("Failed to invoke constructor", e);
        }
    }

    @Override
    public PnetDataClientResultPageWithAggregates<DTO, AggregatesDTO> execute(Locale language, String query,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return searchFunction.search(language, query, getRestricts(), pageIndex, itemsPerPage);
    }

}
