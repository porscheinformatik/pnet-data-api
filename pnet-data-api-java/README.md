# Partner.&#78;et Data API Java Client

This project contains a Java client for the Partner.&#78;et Data API.

[Swagger Documentation of the pnet-data-api](https://porscheinformatik.github.io/pnet-data-api/)

The Java client supports all requests to the Partner.&#78;et Data API. We use it within the Partner.&#78;et and all internal tests for the Data API are based on this client (so even, if this repo does not contain many tests, the client is fully tested).

An up-to-date version is available in the corporate Maven repository. If you do not have access to this repository, you have to build our own version (see [Building](#building))

# Using the Java client

The Java client has been designed to **reduce its dependencies to a minimum**. All dependencies are "provided", which means, you have to define them on your own - within your project. Currently you have the following options:

-   **Just use the DTOs**: You don't need any dependencies for this. You don't need Spring for this.
-   **Apache HTTP Client**: Use the Apache HTTP Client for the communication and Jackson for the JSON mapping and. You don't need Spring for this.
-   **Spring 5**: It uses Jackson for the JSON mapping and a Spring Web client (RestTemplate) for the communication.
-   **Spring 4**: It uses Jackson for the JSON mapping and a Spring Web client (RestTemplate) for the communication.

## Just use the DTOs

You will need the following dependencies:

```
<dependency>
    <groupId>at.porscheinformatik.pnet</groupId>
    <artifactId>pnet-data-api-java</artifactId>
</dependency>
```

That's it. Now, you may implement the client on your own ...

## Apache HTTP Client

You will need the following dependencies:

```
<dependency>
    <groupId>at.porscheinformatik.pnet</groupId>
    <artifactId>pnet-data-api-java</artifactId>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>

<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
</dependency>
```

Create the context:

```
ObjectMapper mapper = JacksonPnetDataApiModule.createObjectMapper();
RestLoggerAdapter loggerAdapter = PrintStreamLoggerAdapter.INSTANCE;
RestCallFactory factory = ApacheRestCallFactory.create(loggerAdapter, mapper);
PnetDataApiTokenRepository repository = new PnetDataApiTokenRepository(factory);
MutablePnetDataClientPrefs prefs = new MutablePnetDataClientPrefs(url, username, password);
PrefsBasedPnetDataApiContext context = new PrefsBasedPnetDataApiContext(repository, prefs);
```

And create the clients with that context:

```
PersonDataClient personDataClient = new PersonDataClient(context);
CompanyDataClient companyDataClient = new CompanyDataClient(context);
...
```

All classes are thread-safe, you can, and you should, reuse them as long as possible.

## Spring

You will need the following dependencies:

```
<dependency>
    <groupId>at.porscheinformatik.pnet</groupId>
    <artifactId>pnet-data-api-java</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
</dependency>
```

### Spring Version >= 5

Import the `PnetDataClientConfig` to your existing configuration. You will need to provide `PnetDataClientPrefs` containing your user data.

```
@Import(PnetDataClientConfig.class)
```

### Spring Version 4

Import the `PnetDataClientSpring4Config` to your existing configuration. You will need to provide `PnetDataClientPrefs` containing your user data.

```
@Import(PnetDataClientSpring4Config.class)
```

### Example

This is an example of a most basic configuration accessing the QA environment and getting the username/password from the application properties:

```
@Configuration
@Import(PnetDataClientConfig.class OR PnetDataClientSpring4Config.class)
public class MyConfig
{
    @Value("${dataApi.username}")
    private String username;

    @Value("${dataApi.password}")
    private String password;

    @Bean
    public MutablePnetDataClientPrefs pnetDataClientPrefs()
    {
        return new MutablePnetDataClientPrefs("https://qa-data.auto-partner.net/data", username, password);
    }
}
```

Afterwards, you autowire the clients you need:

```
@Autowired
private PersonDataClient personDataClient;

@Autowired
private CompanyDataClient companyDataClient;
```

# Common information about the client

There is one client class for each REST interface:

-   `AboutDataClient`
-   `ActivityDataClient`
-   `AdvisorTypeDataClient`
-   `ApplicationDataClient`
-   `BrandDataClient`
-   `CompanyDataClient`
-   `CompanyGroupDataClient`
-   `CompanyGroupTypeDataClient`
-   `CompanyNumberTypeDataClient`
-   `CompanyTypeDataClient`
-   `ContractStateDataClient`
-   `ContractTypeDataClient`
-   `ExternalBrandDataClient`
-   `FunctionDataClient`
-   `LegalFormDataClient`
-   `NumberTypeDataClient`
-   `PersonDataClient`
-   `TodoGroupDataClient`

The clients and all methods are stateless and threadsafe! You can access the three basic methods by using the fluent interface:

-   `get()` for the details
-   `search()` for search operations
-   `find()` for find operations

Execute all requests by calling the `execute(..)` method.

The rest should be quite self-explaning. The `PnetDataClientResultPage` contains a list of all results and some additional information. To call the next page, it's enough to call `nextPage()` method in the result object.

## Examples

Search for all "john doe"s in Austria and stream the first page (keep in mind, that you will only find persons, that gave their consent):

```
personDataClient
    .search()
    .tenant("AT")
    .execute(Locale.getDefault(), "john doe")
    .stream()
    .forEach(person -> ...);
```

Search for all companies in Salzburg:

```
companyDataClient.search().tenant("AT").execute(Locale.getDefault(), "salzburg");
```

Get one person by id (keep in mind, that you will only find persons, that gave their consent):

```
PersonDataDTO person = personDataClient.get().byId(id);
```

Find all dealers in Austira, repsect internet groups, use a scrolling query and stream over all results (which may include multiple requests):

```
companyDataClient
    .find()
    .tenant("AT")
    .contractType("CR_HDL")
    .mergeInternetGroups()
    .scroll()
    .execute(Locale.getDefault())
    .streamAll()
    .forEach(company -> ...);
```

# Building

You will need Git, Java >= 8 and Maven.

First, clone the repository:

```
git clone https://github.com/porscheinformatik/pnet-data-api.git
```

Next, perform a Maven build:

```
mvn install
```

That's it.

Checkout the [pnet-data-api-java-samle](https://github.com/porscheinformatik/pnet-data-api/tree/master/pnet-data-api-java-sample) module for a examples. The `PnetSpringRestClient` is a fully working console tool for accessing the Data API.
