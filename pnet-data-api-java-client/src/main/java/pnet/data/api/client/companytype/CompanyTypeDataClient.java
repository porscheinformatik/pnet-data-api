package pnet.data.api.client.companytype;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.companytype.CompanyTypeDataDTO;
import pnet.data.api.companytype.CompanyTypeDataFacade;
import pnet.data.api.companytype.CompanyTypeItemDTO;

/**
 * Implementation of the {@link CompanyTypeDataFacade}.
 *
 * @author ham
 */
@Service
public class CompanyTypeDataClient extends AbstractPnetDataApiClient<CompanyTypeDataClient>
    implements CompanyTypeDataFacade
{

    //    private static final GenericType<DefaultPnetDataClientResultPage<CompanyTypeDataDTO>> DATA_TYPE =
    //        new GenericType<DefaultPnetDataClientResultPage<CompanyTypeDataDTO>>()
    //        {
    //        };

    public CompanyTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    protected CompanyTypeDataClient newInstance(PnetDataApiContext context)
    {
        return new CompanyTypeDataClient(context);
    }

    @Override
    public CompanyTypeDataDTO getByMatchcode(String matchcode) throws PnetDataApiException
    {
        return getAll(Arrays.asList(matchcode), 0, 1).first();
    }

    @Override
    public PnetDataClientResultPage<CompanyTypeDataDTO> getAll(List<String> matchcodes, int pageIndex, int itemsPerPage)
        throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyTypeDataDTO> resultPage = createRestCall() //
            .parameters("mc", matchcodes)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companytypes/details",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeDataDTO>>() {});

        resultPage.setNextPageSupplier(() -> getAll(matchcodes, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    @Override
    public PnetDataClientResultPage<CompanyTypeItemDTO> search(String language, String query, int pageIndex,
        int itemsPerPage, List<String> tenants) throws PnetDataApiException
    {
        return null;
    }

    @Override
    public PnetDataClientResultPage<CompanyTypeItemDTO> find(String language, List<String> matchcodes,
        List<String> tenants, LocalDateTime updatedAfter, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        return null;
    }

}
