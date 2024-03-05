package pnet.data.api.util;

import java.util.List;

import pnet.data.api.PnetDataClientException;

/**
 * Function for auto complete.
 *
 * @param <DTO> the DTO
 * @author ham
 */
@FunctionalInterface
public interface AutoCompleteFunction<DTO>
{
    List<DTO> autoComplete(List<Pair<String, Object>> restricts) throws PnetDataClientException;
}
