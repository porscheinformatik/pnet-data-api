# Partner.Net Data API - AI Coding Agent Instructions

## Project Overview

Multi-module Maven project providing a **Java client library** for the Partner.Net Data API (a REST API for accessing business data about persons, companies, employments, and master data). The library is designed with **minimal dependencies** - all are "provided" scope, letting consumers choose their HTTP client implementation.

**Repository:** https://github.com/porscheinformatik/pnet-data-api
**Swagger Docs:** https://data.auto-partner.net/data/swagger-ui.html

## Project Structure

```
pnet-data-api/                          # Parent POM (Java 17, Spring Boot 4)
├── pnet-data-api-java/                 # Core client library (DTOs + clients)
├── pnet-data-api-java-sample/          # Executable examples (4 HTTP client variants)
└── pnet-data-api-java-spring-boot-sample/  # Spring Boot integration example
```

The `pnet-data-api` acts as parent POM. Additionally it hosts the main documentation, the Swagger specification and some additional files like scripts (e.g. a Postman collection).

The `pnet-data-api-java` contains a REST client wrapper library (called Happy Rest, in the at.porscheinformatik.happyrest package) and the actual Partner.Net Data API Java client implementation. It contains DTOs for all entity types (Person, Company, Function, etc.) and clients for accessing them.

The `pnet-data-api-java-sample` module contains a command-line sample application demonstrating usage of the client library with 4 different HTTP client implementations (Java 11+ HttpClient, Apache HTTP Client 4, Apache HTTP Client 5, Spring RestTemplate/WebClient).

The `pnet-data-api-java-spring-boot-sample` module contains a Spring Boot application demonstrating integration of the client library in a Spring Boot environment.

## Architecture & Design Patterns

### 1. Factory Pattern for Multiple HTTP Client Implementations

The library supports **5 HTTP client strategies** without taking dependencies:

- **`JavaClientFactory`** - Java 9+ HttpClient (recommended for non-Spring)
- **`Apache5ClientFactory`** - Apache HTTP Client 5
- **`ApacheClientFactory`** - Legacy Apache HTTP Client 4
- **Spring `RestTemplate`** via `RestTemplateBasedRestCallFactoryConfig`
- **Spring `WebClient`** via `WebClientBasedRestCallFactoryConfig` (recommended for Spring)

All factories extend `AbstractClientFactory<T>` which extends `AbstractClientProvider` (lazy-initializing client singletons).

### 2. Client Provider Pattern

`ClientProvider` interface defines getters for all 17 data clients (persons, companies, functions, etc.). Implementations:

- **`AbstractClientProvider`** - Base with lazy initialization
- **`PnetDataApiClientProvider`** - Spring `@Service` with `@Autowired` constructor injection

### 3. Three-Method Data Access Pattern

Most entity clients expose three retrieval methods:

```java
client.get()         // Full details for specific IDs (slower, complete data)
client.search()      // Fuzzy search for user interaction (reduced data)
client.find()        // Fast technical filtering (reduced data, for sync/batch)
```

**Key Rule:** Use `find()` for technical queries (e.g., all persons with function X). Reserve `search()` for user-facing fuzzy queries.

### 4. Authentication

Login required before API calls. Two methods:

- **Token-based** (preferred): `AuthenticationTokenPnetDataApiLoginMethod`
- **Username/Password**: `UsernamePasswordPnetDataApiLoginMethod`

JWT returned is valid ~1 hour. Rate limit: 30 logins per 2 minutes.

### 5. Paging & Synchronization

- Results are paged (max 100 items, may be lower)
- **Always use `sa` (searchAfter) parameter** for subsequent pages, NOT `p` (page number) to avoid duplicates/misses
- For data sync: Use `up` (lastUpdate) parameter + nightly full sync to detect deletions

## Key Implementation Details

### Client Instantiation (Non-Spring)

```java
PnetDataApiLoginMethod loginMethod = new AuthenticationTokenPnetDataApiLoginMethod(url, () -> token);

JavaClientFactory clientFactory = JavaClientFactory.of(loginMethod);

PersonDataClient personClient = clientFactory.getPersonDataClient();
```

### Client Instantiation (Spring)

Add `@EnableRestTemplateBasedPnetDataClient` or `@EnableWebClientBasedPnetDataClient`. Inject:

```java
@Autowired
private PersonDataClient personDataClient;
```

### Data Retrieval Example

```java
// Find all persons with function "Verkäufer" updated since timestamp
PnetDataClientResultPage<PersonItemDTO> page = personClient.find()
    .function("Verkäufer")
    .updatedAfter(lastUpdateTime).execute();

page.streamAll().forEach(person -> /* process */);
```

