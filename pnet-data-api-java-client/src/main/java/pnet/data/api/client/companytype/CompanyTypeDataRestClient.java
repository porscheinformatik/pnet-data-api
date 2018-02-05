package pnet.data.api.client.companytype;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.ResultPage;
import pnet.data.api.Tenant;
import pnet.data.api.client.AbstractPnetDataApiRestClient;
import pnet.data.api.client.PnetDataApiContext;
import pnet.data.api.client.PnetDataApiLoginException;
import pnet.data.api.companytype.CompanyTypeDataDTO;
import pnet.data.api.companytype.CompanyTypeDataFacade;
import pnet.data.api.companytype.CompanyTypeItemDTO;
import pnet.data.api.companytype.CompanyTypeMatchcode;

/**
 * Implementation of the {@link CompanyTypeDataFacade}.
 *
 * @author ham
 */
@Service
public class CompanyTypeDataRestClient extends AbstractPnetDataApiRestClient<CompanyTypeDataRestClient>
    implements CompanyTypeDataFacade
{

    private static final GenericType<ResultPage<CompanyTypeDataDTO>> DATA_TYPE =
        new GenericType<ResultPage<CompanyTypeDataDTO>>()
        {
        };

    public CompanyTypeDataRestClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    protected CompanyTypeDataRestClient newInstance(PnetDataApiContext context)
    {
        return new CompanyTypeDataRestClient(context);
    }

    @Override
    public CompanyTypeDataDTO getByMatchcode(CompanyTypeMatchcode matchcode) throws PnetDataApiLoginException
    {
        return getAll(Arrays.asList(matchcode), 0, 1).first();
    }

    @Override
    public ResultPage<CompanyTypeDataDTO> getAll(Collection<CompanyTypeMatchcode> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiLoginException
    {
        return createRestCall()
            .parameters("mc", matchcodes)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companytypes/details", ResultPage.class);
    }

    @Override
    public ResultPage<CompanyTypeItemDTO> search(String language, String query, int pageIndex, int itemsPerPage,
        Collection<Tenant> tenants) throws PnetDataApiLoginException
    {
        return null;
    }

    @Override
    public ResultPage<CompanyTypeItemDTO> find(String language, Collection<CompanyTypeMatchcode> matchcodes,
        Collection<Tenant> tenants, LocalDateTime updatedAfter, int pageIndex, int itemsPerPage)
        throws PnetDataApiLoginException
    {
        return null;
    }

}
