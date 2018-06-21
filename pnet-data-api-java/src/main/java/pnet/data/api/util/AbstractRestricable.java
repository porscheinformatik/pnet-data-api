package pnet.data.api.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of a restrictable query.
 *
 * @author HAM
 *
 * @param <SELF> the type of the filter itself for fluent interface
 */
public abstract class AbstractRestricable<SELF extends AbstractRestricable<SELF>>
{

    private final List<Pair<String, Object>> restricts;

    public AbstractRestricable(List<Pair<String, Object>> restricts)
    {
        super();

        this.restricts = restricts;
    }

    protected abstract SELF newInstance(List<Pair<String, Object>> restricts);

    protected List<Pair<String, Object>> getRestricts()
    {
        return restricts;
    }

    public SELF restrict(String parameterName, Object... values)
    {
        List<Pair<String, Object>> restricts =
            this.restricts != null ? new ArrayList<>(this.restricts) : new ArrayList<>();

        for (Object value : values)
        {
            restricts.add(Pair.of(parameterName, value));
        }

        return newInstance(restricts);
    }

}