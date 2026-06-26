package pnet.data.api;

import java.util.Arrays;
import java.util.List;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.companygroup.CompanyGroupDataClient;
import pnet.data.api.companygroup.CompanyGroupDataDTO;
import pnet.data.api.companygroup.CompanyGroupDataGet;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataClient;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataFind;
import pnet.data.api.companygrouptype.CompanyGroupTypeItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Table;

public final class PnetRestClientCompanyGroups implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final CompanyGroupDataClient client;
    private final CompanyGroupTypeDataClient companyGroupTypeClient;

    PnetRestClientCompanyGroups(
        PnetRestClient pnetRestClient,
        CompanyGroupDataClient client,
        CompanyGroupTypeDataClient companyGroupTypeClient
    ) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
        this.companyGroupTypeClient = companyGroupTypeClient;
    }

    @CLI.Command(
        name = { "get company group by leading company id", "get company groups by leading company id" },
        format = "<COMPANY-ID...>",
        description = "Returns the company groups with the specified ids."
    )
    public void getCompanyGroupByLeadingCompanyIds(Integer... ids) throws PnetDataClientException {
        CompanyGroupDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByLeadingCompanyIds(
            Arrays.asList(ids),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "get company group by leading company number", "get company groups by leading company number" },
        format = "<COMPANY-NUMBER...>",
        description = "Returns the company groups with the specified numbers."
    )
    public void getCompanyGroupByLeadingCompanyNumbers(String... numbers) throws PnetDataClientException {
        CompanyGroupDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByLeadingCompanyNumbers(
            Arrays.asList(numbers),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "get company group by leading company mc", "get company groups by leading company mc" },
        format = "<COMPANY-MC...>",
        description = "Returns the company groups with the specified matchcodes."
    )
    public void getCompanyGroupByLeadingCompanyMatchcodes(String... matchcodes) throws PnetDataClientException {
        CompanyGroupDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByLeadingCompanies(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "get company group by company id", "get company groups by company id" },
        format = "<COMPANY-ID...>",
        description = "Returns the company groups with the specified ids."
    )
    public void getCompanyGroupByCompanyIds(Integer... ids) throws PnetDataClientException {
        CompanyGroupDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanyIds(
            Arrays.asList(ids),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "get company group by company number", "get company groups by company number" },
        format = "<COMPANY-NUMBER...>",
        description = "Returns the company groups with the specified numbers."
    )
    public void getCompanyGroupByCompanyNumbers(String... numbers) throws PnetDataClientException {
        CompanyGroupDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanyNumbers(
            Arrays.asList(numbers),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "get company group by company mc", "get company groups by company mc" },
        format = "<COMPANY-MC...>",
        description = "Returns the company groups with the specified matchcodes."
    )
    public void getCompanyGroupByCompanyMatchcodes(String... matchcodes) throws PnetDataClientException {
        CompanyGroupDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanies(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export all company groups",
        format = "[<GROUP-MC...>]",
        description = "Exports all company groups."
    )
    public void exportAllCompanyGroups(String... matchcodes) throws PnetDataClientException {
        CompanyGroupTypeDataFind find = companyGroupTypeClient.find();

        if (matchcodes != null && matchcodes.length > 0) {
            find = find.matchcode(matchcodes);
        }

        List<String> companyGroupTypeMatchcodes = pnetRestClient
            .restrict(find)
            .execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize())
            .stream()
            .map(CompanyGroupTypeItemDTO::getMatchcode)
            .toList();

        CompanyGroupDataGet query = pnetRestClient.restrict(client.get().types(companyGroupTypeMatchcodes));
        PnetDataClientResultPage<CompanyGroupDataDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(25)
            : query.execute(0, 25);

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    private void populateTable(Table table, CompanyGroupDataDTO dto) {
        table.addRow(
            dto.getLeadingCompanyId(),
            dto.getLeadingCompanyMatchcode(),
            dto.getLeadingCompanyNumber(),
            dto.getMembers()
        );
    }
}
