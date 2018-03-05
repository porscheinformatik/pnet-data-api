package pnet.data.api.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Abstract implementation of a search query.
 *
 * @author ham
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the filter itself for fluent interface
 */
public abstract class AbstractSearch<DTO, SELF extends AbstractSearch<DTO, SELF>> implements Search<DTO>, Restrict<SELF>
{

    private final SearchFunction<DTO> searchFunction;
    private final List<Pair<String, Object>> restricts;

    protected AbstractSearch(SearchFunction<DTO> searchFunction, List<Pair<String, Object>> restricts)
    {
        super();

        this.searchFunction = searchFunction;
        this.restricts = restricts;
    }

    @SuppressWarnings("unchecked")
    protected SELF newInstance(List<Pair<String, Object>> filterItems)
    {
        Constructor<?> constructor;
        try
        {
            constructor = getClass().getConstructor(SearchFunction.class, List.class);
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try
        {
            return (SELF) constructor.newInstance(searchFunction, filterItems);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            throw new IllegalArgumentException("Failed to invoke constructor", e);
        }
    }

    @Override
    public PnetDataClientResultPage<DTO> execute(Locale language, String query, int pageIndex, int itemsPerPage)
        throws PnetDataApiException
    {
        return searchFunction.search(language, query, restricts, pageIndex, itemsPerPage);
    }

    @Override
    public SELF restrict(String parameterName, Object... values)
    {
        List<Pair<String, Object>> filterItems =
            this.restricts != null ? new ArrayList<>(this.restricts) : new ArrayList<>();

        for (Object value : values)
        {
            filterItems.add(Pair.of(parameterName, value));
        }

        return newInstance(filterItems);
    }

}
