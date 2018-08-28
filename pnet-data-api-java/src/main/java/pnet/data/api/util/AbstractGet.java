package pnet.data.api.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Abstract implementation of a find query.
 *
 * @author ham
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the restriction itself for fluent interface
 */
public abstract class AbstractGet<DTO, SELF extends AbstractGet<DTO, SELF>> extends AbstractRestricable<SELF>
    implements Get<DTO>, Restrict<SELF>
{

    private final GetFunction<DTO> getFunction;

    protected AbstractGet(GetFunction<DTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(restricts);

        this.getFunction = getFunction;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected SELF newInstance(List<Pair<String, Object>> restricts)
    {
        Constructor<?> constructor;
        try
        {
            constructor = getClass().getConstructor(GetFunction.class, List.class);
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try
        {
            return (SELF) constructor.newInstance(getFunction, restricts);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            throw new IllegalArgumentException("Failed to invoke constructor", e);
        }
    }

    public PnetDataClientResultPage<DTO> execute(String parameterName, List<?> parameterValues, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return getFunction
            .get(restrict(parameterName, parameterValues.toArray()).getRestricts(), pageIndex, itemsPerPage);
    }

}
