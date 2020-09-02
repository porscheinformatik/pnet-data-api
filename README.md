# Partner.&#78;et Data API

This project contains the definition of the API for accessing the data stock of the Partner.&#78;et and clients for the various platforms.

The Partner.&#78;et Data API provides access to the data of the Partner.&#78;et. This data contains:

* persons and companies
* employments (the link between persons and companies)
* roles (the area of activity of a person)
* hosted applications
* and all necessary base data needed by the above items

The access conforms the General Data Protection Regulation.

# Contents

* [REST Interface](#rest-interface)
* [Available Data](#available-data)
* [Considerations](#considerations)
* [User for Access](#user-for-access)
* [Available Instances](#available-instances)
* [Testing](#testing)
* [Testing with Postman](#testing-with-postman)
* [Java Client Library](#java-client-library)
* [Common Problems](#common-problems)

# REST Interface

The data is available by a REST interface. Check the current version (production) of [Swagger-Documentation](https://data.auto-partner.net/data/swagger-ui.html) for more information. The QA environment contains the
[upcoming Swagger-Documentation](https://qa-data.auto-partner.net/data/swagger-ui.html), that might differ
a little bit.

# Available Data

The available data consists of:

* Persons
    * Ids and the name
    * Contact information (email, phone, ...)
    * Employments (companies, personnel number, department, ...)
    * Roles (functions and activities)
* Companies
    * Ids and the name
    * Contract information (email, phone, ...)
    * Address
    * Bands, company types and contracts
* Company Groups

The data of persons and companies contains references to some master data, that can be access, too:

* Activities
* Advisor Types
* Applications
* Brands
* Company Group Types
* Company Number Types
* Company Types
* Contract States
* Contract Types
* External Brands
* Functions
* Legal Forms
* Number Types

Most of the data interfaces provide three methods:

* details - For accessing all available information about one or more data items (e.g. the person with the id 100).
* search - For accessing a reduced amount of information per data item, but supporting fuzzy searches
* find - For accessing a reduced amount of information per data item, but with fast filters (e.g. all persons with a defined function or at specific companies).

The person data is filtered by default. Persons will have to give their consent, otherwise, no personal information will be shared with your application.

# Considerations

## Performance

The Partner.&#78;et Data API is a scalable application hosted in a cloud environment and is based on an NoSQL-Database in the background. Requests are fast and the data is accurate. The data from the Partner.&#78;et gets synced every few seconds and is up-to-date most of the time.

The REST Interface provides the data as fast as it can. Currently we don't have performance issues because of too many users or too many requests. Generally, it is better to make multiple smaller requests, than fewer larger ones.

Consider caching relevant information! Most of the data does not change very often. We encourage you to store results locally and reuse the information instead of performing the same request over and over again.

Some of the interfaces support scrolling. This allows your application to rapidly browse though thousands of data items.

## Paging

The result of each request is paged, that means, that it splits the result into multiple pages. You can add the desired page size to each request (`pp` parameter), but the server may reduce it, if it is too large. Currently, each page is limited to, at most, 100 items.

You can request subsequent pages by adding a page (`p`) parameter, but the rest of the request must be exactly the same (otherwise you may miss some items).

## `lastUpdate`

If you are syncing data with your local database, consider using the `lastUpdate` value. You can add an `up`-parameter to your find request, and the result will only contain items, that have been changed since your specified date.

**When syncing person data with your application, this is a must.**

A typical implementation would sync data every other minute by using the `up`-parameter. Once per night it requests the complete set of data - this is necessary to detect deleted items (as a wise hint, don't delete your data immediatly - remember the date of the last successful sync operation and use this date to delete outdated data some days later).

## `details` vs `search` vs `find`

The difference between `search` and `find`, is, that the `search` aims for user interaction (e.g. like searching with a fuzzy query) and the `find` aims for technical solutions (e.g. like finding all persons with a defined function).

Use `search` only, if you need a fuzzy search for a specified term! For all other cases, use `find`!

The `details` requests return more information per item, but the request is slower compared to the `find` and `search` requests.

## Scroll Queries

Some `find`-queries support scrolling (applications, activities, companies, company groups, functions and persons). Use scrolling queries (only) if you expect and process large result sets (that won't find on one page). Pass `scroll=true` to the request. The first response will contain a `scrollId`. Call the the `next` interface with the `scrollId` as last path segment to get the next result set.

**Be aware, that the `scrollId` is only valid for a few seconds after each request (it's like a session timeout)! If you don't have enough time to process the data on your side, choose a smaller page size.**

# User for Access

You will need a system user for accessing the data. You can request such a user with a [Partner.&#78;et Wartungsantrag](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_MAINT_PROP). Just create a new document and select "Sonstiges".

Bezeichnung: The name of the user, something like: "My Application System User"

Informationen und Freigaben: We need the name of the application, that will use this user. The application must already be known to the Partner.&#78;et. It depends on the application, which persons are accessible (the consent of the person is needed).

Additionally, we will enter your own user as manager for the system user. This means that you, and nobody else, can request a password for the user. If more users should be allowed to do this, please add a list.

After the system user has been created, you can use the [System User Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) (respectively the the [System User Self-Service for QA](https://qa.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF)) for requesting a password.

# Available Instances

Following instances are accessible:

PROD: https://data.auto-partner.net/data

QA: https://qa-data.auto-partner.net/data

First, perform a login:

```
POST /data/login/ {
	"username": "...",
	"password": "..."
}
```

You will receive a response with a [JSON Web Token](https://jwt.io/) as "Authorization" header field. The token is valid for one hour. Add the header field to all your requests!

```
GET /data/api/v1/about/
```

This will return some information about the server and your user. It's a perfect start.

# Testing

## Using cURL

The simplest way to access the data, is by using the [cURL-Tool](https://curl.haxx.se/). It's available on most Linux installation, and even in Windows, it's quite common and easy to install.

### Perform a login

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

> In windows, you can experiment with the `set` command and `%JWT%` as placeholder.

### Access data

First call the `about` interface, just for testing:

```
curl -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJwbmV0LmFwSWQ...' -X GET https://qa-data.auto-partner.net/data/api/v1/about/
```
> Please, pay attention to the / at the end of the URL and the ' around the authorization header.

This should return some information about the version and your user.

## Using Powershell

### Perform a login

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
 Invoke-WebRequest -uri "$url/api/v1/about/" -Headers $jwt
 ```

> Please, pay attention to the / at the end of the URL.

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

* `activities`
* `advisortypes`
* `applications`
* `brands`
* `companygrouptypes`
* `companynumbertypes`
* `companytypes`
* `contractstates`
* `contracttypes`
* `externalbrands`
* `functions`
* `numbertypes`

With companies and persons, you have to use the `id` instead of the `matchcode`.

* `companies`
* `persons`

CompanyGroups only contain the `details` interface.

* `companygroups`

# Testing with Postman

[Postman](https://www.getpostman.com/) is a free tool for accessing REST interfaces. Download the tool and install it. Finally, you can import the [Partner.&#78;et Data API.postman_collection.json](https://raw.githubusercontent.com/porscheinformatik/pnet-data-api/master/src/postman/Partner.&#78;et%20Data%20API.postman_collection.json) collection with a lot of samples.

## Perform a login

First, execute the "Login" request. Set the username and the password in the "Body" tab (alternatively, you can specify global variables). The request should return with `200 OK`. Select the "Headers" tab and copy the token from the `Authorization` header field.

## Access data

Next, execute the "About" request. Paste the token in the "Authorization" tab (alternatively, you can specify a global variable). Be aware, that the token is only valid for one hour after the login! The request should return with `200 OK` and the "Body" tab should contain some information about versions an the user.

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

You can avoid this problem at all by using the `scroll` parameter (it's only available with `find` queries). Check [Scroll Queries](#scroll-queries) for more information.

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
.../persons/find?scroll=true&activity=TA_ACT_1&activity=TA_ACT_2&activity=TA_ACT_3
```

You may note, that the parameter separator of the URL looks like an "and", but, in this case, it's an "or" condition.

A common pitfall is, that if you are using other conditions in the same query: they are treated as "and". Only conditions to the same field are treated as "or".

```
.../persons/find?scroll=true&activity=TA_ACT_1&activity=TA_ACT_2&function=FU_FUN_1&function=FU_FUN_1
```

This will result in a search that's more like: `(TA_ACT_1 OR TA_ACT_2) AND (FU_FUN_1 OR FU_FUN_2)`.

To avoid this common pitfall, there is a search for `role`s now, searching for both, functions and activities, using an `OR` query.

# I Still Need Help

Feel free to contact us via the inhouse "POI-Partner.Net / Data API" Teams channel or via your contact person at the Porsche Informatik. Please to not contact our development staff directly - they are very jumpy and it takes hours to calm them down.
