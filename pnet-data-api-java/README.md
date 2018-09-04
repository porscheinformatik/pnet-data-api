# Partner.&#78;et Data API Java Client

This project contains a Java client for the Partner.&#78;et Data API.

[Swagger Documentation of the pnet-data-api](https://porscheinformatik.github.io/pnet-data-api/)

The Java client supports all requests to the Partner.&#78;et Data API. We use it within the Partner.&#78;et and all internal tests for the Data API are based on this client (so even, if this repo does not contain many tests, the client with fully tested).

Currently the client is not available via public repository. You have to build it on your own.

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

# Using the Java client

## Spring (>= 5)

Import the `PnetDataClientConfig` to your existing configuration. You will need to provide `PnetDataClientPrefs` containing your user data.

This is an example of a most basic configuration accsing the QA environment and getting the username/password from the application properties:

```
@Configuration
@Import(PnetDataClientConfig.class)
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

## Using the Library

You can import the *Client service, e.g:

```
@Autowired
private PersonDataClient personDataClient;
```

And perform requests using the client:

```
personDataClient.search().tenant("AT").execute(Loclae.getDefault(), "John");
```

# Common information about the client

There is a client class for each REST interface:

* `AboutDataClient`
* `ActivityDataClient`
* `AdvisorTypeDataClient`
* `ApplicationDataClient`
* `BrandDataClient`
* `CompanyDataClient`
* `CompanyGroupDataClient`
* `CompanyGroupTypeDataClient`
* `CompanyNumberTypeDataClient`
* `CompanyTypeDataClient`
* `ContractStateDataClient`
* `ContractTypeDataClient`
* `ExternalBrandDataClient`
* `FunctionDataClient`
* `NumberTypeDataClient`
* `PersonDataClient`

The clients and all methods are stateless and threadsafe! You can access the three basic methods by using the fluent interface:

* `get()` for the details
* `search()` for search operations
* `find()` for find operations

Execute all requests by calling the `execute(..)` method.

The rest should be quite self-explaning. The `PnetDataClientResultPage` contains a list of all results and some additional information. To call the next page, it's enough to call `nextPage()` method in the result object.

