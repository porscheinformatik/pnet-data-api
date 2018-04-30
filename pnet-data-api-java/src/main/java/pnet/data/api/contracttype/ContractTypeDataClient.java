package pnet.data.api.contracttype;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.company.ContractTypeDataFind;
import pnet.data.api.util.GetByMatchcode;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link ContractTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class ContractTypeDataClient extends AbstractPnetDataApiClient<ContractTypeDataClient>
    implements GetByMatchcode<ContractTypeDataDTO>
{

    public ContractTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<ContractTypeDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<ContractTypeDataDTO> resultPage = createRestCall() //
            .parameters("mc", matchcodes)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/contracttypes/details",
                new GenericType.Of<DefaultPnetDataClientResultPage<ContractTypeDataDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public ContractTypeDataSearch search()
    {
        return new ContractTypeDataSearch(getMapper(), this::search, null);
    }

    protected PnetDataClientResultPage<ContractTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<ContractTypeItemDTO> resultPage = createRestCall()
            .parameter("l", language)
            .parameter("q", query)
            .parameters(restricts)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/contracttypes/search",
                new GenericType.Of<DefaultPnetDataClientResultPage<ContractTypeItemDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public ContractTypeDataFind find()
    {
        return new ContractTypeDataFind(getMapper(), this::find, null);
    }

    protected PnetDataClientResultPage<ContractTypeItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<ContractTypeItemDTO> resultPage = createRestCall()
            .parameters(restricts)
            .parameter("l", language)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/contracttypes/find",
                new GenericType.Of<DefaultPnetDataClientResultPage<ContractTypeItemDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }
}
