# Partner.&#78;et Data API Java Client

This project contains a Java client for the Partner.&#78;et Data API.

[Swagger Documentation of the pnet-data-api](https://porscheinformatik.github.io/pnet-data-api/)

The Java client supports all requests to the Partner.&#78;et Data API. We use it within the Partner.&#78;et and all internal tests for the Data API are based on this client (so even, if this repo does not contain many tests, the client is fully tested).

An up-to-date version is available in the corporate Maven repository. If you do not have access to this repository, you have to build our own version (see [Building](#building))

# Main / Java 11 branch

Currently we are maintaining two branches:

* `master`: Contains the version 2 of the Java client, that needs at least Java 17 and, if you are using Spring, it needs Spring 6.
* `java-11`: Contains the version 1 of the Java client, that's suited for Java 11 and, if you are using Spring, it's only compatible with Spring 5.

# Using the Java client

The Java client has been designed to **reduce its dependencies to a minimum**. All dependencies are "provided", which means, you have to define them on your own - within your project. Currently you have the following options:

-   **[Just use the DTOs](#just-use-the-dtos)**: You don't need any dependencies for this. You don't need Spring for this.
-   **[Java HTTP Client](#java-http-client)**: Use the Java HTTP Client introduced with Java 9 for the communication and Jackson for the JSON mapping and. You don't need Spring for this.
-   **[Apache HTTP Client](#apache-http-client)**: Use the Apache HTTP Client for the communication and Jackson for the JSON mapping and. You don't need Spring for this.
-   **[Spring](#spring)**: It uses Jackson for the JSON mapping and a Spring Web client (RestTemplate) for the communication. You need Spring 6 to be compatible. For older Spring version, please use the version the `java-11` branch.

## Just use the DTOs

You will need the following dependencies:

```
<dependency>
    <groupId>at.porscheinformatik.pnet</groupId>
    <artifactId>pnet-data-api-java</artifactId>
</dependency>
```

That's it. Now, you may implement the client on your own.

The project contains an adapter that utilizes different kinds of HTTP clients. You may wish to use this adapter while adapting to your own preferred HTTP client. This holds the advantage, that you can use the rest of the infrastructure provided by this library. Have a look at the implementations of the `at.porscheinformatik.happyrest.java.JavaRestCall` (using the Java HTTP client), the `at.porscheinformatik.happyrest.apache.ApacheRestCall` (using the Apache HTTP Client) or the `at.porscheinformatik.happyrest.spring.SpringRestCall` (using Spring's REST Template).

## Java HTTP Client

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
```

Use your preferred login method.

### Login via Token

The preferred way for authenticating your client is by using the `AuthenticationTokenPnetDataApiLoginMethod`.

```
PnetDataApiLoginMethod loginMethod = new AuthenticationTokenPnetDataApiLoginMethod(url, () -> <TOKEN>);
```

Use the Partner.Net Self-Service to aquire a token and provide it with the `<TOKEN>` call. It lies in your responsibility that this token is stored in a secure and secret
area of your application. The method will be called around once per hour thus aquiring
the token must not be very performant.

### Login via Username/Password

Alternatively, you may use the `UsernamePasswordPnetDataApiLoginMethod`, but be aware, that the preferred way to authenticate your client is by using the token.

```
PnetDataApiLoginMethod loginMethod = new UsernamePasswordPnetDataApiLoginMethod(url, () -> new UsernamePasswordCredentials(<USERNAME>, <PASSWORD>));
```

Use the Partner.Net Self-Service to aquire a the username and password. Provide the credentials with the lambda call. It lies in your responsibility that the credentials are stored in a secure and secret area of your application. The method will be called around once per hour thus aquiring
the username/password must not be very performant.

### Create the Client Factory

```
JavaClientFactory clientFactory = JavaClientFactory.of(loginMethod);
```

### Example

```
PnetDataApiLoginMethod loginMethod = new AuthenticationTokenPnetDataApiLoginMethod(url, () -> <TOKEN>);
JavaClientFactory clientFactory = JavaClientFactory.of(loginMethod);

clientFactory
    .getCompanyDataClient()
    .search()
    .execute(Locale.getDefault(), "Informatik")
    .forEach(company -> System.out.println(company));
```

All classes are unmodifyalbe and thread-safe. You can and you should reuse them as long as possible.

Be warned that the first login will usually fail, because of IP restrictions defined for your systemuser. Have a look at the Partner.Net "Systemuser Selfservice" to fix this issue.

Have a look at https://github.com/porscheinformatik/pnet-data-api/blob/master/pnet-data-api-java-sample/src/main/java/pnet/data/api/java/PnetJavaRestClientTemplate.java for some super simple sample code.

## Apache HTTP Client 5

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
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
</dependency>
```

Use your preferred login method.

### Login via Token

The preferred way for authenticating your client is by using the `AuthenticationTokenPnetDataApiLoginMethod`.

```
PnetDataApiLoginMethod loginMethod = new AuthenticationTokenPnetDataApiLoginMethod(url, () -> <TOKEN>);
```

Use the Partner.Net Self-Service to aquire a token and provide it with the `<TOKEN>` call. It lies in your responsibility that this token is stored in a secure and secret
area of your application. The method will be called around once per hour thus aquiring
the token must not be very performant.

### Login via Username/Password

Alternatively, you may use the `UsernamePasswordPnetDataApiLoginMethod`, but be aware, that the preferred way to authenticate your client is by using the token.

```
PnetDataApiLoginMethod loginMethod = new UsernamePasswordPnetDataApiLoginMethod(url, () -> new UsernamePasswordCredentials(<USERNAME>, <PASSWORD>));
```

Use the Partner.Net Self-Service to aquire a the username and password. Provide the credentials with the lambda call. It lies in your responsibility that the credentials are stored in a secure and secret area of your application. The method will be called around once per hour thus aquiring
the username/password must not be very performant.

### Create the Client Factory

```
Apache5ClientFactory clientFactory = Apache5ClientFactory.of(loginMethod);
```

### Example

```
PnetDataApiLoginMethod loginMethod = new AuthenticationTokenPnetDataApiLoginMethod(url, () -> <TOKEN>);
Apache5ClientFactory clientFactory = Apache5ClientFactory.of(loginMethod);

clientFactory
    .getCompanyDataClient()
    .search()
    .execute(Locale.getDefault(), "Informatik")
    .forEach(company -> System.out.println(company));
```

All classes are unmodifyalbe and thread-safe. You can and you should reuse them as long as possible.

Be warned that the first login will usually fail, because of IP restrictions defined for your systemuser. Have a look at the Partner.Net "Systemuser Selfservice" to fix this issue.

Have a look at https://github.com/porscheinformatik/pnet-data-api/blob/master/pnet-data-api-java-sample/src/main/java/pnet/data/api/apache5/PnetApache5RestClientTemplate.java for some super simple sample code.

## (Old) Apache HTTP Client

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

Use your preferred login method.

### Login via Token

The preferred way for authenticating your client is by using the `AuthenticationTokenPnetDataApiLoginMethod`.

```
PnetDataApiLoginMethod loginMethod = new AuthenticationTokenPnetDataApiLoginMethod(url, () -> <TOKEN>);
```

Use the Partner.Net Self-Service to aquire a token and provide it with the `<TOKEN>` call. It lies in your responsibility that this token is stored in a secure and secret
area of your application. The method will be called around once per hour thus aquiring
the token must not be very performant.

### Login via Username/Password

Alternatively, you may use the `UsernamePasswordPnetDataApiLoginMethod`, but be aware, that the preferred way to authenticate your client is by using the token.

```
PnetDataApiLoginMethod loginMethod = new UsernamePasswordPnetDataApiLoginMethod(url, () -> new UsernamePasswordCredentials(<USERNAME>, <PASSWORD>));
```

Use the Partner.Net Self-Service to aquire a the username and password. Provide the credentials with the lambda call. It lies in your responsibility that the credentials are stored in a secure and secret area of your application. The method will be called around once per hour thus aquiring
the username/password must not be very performant.

### Create the Client Factory

```
ApacheClientFactory clientFactory = ApacheClientFactory.of(loginMethod);
```

### Example

```
PnetDataApiLoginMethod loginMethod = new AuthenticationTokenPnetDataApiLoginMethod(url, () -> <TOKEN>);
ApacheClientFactory clientFactory = ApacheClientFactory.of(loginMethod);

clientFactory
    .getCompanyDataClient()
    .search()
    .execute(Locale.getDefault(), "Informatik")
    .forEach(company -> System.out.println(company));
```

All classes are unmodifyalbe and thread-safe. You can and you should reuse them as long as possible.

Be warned that the first login will usually fail, because of IP restrictions defined for your systemuser. Have a look at the Partner.Net "Systemuser Selfservice" to fix this issue.

Have a look at https://github.com/porscheinformatik/pnet-data-api/blob/master/pnet-data-api-java-sample/src/main/java/pnet/data/api/apache/PnetApacheRestClientTemplate.java for some super simple sample code.

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

Import the `PnetDataClientConfig` to your existing configuration. First, you will need to provide a `PnetDataApiLoginMethod`. Use your preferred one.

### Login via Token

The preferred way for authenticating your client is by using the `AuthenticationTokenPnetDataApiLoginMethod`.

```
@Bean
public PnetDataApiLoginMethod pnetDataApiLoginMethod()
{
    return new AuthenticationTokenPnetDataApiLoginMethod(url, () -> <TOKEN>);
}
```

Use the Partner.Net Self-Service to aquire a token and provide it with the `<TOKEN>` call. It lies in your responsibility that this token is stored in a secure and secret
area of your application. The method will be called around once per hour thus aquiring
the token must not be very performant.

### Login via Username/Password

Alternatively, you may use the `UsernamePasswordPnetDataApiLoginMethod`, but be aware, that the preferred way to authenticate your client is by using the token.

```
@Bean
public PnetDataApiLoginMethod pnetDataApiLoginMethod()
{
    return new UsernamePasswordPnetDataApiLoginMethod(url, () -> new UsernamePasswordCredentials(<USERNAME>, <PASSWORD>));
}
```

Use the Partner.Net Self-Service to aquire a the username and password. Provide the credentials with the lambda call. It lies in your responsibility that the credentials are stored in a secure and secret area of your application. The method will be called around once per hour thus aquiring
the username/password must not be very performant.

### Create the Client Factory

```
@Import(PnetDataClientConfig.class)
```

You will need a logging adapter as well:

```
@Bean
public RestLoggerAdapter restLoggerAdapter()
{
    return SystemRestLoggerAdapter.INSTANCE;
}
```

### Usage

Inject the clients whenever needed:

```
@Autowired
CompanyDataClient companyDataClient;
```

### Example

This is an example of a most basic configuration accessing the QA environment and getting the token from the application properties (which isn't very secure):

```
@Configuration
@Import(PnetDataClientConfig.class)
public class MyConfig
{
    @Value("${dataApi.token}")
    private String token;

    @Bean
    public PnetDataApiLoginMethod pnetDataApiLoginMethod()
    {
        return new AuthenticationTokenPnetDataApiLoginMethod("https://qa-data.auto-partner.net/data", () -> token);
    }

    @Bean
    public RestLoggerAdapter restLoggerAdapter()
    {
        return SystemRestLoggerAdapter.INSTANCE;
    }
}
```

Afterwards, you autowire the clients you need:

```
@Autowired
CompanyDataClient companyDataClient;

public void test() {
    companyDataClient
        .search()
        .execute(Locale.getDefault(), "Informatik")
        .forEach(company -> System.out.println(company));
}
```

Have a look at https://github.com/porscheinformatik/pnet-data-api/blob/master/pnet-data-api-java-sample/src/main/java/pnet/data/api/spring/PnetSpringRestClientTemplate.java for some super simple sample code.

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

The clients and all methods are stateless and threadsafe! You can access the three basic methods by using the fluent interface:

-   `get()` for the details
-   `search()` for search operations
-   `find()` for find operations
-   `autocomplete()` for non-fuzzy search operations

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

Find all dealers in Austria, repsect internet groups, use a scrolling query and stream over all results (which may include multiple requests):

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

You will need Git, Java >= 17 and Maven.

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

# Troubleshooting

## TimeZone

The date/time values, sent by the server, contain time zone information. Since the Java client uses LocalDateTime objects, this information is not available anymore. By default, the client correctly converts the zoned timestamps by using the systems default zone (ZoneId.systemDefault). This behavior may be modified by using the JacksonPnetDataApiModule.createObjectMapper(ZoneId) method.
