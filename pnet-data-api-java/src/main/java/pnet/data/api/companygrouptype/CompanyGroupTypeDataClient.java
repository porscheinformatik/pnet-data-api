package pnet.data.api.companygrouptype;

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
 * Data-API client for {@link CompanyGroupTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class CompanyGroupTypeDataClient extends AbstractPnetDataApiClient<CompanyGroupTypeDataClient> {

    @Autowired
    public CompanyGroupTypeDataClient(PnetDataApiContext context) {
        super(context);
    }

    public CompanyGroupTypeDataGet get() {
        return new CompanyGroupTypeDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyGroupTypeDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companygrouptypes/details")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupTypeDataDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public CompanyGroupTypeDataSearch search() {
        return new CompanyGroupTypeDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyGroupTypeItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companygrouptypes/search")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public CompanyGroupTypeDataFind find() {
        return new CompanyGroupTypeDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyGroupTypeItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companygrouptypes/find")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
