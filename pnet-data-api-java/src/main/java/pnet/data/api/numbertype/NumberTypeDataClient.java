package pnet.data.api.numbertype;

import at.porscheinformatik.happyrest.GenericType;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Client for {@link NumberTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class NumberTypeDataClient extends AbstractPnetDataApiClient<NumberTypeDataClient> {

    @Autowired
    public NumberTypeDataClient(PnetDataApiContext context) {
        super(context);
    }

    public NumberTypeDataGet get() {
        return new NumberTypeDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<NumberTypeDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<NumberTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/numbertypes/details")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<NumberTypeDataDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public NumberTypeDataSearch search() {
        return new NumberTypeDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<NumberTypeItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<NumberTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/numbertypes/search")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<NumberTypeItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public NumberTypeDataFind find() {
        return new NumberTypeDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<NumberTypeItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<NumberTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/numbertypes/find")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<NumberTypeItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
