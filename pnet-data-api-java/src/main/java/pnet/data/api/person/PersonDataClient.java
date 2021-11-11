package pnet.data.api.person;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestResponseException;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.DefaultPnetDataClientResultPageWithAggregations;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.ImageType;
import pnet.data.api.util.Pair;
import pnet.data.api.util.Resource;

/**
 * Data-API client for {@link PersonDataDTO}s.
 */
@Service
public class PersonDataClient extends AbstractPnetDataApiClient<PersonDataClient>
{

    public PersonDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public PersonDataGet get()
    {
        return new PersonDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<PersonDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<PersonDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/persons/details", new GenericType.Of<DefaultPnetDataClientResultPage<PersonDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public PersonDataSearch search()
    {
        return new PersonDataSearch(this::search, null);
    }

    protected PnetDataClientResultPageWithAggregations<PersonItemDTO, PersonAggregationsDTO> search(Locale language,
        String query, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPageWithAggregations<PersonItemDTO, PersonAggregationsDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/persons/search",
                    new GenericType.Of<DefaultPnetDataClientResultPageWithAggregations<PersonItemDTO, PersonAggregationsDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public PersonDataAutoComplete autoComplete()
    {
        return new PersonDataAutoComplete(this::autoComplete, null);
    }

    protected List<PersonAutoCompleteDTO> autoComplete(Locale language, String query,
        List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        return invoke(restCall -> restCall
            .parameter("l", language)
            .parameter("q", query)
            .parameters(restricts)
            .get("/api/v1/persons/autocomplete", new GenericType.Of<List<PersonAutoCompleteDTO>>()
            {
                // intentionally left blank
            }));
    }

    public PersonDataFind find()
    {
        return new PersonDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<PersonItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<PersonItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/persons/find", new GenericType.Of<DefaultPnetDataClientResultPage<PersonItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<PersonItemDTO> next(String scrollId) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<PersonItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .get("/api/v1/persons/next/{scrollId}",
                    new GenericType.Of<DefaultPnetDataClientResultPage<PersonItemDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    public Optional<Resource> portrait(Integer personId, ImageType imageType) throws PnetDataClientException
    {
        return invoke(restCall -> {
            try
            {
                RestResponse<byte[]> response = restCall
                    .variable("id", personId)
                    .parameter("type", imageType)
                    .invoke(RestMethod.GET, "api/v1/persons/portrait/{id}", byte[].class);

                return Optional.of(new Resource(response.getContentType().toString(), response.getBody()));
            }
            catch (RestResponseException e)
            {
                if (e.getStatusCode() == 404)
                {
                    return Optional.empty();
                }

                throw new PnetDataClientException("Image request for person %s failed", e, personId);
            }
        });
    }
}
