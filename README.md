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
* [Testing with cURL](#testing-with-curl)
* [Testing with Postman](#testing-with-postman)
* [Java Client Library](#java-client-library)

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
* Number Types

Most of the data interfaces provide three methods:

* details - For accessing all available information about the data item (e.g. the person with the id 100).
* search - For accessing a reduced amount of information per data item, but supporting fuzzy searches including wildcards (e.g. all persons called "Jane").
* find - For accessing a reduced amount of information per data item, but with fast filters (e.g. all persons with a defined function).

The person data is filtered by default. You will only be able to access persons, that are relevant for your application (gave the consent for accessing their data) and the data of each person is filtered according to the claims of your application.

# Considerations

## Performance

The Partner.&#78;et Data API is a scaleable application hosted in a cloud environment and is based on an NoSQL-Database in the background. Requests should be fast and the data should be accurate. The data from the Partner.&#78;et get's synced every few seconds and should be up-to-date most of the time.

The REST Interface provides the data as fast as it can. Currently we don't have performance issues because of too many users or too many requests. Generally, it is better to make multple smaller requests, than fewer larger ones.

Consider caching relevant information! Most of the data does not change very often. We encourage you to store results locally and reuse the information instead of performing the same request over and over again.

Use scroll-queries if you want to process large result sets.

## Paging

The result of each request is paged, that means, that it splits the result into multiple pages. You can add the desired page size to each request (`pp` parameter), but the server may reduce it, if it is too large. Currently, each page is limited to, at most, 100 items.

You can request subsequent pages by adding a page (`p`) parameter, but the rest of the request must be exactly the same (otherwise you may miss some items).

## Last Update

If you are syncing data with your local database, consider using the `lastUpdate` value. You can add an `up`-parameter to your find request, and the result will only contain items, that have been changed since your specified date.

**When syncing person data with your application, this is a must.**

## Details vs Search vs Find

The difference between `search` and `find`, is, that the `search` aims for user interaction (e.g. like searching with a fuzzy query) and the `find` aims for technical solutions (e.g. like finding all persons with a defined function).

Use `search` only, if you need a fuzzy search for a specified term other than `*`! For all other cases, use `find`!

The `details` requests return more information per item, but the request is slower compared to the `find` and `search` requests.

## Scroll-Queries

Some `find`-queries support scrolling (applications, activities, companies, company groups, functions and persons). Use scrolling queries (only) if you expect and process large result sets (> 1000 entries). Pass `scroll=true` to the request. The first response will contain a `scrollId`. Call the the `next` interface with the `scrollId` as last path segment to get the next result set.

**Be aware, that the `scrollId` is only valid for a few seconds after each request (it's like a session timeout)! If you don't have enough time to process the data on your side, choose a smaller page size.**

# User for Access

You will need a systemuser for accessing the data. You can request such a user with a [Partner.Net Wartungsantrag](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_MAINT_PROP). Just create a new document and select "Sonstiges".

Bezeichnung: The name of the user, something like: "My Application System User"

Informationen und Freigaben: We need the name of the application, that will use this user. The application must already be known to the Partner.&#78;et. It depends on the application, which persons are accessible (the consent of the person is needed).

Additionally, we will enter your own user as manager for the systemuser. This means that you, and nobody else, can request a password for the user. If more users should be allowed to do this, please add a list.

After the systemuser has been created, you can use the [Systemuser Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) for requesting a password.

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

# Testing with cURL

The simpliest way to access the data, is by using the [cURL-Tool](https://curl.haxx.se/). It's available on most Linux installation, and even in Windows, it's quite common and easy to install.

## Perform a login

```
curl -i -X POST https://qa-data.auto-partner.net/data/login -d '{"username":"...","password":"..."}'
```

> Keep in mind, that the Linux-Bash stores the history to a file called ` ~/.bash_history` after you close the shell. You may want to remove the password from the history.

The command returns an authorization header, that's the JSON-Web Token:

```
HTTP/1.1 200
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJwbmV0LmFwSWQ...
```

Copy `Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJwbmV0LmFwSWQ...` and store the JWT somewhere temporarely - it's valid for one hour.

## Access data

First call the `about` interface, just for testing:

```
curl -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJwbmV0LmFwSWQ...' -X GET https://qa-data.auto-partner.net/data/api/v1/about/
```
> Please, pay attention to the / at the end of the URL and the ' around the authorization header.

This should return some information about the version and your user.

## Search data

The simpliest call, is to search for data. Let's test it with functions:

```
curl -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJwbmV0LmFwSWQ...' -X GET https://qa-data.auto-partner.net/data/api/v1/functions/search?l=en\&q=*
```

> You may notice the \\ before the &. In some shells, this it's necessary to escape the &, because it is a delimiter for the command.

> With `search` and `find` requests, you need to specify the language with the parameter `l=en`.

The command should return lot's of functions. You can copy the `matchcode` and get the details for it:

```
curl -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJwbmV0LmFwSWQ...' -X GET https://qa-data.auto-partner.net/data/api/v1/functions/details/FU_ST_USER
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

## Pro Tip

When you are using bash, you can export the token and use it as a placeholder.

```
export JWT='Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJwbmV0LmFwSWQ...'
```

You can use the token with `$JWT`.

In windows, you can experiment with the `set` command and `%JWT%` as placeholder.

# Testing with Postman

[Postman](https://www.getpostman.com/) is a free tool for accessing REST interfaces. Download the tool and install it. Finally, you can import the [Partner.&#78;et Data API.postman_collection.json](https://raw.githubusercontent.com/porscheinformatik/pnet-data-api/master/src/postman/Partner.Net%20Data%20API.postman_collection.json) collection with a lot of samples.

## Perform a login

First, execute the "Login" request. Set the username and the password in the "Body" tab (alternatively, you can specify global variables). The request should return with `200 OK`. Select the "Headers" tab and copy the token from the `Authorization` header field.

## Access data

Next, execute the "About" request. Paste the token in the "Authorization" tab (alternatively, you can specify a global variable). Be aware, that the token is only valid for one hour after the login! The request should return with `200 OK` and the "Body" tab should contain some information about versions an the user.

# Java Client Library

If you are using Java, you can checkout the [Partner.&#78;et Data API Java Client](https://github.com/porscheinformatik/pnet-data-api/tree/master/pnet-data-api-java). It contains a useful library and a sample implementation.

Tip: You can use the [Sample Implementation](https://github.com/porscheinformatik/pnet-data-api/tree/master/pnet-data-api-java-sample) as a standalone client.

