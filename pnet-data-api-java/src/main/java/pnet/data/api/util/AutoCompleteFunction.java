package pnet.data.api.util;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;

/**
 * Function for auto complete.
 *
 * @author ham
 * @param <DTO> the DTO
 */
@FunctionalInterface
public interface AutoCompleteFunction<DTO>
{

    List<DTO> autoComplete(Locale language, String query, List<Pair<String, Object>> restricts)
        throws PnetDataClientException;

}
