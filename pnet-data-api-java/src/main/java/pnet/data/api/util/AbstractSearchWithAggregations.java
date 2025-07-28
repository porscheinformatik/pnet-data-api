package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.SearchAfter;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;

/**
 * Abstract implementation of a search query.
 *
 * @param <DTO> the type of the DTO
 * @param <AggregationsDTO> the type of aggregations DTO
 * @param <SELF> the type of the filter itself for fluent interface
 * @author ham
 */
public abstract class AbstractSearchWithAggregations<
    DTO,
    AggregationsDTO,
    SELF extends AbstractSearchWithAggregations<DTO, AggregationsDTO, SELF>
>
    extends AbstractSearch<DTO, SELF>
    implements SearchWithAggregations<DTO, AggregationsDTO> {

    private final SearchWithAggregationsFunction<DTO, AggregationsDTO> searchFunction;

    protected AbstractSearchWithAggregations(
        SearchWithAggregationsFunction<DTO, AggregationsDTO> searchFunction,
        List<Pair<String, Object>> restricts
    ) {
        super(searchFunction, restricts);
        this.searchFunction = searchFunction;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected SELF newInstance(List<Pair<String, Object>> restricts) {
        Constructor<?> constructor;
        try {
            constructor = getClass().getConstructor(SearchWithAggregationsFunction.class, List.class);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try {
            return (SELF) constructor.newInstance(searchFunction, restricts);
        } catch (
            InstantiationException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException e
        ) {
            throw new IllegalArgumentException("Failed to invoke constructor", e);
        }
    }

    @Override
    public DTO firstOnly(Locale language, String query) throws PnetDataClientException {
        PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> results = execute(language, query, 0, 1);

        return !results.isEmpty() ? results.get(0) : null;
    }

    @Override
    public PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> execute(Locale language, String query)
        throws PnetDataClientException {
        return execute(language, query, 0, 10);
    }

    @Override
    public PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> execute(
        Locale language,
        String query,
        SearchAfter searchAfter,
        int itemsPerPage
    ) throws PnetDataClientException {
        List<Pair<String, Object>> restricts = new ArrayList<>(getRestricts());

        restricts.add(Pair.of(LANGUAGE_KEY, language));
        restricts.add(Pair.of(QUERY_KEY, query));
        restricts.add(Pair.of(SEARCH_AFTER_KEY, searchAfter.getValue()));
        restricts.add(Pair.of(ITEMS_PER_PAGE_KEY, itemsPerPage));

        return execute(restricts);
    }

    @Override
    public PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> execute(
        Locale language,
        String query,
        int pageIndex,
        int itemsPerPage
    ) throws PnetDataClientException {
        List<Pair<String, Object>> restricts = new ArrayList<>(getRestricts());

        restricts.add(Pair.of(LANGUAGE_KEY, language));
        restricts.add(Pair.of(QUERY_KEY, query));
        restricts.add(Pair.of(PAGE_INDEX_KEY, pageIndex));
        restricts.add(Pair.of(ITEMS_PER_PAGE_KEY, itemsPerPage));

        return execute(restricts);
    }

    @Override
    protected PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> execute(
        List<Pair<String, Object>> restricts
    ) throws PnetDataClientException {
        return searchFunction.search(Collections.unmodifiableList(restricts));
    }
}
