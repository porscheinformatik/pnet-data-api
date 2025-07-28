package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import pnet.data.api.PnetDataClientException;

/**
 * Abstract implementation of an autoComplete query.
 *
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the filter itself for fluent interface
 * @author ham
 */
public abstract class AbstractAutoComplete<DTO, SELF extends AbstractAutoComplete<DTO, SELF>>
    extends AbstractRestricable<SELF>
    implements AutoComplete<DTO>, Restrict<SELF>, Aggregate<SELF> {

    private final AutoCompleteFunction<DTO> autoCompleteFunction;

    protected AbstractAutoComplete(
        AutoCompleteFunction<DTO> autoCompleteFunction,
        List<Pair<String, Object>> restricts
    ) {
        super(restricts);
        this.autoCompleteFunction = autoCompleteFunction;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected SELF newInstance(List<Pair<String, Object>> restricts) {
        Constructor<?> constructor;
        try {
            constructor = getClass().getConstructor(AutoCompleteFunction.class, List.class);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try {
            return (SELF) constructor.newInstance(autoCompleteFunction, restricts);
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
    public List<DTO> execute(Locale language, String query) throws PnetDataClientException {
        List<Pair<String, Object>> restricts = new ArrayList<>(getRestricts());

        restricts.add(Pair.of(LANGUAGE_KEY, language));
        restricts.add(Pair.of(QUERY_KEY, query));

        return autoCompleteFunction.autoComplete(Collections.unmodifiableList(restricts));
    }
}
