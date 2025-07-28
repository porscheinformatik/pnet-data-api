package pnet.data.api.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract implementation of a restrictable query.
 *
 * @param <SELF> the type of the filter itself for fluent interface
 * @author HAM
 */
public abstract class AbstractRestricable<SELF extends AbstractRestricable<SELF>> {

    private final List<Pair<String, Object>> restricts;

    public AbstractRestricable(List<Pair<String, Object>> restricts) {
        super();
        this.restricts = Collections.unmodifiableList(restricts);
    }

    protected abstract SELF newInstance(List<Pair<String, Object>> restricts);

    protected List<Pair<String, Object>> getRestricts() {
        return restricts;
    }

    @SuppressWarnings("unchecked")
    public SELF restrict(String parameterName, Object... values) {
        if (values == null || values.length == 0) {
            return (SELF) this;
        }

        List<Pair<String, Object>> restricts = this.restricts != null
            ? new ArrayList<>(this.restricts)
            : new ArrayList<>();

        for (Object value : values) {
            restricts.add(Pair.of(parameterName, value));
        }

        return newInstance(restricts);
    }

    public SELF aggregate(String name) {
        return restrict("aggregate", name);
    }
}
