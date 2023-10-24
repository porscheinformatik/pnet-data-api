package pnet.data.api.brand;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Client of {@link BrandDataDTO}s and {@link BrandItemDTO}s
 *
 * @author ham
 */
@Service
public class BrandDataClient extends AbstractPnetDataApiClient<BrandDataClient>
{

    @Autowired
    public BrandDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public BrandDataGet get()
    {
        return new BrandDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<BrandDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<BrandDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/brands/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<BrandDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public BrandDataSearch search()
    {
        return new BrandDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<BrandItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<BrandItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/brands/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<BrandItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public BrandDataFind find()
    {
        return new BrandDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<BrandItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<BrandItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/brands/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<BrandItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
