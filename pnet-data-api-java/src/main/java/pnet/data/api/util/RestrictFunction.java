package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts functions.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictFunction<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF function(String... functionMatchcodes) {
        return restrict("function", (Object[]) functionMatchcodes);
    }

    default SELF functions(Collection<String> functionMatchcodes) {
        return function(functionMatchcodes.toArray(new String[0]));
    }

    /**
     * Can be used to find "active" users without a specific function, but with at least one active function.
     *
     * @return a new instance with this restriction
     */
    default SELF anyFunction() {
        return function(PnetDataApiUtils.WILDCARD);
    }
}
