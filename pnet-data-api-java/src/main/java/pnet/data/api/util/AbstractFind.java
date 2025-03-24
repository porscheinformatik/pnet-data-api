package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.ITEMS_PER_PAGE_KEY;
import static pnet.data.api.PnetDataConstants.LANGUAGE_KEY;
import static pnet.data.api.PnetDataConstants.PAGE_INDEX_KEY;
import static pnet.data.api.PnetDataConstants.SEARCH_AFTER_KEY;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.SearchAfter;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Abstract implementation of a find query.
 *
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the restriction itself for fluent interface
 * @author ham
 */
public abstract class AbstractFind<DTO, SELF extends AbstractFind<DTO, SELF>> extends AbstractRestricable<SELF>
    implements Find<DTO>, Restrict<SELF>
{
    private final FindFunction<DTO> findFunction;

    protected AbstractFind(FindFunction<DTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(restricts);

        this.findFunction = findFunction;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected SELF newInstance(List<Pair<String, Object>> restricts)
    {
        Constructor<?> constructor;
        try
        {
            constructor = getClass().getConstructor(FindFunction.class, List.class);
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try
        {
            return (SELF) constructor.newInstance(findFunction, restricts);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            throw new IllegalArgumentException("Failed to invoke constructor", e);
        }
    }

    @Override
    public DTO firstOnly(Locale language) throws PnetDataClientException
    {
        PnetDataClientResultPage<DTO> results = execute(language, 0, 1);

        return !results.isEmpty() ? results.get(0) : null;
    }

    @Override
    public PnetDataClientResultPage<DTO> execute(Locale language) throws PnetDataClientException
    {
        return execute(language, 0, 10);
    }

    @Override
    public PnetDataClientResultPage<DTO> execute(Locale language, SearchAfter searchAfter, int itemsPerPage)
        throws PnetDataClientException
    {
        List<Pair<String, Object>> restricts = new ArrayList<>(getRestricts());

        restricts.add(Pair.of(LANGUAGE_KEY, language));
        restricts.add(Pair.of(SEARCH_AFTER_KEY, searchAfter.getValue()));
        restricts.add(Pair.of(ITEMS_PER_PAGE_KEY, itemsPerPage));

        return execute(restricts);
    }

    @Override
    public PnetDataClientResultPage<DTO> execute(Locale language, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        List<Pair<String, Object>> restricts = new ArrayList<>(getRestricts());

        restricts.add(Pair.of(LANGUAGE_KEY, language));
        restricts.add(Pair.of(PAGE_INDEX_KEY, pageIndex));
        restricts.add(Pair.of(ITEMS_PER_PAGE_KEY, itemsPerPage));

        return execute(restricts);
    }

    protected PnetDataClientResultPage<DTO> execute(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        return findFunction.find(Collections.unmodifiableList(restricts));
    }
}
