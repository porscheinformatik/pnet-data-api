package pnet.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;
import pnet.data.api.person.ActivePersonCompanyLinkDTO;
import pnet.data.api.person.ActivePersonFunctionLinkDTO;
import pnet.data.api.person.PersonAggregationsDTO;
import pnet.data.api.person.PersonAutoCompleteDTO;
import pnet.data.api.person.PersonDataAutoComplete;
import pnet.data.api.person.PersonDataClient;
import pnet.data.api.person.PersonDataDTO;
import pnet.data.api.person.PersonDataFind;
import pnet.data.api.person.PersonDataGet;
import pnet.data.api.person.PersonDataSearch;
import pnet.data.api.person.PersonItemDTO;
import pnet.data.api.person.PersonTypeFilter;
import pnet.data.api.util.CLI;
import pnet.data.api.util.ImageType;
import pnet.data.api.util.Resource;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictPersonType;
import pnet.data.api.util.Table;

public final class PnetRestClientPersons implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final PersonDataClient client;

    PnetRestClientPersons(PnetRestClient pnetRestClient, PersonDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    private final List<PersonTypeFilter> restrictedPersonTypes = new ArrayList<>();

    @CLI.Command(
        name = { "get person by id", "get persons by id" },
        format = "<ID...>",
        description = "Returns all details of persons with the specified ids."
    )
    public void getPersonById(Integer... ids) throws PnetDataClientException {
        PersonDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByIds(
            Arrays.asList(ids),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get person by external id", "get persons by external id" },
        format = "<EXTERNALID...>",
        description = "Returns all details of persons with the specified external ids."
    )
    public void getPersonByExternalId(String... externalIds) throws PnetDataClientException {
        PersonDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByExternalIds(
            Arrays.asList(externalIds),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get person by guid", "get persons by guid" },
        format = "<GUID...>",
        description = "Returns all details of persons with the specified guids."
    )
    public void getPersonByGuid(String... guids) throws PnetDataClientException {
        PersonDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByGuids(
            Arrays.asList(guids),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get person by preferredUserId", "get persons by preferredUserId" },
        format = "<PREFID...>",
        description = "Returns all details of persons with the specified preferredUserIds."
    )
    public void getPersonByPreferredUserId(String... preferredUserIds) throws PnetDataClientException {
        PersonDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByPreferredUserIds(
            Arrays.asList(preferredUserIds),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get person by email", "get persons by email" },
        format = "<EMAIL...>",
        description = "Returns all details of persons with the specified emails."
    )
    public void getPersonByEmail(String... emails) throws PnetDataClientException {
        PersonDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByEmails(
            Arrays.asList(emails),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get person by personnel number", "get persons by personnel number" },
        format = "<PERSNUMBER...>",
        description = "Returns all details of persons with the specified personnelNumbers."
    )
    public void getPersonByPersonnelNumber(String... personnelNumbers) throws PnetDataClientException {
        PersonDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<PersonDataDTO> result = query.allByPersonnelNumbers(
            Arrays.asList(personnelNumbers),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all persons", description = "Exports all persons available for the current user.")
    public void exportAllPersons() throws PnetDataClientException {
        PersonDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<PersonItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated persons",
        format = "[updatedAfter]",
        description = "Exports all persons available for the current user, that have been updated since yesterday."
    )
    public void exportAllUpdatedPersons(String updatedAfter) throws PnetDataClientException {
        PersonDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<PersonItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(name = "find matching persons", description = "Find persons matching the restrictions.")
    public void findMatchingPersons() throws PnetDataClientException {
        PersonDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find persons by personnel number", "find person by personnel number" },
        format = "<NUMBER...>",
        description = "Find persons by personnel number."
    )
    public void findPersonsByPersonnelNumber(String... numbers) throws PnetDataClientException {
        PersonDataFind query = pnetRestClient.restrict(client.find().personnelNumber(numbers));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find persons by email", "find person by email" },
        format = "<EMAIL...>",
        description = "Find persons by email."
    )
    public void findPersonsByEmail(String... emails) throws PnetDataClientException {
        PersonDataFind query = pnetRestClient.restrict(client.find().email(emails));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find persons by salesman number", "find person by salesman number" },
        format = "<NUMBER...>",
        description = "Find persons by salesman number."
    )
    public void findPersonsBySalesmanNumber(String... numbers) throws PnetDataClientException {
        PersonDataFind query = pnetRestClient.restrict(client.find().numberType("NT_VERK_NR").number(numbers));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find persons by id", "find person by id" },
        format = "<ID...>",
        description = "Find a person by id."
    )
    public void findPersonById(Integer... ids) throws PnetDataClientException {
        PersonDataFind query = pnetRestClient.restrict(client.find().id(ids));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find persons by company", "find person by company" },
        format = "<COMPANY-MC...>",
        description = "Find persons at a specific company."
    )
    public void findPersonsByCompany(String... matchcodes) throws PnetDataClientException {
        PersonDataFind query = pnetRestClient.restrict(client.find().company(matchcodes));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find persons by role", "find person by role" },
        format = "<ROLE-MC...>",
        description = "Find persons by functions and activities."
    )
    public void findPersonsByRole(String... matchcodes) throws PnetDataClientException {
        PersonDataFind query = pnetRestClient.restrict(client.find().role(matchcodes));
        PnetDataClientResultPage<PersonItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(name = { "search persons", "search person" }, format = "<QUERY>", description = "Search for a person.")
    public void searchPersons(String... qs) throws PnetDataClientException {
        PersonDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPageWithAggregations<PersonItemDTO, PersonAggregationsDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "auto complete persons", "auto complete person" },
        format = "<QUERY>",
        description = "Auto complete the name of person."
    )
    public void autoCompletePersons(String... qs) throws PnetDataClientException {
        PersonDataAutoComplete query = pnetRestClient.restrict(client.autoComplete());
        List<PersonAutoCompleteDTO> result = query.execute(pnetRestClient.getLanguage(), PnetRestClient.joinQuery(qs));

        pnetRestClient.printResults(result, this::populateAutoComplete);
    }

    @CLI.Command(
        name = "get portrait of person",
        format = "<ID>",
        description = "Shows the portrait image of the person."
    )
    public void getPersonPortraitById(Integer id) throws PnetDataClientException {
        Optional<Resource> portrait = client.portrait(id, ImageType.ORIGINAL);

        if (portrait.isEmpty()) {
            pnetRestClient.getCli().error("Image not found.");
            return;
        }

        PnetRestClient.showImage("Portrait of Person " + id, portrait.get().toImage());
    }

    @CLI.Command(
        name = "get thumbnail of person",
        format = "<ID>",
        description = "Shows the thumbnail portrait image of the person."
    )
    public void getPersonPortraitThumbnailById(Integer id) throws PnetDataClientException {
        Optional<Resource> portrait = client.portrait(id, ImageType.THUMBNAIL);

        if (portrait.isEmpty()) {
            pnetRestClient.getCli().error("Image not found.");
            return;
        }

        PnetRestClient.showImage("Portrait thumbnail of Person " + id, portrait.get().toImage());
    }

    private void populateAutoComplete(Table table, PersonAutoCompleteDTO dto) {
        table.addRow(dto.getPersonId(), dto.getLabel(), dto.getDescription(), dto.getScore());
    }

    private void populateTable(Table table, PersonItemDTO dto) {
        table.addRow(
            dto.getPersonId(),
            dto.getPersonnelNumber(),
            dto.getFormOfAddress(),
            dto.getAcademicTitle(),
            dto.getFirstName(),
            dto.getLastName(),
            dto.getAcademicTitlePostNominal(),
            dto.getAdministrativeTenant(),
            dto.getTaxNumber(),
            dto.getBdoId(),
            dto.getTapId(),
            dto.getCompanies() != null
                ? dto
                      .getCompanies()
                      .stream()
                      .filter(link -> Objects.equals(dto.getContactCompanyId(), link.getCompanyId()))
                      .map(ActivePersonCompanyLinkDTO::getCompanyLabelWithNumber)
                      .collect(Collectors.joining(", "))
                : null,
            dto.getFunctions() != null
                ? dto
                      .getFunctions()
                      .stream()
                      .filter(ActivePersonFunctionLinkDTO::isMainFunction)
                      .map(ActivePersonFunctionLinkDTO::getLabel)
                      .collect(Collectors.joining(", "))
                : null,
            dto.getLastUpdate(),
            dto.getScore()
        );
    }

    @CLI.Command(
        name = { "restrict person types", "restrict person type" },
        format = "[<FIELDS>...]",
        description = "Places a restriction for person types."
    )
    public void restrictPersonTypes(PersonTypeFilter... personTypes) {
        if (personTypes != null && personTypes.length > 0) {
            restrictedPersonTypes.addAll(Arrays.asList(personTypes));
        }

        pnetRestClient.getCli().info("Persons are restricted to following types: %s", restrictedPersonTypes);
    }

    @CLI.Command(name = "clear person type restriction", description = "Removes all restrictions to person types.")
    public void clearPersonTypeRestrictions() {
        pnetRestClient.getCli().info("Remove person type restrictions.");

        clearRestrictions();
    }

    @Override
    public void clearRestrictions() {
        restrictedPersonTypes.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Restrict<T>> T applyRestrictions(T request) {
        if (request instanceof RestrictPersonType<?> && !restrictedPersonTypes.isEmpty()) {
            pnetRestClient.getCli().info("A restriction for person types is in place: %s", restrictedPersonTypes);
            request = ((RestrictPersonType<T>) request).types(restrictedPersonTypes);
        }
        return request;
    }
}
