# Partner.&#78;et Data API

This project contains the definition of the API for accessing the dataset of the Partner.&#78;et and clients for different platforms.

The Partner.&#78;et Data API provides access to the data of the Partner.&#78;et. This data includes:

-   persons and companies
-   employments (the link between persons and companies)
-   roles (the area of activity of a person)
-   hosted applications
-   and all necessary basic data required by the above items

Access is compilant witht the General Data Protection Regulation.

# Contents

-   [REST Interface](#rest-interface)
-   [Available Data](#available-data)
-   [Considerations](#considerations)
-   [User for Access](#user-for-access)
-   [Available Instances](#available-instances)
-   [Testing](#testing)
-   [Testing with Postman](#testing-with-postman)
-   [Java Client Library](#java-client-library)
-   [Common Problems](#common-problems)

# REST Interface

The data is available through a REST interface. See the current production version of the [Swagger documentation](https://data.auto-partner.net/data/swagger-ui.html) for more information. The QA environment contains the [upcoming Swagger documentation](https://qa-data.auto-partner.net/data/swagger-ui.html), which may differ slightly.

# Available Data

The available data consists of:

-   Persons
    -   Ids and the name
    -   Contact information (email, phone, ...)
    -   Employments (companies, personnel number, department, ...)
    -   Roles (functions and activities)
    -   Advisor assignments
-   Companies
    -   Ids and the name
    -   Contract information (email, phone, ...)
    -   Address
    -   Bands, company types and contracts
    -   Advisor assignments
-   Company Groups

The data of persons and companies contains references to some master data, that can be access, too:

-   Activities
-   Advisor Types
-   Applications
-   Brands
-   Company Group Types
-   Company Number Types
-   Company Types
-   Contract States
-   Contract Types
-   External Brands
-   Functions
-   Legal Forms
-   Number Types

Most of the data interfaces provide three methods:

-   **details**: for accessing all available information about one or more data elements (e.g. the person with ID 100).
-   **search**: for accessing a reduced amount of information per data item, but with support for fuzzy searches
-   **find**: for accessing a reduced amount of information per record, but with fast filters (e.g. all persons with a certain function or in certain companies).

Personal data is filtered by default. Persons must give their consent, otherwise, no personal information will be shared with your application.

# Considerations

**When using the Data API, please be aware of the following important issues.**

## Performance

The Partner.&#78;et Data API is a scalable application hosted in a cloud environment and is based on an NoSQL database in the background. Requests are fast and the data is accurate. Partner.&#78;et data is synchronized every few seconds and is up to date most of the time.

The REST interface delivers the data as fast as possible. Currently, we don't have any performance issues due to too many users or too many requests. **In general, it is better to have more smaller requests, than fewer larger ones.**

**Consider caching relevant information!** Most of the data does not change very often. We encourage you to store results locally and reuse the information instead of making the same request over and over again.

## Paging

The result of each request is paged, that means, that it splits the result into multiple pages. You can add the desired page size to each request (`pp` parameter), but the server may reduce it, if it is too large. Currently, each page is limited to, at most, 100 items, **but do not bet on it. We may decide to return fewer items (for reasons), make sure your code can cope with that**.

You can request subsequent pages by adding a page (`p`) or a search after (`sa`) parameter, but the rest of the request must be exactly the same.

**If you are requesting multiple pages, we recommend that you to use the `sa` parameter instead of the `p` parameter.** Each response will return a `searchAfter` value. When you make the request for the next page, add the search after parameter as `&sa=...` (instead of the `p` parameter). This will ensure that the response contains the results of the next page. If you use the `p` parameter instead, you may miss items or get duplicates, if the underlying data has changed in the meantime.

## `lastUpdate`

If you are syncing data with your local database, consider using the `lastUpdate` value. You can add an `up`-parameter to your find request, and the result will only contain items, that have been changed since your specified date.

**When syncing person data with your application, this is a must.**

A typical implementation would sync data every other minute by using the `up`-parameter. Once per night it requests the complete set of data - this is necessary to detect deleted items (as a wise hint, don't delete your data immediatly - remember the date of the last successful sync operation and use this date to delete outdated data some days later).

## ValidFrom/ValidTo

All validity ranges contain date and time information, even those, that would normally considered to be date only, like contracts. The reason for this is, that all dates are used for validity checks based on "Wolfsburg-Time", which is CET. When transferred, these dates will be converted to UTC. This means, that a contract, that starts to be valid at 2000-01-01 (00:00 CET) will be transferred as 1999-12-31 23:00 UTC.

In order to know at which "day" this contract starts to be valid, you have to convert the valid-from field to CET.

If you are using the Java client, it will automatically convert the timestamp to your server time.

The valid-to date points to the next day and is exclusive. When a contract ends at 1999-12-31, this date is meant to be inclusive. For this reason, it is stored as 2020-01-01 00:00 CET and you will get 1999-12-31 23:00 UTC (converted to your server timezone, if you are using the Java client).

When you show this end date to the user you have to convert it to "Wolfsburg-Time" (CET) and subtract one day, that the day you are looking for. If you need it for validity checks, just use it as it is, even it this means, that the contract ends during the working hours.

**Tip for the Java client:** you may instantiate the `pnet.data.api.client.jackson.JacksonPnetDataApiModule` with a specific timezone in order to affect the way, how the client parses the UTC date in the server response.

## `details` vs `search` vs `find`

The difference between `search` and `find`, is, that the `search` aims for user interaction (e.g. like searching with a fuzzy query) and the `find` aims for technical solutions (e.g. like finding all persons with a defined function).

Use `search` only, if you need a fuzzy search for a specified term! For all other cases, use `find`!

The `details` requests return more information per item, but the request is slower compared to the `find` and `search` requests.

# User for Access

You will need a peronalized system user for accessing the data. You can request such a user with a [Partner.&#78;et Wartungsantrag](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_MAINT_PROP). Just create a new document and select "Sonstiges".

Bezeichnung: The name of the user, something like: "My Application System User"

Informationen und Freigaben: We need the name of the application, that will use this user. The application must already be known to the Partner.&#78;et. It depends on the application, which persons are accessible (the consent of the person is needed).

Additionally, we will enter your own user as manager for the system user. This means that you, and nobody else, can request a password for the user. If more users should be allowed to do this, please add a list.

After the system user has been created, you can use the [System User Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) (respectively the the [System User Self-Service for QA](https://qa.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF)) for requesting a password or a secret token.

## Logging in

Before making any requests to the Data API, you must perform a login request using either the username/password combination or the secret token. If the login is successful, a JWT is returned that's valid for about an hour (but it may be less for internal reasons).

Use this JWT for all subsequent requests.

**To prevent massive amounts of logins, the number of logins is limited to 30 per two minutes.**

# Available Instances

Before executing requests to the Data API, you have to perform a login request by either using the username/password combination or the secret token. If the login is successful, a JWT will be returned, that's valid for about one hour (but it may be less for internal reasons).

Use this JWT for any subsequent request.

In order to prevent massive amounts of logins, the number of logins is limited to 30 per two minutes.

Following instances are accessible:

PROD: https://data.auto-partner.net/data

QA: https://qa-data.auto-partner.net/data

## Login with Auth Token (JSON Web Token)

The preferred way to log in is by using a [JSON Web Token](https://jwt.io/) generated in the [System User Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) (respectively the the [System User Self-Service for QA](https://qa.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF)).

```
POST /data/login --authorization "Bearer {{auth-token}}"
```

You will receive a response with a short lived [JSON Web Token](https://jwt.io/) as "Authorization" header field. The new token is valid for one hour. Add the "Authorization" header field with the new token to all your subsequent requests!

## Login with Username/Password

You can aquire the username/password with the [System User Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) (respectively the the [System User Self-Service for QA](https://qa.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF)).

```
POST /data/login {
	"username": "...",
	"password": "..."
}
```

You will receive a response with a [JSON Web Token](https://jwt.io/) as "Authorization" header field. The token is valid for one hour. Add the header field to all your requests!

```
GET /data/api/v1/about
```

This will return some information about the server and your user. It's a perfect start.

# Testing

## Using cURL

The simplest way to access the data, is by using the [cURL-Tool](https://curl.haxx.se/). It's available on most Linux installation, and even in Windows, it's quite common and easy to install.

### Perform a login via auth-token

```
curl -i -X POST https://qa-data.auto-partner.net/data/login -H "Authorization: Bearer {{auth-token}}"
```

### Perform a login via username/password

```
curl -i -X POST https://qa-data.auto-partner.net/data/login -d '{"username":"...","password":"..."}'
```

> Keep in mind, that the Linux-Bash stores the history to a file called ` ~/.bash_history` after you close the shell. You may want to remove the password from the history.

The command returns an authorization header, that's the JSON-Web Token:

```
HTTP/1.1 200
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJwbmV0LmFwSWQ...
```

Store the token in a variable, it is valid for one hour:

```
export JWT='Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJwbmV0LmFwSWQ...'
```

> In Windows, you can experiment with the `set` command and `%JWT%` as placeholder.

### Access data

First call the `about` interface, just for testing:

```
curl -H "$JWT" -X GET https://qa-data.auto-partner.net/data/api/v1/about
```

> In Windows, use `%JTW%` instead.

This should return some information about the version and your user.

Example for searching a `function`:

```
curl -H "$JWT" -X GET https://qa-data.auto-partner.net/data/api/v1/functions/search?q=test\&t=AT\&l=de
```

You should be aware that the ampersand has to be escaped.

## Using Powershell

### Perform a login with authentication token

```
$headers = @{
    "Authorization" = "Bearer <AUTH_TOKEN>"
}
$url = "https://qa-data.auto-partner.net/data"
$result = Invoke-WebRequest -Uri "$url/login" -Method Post -Headers $headers -UseBasicParsing
$jwt = @{ Authorization = $result.Headers.Authorization }

echo $jwt
```

This should store the authorization token to `jwt` and print it. The token is valid for one hour.

### Perform a login with username/password

```
$body = @{
    "username"="<USERNAME>"
    "password"="<PASSWORD>"
} | ConvertTo-Json
$url = "https://qa-data.auto-partner.net/data"
$result = Invoke-WebRequest -Uri "$url/login" -Method Post -Body $body -UseBasicParsing
$jwt = @{ Authorization = $result.Headers.Authorization }

echo $jwt
```

This should store the authorization token to `jwt` and print it. The token is valid for one hour.

### Access data

First call the `about` interface, just for testing:

```
Invoke-WebRequest -uri "$url/api/v1/about" -Headers $jwt
```

This should return some information about the version and your user.

## Test samples

# Search data

The simplest call, is to search for data. Let's test it with functions:

```
curl -H 'Authorization: $JWT' -X GET https://qa-data.auto-partner.net/data/api/v1/functions/search?l=en\&q=
```

> You may notice the \\ before the &. In some shells, this it's necessary to escape the &, because it is a delimiter for the command.

or using Powershell:

```
Invoke-WebRequest -uri "$url/api/v1/functions/search?l=en&q=" -Headers $jwt
```

> With `search` and `find` requests, you need to specify the language with the parameter `l=en`.

The command should return lot's of functions. You can copy the `matchcode` and get the details for it:

```
curl -H 'Authorization: $JWT' -X GET https://qa-data.auto-partner.net/data/api/v1/functions/details/FU_ST_USER
```

or using Powershell:

```
Invoke-WebRequest -uri "$url/api/v1/functions/details/FU_ST_USER" -Headers $jwt
```

This will return the details of the function. You can do the same for a lot of other interfaces:

-   `activities`
-   `advisortypes`
-   `applications`
-   `brands`
-   `companygrouptypes`
-   `companynumbertypes`
-   `companytypes`
-   `contractstates`
-   `contracttypes`
-   `externalbrands`
-   `functions`
-   `numbertypes`

With companies and persons, you have to use the `id` instead of the `matchcode`.

-   `companies`
-   `persons`

CompanyGroups only contain the `details` interface.

-   `companygroups`

## Testing with Postman

[Postman](https://www.getpostman.com/) is a free tool for accessing REST interfaces. Download the tool and install it. Finally, you can import the [Partner.&#78;et Data API.postman_collection.json](https://raw.githubusercontent.com/porscheinformatik/pnet-data-api/master/src/postman/Partner.Net%20Data%20API.postman_collection.json) collection with a lot of samples.

### Perform a login

First, execute the "Login via Token" or "Login via Username/Password" request. In order to do so, edit the collection in the sidebar, open the variables tab and enter the following properties:

| Name       | Description                                                                                                                                                                                                                                                                                                            |
| ---------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| url        | The URL, either https://qa-data.auto-partner.net/data or https://data.auto-partner.net/data                                                                                                                                                                                                                            |
| auth-token | Needed fro the token login. The token from the [System User Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) (respectively the the [System User Self-Service for QA](https://qa.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF))                          |
| username   | Needed for the username/password login. The username from the [System User Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) (respectively the the [System User Self-Service for QA](https://qa.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF))           |
| password   | Needed for the username/password login. The generated password from the [System User Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) (respectively the the [System User Self-Service for QA](https://qa.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF)) |

Execute the Login request afterwards. It should return with `200 OK`. The "Headers" tab contains the `Authorization` header field. The Login request will automatically copy this bearer code to the appropriate collection variable. The token is valid for one hour.

### Access data

Next, execute the "About" request (the collection variables should contain the necessary token after you have executed the login request). The request should return with `200 OK` and the "Body" tab should contain some information about versions an the user.

All the other GET request work likewise.

# Java Client Library

If you are using Java, you can checkout the [Partner.&#78;et Data API Java Client](https://github.com/porscheinformatik/pnet-data-api/tree/master/pnet-data-api-java). It contains a useful library and a sample implementation.

Tip: You can use the [Sample Implementation](https://github.com/porscheinformatik/pnet-data-api/tree/master/pnet-data-api-java-sample) as a standalone client.

# Common Problems

## Where can I apply for a system user?

Please check the chapter [User for Access](#user-for-access).

## Where can I get my password from?

After the system user has been created, you can use the [System User Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) (respectively the the [System User Self-Service for QA](https://qa.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF)) for requesting a password. You should see the system user in the list. Click on the name and you will see the details of this user. In this form, you can request a new password.

If the system user is missing, you may be missing in the list of responsible persons for this system user. In such a case, please contact your contact person at the Porsche Holding.

## I'm not allowed to access the Data API, but the password is correct!

Use the [System User Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) (respectively the the [System User Self-Service for QA](https://qa.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF)) to check the access log. It will contain a more detailed explanation of what went wrong. Quite often the IP is not allowed. You can add it in the Self-Service as well.

## When querying data, why do I get duplicate entries while others are missing?

With reach request a new search will be performed in our database. When your are iterating over pages, it may happen, that the next page contains an entry, that was already present on the last page, while another one is missing. We are trying our best to avoid this by sorting the results, but if someone is changing the data at the same moment you are accessing it, it may happen nevertheless.

You can avoid this problem at all by using the `searchAfter` parameter. Check [Paging](#paging) for more information.

## When querying persons, why are some missing?

When you are accessing the Data API, you are using a system user that is linked to a specific application. For data protection reasons, you can only access persons, that gave their consent, that the application is allowed to use their data.

Giving consent may either happen when the person first accesses the application by clicking on the link in the Partner.&#78;et Portal or automatically, if the person got a right (function or activity), that implies that she or he needs this application of the daily work (legitimate interest).

All queries to personal data are checked against these consents. Especially when you are testing on the QA system, remember that the only very few persons have access to this instance.

## When querying persons, why is some data missing?

When requesting the consent for using personal data, the Partner.Net lists the fields, that will be accessible by your application. Only those fields are, in fact, transferred to your application.

If some fields are missing, please create a [Partner.&#78;et Wartungsantrag](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_MAINT_PROP) and describe the missing fields. After changing those fields, all uses will be asked for their consent once more.

## How can I use multiple conditions in one query?

You can add parameters more than once. The following query will return all persons with at least one of the specified activities:

```
.../persons/find?activity=TA_ACT_1&activity=TA_ACT_2&activity=TA_ACT_3
```

You may note, that the parameter separator of the URL looks like an "and", but, in this case, it's an "or" condition.

A common pitfall is, that if you are using other conditions in the same query: they are treated as "and". Only conditions to the same field are treated as "or".

```
.../persons/find?activity=TA_ACT_1&activity=TA_ACT_2&function=FU_FUN_1&function=FU_FUN_1
```

This will result in a search that's more like: `(TA_ACT_1 OR TA_ACT_2) AND (FU_FUN_1 OR FU_FUN_2)`.

To avoid this common pitfall, there is a search for `role`s now, searching for both, functions and activities, using an `OR` query.

## What's the problem with scolling

The use of "scrolling" as mentioned in earlier versions of this documentation has been deprecated by our NoSQL database vendor. As a result, we no longer recommend using it. Please refer to the [Paging documentation](#paging) for alternatives.

For security reasons, the access to scrolling requests has been limited to 16 parallel scrolls per user, and the maximum time a scroll ID remains valid has been limited to 60 minutes.

# I Still Need Help

Feel free to contact us via the inhouse "POI-Partner.Net / Data API" Teams channel or via your contact person at the Porsche Informatik. Please to not contact our development staff directly - they are very jumpy and it takes hours to calm them down.
