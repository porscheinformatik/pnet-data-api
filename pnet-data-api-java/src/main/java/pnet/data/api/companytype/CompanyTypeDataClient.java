package pnet.data.api.companytype;

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
import pnet.data.api.function.FunctionAutoCompleteDTO;
import pnet.data.api.function.FunctionDataAutoComplete;
import pnet.data.api.util.Pair;

/**
 * Implementation of the {@link AbstractPnetDataApiClient}.
 *
 * @author ham
 */
@Service
public class CompanyTypeDataClient extends AbstractPnetDataApiClient<CompanyTypeDataClient>
{
    @Autowired
    public CompanyTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public CompanyTypeDataGet get()
    {
        return new CompanyTypeDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyTypeDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companytypes/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public CompanyTypeDataSearch search()
    {
        return new CompanyTypeDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyTypeItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companytypes/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public CompanyTypeDataAutoComplete autoComplete()
    {
        return new CompanyTypeDataAutoComplete(this::autoComplete, Collections.emptyList());
    }

    protected List<CompanyTypeAutoCompleteDTO> autoComplete(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> restCall
            .parameters(restricts)
            .path("/api/v1/companytypes/autocomplete")
            .get(new GenericType.Of<List<CompanyTypeAutoCompleteDTO>>()
            {
                // intentionally left blank
            }));
    }

    public CompanyTypeDataFind find()
    {
        return new CompanyTypeDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyTypeItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companytypes/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
