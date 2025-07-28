package pnet.data.api.application;

import at.porscheinformatik.happyrest.GenericType;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link ApplicationDataDTO}s.
 *
 * @author cet
 */
@Service
public class ApplicationDataClient extends AbstractPnetDataApiClient<ApplicationDataClient> {

    public ApplicationDataClient(PnetDataApiContext context) {
        super(context);
    }

    public ApplicationDataGet get() {
        return new ApplicationDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ApplicationDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ApplicationDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/applications/details")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationDataDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public ApplicationDataSearch search() {
        return new ApplicationDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ApplicationItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ApplicationItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/applications/search")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public ApplicationDataAutoComplete autoComplete() {
        return new ApplicationDataAutoComplete(this::autoComplete, Collections.emptyList());
    }

    protected List<ApplicationAutoCompleteDTO> autoComplete(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall ->
            restCall
                .parameters(restricts)
                .path("/api/v1/applications/autocomplete")
                .get(
                    new GenericType.Of<List<ApplicationAutoCompleteDTO>>() {
                        // intentionally left blank
                    }
                )
        );
    }

    public ApplicationDataFind find() {
        return new ApplicationDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ApplicationItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ApplicationItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/applications/find")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::find);
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<ApplicationItemDTO> next(String scrollId) throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ApplicationItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .path("/api/v1/applications/next/{scrollId}")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }
}
