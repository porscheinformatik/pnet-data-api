package pnet.data.api.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract implementation of a restrictable query.
 *
 * @author HAM
 *
 * @param <SELF> the type of the filter itself for fluent interface
 */
public abstract class AbstractRestricable<SELF extends AbstractRestricable<SELF>>
{

    private final ObjectMapper mapper;
    private final List<Pair<String, Object>> restricts;

    public AbstractRestricable(ObjectMapper mapper, List<Pair<String, Object>> restricts)
    {
        super();

        this.mapper = mapper;
        this.restricts = restricts;
    }

    protected abstract SELF newInstance(ObjectMapper mapper, List<Pair<String, Object>> restricts);

    protected ObjectMapper getMapper()
    {
        return mapper;
    }

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
            restricts.add(Pair.of(parameterName, convert(value)));
        }

        return newInstance(mapper, restricts);
    }

    protected Object convert(Object value)
    {
        if (value == null)
        {
            return value;
        }

        if (value instanceof CharSequence
            || value instanceof Boolean
            || value instanceof Character
            || value instanceof Number)
        {
            return value;
        }

        try
        {
            return mapper.writeValueAsString(value);
        }
        catch (JsonProcessingException e)
        {
            throw new IllegalArgumentException("Failed to map value: " + value);
        }
    }

}