package pnet.data.api.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Abstract implementation of a find query.
 *
 * @author ham
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the restriction itself for fluent interface
 */
public abstract class AbstractFind<DTO, SELF extends AbstractFind<DTO, SELF>> implements Find<DTO>, Restrict<SELF>
{

    private final FindFunction<DTO> findFunction;
    private final List<Pair<String, Object>> restricts;

    protected AbstractFind(FindFunction<DTO> findFunction, List<Pair<String, Object>> restrictItems)
    {
        super();

        this.findFunction = findFunction;
        this.restricts = restrictItems;
    }

    @SuppressWarnings("unchecked")
    protected SELF newInstance(List<Pair<String, Object>> restrictItems)
    {
        Constructor<?> constructor;
        try
        {
            constructor = getClass().getConstructor(FindFunction.class, List.class);
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            throw new UnsupportedOperationException("Necessary constructor not available", e);
        }

        try
        {
            return (SELF) constructor.newInstance(findFunction, restrictItems);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            throw new IllegalArgumentException("Failed to invoke constructor", e);
        }
    }

    @Override
    public PnetDataClientResultPage<DTO> execute(Locale language, int pageIndex, int itemsPerPage)
        throws PnetDataApiException
    {
        return findFunction.find(language, restricts, pageIndex, itemsPerPage);
    }

    @Override
    public SELF restrict(String key, Object... values)
    {
        List<Pair<String, Object>> restrictItems =
            this.restricts != null ? new ArrayList<>(this.restricts) : new ArrayList<>();

        for (Object value : values)
        {
            restrictItems.add(Pair.of(key, value));
        }

        return newInstance(restrictItems);
    }

}
