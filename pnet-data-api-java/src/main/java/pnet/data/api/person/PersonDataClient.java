package pnet.data.api.person;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestResponseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
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
public class PersonDataClient extends AbstractPnetDataApiClient<PersonDataClient> {

    public PersonDataClient(PnetDataApiContext context) {
        super(context);
    }

    public PersonDataGet get() {
        return new PersonDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<PersonDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<PersonDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/persons/details")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<PersonDataDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public PersonDataSearch search() {
        return new PersonDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPageWithAggregations<PersonItemDTO, PersonAggregationsDTO> search(
        List<Pair<String, Object>> restricts
    ) throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPageWithAggregations<PersonItemDTO, PersonAggregationsDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/persons/search")
                .get(
                    new GenericType.Of<
                        DefaultPnetDataClientResultPageWithAggregations<PersonItemDTO, PersonAggregationsDTO>
                    >() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public PersonDataAutoComplete autoComplete() {
        return new PersonDataAutoComplete(this::autoComplete, Collections.emptyList());
    }

    protected List<PersonAutoCompleteDTO> autoComplete(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall ->
            restCall
                .parameters(restricts)
                .path("/api/v1/persons/autocomplete")
                .get(
                    new GenericType.Of<List<PersonAutoCompleteDTO>>() {
                        // intentionally left blank
                    }
                )
        );
    }

    public PersonDataFind find() {
        return new PersonDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<PersonItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<PersonItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/persons/find")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<PersonItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::find);
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<PersonItemDTO> next(String scrollId) throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<PersonItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .path("/api/v1/persons/next/{scrollId}")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<PersonItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    public Optional<Resource> portrait(Integer personId, ImageType imageType) throws PnetDataClientException {
        return invoke(restCall -> {
            try {
                RestResponse<byte[]> response = restCall
                    .variable("id", personId)
                    .parameter("type", imageType)
                    .path("api/v1/persons/portrait/{id}")
                    .invoke(RestMethod.GET, byte[].class);

                return Optional.of(new Resource(response.getContentType().toString(), response.getBody()));
            } catch (RestResponseException e) {
                if (e.getStatusCode() == 404) {
                    return Optional.empty();
                }

                throw new PnetDataClientException("Image request for person " + personId + " failed", e);
            }
        });
    }
}
