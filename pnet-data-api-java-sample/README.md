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

The sample application will present you a console interface. Type `help` and press enter. It will show you all available commands.

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
> search functions verkäufer
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
? [q] .................................. Prints this help.
about  ................................. Info about the Partner.Net Data API and the user.
aggs  .................................. Enables aggregations or prints them, if available.
clear brand restrictions  .............. Removes all restrictions for brands.
clear company restrictions  ............ Removes all restrictions for companies.
clear restrictions  .................... Removes all restrictions.
clear tenant restrictions  ............. Removes all restrictions for tenants.
dated back [<DAYS>] .................... Sets the dated back parameter for the specified days
exit  .................................. Exit this program.
export all activities  ................. Exports all activities.
export all advisor types  .............. Exports all advisor types.
export all applications  ............... Exports all applications.
export all brands  ..................... Export all brands updated since yesterday.
export all companies  .................. Exports all companies.
export all company group types  ........ Exports all company group types.
export all company groups [<GROUP-MC...>]  Exports all company groups.
export all company number types  ....... Exports all company number types.
export all company types  .............. Exports all company types.
export all contract states  ............ Exports all contract states.
export all contract types  ............. Exports all contract types.
export all external brands  ............ Exports all external brands.
export all functions  .................. Exports all functions.
export all number types  ............... Exports all number types.
export all persons  .................... Exports all persons available for the current user.
export all todo groups  ................ Exports all todo groups.
export all updated activities [<DAYS>:1]  Exports all activities updated since yesterday.
export all updated advisor types [<DAYS>:1]  Exports all advisor types updated since yesterday.
export all updated applications [<DAYS>:1]  Exports all applications updated since yesterday.
export all updated brands [<DAYS>:1] ... Export all brands updated since yesterday.
export all updated companies [<DAYS>:1]  Exports all companies updated since yesterday.
export all updated company group types [<DAYS>:1]  Exports all company group types updated since yesterday.
export all updated company number types [<DAYS>:1]  Exports all updated company number types.
export all updated company types [<DAYS>:1]  Exports all company types updated since yesterday.
export all updated contract states [<DAYS>:1]  Exports all contract states updated since yesterday.
export all updated contract types [<DAYS>:1]  Exports all contract types updated since yesterday.
export all updated external brands [<DAYS>:1]  Exports all external brands updated since yesterday.
export all updated functions [<DAYS>:1]  Exports all functions updated since yesterday.
export all updated number types [<DAYS>:1]  Exports all number types updated since yesterday.
export all updated persons [<DAYS>:1] .. Exports all persons available for the current user, that have been updated since yesterday.
export all updated todo groups [<DAYS>:1]  Exports all todo groups updated since yesterday.
find activities by mc <MC...> .......... Find activities by matchcodes.
find advisor types by mc <MC...> ....... Find advisor types by matchcodes.
find applications by mc <MC...> ........ Find applications by matchcodes.
find brands by mc <MC...> .............. Find brands by matchcodes.
find companies by id <ID...> ........... Find companies by id.
find companies by mc <MC...> ........... Find companies by matchcode.
find companies by number <NUMBER...> ... Find companies by company number.
find company group types by mc <MC...> . Find comany group types by matchcodes.
find company groups by leader <COMPANY-ID...>  Find all company groups with the specified leader.
find company groups by member <COMPANY-ID...>  Find all company groups with the specified member.
find company number types by mc <MC...>  Find comany number types by matchcodes.
find contract states by mc <MC...> ..... Find contract states by matchcodes.
find contract types by mc <MC...> ...... Find contract types by matchcodes.
find external brands by mc <MC...> ..... Find external brands by matchcodes.
find functions by mc <MC...> ........... Find functions by matchcodes.
find number types by mc <MC...> ........ Find number types by matchcodes.
find persons by company <COMPANY-MC...>  Find persons at a specific company.
find persons by id <ID...> ............. Find a person by id.
find persons by personnel number <NUMBER...>  Find persons by personnel number.
find persons by role <ROLE-MC...> ...... Find persons by functions and activities.
find persons by salesman number <NUMBER...>  Find persons by salesman number.
find todo groups by category <CATEGORY...>  Find todo groups by category.
find todo groups by mc <ID...> ......... Find todo groups by reference matchcode.
find todo groups by person id <PERSION-ID...>  Find todo groups by person id.
get activity by mc <MC...> ............. Returns the activities with the specified matchcodes.
get advisor type by mc <MC...> ......... Returns the advisor types with the specified matchcodes.
get application by mc <MC...> .......... Returns the applications with the specified matchcodes.
get brand by mc <MC...> ................ Returns the brands with the specified matchcodes.
get company by dvr <COMPANY-DPRN...> ... Returns the companies with the specified data processing register numbers.
get company by email <COMPANY-EMAIL...>  Returns the company with the specified emails.
get company by iban <COMPANY-IBAN...> .. Returns the company with the specified ibans.
get company by id <COMPANY-ID...> ...... Returns the companies with the specified ids.
get company by mc <COMPANY-MC...> ...... Returns the companies with the specified matchcode.
get company by number <COMPANY-NUMBER...>  Returns the companies with the specified company numbers.
get company by sap <COMPANY-SAPNUMBER...>  Returns the companies with the specified sap numbers.
get company by vat <COMPANY-VATIDNUMBER...>  Returns the companies with the specified vat id numbers.
get company group by company id <COMPANY-ID...>  Returns the company groups with the specified ids.
get company group by company mc <COMPANY-MC...>  Returns the company groups with the specified matchcodes.
get company group by company number <COMPANY-NUMBER...>  Returns the company groups with the specified numbers.
get company group by leading company id <COMPANY-ID...>  Returns the company groups with the specified ids.
get company group by leading company mc <COMPANY-MC...>  Returns the company groups with the specified matchcodes.
get company group by leading company number <COMPANY-NUMBER...>  Returns the company groups with the specified numbers.
get company group by type <MC...> ...... Returns the company groups with the specified matchcodes.
get company group type by mc <MC...> ... Returns the company group types with the specified matchcodes.
get company number type by mc <MC...> .. Returns the company number types with the specified matchcodes.
get contract state by mc <MC...> ....... Returns the contract states with the specified matchcodes.
get contract type by mc <MC...> ........ Returns the contract types with the specified matchcodes.
get external brand by mc <MC...> ....... Returns the external brands with the specified matchcodes.
get function by mc <MC...> ............. Returns the functions with the specified matchcodes.
get number type by mc <MC...> .......... Returns the number types with the specified matchcodes.
get person by email <EMAIL...> ......... Returns all details of persons with the specified emails.
get person by external id <EXTERNALID...>  Returns all details of persons with the specified external ids.
get person by guid <GUID...> ........... Returns all details of persons with the specified guids.
get person by id <ID...> ............... Returns all details of persons with the specified ids.
get person by personnelNumber <PERSNUMBER...>  Returns all details of persons with the specified personnelNumbers.
get person by preferredUserId <PREFID...>  Returns all details of persons with the specified prefferedUserIds.
get portrait of person <ID> ............ Shows the portrait image of the person.
get thumbnail of person <ID> ........... Shows the thumbnail portrait image of the person.
help [q] ............................... Prints this help.
list  .................................. Lists all locally stored keys
load [<KEY>] ........................... Loads the URL and username/password from your prefernces.
logout  ................................ Invalidates the stored JSON Web Token.
merge internet groups  ................. Merges companies according to their internet group settings.
merge none  ............................ Do not merge companies.
migrate all <INDEXNAME> ................ Performs a full migration for the specified index.
migrate delta <INDEXNAME> .............. Performs a delta migration for the specified index.
migrate explicit <INDEXNAME> [<IDS>] ... Runs an explicit migration.
migrate state <INDEXNAME> .............. Prints the state of the migration.
next  .................................. Prints the next page of the last result.
no aggs  ............................... Disables aggregations.
page [<NUMBER>] ........................ Prints the page with the specified number.
prev  .................................. Prints the previous page of the last result.
remove [<KEY>] ......................... Remove the URL and username/password from your prefernces.
restrict activities <MC...> ............ Places a restriction of activities for subsequent operations.
restrict brands [<BRAND>...] ........... Places a restriction with brands for subsequent operations.
restrict company ids <ID...> ........... Places a restriction with company numbers for subsequent operations.
restrict company mcs <MC...> ........... Places a restriction with company matchcodes for subsequent operations.
restrict company numbers <NUMBER...> ... Places a restriction with company numbers for subsequent operations.
restrict functions <MC...> ............. Places a restriction of functions for subsequent operations.
restrict number types <MC...> .......... Places a restriction of number types for subsequent operations.
restrict tenants [<TENANT>...] ......... Places a restriction with tenants for subsequent operations.
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
search todo groups <QUERY> ............. Query todo groups.
store [<KEY>] .......................... Stores the URL and username/password to your prefernces.
swagger  ............................... Opens the Swagger Documentation.
token  ................................. Prints the JSON Web Token of the user.
url [<URL>] [<USERNAME>] [<PASSWORD>] .. Prints or overrides the predefined URL.
user [<USERNAME>] [<PASSWORD>] ......... Prints or overrides the username and password.
```
