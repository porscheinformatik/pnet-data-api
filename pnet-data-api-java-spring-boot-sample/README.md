# Partner.&#78;et Data API Java Client

This is a most simple sample application using Spring Boot.

Start it with one of the following login methods.
You can also use one of the launch configs in group `Spring Boot` in your IDE.

## IDP Client Credentials

```
mvn spring-boot:run -Dspring-boot.run.arguments='--pnet-data-api.login-method=idp-client --pnet-data-api.url=https://qa-data.auto-partner.net/data --pnet-data-api.idp-url=https://qa-identity.auto-partner.net/identity --pnet-data-api.client-id=... --pnet-data-api.client-secret=...'
```

The sample uses OAuth2 client credentials to obtain an IDP access token for the Data API.

## Authentication Token

```
mvn spring-boot:run -Dspring-boot.run.arguments='--pnet-data-api.login-method=token --pnet-data-api.url=https://qa-data.auto-partner.net/data --pnet-data-api.token=...'
```

## Username/Password

```
mvn spring-boot:run -Dspring-boot.run.arguments='--pnet-data-api.login-method=username-password --pnet-data-api.url=https://qa-data.auto-partner.net/data --pnet-data-api.username=... --pnet-data-api.password=...'
```

Then open: https://localhost:8080

It should display the name of a function.
