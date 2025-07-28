package pnet.data.api.externalbrand;

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
 * Client for {@link ExternalBrandDataDTO}s.
 *
 * @author cet
 */
@Service
public class ExternalBrandDataClient extends AbstractPnetDataApiClient<ExternalBrandDataClient> {

    @Autowired
    public ExternalBrandDataClient(PnetDataApiContext context) {
        super(context);
    }

    public ExternalBrandDataGet get() {
        return new ExternalBrandDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ExternalBrandDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ExternalBrandDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/externalbrands/details")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ExternalBrandDataDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public ExternalBrandDataSearch search() {
        return new ExternalBrandDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ExternalBrandItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ExternalBrandItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/externalbrands/search")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ExternalBrandItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public ExternalBrandDataFind find() {
        return new ExternalBrandDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ExternalBrandItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ExternalBrandItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/externalbrands/find")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ExternalBrandItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
