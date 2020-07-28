package pnet.data.api.util;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;

/**
 * An autoComplete query.
 *
 * @author ham
 * @param <DTO> the type of DTO
 */
public interface AutoComplete<DTO>
{

    List<DTO> execute(Locale language, String query) throws PnetDataClientException;

}
