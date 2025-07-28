package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts SAP number
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictSapNumber<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    /**
     * Restricts the SAP number.
     *
     * @param numbers the SAP number
     * @return the request for chaining
     * @deprecated use the restriction for companyNumber instead
     */
    @Deprecated
    default SELF sapNumber(String... numbers) {
        return restrict("sapNumber", (Object[]) numbers);
    }

    /**
     * Restricts the SAP number.
     *
     * @param numbers the SAP number
     * @return the request for chaining
     * @deprecated use the restriction for companyNumber instead
     */
    @Deprecated
    default SELF sapNumbers(Collection<String> numbers) {
        return sapNumber(numbers.toArray(new String[0]));
    }
}
