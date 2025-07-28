package pnet.data.api.company;

import at.porscheinformatik.happyrest.GenericType;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.DefaultPnetDataClientResultPageWithAggregations;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link CompanyDataDTO}s.
 */
@Service
public class CompanyDataClient extends AbstractPnetDataApiClient<CompanyDataClient> {

    @Autowired
    public CompanyDataClient(PnetDataApiContext context) {
        super(context);
    }

    public CompanyDataGet get() {
        return new CompanyDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companies/details")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyDataDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public CompanyDataSearch search() {
        return new CompanyDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPageWithAggregations<CompanyItemDTO, CompanyAggregationsDTO> search(
        List<Pair<String, Object>> restricts
    ) throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPageWithAggregations<CompanyItemDTO, CompanyAggregationsDTO> resultPage =
                restCall
                    .parameters(restricts)
                    .path("/api/v1/companies/search")
                    .get(
                        new GenericType.Of<
                            DefaultPnetDataClientResultPageWithAggregations<CompanyItemDTO, CompanyAggregationsDTO>
                        >() {
                            // intentionally left blank
                        }
                    );

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public CompanyDataAutoComplete autoComplete() {
        return new CompanyDataAutoComplete(this::autoComplete, Collections.emptyList());
    }

    protected List<CompanyAutoCompleteDTO> autoComplete(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall ->
            restCall
                .parameters(restricts)
                .path("/api/v1/companies/autocomplete")
                .get(
                    new GenericType.Of<List<CompanyAutoCompleteDTO>>() {
                        // intentionally left blank
                    }
                )
        );
    }

    public CompanyDataFind find() {
        return new CompanyDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companies/find")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::find);
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<CompanyItemDTO> next(String scrollId) throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .path("/api/v1/companies/next/{scrollId}")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }
}
