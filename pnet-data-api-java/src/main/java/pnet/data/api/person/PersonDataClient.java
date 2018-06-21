package pnet.data.api.person;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiAccessException;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.PnetDataApiServerException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.GetById;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link PersonDataDTO}s.
 */
@Service
public class PersonDataClient extends AbstractPnetDataApiClient<PersonDataClient> implements GetById<PersonDataDTO>
{

    public PersonDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<PersonDataDTO> getAllByIds(List<Integer> ids, int pageIndex, int itemsPerPage)
        throws PnetDataApiException
    {
        return getAll(ids, null, null, null, null, pageIndex, itemsPerPage);
    }

    public PersonDataDTO getByGuid(String guid) throws PnetDataApiException
    {
        return getAllByGuids(Arrays.asList(guid), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> getAllByGuids(List<String> guids, int pageIndex, int itemsPerPage)
        throws PnetDataApiException
    {
        return getAll(null, guids, null, null, null, pageIndex, itemsPerPage);
    }

    public PersonDataDTO getByPreferredUserId(String preferredUserId) throws PnetDataApiException
    {
        return getAllByPreferredUserIds(Arrays.asList(preferredUserId), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> getAllByPreferredUserIds(List<String> preferredUserIds,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        return getAll(null, null, preferredUserIds, null, null, pageIndex, itemsPerPage);
    }

    public PersonDataDTO getByEmail(String email) throws PnetDataApiException
    {
        return getAllByEmails(Arrays.asList(email), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> getAllByEmails(List<String> emails, int pageIndex, int itemsPerPage)
        throws PnetDataApiException
    {
        return getAll(null, null, null, emails, null, pageIndex, itemsPerPage);
    }

    /**
     * Returns the person with the specified personnel number. Leading zeros will be ignored.
     *
     * @param personnelNumber the personnel number
     * @return the person, null if not found
     * @throws PnetDataApiException on occasion
     */
    public PersonDataDTO getByPersonnelNumber(String personnelNumber) throws PnetDataApiException
    {
        return getAllByPersonnelNumbers(Arrays.asList(personnelNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<PersonDataDTO> getAllByPersonnelNumbers(List<String> personnelNumbers,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        return getAll(null, null, null, null, personnelNumbers, pageIndex, itemsPerPage);
    }

    protected PnetDataClientResultPage<PersonDataDTO> getAll(List<Integer> ids, List<String> guids,
        List<String> preferredUserIds, List<String> emails, List<String> personnelNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<PersonDataDTO> resultPage = createRestCall() //
                .parameters("id", ids)
                .parameters("guid", guids)
                .parameters("preferredUserId", preferredUserIds)
                .parameters("email", emails)
                .parameters("personnelNumber", personnelNumbers)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/persons/details", new GenericType.Of<DefaultPnetDataClientResultPage<PersonDataDTO>>()
                {
                });

            resultPage.setNextPageSupplier(
                () -> getAll(ids, guids, preferredUserIds, emails, personnelNumbers, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (ResourceAccessException e)
        {
            throw new PnetDataApiAccessException(e);
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException(e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public PersonDataSearch search()
    {
        return new PersonDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<PersonItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<PersonItemDTO> resultPage = createRestCall()
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/persons/search", new GenericType.Of<DefaultPnetDataClientResultPage<PersonItemDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (ResourceAccessException e)
        {
            throw new PnetDataApiAccessException(e);
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException(e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public PersonDataFind find()
    {
        return new PersonDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<PersonItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<PersonItemDTO> resultPage = createRestCall()
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/persons/find", new GenericType.Of<DefaultPnetDataClientResultPage<PersonItemDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (ResourceAccessException e)
        {
            throw new PnetDataApiAccessException(e);
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException(e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }
}
