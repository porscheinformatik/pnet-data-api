package pnet.data.api.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Abstract implementation of a find query.
 *
 * @author ham
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the restriction itself for fluent interface
 */
public abstract class AbstractFind<DTO, SELF extends AbstractFind<DTO, SELF>> extends AbstractRestricable<SELF>
    implements Find<DTO>, Restrict<SELF>
{

    private final FindFunction<DTO> findFunction;

    protected AbstractFind(ObjectMapper mapper, FindFunction<DTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(mapper, restricts);

        this.findFunction = findFunction;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected SELF newInstance(ObjectMapper mapper, List<Pair<String, Object>> restricts)
    {
        Constructor<?> constructor;
        try
        {
            constructor = getClass().getConstructor(ObjectMapper.class, FindFunction.class, List.class);
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try
        {
            return (SELF) constructor.newInstance(getMapper(), findFunction, restricts);
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
        return findFunction.find(language, getRestricts(), pageIndex, itemsPerPage);
    }

}
