package pnet.data.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simulated backend
 *
 * @author HAM
 * @param <T> the type of data DTOs
 */
public class MockStore<T>
{

    private static final Map<Object, Map<Class<?>, MockStore<?>>> STORES = new WeakHashMap<>();

    public static <T> MockStore<T> get(Object instance, Class<T> type)
    {
        Map<Class<?>, MockStore<?>> map = STORES.get(instance);

        if (map == null)
        {
            map = new HashMap<>();
            STORES.put(instance, map);
        }

        @SuppressWarnings("unchecked")
        MockStore<T> store = (MockStore<T>) map.get(type);

        if (store == null)
        {
            store = new MockStore<>();
            map.put(type, store);
        }

        return store;
    }

    private final List<T> entries = new ArrayList<>();
    private final Map<String, BiPredicate<T, ?>> filters = new HashMap<>();

    public void add(T entry)
    {
        entries.add(entry);
    }

    public <ValueT> void addFilter(String key, BiPredicate<T, ValueT> predicate)
    {
        filters.put(key, predicate);
    }

    public List<T> find(List<Pair<String, Object>> restricts)
    {
        if (restricts.isEmpty())
        {
            return new ArrayList<>(entries);
        }

        Map<String, List<Object>> restrictMap = restricts
            .stream()
            .collect(Collectors.groupingBy(Pair::getLeft, Collectors.mapping(Pair::getRight, Collectors.toList())));
        Stream<T> stream = entries.stream();

        for (Entry<String, List<Object>> restrict : restrictMap.entrySet())
        {
            String key = restrict.getKey();
            List<Object> values = restrict.getValue();

            if (!filters.containsKey(key))
            {
                System.err
                    .printf("Warning: Filtering for \"%s\" is currently not supported by default. "
                        + "You have to add a filter manually to your mocked client!\n", key);
                continue;
            }

            @SuppressWarnings("unchecked")
            BiPredicate<T, Object> filter = (BiPredicate<T, Object>) filters.get(key);

            if (filter == null)
            {
                // ignored
                continue;
            }

            Predicate<T> predicate = null;

            for (Object value : values)
            {
                if (predicate == null)
                {
                    predicate = item -> filter.test(item, value);
                }
                else
                {
                    predicate = predicate.or(item -> filter.test(item, value));
                }
            }

            stream = stream.filter(predicate);
        }

        return stream.collect(Collectors.toList());
    }

}
