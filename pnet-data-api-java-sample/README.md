# Partner.&#78;et Data API Java Client Sample

This project contains a sample for using the Java client for the Partner.&#78;et Data API.

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

# Execute

You can execute `pnet-data-api-java-sample-*-jar-with-dependencies.jar` in the target directory. It's a console tool for accessing the Data API. Start it with `java -jar pnet-data-api-java-sample-*-jar-with-dependencies.jar` (don't forget to set the current version instead of the '*').

The sample application will present to a console interface. Type `help` and press enter. It will show you all available commands.

First, set the URL and the user creditals. Use the `url` command for this:

```
> url https://qa-data.auto-partner.net/data <USERNAME> <PASSWORD>
```

Replace the `<USERNAME>` and  `<PASSWORD>` with your credentials. Use the `about` command to test the connection. It will perform a login on the first request and use the JSON-Web Token for all later ones.

```
> about
```

It will return some information about your user:

```
{
  "partnerNetVersion" : "6.16.27",
  "dataApiVersion" : "1",
  "userId" : xxx,
  "tenants" : [ "AT" ],
  "authorities" : [ "SWAGGER_API", "DATA_API" ]
}
```

If everything is ok, you can store the URL and the login credentials for later use in the registry:

```
> store <KEY>
```

You can list stored URLs with `list` and load one with `load <KEY>`.

Next try to search for functions. Type:

```
> search functions *
```

It will print the first ten results. To navigate the pages, use `next` and `prev`.

To list all persons, that a visible to your user, try:

```
> export all persons
```

This will list all persons, that gave the consent to the application your system user is linked to.

Finally you can exit the application with `exit`.

# Available Commands

