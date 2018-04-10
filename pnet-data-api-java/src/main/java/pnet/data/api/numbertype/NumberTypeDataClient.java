package pnet.data.api.numbertype;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.GetByMatchcode;
import pnet.data.api.util.Pair;

@Service
public class NumberTypeDataClient extends AbstractPnetDataApiClient<NumberTypeDataClient>
    implements GetByMatchcode<NumberTypeDataDTO>
{

    public NumberTypeDataClient(ObjectMapper mapper, PnetDataApiContext context)
    {
        super(mapper, context);
    }

    @Override
    public PnetDataClientResultPage<NumberTypeDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<NumberTypeDataDTO> resultPage = createRestCall() //
            .parameters("mc", matchcodes)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/numbertypes/details", new GenericType.Of<DefaultPnetDataClientResultPage<NumberTypeDataDTO>>()
            {
            });

        resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public NumberTypeDataSearch search()
    {
        return new NumberTypeDataSearch(getMapper(), this::search, null);
    }

    protected PnetDataClientResultPage<NumberTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<NumberTypeItemDTO> resultPage = createRestCall()
            .parameter("l", language)
            .parameter("q", query)
            .parameters(restricts)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/numbertypes/search", new GenericType.Of<DefaultPnetDataClientResultPage<NumberTypeItemDTO>>()
            {
            });

        resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public NumberTypeDataFind find()
    {
        return new NumberTypeDataFind(getMapper(), this::find, null);
    }

    protected PnetDataClientResultPage<NumberTypeItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<NumberTypeItemDTO> resultPage = createRestCall()
            .parameters(restricts)
            .parameter("l", language)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/numbertypes/find", new GenericType.Of<DefaultPnetDataClientResultPage<NumberTypeItemDTO>>()
            {
            });

        resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }
}
