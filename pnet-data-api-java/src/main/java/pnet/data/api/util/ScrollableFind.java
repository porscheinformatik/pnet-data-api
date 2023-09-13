package pnet.data.api.util;

import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A find query.
 *
 * @author ham
 * @param <DTO> the type of DTO
 */
public interface ScrollableFind<DTO> extends Find<DTO>
{
    PnetDataClientResultPage<DTO> executeAndScroll(Locale language, int itemsPerPage) throws PnetDataClientException;
}