```
?  ..................................... Prints this help.
about  ................................. Info about the Partner.Net Data API and the user.
exit  .................................. Exit this program.
export all activities  ................. Exports all activities.
export all advisor types  .............. Exports all advisor types.
export all applications  ............... Exports all applications.
export all brands  ..................... Export all brands.
export all companies  .................. Exports all companies.
export all company group types  ........ Exports all company group types.
export all company groups  ............. Exports all company groups.
export all company number types  ....... Exports all company number types.
export all company types  .............. Exports all company types.
export all contract states  ............ Exports all contract states.
export all contract types  ............. Exports all contract types.
export all external brands  ............ Exports all external brands.
export all functions  .................. Exports all functions.
export all number types  ............... Exports all number types.
export all persons  .................... Exports all persons available for the current user.
find activity by mc <MATCHCODE...> ..... Find activities by matchcodes.
find advisor types by mc <MATCHCODE...>  Find advisor types by matchcodes.
find applications by mc <MATCHCODE...> . Find applications by matchcodes.
find brands by mc <MATCHCODE...> ....... Find brands by matchcodes.
find companies by id <ID...> ........... Find companies by id.
find companies by number <NUMBER...> ... Find companies by company number.
find company group types by mc <MATCHCODE...>  Find comany group types by matchcodes.
find company groups by leader <COMPANY-ID...>  Find all company groups with the specified leader.
find company groups by member <COMPANY-ID...>  Find all company groups with the specified member.
find company number types by mc <MATCHCODE...>  Find comany number types by matchcodes.
find contract states by mc <MATCHCODE...>  Find contract states by matchcodes.
find contract types by mc <MATCHCODE...>  Find contract types by matchcodes.
find external brands by mc <MATCHCODE...>  Find external brands by matchcodes.
find functions by mc <MATCHCODE...> .... Find functions by matchcodes.
find number types by mc <MATCHCODE...> . Find number types by matchcodes.
find persons by id <ID...> ............. Find a person by id.
find persons by number <NUMBER...> ..... Find persons by personnel number.
get activity by mc <MATCHCODE...> ...... Returns the activities with the specified matchcodes.
get advisor type by mc <MATCHCODE...> .. Returns the advisor types with the specified matchcodes.
get application by mc <MATCHCODE...> ... Returns the applications with the specified matchcodes.
get brand by mc <MATCHCODE...> ......... Returns the brands with the specified matchcodes.
get company by dvr <COMPANY-DPRN...> ... Returns the companies with the specified data processing register numbers.
get company by email <COMPANY-EMAIL...>  Returns the company with the specified emails.
get company by iban <COMPANY-IBAN...> .. Returns the company with the specified ibans.
get company by id <COMPANY-ID...> ...... Returns the companies with the specified ids.
get company by number <COMPANY-NUMBER...>  Returns the companies with the specified company numbers.
get company by sap <COMPANY-SAPNUMBER...>  Returns the companies with the specified sap numbers.
get company by vat <COMPANY-VATIDNUMBER...>  Returns the companies with the specified vat id numbers.
get company group by company id <COMPANY-ID...>  Returns the company groups with the specified ids.
get company group by leading company id <COMPANY-ID...>  Returns the company groups with the specified ids.
get company group by type <MATCHCODE...>  Returns the company groups with the specified matchcodes.
get company group type by mc <MATCHCODE...>  Returns the company group types with the specified matchcodes.
get company number type by mc <MATCHCODE...>  Returns the company number types with the specified matchcodes.
get contract state by mc <MATCHCODE...>  Returns the contract states with the specified matchcodes.
get contract type by mc <MATCHCODE...> . Returns the contract types with the specified matchcodes.
get external brand by mc <MATCHCODE...>  Returns the external brands with the specified matchcodes.
get function by mc <MATCHCODE...> ...... Returns the functions with the specified matchcodes.
get number type by mc <MATCHCODE...> ... Returns the number types with the specified matchcodes.
get persons by email <EMAIL...> ........ Returns all details of persons with the specified emails.
get persons by external id <EXTERNALID...>  Returns all details of persons with the specified external ids.
get persons by guid <GUID...> .......... Returns all details of persons with the specified guids.
get persons by id <ID...> .............. Returns all details of persons with the specified ids.
get persons by personnelNumber <PERSNUMBER...>  Returns all details of persons with the specified personnelNumbers.
get persons by preferredUserId <PREFID...>  Returns all details of persons with the specified prefferedUserIds.
help  .................................. Prints this help.
list  .................................. Lists all locally stored keys
load [<KEY>] ........................... Loads the URL and username/password from your prefernces.
logout  ................................ Invalidates the stored JSON Web Token.
migrate all <INDEXNAME> ................ Performs a full migration for the specified index.
migrate delta <INDEXNAME> .............. Performs a delta migration for the specified index.
migrate explicit <INDEXNAME> [<IDS>] ... Runs an explicit migration.
migrate state <INDEXNAME> .............. Prints the state of the migration.
next  .................................. Prints the next page of the last result.
page [<NUMBER>] ........................ Prints the page with the specified number.
prev  .................................. Prints the previous page of the last result.
remove [<KEY>] ......................... Remove the URL and username/password from your prefernces.
search activities <QUERY> .............. Query activities.
search advisor types <QUERY> ........... Query advisor types.
search applications <QUERY> ............ Query applications.
search brands <QUERY> .................. Query brands.
search companies <QUERY> ............... Query companies.
search company group types <QUERY> ..... Query company group types.
search company group types <QUERY> ..... Query company group types.
search company number types <QUERY> .... Query company number types.
search company types <QUERY> ........... Query company types.
search contract states <QUERY> ......... Query contract states types.
search contract types <QUERY> .......... Query contract types.
search external brands <QUERY> ......... Query external brands.
search functions <QUERY> ............... Query functions.
search number types <QUERY> ............ Query number types.
search persons <QUERY> ................. Search for a person.
store [<KEY>] .......................... Stores the URL and username/password to your prefernces.
swagger  ............................... Opens the Swagger Documentation.
tenant [<TENANT>...] ................... Sets the tenant filter.
token  ................................. Prints the JSON Web Token of the user.
url [<URL>] [<USERNAME>] [<PASSWORD>] .. Prints or overrides the predefined URL.
user [<USERNAME>] [<PASSWORD>] ......... Prints or overrides the username and password.
```
