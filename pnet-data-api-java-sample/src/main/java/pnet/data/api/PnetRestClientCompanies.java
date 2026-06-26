package pnet.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.company.CompanyAutoCompleteDTO;
import pnet.data.api.company.CompanyDataAutoComplete;
import pnet.data.api.company.CompanyDataClient;
import pnet.data.api.company.CompanyDataDTO;
import pnet.data.api.company.CompanyDataFind;
import pnet.data.api.company.CompanyDataGet;
import pnet.data.api.company.CompanyDataSearch;
import pnet.data.api.company.CompanyItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictCompany;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictCountryCode;
import pnet.data.api.util.Table;

public final class PnetRestClientCompanies implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final CompanyDataClient client;

    PnetRestClientCompanies(PnetRestClient pnetRestClient, CompanyDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    private final List<Integer> restrictedCompanyIds = new ArrayList<>();
    private final List<String> restrictedCompanyMatchcodes = new ArrayList<>();
    private final List<String> restrictedCompanyNumbers = new ArrayList<>();
    private final List<String> restrictedCountryCodes = new ArrayList<>();

    @CLI.Command(
        name = { "get company by id", "get companies by id" },
        format = "<COMPANY-ID...>",
        description = "Returns the companies with the specified ids."
    )
    public void getCompaniesByIds(Integer... ids) throws PnetDataClientException {
        CompanyDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByIds(
            Arrays.asList(ids),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get company by mc", "get companies by mc" },
        format = "<COMPANY-MC...>",
        description = "Returns the companies with the specified matchcode."
    )
    public void getCompaniesByMatchcodes(String... matchcodes) throws PnetDataClientException {
        CompanyDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get company by vat", "get companies by vat" },
        format = "<COMPANY-VATIDNUMBER...>",
        description = "Returns the companies with the specified vat id numbers."
    )
    public void getCompaniesByVatIdNumbers(String... vatIdNumbers) throws PnetDataClientException {
        CompanyDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByVatIdNumbers(
            Arrays.asList(vatIdNumbers),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get company by number", "get companies by number" },
        format = "<COMPANY-NUMBER...>",
        description = "Returns the companies with the specified company numbers."
    )
    public void getCompaniesByCompanyNumbers(String... companyNumbers) throws PnetDataClientException {
        CompanyDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByCompanyNumbers(
            Arrays.asList(companyNumbers),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get company by iban", "get companies by iban" },
        format = "<COMPANY-IBAN...>",
        description = "Returns the company with the specified ibans."
    )
    public void getCompaniesByIbans(String... ibans) throws PnetDataClientException {
        CompanyDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByIbans(
            Arrays.asList(ibans),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get company by email", "get companies by email" },
        format = "<COMPANY-EMAIL...>",
        description = "Returns the company with the specified emails."
    )
    public void getCompaniesByEmails(String... emails) throws PnetDataClientException {
        CompanyDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByEmails(
            Arrays.asList(emails),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get company by dvr", "get companies by dvr" },
        format = "<COMPANY-DPRN...>",
        description = "Returns the companies with the specified data processing register numbers."
    )
    public void getCompaniesByDataProcessingRegisterNumbers(String... dataProcessingRegisterNumbers)
        throws PnetDataClientException {
        CompanyDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyDataDTO> result = query.allByDataProcessingRegisterNumbers(
            Arrays.asList(dataProcessingRegisterNumbers),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all companies", description = "Exports all companies.")
    public void exportAllCompanies() throws PnetDataClientException {
        CompanyDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<CompanyItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated companies",
        format = "[updatedAfter]",
        description = "Exports all companies updated since yesterday."
    )
    public void exportAllUpdatedCompanies(String updatedAfter) throws PnetDataClientException {
        CompanyDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<CompanyItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find companies by id", "find company by id" },
        format = "<ID...>",
        description = "Find companies by id."
    )
    public void findCompaniesByIds(Integer... ids) throws PnetDataClientException {
        CompanyDataFind query = pnetRestClient.restrict(client.find().id(ids));
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find companies by mc", "find company by mc" },
        format = "<MC...>",
        description = "Find companies by matchcode."
    )
    public void findCompaniesByMatchcodes(String... mcs) throws PnetDataClientException {
        CompanyDataFind query = pnetRestClient.restrict(client.find().matchcode(mcs));
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find companies by number", "find company by number" },
        format = "<NUMBER...>",
        description = "Find companies by company number."
    )
    public void findCompaniesByNumbers(String... numbers) throws PnetDataClientException {
        CompanyDataFind query = pnetRestClient.restrict(client.find().companyNumber(numbers));
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(name = { "search companies", "search company" }, format = "<QUERY>", description = "Query companies.")
    public void searchCompanies(String... qs) throws PnetDataClientException {
        CompanyDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<CompanyItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "auto complete companies", "auto complete company" },
        format = "<QUERY>",
        description = "Auto complete the name of companies."
    )
    public void autoCompleteCompanies(String... qs) throws PnetDataClientException {
        CompanyDataAutoComplete query = pnetRestClient.restrict(client.autoComplete());
        List<CompanyAutoCompleteDTO> result = query.execute(pnetRestClient.getLanguage(), PnetRestClient.joinQuery(qs));

        pnetRestClient.printResults(result, this::populateAutoComplete);
    }

    private void populateAutoComplete(Table table, CompanyAutoCompleteDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getScore());
    }

    private void populateTable(Table table, CompanyItemDTO dto) {
        table.addRow(
            dto.getCompanyId(),
            dto.getMatchcode(),
            dto.getLabel(),
            dto.getAdministrativeTenant(),
            dto.getBrands(),
            dto.getTenants(),
            dto.getTypes(),
            dto.getLastUpdate(),
            dto.getScore()
        );
    }

    @CLI.Command(
        name = { "restrict company ids", "restrict company id" },
        format = "<ID...>",
        description = "Places a restriction with company ids for subsequent operations."
    )
    public void restrictCompaniesById(Integer... ids) {
        if (ids != null && ids.length > 0) {
            restrictedCompanyIds.addAll(Arrays.asList(ids));
        }

        pnetRestClient.getCli().info("Requests are restricted to company ids: %s", restrictedCompanyIds);
    }

    @CLI.Command(
        name = { "restrict company mcs", "restrict company mc" },
        format = "<MC...>",
        description = "Places a restriction with company matchcodes for subsequent operations."
    )
    public void restrictCompaniesByMatchcode(String... matchcodes) {
        if (matchcodes != null && matchcodes.length > 0) {
            restrictedCompanyMatchcodes.addAll(Arrays.asList(matchcodes));
        }

        pnetRestClient.getCli().info("Requests are restricted to company matchcodes: %s", restrictedCompanyMatchcodes);
    }

    @CLI.Command(
        name = { "restrict company numbers", "restrict company number" },
        format = "<NUMBER...>",
        description = "Places a restriction with company numbers for subsequent operations."
    )
    public void restrictCompaniesByNumber(String... numbers) {
        if (numbers != null && numbers.length > 0) {
            restrictedCompanyNumbers.addAll(Arrays.asList(numbers));
        }

        pnetRestClient.getCli().info("Requests are restricted to company numbers: %s", restrictedCompanyNumbers);
    }

    @CLI.Command(
        name = { "restrict country codes", "restrict country code" },
        format = "<NUMBER...>",
        description = "Places a restriction with country codes for subsequent operations."
    )
    public void restrictCompaniesByCountryCode(String... countryCodes) {
        if (countryCodes != null && countryCodes.length > 0) {
            restrictedCountryCodes.addAll(Arrays.asList(countryCodes));
        }

        pnetRestClient.getCli().info("Requests are restricted to country codes: %s", restrictedCountryCodes);
    }

    @CLI.Command(
        name = { "clear company restrictions", "clear company restriction" },
        description = "Removes all restrictions for companies."
    )
    public void clearCompanyRestrictions() {
        pnetRestClient
            .getCli()
            .info(
                "Removed %s company restrictions.",
                restrictedCompanyIds.size() +
                    restrictedCompanyMatchcodes.size() +
                    restrictedCompanyNumbers.size() +
                    restrictedCountryCodes.size()
            );

        clearRestrictions();
    }

    @Override
    public void clearRestrictions() {
        restrictedCompanyIds.clear();
        restrictedCompanyMatchcodes.clear();
        restrictedCompanyNumbers.clear();
        restrictedCountryCodes.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Restrict<T>> T applyRestrictions(T request) {
        if (request instanceof RestrictCompanyId && !restrictedCompanyIds.isEmpty()) {
            pnetRestClient.getCli().info("A restriction for company ids is in place: %s", restrictedCompanyIds);
            request = ((RestrictCompanyId<T>) request).companyIds(restrictedCompanyIds);
        }
        if (request instanceof RestrictCompany && !restrictedCompanyMatchcodes.isEmpty()) {
            pnetRestClient
                .getCli()
                .info("A restriction for company matchcodes is in place: %s", restrictedCompanyMatchcodes);
            request = ((RestrictCompany<T>) request).companies(restrictedCompanyMatchcodes);
        }
        if (request instanceof RestrictCompanyNumber && !restrictedCompanyNumbers.isEmpty()) {
            pnetRestClient.getCli().info("A restriction for company numbers is in place: %s", restrictedCompanyNumbers);
            request = ((RestrictCompanyNumber<T>) request).companyNumbers(restrictedCompanyNumbers);
        }
        if (request instanceof RestrictCountryCode && !restrictedCountryCodes.isEmpty()) {
            pnetRestClient.getCli().info("A restriction for country codes is in place: %s", restrictedCountryCodes);
            request = ((RestrictCountryCode<T>) request).countryCodes(restrictedCountryCodes);
        }
        return request;
    }
}