### Timezone Handling

All dates in API responses are UTC but represent "Wolfsburg-Time" (CET). The Java client **auto-converts to server timezone**.

**ValidTo dates are exclusive** (end date 1999-12-31 stored as 2000-01-01 00:00 CET). Subtract 1 day when displaying to users.

Configure custom timezone:

```java
JacksonPnetDataApiModule module = new JacksonPnetDataApiModule(ZoneId.of("Europe/Vienna"));
```

## Development Workflow

### Build

```bash
mvn clean install              # Build all modules
mvn clean install -DskipTests  # Skip tests
```

Uses **Java 17** as source/target. Spring Boot version managed in parent POM property `spring-boot.version` (currently 4.0.0).

**Jackson 2 (Temporary):** Spring Boot 4.0.0 currently uses Jackson 2.20.1. Jackson 3 migration will occur when Spring Boot officially adopts it. Current package structure:

- All Jackson imports use: `com.fasterxml.jackson.*`
- When Spring Boot upgrades to Jackson 3, imports will change to `tools.jackson.*` (except annotations)

### Testing

- Unit tests in `pnet-data-api-java/src/test/java` focus on Jackson serialization/deserialization
- Live tests done internally at Porsche Informatik using this client
- No extensive test suite in this repo (client is battle-tested in production)

### Sample Execution

```bash
cd pnet-data-api-java-sample/target
java -jar pnet-data-api-java-sample-*-java-jar-with-dependencies.jar

# Console commands:
> url https://qa-data.auto-partner.net/data
> token <TOKEN>
> about                # Test connection
> search functions verkäufer
> export all persons
```

### Code Formatting

Uses **Prettier** with Java plugin:

```bash
npm run prettier:check
npm run prettier:write
```

## Dependency Management Rules

1. **Parent POM** (`/pom.xml`) defines all version properties and `<dependencyManagement>`
2. **Never specify versions in child POMs** - inherit from parent
3. Spring Boot version controlled via `${spring-boot.version}` property
4. When updating dependencies:
    - Spring Boot 4.x is the current major version
    - Update version properties in parent POM only
    - Ensure compatibility with Java 17+
    - Spring Boot 4.0.0 currently uses Jackson 2.20.1 (Jackson 3 pending official Spring Boot support)

- **All dependencies are `provided` scope** except test dependencies
- Generates TypeScript definitions in `target/pnet-data-api.d.ts` via `typescript-generator-maven-plugin`
- Contains DTOs for all entity types (Person, Company, Function, etc.)
- Each entity has its own package with Client, DTOs, and query builders

### `pnet-data-api-java-sample`

- Creates 8 assembly artifacts (4 executable JARs + 4 distributables) for different HTTP client implementations
- Main classes: `PnetJavaRestClientLauncher`, `PnetApacheRestClientLauncher`, `PnetApache5RestClientLauncher`, `PnetSpringRestClientLauncher`
- Uses `maven-assembly-plugin:3.8.0` with custom descriptors

### `pnet-data-api-java-spring-boot-sample`

- Demonstrates Spring Boot integration with both `spring-boot-starter-webmvc` and `spring-boot-starter-webflux`
- Uses `spring-boot-maven-plugin` with version from parent property
- Spring Boot 4.0 requires explicit starter names (webmvc instead of web)

## Common Pitfalls

1. **Don't use `search()` for batch operations** - it's for fuzzy user queries. Use `find()` instead
2. **Always handle paging with `sa` parameter**, not `p`
3. **JWT token expires** - clients should auto-refresh via login method supplier
4. **ValidTo dates are exclusive** - subtract 1 day when displaying
5. **Rate limiting on login** - cache tokens appropriately (valid ~1 hour)
6. **Dependencies must be provided by consumer** - don't add compile-scope HTTP clients to `pnet-data-api-java`
7. **Spring Framework 7 API changes** - HttpHeaders no longer has `entrySet()`, use `forEach()` instead

## Entity Clients Available

AboutDataClient, ActivityDataClient, AdvisorTypeDataClient, ApplicationDataClient, BrandDataClient, CompanyDataClient, CompanyGroupDataClient, CompanyGroupTypeDataClient, CompanyNumberTypeDataClient, CompanyTypeDataClient, ContractStateDataClient, ContractTypeDataClient, ExternalBrandDataClient, FunctionDataClient, LegalFormDataClient, NumberTypeDataClient, PersonDataClient

All follow the same pattern: `get()`, `search()`, `find()` (where applicable) returning query builders that call protected methods in the client.
