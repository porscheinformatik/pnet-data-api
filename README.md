# Partner.&#78;et Data API

This project contains the definition of the API for accessing the data stock of the Partner.&#78;et and clients for the various platforms.

The Partner.&#78;et Data API provides access to the data of the Partner.&#78;et. This data contains:

* persons and companies
* employments (the link between persons and companies)
* roles (the area of activity of a person)
* hosted applications
* and all necessary base data needed by the above items

The access conforms the General Data Protection Regulation.

# REST Interface

The data is available by a REST interface. Check the current version of [Swagger-Documentation](https://data.auto-partner.net/data/swagger-ui.html) for more information.

# User

You will need a systemuser for accessing the data. You can request such a user with a [Partner.Net Wartungsantrag](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_MAINT_PROP). Just create a new document and select "Sonstiges".

Bezeichnung: The name of the user, something like: "My Application System User"

Informationen und Freigaben: We need the name of the application, that will use this user. The application must already be known to the Partner.&#78;et. It depends on the application, which persons are accessible (the consent of the person is needed).

Additionally, we will enter your own user as manager for the systemuser. This means that you, and nobody else, can request a password for the user. If more uses should be allowed to do this, add a list to this field.

After the systemuser has been created, you can use the [Systemuser Self-Service](https://www.auto-partner.net/portal/at/thirdparty?directlink=MN_SYSTEMU_SELF) for requesting a password.

# Access

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

You can use the [Partner.&#78;et Data API.postman_collection.json](https://raw.githubusercontent.com/porscheinformatik/pnet-data-api/master/src/postman/Partner.Net%20Data%20API.postman_collection.json) with the [Postman Tool](https://www.getpostman.com/) for testing. Download the tool, import the collection.

# Java Client Library

If you are using Java, you can checkout the [Partner.&#78;et Data API Java Client](https://github.com/porscheinformatik/pnet-data-api/tree/master/pnet-data-api-java). It contains a useful library and a sample implementation.

