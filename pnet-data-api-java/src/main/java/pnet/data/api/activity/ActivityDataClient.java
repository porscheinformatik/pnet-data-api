package pnet.data.api.activity;

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
 * Data-API client for {@link ActivityDataDTO}s.
 *
 * @author ham
 */
@Service
public class ActivityDataClient extends AbstractPnetDataApiClient<ActivityDataClient> {

    @Autowired
    public ActivityDataClient(PnetDataApiContext context) {
        super(context);
    }

    public ActivityDataGet get() {
        return new ActivityDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ActivityDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ActivityDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/activities/details")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ActivityDataDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public ActivityDataSearch search() {
        return new ActivityDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ActivityItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ActivityItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/activities/search")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ActivityItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public ActivityDataAutoComplete autoComplete() {
        return new ActivityDataAutoComplete(this::autoComplete, Collections.emptyList());
    }

    protected List<ActivityAutoCompleteDTO> autoComplete(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall ->
            restCall
                .parameters(restricts)
                .path("/api/v1/activities/autocomplete")
                .get(
                    new GenericType.Of<List<ActivityAutoCompleteDTO>>() {
                        // intentionally left blank
                    }
                )
        );
    }

    public ActivityDataFind find() {
        return new ActivityDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ActivityItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ActivityItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/activities/find")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ActivityItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::find);
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<ActivityItemDTO> next(String scrollId) throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ActivityItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .path("/api/v1/activities/next/{scrollId}")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ActivityItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }
}
