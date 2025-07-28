# Partner.&#78;et Data API Java Client Sample

This project contains a sample for using the Java client for the Partner.&#78;et Data API.

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

# Execute

The build will create various executables in the target directory. The sample is a console tool for accessing the Data API. Start it with `java -jar pnet-data-api-java-sample-*-jar-with-dependencies.jar` (don't forget to set the current version instead of the '\*').

The sample application will present you a console interface. Type `help` and press enter. It will show you all available commands.

First, set the URL and the user creditals. Use the `url` command for this:

```
> url https://qa-data.auto-partner.net/data
```

Next, set the token or the username/password:

```
> token <TOKEN>
```

or

```
> user <USERNAME> <PASSWORD>
```

You can aquire the <TOKEN> or the <USERNAME>/<PASSWORD> with the Partner.Net "Systemuser Selfservice" interface.

Use the `about` command to test the connection. It will perform a login on the first request and use the JSON-Web Token for all later ones.

```
> about
```

If the call fails, check the logs in the Partner.Net "Systemuser Selfservice". Maybe you IP is not permitted. If this is the case, add it to the list.

The `about`command will return some information about your user:

```
{
  "partnerNetVersion" : "6.41.9",
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

| Command                                                            | Description                                                                                 |
| ------------------------------------------------------------------ | ------------------------------------------------------------------------------------------- |
| `? [q]`                                                            | Prints this help.                                                                           |
| `about`                                                            | Info about the Partner.Net Data API and the user.                                           |
| `aggs`                                                             | Enables aggregations or prints them, if available.                                          |
| `approval needed`                                                  | Include only items, that need approval.                                                     |
| `approval pending only`                                            | Show only persons, that have not been approved, yet.                                        |
| `approved only`                                                    | Show only persons, that have been approved.                                                 |
| `archived only`                                                    | Show only results, that have been archived.                                                 |
| `auto complete companies <QUERY>`                                  | Auto complete the name of companies.                                                        |
| `auto complete company <QUERY>`                                    | Auto complete the name of companies.                                                        |
| `auto complete person <QUERY>`                                     | Auto complete the name of person.                                                           |
| `auto complete persons <QUERY>`                                    | Auto complete the name of person.                                                           |
| `clear brand restriction`                                          | Removes all restrictions for brands.                                                        |
| `clear brand restrictions`                                         | Removes all restrictions for brands.                                                        |
| `clear company restriction`                                        | Removes all restrictions for companies.                                                     |
| `clear company restrictions`                                       | Removes all restrictions for companies.                                                     |
| `clear query field restriction`                                    | Removes all restrictions to query fields.                                                   |
| `clear query field restrictions`                                   | Removes all restrictions to query fields.                                                   |
| `clear restriction`                                                | Removes all restrictions.                                                                   |
| `clear restrictions`                                               | Removes all restrictions.                                                                   |
| `clear tenant restriction`                                         | Removes all restrictions for tenants.                                                       |
| `clear tenant restrictions`                                        | Removes all restrictions for tenants.                                                       |
| `clear visibility restriction`                                     | Removes all restrictions to query fields.                                                   |
| `compact`                                                          | Print compact results.                                                                      |
| `credentials available only`                                       | Show only persons with login credentials                                                    |
| `dated back [updatedAfter]`                                        | Sets the dated back parameter for the specified days                                        |
| `detailed`                                                         | Print detailed results.                                                                     |
| `exclude inactive`                                                 | Exclude inactive items.                                                                     |
| `exit`                                                             | Exit this program.                                                                          |
| `export all activities`                                            | Exports all activities.                                                                     |
| `export all advisor types`                                         | Exports all advisor types.                                                                  |
| `export all applications`                                          | Exports all applications.                                                                   |
| `export all brands`                                                | Export all brands updated since yesterday.                                                  |
| `export all companies`                                             | Exports all companies.                                                                      |
| `export all company group types`                                   | Exports all company group types.                                                            |
| `export all company groups [<GROUP-MC...>]`                        | Exports all company groups.                                                                 |
| `export all company number types`                                  | Exports all company number types.                                                           |
| `export all company types`                                         | Exports all company types.                                                                  |
| `export all contract states`                                       | Exports all contract states.                                                                |
| `export all contract types`                                        | Exports all contract types.                                                                 |
| `export all external brands`                                       | Exports all external brands.                                                                |
| `export all functions`                                             | Exports all functions.                                                                      |
| `export all legal forms`                                           | Exports all legal forms.                                                                    |
| `export all number types`                                          | Exports all number types.                                                                   |
| `export all persons`                                               | Exports all persons available for the current user.                                         |
| `export updated activities [updatedAfter]`                         | Exports all activities updated since yesterday.                                             |
| `export updated advisor types [updatedAfter]`                      | Exports all advisor types updated since yesterday.                                          |
| `export updated applications [updatedAfter]`                       | Exports all applications updated since yesterday.                                           |
| `export updated brands [updatedAfter]`                             | Export all brands updated since yesterday.                                                  |
| `export updated companies [updatedAfter]`                          | Exports all companies updated since yesterday.                                              |
| `export updated company group types [updatedAfter]`                | Exports all company group types updated since yesterday.                                    |
| `export updated company number types [updatedAfter]`               | Exports updated company number types.                                                       |
| `export updated company types [updatedAfter]`                      | Exports all company types updated since yesterday.                                          |
| `export updated contract states [updatedAfter]`                    | Exports all contract states updated since yesterday.                                        |
| `export updated contract types [updatedAfter]`                     | Exports all contract types updated since yesterday.                                         |
| `export updated external brands [updatedAfter]`                    | Exports all external brands updated since yesterday.                                        |
| `export updated functions [updatedAfter]`                          | Exports all functions updated since yesterday.                                              |
| `export updated legal forms [updatedAfter]`                        | Exports all legal forms updated since yesterday.                                            |
| `export updated number types [updatedAfter]`                       | Exports all number types updated since yesterday.                                           |
| `export updated persons [updatedAfter]`                            | Exports all persons available for the current user, that have been updated since yesterday. |
| `find activities by mc <MC...>`                                    | Find activities by matchcodes.                                                              |
| `find activity by mc <MC...>`                                      | Find activities by matchcodes.                                                              |
| `find advisor type by mc <MC...>`                                  | Find advisor types by matchcodes.                                                           |
| `find advisor types by mc <MC...>`                                 | Find advisor types by matchcodes.                                                           |
| `find application by mc <MC...>`                                   | Find applications by matchcodes.                                                            |
| `find applications by mc <MC...>`                                  | Find applications by matchcodes.                                                            |
| `find brand by mc <MC...>`                                         | Find brands by matchcodes.                                                                  |
| `find brands by mc <MC...>`                                        | Find brands by matchcodes.                                                                  |
| `find companies by id <ID...>`                                     | Find companies by id.                                                                       |
| `find companies by mc <MC...>`                                     | Find companies by matchcode.                                                                |
| `find companies by number <NUMBER...>`                             | Find companies by company number.                                                           |
| `find company by id <ID...>`                                       | Find companies by id.                                                                       |
| `find company by mc <MC...>`                                       | Find companies by matchcode.                                                                |
| `find company by number <NUMBER...>`                               | Find companies by company number.                                                           |
| `find company group by leader <COMPANY-ID...>`                     | Find all company groups with the specified leader.                                          |
| `find company group by member <COMPANY-ID...>`                     | Find all company groups with the specified member.                                          |
| `find company group type by mc <MC...>`                            | Find comany group types by matchcodes.                                                      |
| `find company group types by mc <MC...>`                           | Find comany group types by matchcodes.                                                      |
| `find company groups by leader <COMPANY-ID...>`                    | Find all company groups with the specified leader.                                          |
| `find company groups by member <COMPANY-ID...>`                    | Find all company groups with the specified member.                                          |
| `find company number type by mc <MC...>`                           | Find comany number types by matchcodes.                                                     |
| `find company number types by mc <MC...>`                          | Find comany number types by matchcodes.                                                     |
| `find contract state by mc <MC...>`                                | Find contract states by matchcodes.                                                         |
| `find contract states by mc <MC...>`                               | Find contract states by matchcodes.                                                         |
| `find contract type by mc <MC...>`                                 | Find contract types by matchcodes.                                                          |
| `find contract types by mc <MC...>`                                | Find contract types by matchcodes.                                                          |
| `find external brand by mc <MC...>`                                | Find external brands by matchcodes.                                                         |
| `find external brands by mc <MC...>`                               | Find external brands by matchcodes.                                                         |
| `find function by mc <MC...>`                                      | Find functions by matchcodes.                                                               |
| `find functions by mc <MC...>`                                     | Find functions by matchcodes.                                                               |
| `find legal form by mc <MC...>`                                    | Find comany group types by matchcodes.                                                      |
| `find legal forms by mc <MC...>`                                   | Find comany group types by matchcodes.                                                      |
| `find number type by mc <MC...>`                                   | Find number types by matchcodes.                                                            |
| `find number types by mc <MC...>`                                  | Find number types by matchcodes.                                                            |
| `find person by company <COMPANY-MC...>`                           | Find persons at a specific company.                                                         |
| `find person by email <EMAIL...>`                                  | Find persons by email.                                                                      |
| `find person by id <ID...>`                                        | Find a person by id.                                                                        |
| `find person by personnel number <NUMBER...>`                      | Find persons by personnel number.                                                           |
| `find person by role <ROLE-MC...>`                                 | Find persons by functions and activities.                                                   |
| `find person by salesman number <NUMBER...>`                       | Find persons by salesman number.                                                            |
| `find persons by company <COMPANY-MC...>`                          | Find persons at a specific company.                                                         |
| `find persons by email <EMAIL...>`                                 | Find persons by email.                                                                      |
| `find persons by id <ID...>`                                       | Find a person by id.                                                                        |
| `find persons by personnel number <NUMBER...>`                     | Find persons by personnel number.                                                           |
| `find persons by role <ROLE-MC...>`                                | Find persons by functions and activities.                                                   |
| `find persons by salesman number <NUMBER...>`                      | Find persons by salesman number.                                                            |
| `get URI`                                                          | Performs an GET request.                                                                    |
| `get activities by mc <MC...>`                                     | Returns the activities with the specified matchcodes.                                       |
| `get activity by mc <MC...>`                                       | Returns the activities with the specified matchcodes.                                       |
| `get advisor type by mc <MC...>`                                   | Returns the advisor types with the specified matchcodes.                                    |
| `get advisor types by mc <MC...>`                                  | Returns the advisor types with the specified matchcodes.                                    |
| `get application by mc <MC...>`                                    | Returns the applications with the specified matchcodes.                                     |
| `get applications by mc <MC...>`                                   | Returns the applications with the specified matchcodes.                                     |
| `get brand by mc <MC...>`                                          | Returns the brands with the specified matchcodes.                                           |
| `get brands by mc <MC...>`                                         | Returns the brands with the specified matchcodes.                                           |
| `get companies by dvr <COMPANY-DPRN...>`                           | Returns the companies with the specified data processing register numbers.                  |
| `get companies by email <COMPANY-EMAIL...>`                        | Returns the company with the specified emails.                                              |
| `get companies by iban <COMPANY-IBAN...>`                          | Returns the company with the specified ibans.                                               |
| `get companies by id <COMPANY-ID...>`                              | Returns the companies with the specified ids.                                               |
| `get companies by mc <COMPANY-MC...>`                              | Returns the companies with the specified matchcode.                                         |
| `get companies by number <COMPANY-NUMBER...>`                      | Returns the companies with the specified company numbers.                                   |
| `get companies by vat <COMPANY-VATIDNUMBER...>`                    | Returns the companies with the specified vat id numbers.                                    |
| `get company by dvr <COMPANY-DPRN...>`                             | Returns the companies with the specified data processing register numbers.                  |
| `get company by email <COMPANY-EMAIL...>`                          | Returns the company with the specified emails.                                              |
| `get company by iban <COMPANY-IBAN...>`                            | Returns the company with the specified ibans.                                               |
| `get company by id <COMPANY-ID...>`                                | Returns the companies with the specified ids.                                               |
| `get company by mc <COMPANY-MC...>`                                | Returns the companies with the specified matchcode.                                         |
| `get company by number <COMPANY-NUMBER...>`                        | Returns the companies with the specified company numbers.                                   |
| `get company by vat <COMPANY-VATIDNUMBER...>`                      | Returns the companies with the specified vat id numbers.                                    |
| `get company group by company id <COMPANY-ID...>`                  | Returns the company groups with the specified ids.                                          |
| `get company group by company mc <COMPANY-MC...>`                  | Returns the company groups with the specified matchcodes.                                   |
| `get company group by company number <COMPANY-NUMBER...>`          | Returns the company groups with the specified numbers.                                      |
| `get company group by leading company id <COMPANY-ID...>`          | Returns the company groups with the specified ids.                                          |
| `get company group by leading company mc <COMPANY-MC...>`          | Returns the company groups with the specified matchcodes.                                   |
| `get company group by leading company number <COMPANY-NUMBER...>`  | Returns the company groups with the specified numbers.                                      |
| `get company group by type <MC...>`                                | Returns the company groups with the specified matchcodes.                                   |
| `get company group type by mc <MC...>`                             | Returns the company group types with the specified matchcodes.                              |
| `get company group types by mc <MC...>`                            | Returns the company group types with the specified matchcodes.                              |
| `get company groups by company id <COMPANY-ID...>`                 | Returns the company groups with the specified ids.                                          |
| `get company groups by company mc <COMPANY-MC...>`                 | Returns the company groups with the specified matchcodes.                                   |
| `get company groups by company number <COMPANY-NUMBER...>`         | Returns the company groups with the specified numbers.                                      |
| `get company groups by leading company id <COMPANY-ID...>`         | Returns the company groups with the specified ids.                                          |
| `get company groups by leading company mc <COMPANY-MC...>`         | Returns the company groups with the specified matchcodes.                                   |
| `get company groups by leading company number <COMPANY-NUMBER...>` | Returns the company groups with the specified numbers.                                      |
| `get company groups by type <MC...>`                               | Returns the company groups with the specified matchcodes.                                   |
| `get company number type by mc <MC...>`                            | Returns the company number types with the specified matchcodes.                             |
| `get company number types by mc <MC...>`                           | Returns the company number types with the specified matchcodes.                             |
| `get company type by mc <MC...>`                                   | Returns the company types with the specified matchcodes.                                    |
| `get company types by mc <MC...>`                                  | Returns the company types with the specified matchcodes.                                    |
| `get contract state by mc <MC...>`                                 | Returns the contract states with the specified matchcodes.                                  |
| `get contract states by mc <MC...>`                                | Returns the contract states with the specified matchcodes.                                  |
| `get contract type by mc <MC...>`                                  | Returns the contract types with the specified matchcodes.                                   |
| `get contract types by mc <MC...>`                                 | Returns the contract types with the specified matchcodes.                                   |
| `get external brand by mc <MC...>`                                 | Returns the external brands with the specified matchcodes.                                  |
| `get external brands by mc <MC...>`                                | Returns the external brands with the specified matchcodes.                                  |
| `get function by mc <MC...>`                                       | Returns the functions with the specified matchcodes.                                        |
| `get functions by mc <MC...>`                                      | Returns the functions with the specified matchcodes.                                        |
| `get legal form by mc <MC...>`                                     | Returns the legal forms with the specified matchcodes.                                      |
| `get legal forms by mc <MC...>`                                    | Returns the legal forms with the specified matchcodes.                                      |
| `get number type by mc <MC...>`                                    | Returns the number types with the specified matchcodes.                                     |
| `get number types by mc <MC...>`                                   | Returns the number types with the specified matchcodes.                                     |
| `get person by email <EMAIL...>`                                   | Returns all details of persons with the specified emails.                                   |
| `get person by external id <EXTERNALID...>`                        | Returns all details of persons with the specified external ids.                             |
| `get person by guid <GUID...>`                                     | Returns all details of persons with the specified guids.                                    |
| `get person by id <ID...>`                                         | Returns all details of persons with the specified ids.                                      |
| `get person by personnel number <PERSNUMBER...>`                   | Returns all details of persons with the specified personnelNumbers.                         |
| `get person by preferredUserId <PREFID...>`                        | Returns all details of persons with the specified preferredUserIds.                         |
| `get persons by email <EMAIL...>`                                  | Returns all details of persons with the specified emails.                                   |
| `get persons by external id <EXTERNALID...>`                       | Returns all details of persons with the specified external ids.                             |
| `get persons by guid <GUID...>`                                    | Returns all details of persons with the specified guids.                                    |
| `get persons by id <ID...>`                                        | Returns all details of persons with the specified ids.                                      |
| `get persons by personnel number <PERSNUMBER...>`                  | Returns all details of persons with the specified personnelNumbers.                         |
| `get persons by preferredUserId <PREFID...>`                       | Returns all details of persons with the specified preferredUserIds.                         |
| `get portrait of person <ID>`                                      | Shows the portrait image of the person.                                                     |
| `get thumbnail of person <ID>`                                     | Shows the thumbnail portrait image of the person.                                           |
| `help [q]`                                                         | Prints this help.                                                                           |
| `hidden items`                                                     | Restrict to hidden items.                                                                   |
| `ignore approval needed inactive`                                  | Ignore the approval needed flag.                                                            |
| `ignore approved`                                                  | Ignore the approved flag.                                                                   |
| `ignore archived flag`                                             | Ignore the archived flag.                                                                   |
| `ignore credentials available`                                     | Ignore the crdentials available flag.                                                       |
| `ignore rejected flag`                                             | Ignore the rejected flag.                                                                   |
| `include inactive`                                                 | Include inactive items.                                                                     |
| `jwt`                                                              | Prints the JSON Web Token of the user.                                                      |
| `language LANGUAGE-TAG`                                            | Set the language.                                                                           |
| `list`                                                             | Lists all locally stored keys                                                               |
| `load [<KEY>]`                                                     | Loads the URL and username/password from your prefernces.                                   |
| `logout`                                                           | Invalidates the stored JSON Web Token.                                                      |
| `merge internet groups`                                            | Merges companies according to their internet group settings.                                |
| `merge none`                                                       | Do not merge companies.                                                                     |
| `migrate all <INDEXNAME>`                                          | Performs a full migration for the specified index.                                          |
| `migrate delta <INDEXNAME>`                                        | Performs a delta migration for the specified index.                                         |
| `migrate explicit <INDEXNAME> [<IDS>]`                             | Runs an explicit migration.                                                                 |
| `migrate state <INDEXNAME>`                                        | Prints the state of the migration.                                                          |
| `next`                                                             | Prints the next page of the last result.                                                    |
| `no aggs`                                                          | Disables aggregations.                                                                      |
| `no approval needed`                                               | Include only items, that do not need approval.                                              |
| `no credentials available only`                                    | Show only persons without login credentials                                                 |
| `not archived only`                                                | Show only results, that are not archived.                                                   |
| `not rejected only`                                                | Show only results, that are not rejected.                                                   |
| `page size <SIZE>`                                                 | Sets the number of items per page.                                                          |
| `rejected only`                                                    | Show only results, that have been rejected.                                                 |
| `remove [<KEY>]`                                                   | Remove the URL and username/password from your prefernces.                                  |
| `restrict activities <MC...>`                                      | Places a restriction of activities for subsequent operations.                               |
| `restrict activity <MC...>`                                        | Places a restriction of activities for subsequent operations.                               |
| `restrict brand [<BRAND>...]`                                      | Places a restriction with brands for subsequent operations.                                 |
| `restrict brands [<BRAND>...]`                                     | Places a restriction with brands for subsequent operations.                                 |
| `restrict company id <ID...>`                                      | Places a restriction with company numbers for subsequent operations.                        |
| `restrict company ids <ID...>`                                     | Places a restriction with company numbers for subsequent operations.                        |
| `restrict company mc <MC...>`                                      | Places a restriction with company matchcodes for subsequent operations.                     |
| `restrict company mcs <MC...>`                                     | Places a restriction with company matchcodes for subsequent operations.                     |
| `restrict company number <NUMBER...>`                              | Places a restriction with company numbers for subsequent operations.                        |
| `restrict company numbers <NUMBER...>`                             | Places a restriction with company numbers for subsequent operations.                        |
| `restrict company type <MC...>`                                    | Places a restriction of company types for subsequent operations.                            |
| `restrict company types <MC...>`                                   | Places a restriction of company types for subsequent operations.                            |
| `restrict contract state <MC...>`                                  | Places a restriction of contract states for subsequent operations.                          |
| `restrict contract states <MC...>`                                 | Places a restriction of contract states for subsequent operations.                          |
| `restrict contract type <MC...>`                                   | Places a restriction of contract types for subsequent operations.                           |
| `restrict contract types <MC...>`                                  | Places a restriction of contract types for subsequent operations.                           |
| `restrict function <MC...>`                                        | Places a restriction of functions for subsequent operations.                                |
| `restrict functions <MC...>`                                       | Places a restriction of functions for subsequent operations.                                |
| `restrict number type <MC...>`                                     | Places a restriction of number types for subsequent operations.                             |
| `restrict number types <MC...>`                                    | Places a restriction of number types for subsequent operations.                             |
| `restrict query field [<FIELDS>...]`                               | Places a restriction for query fields.                                                      |
| `restrict query fields [<FIELDS>...]`                              | Places a restriction for query fields.                                                      |
| `restrict tenant [<TENANT>...]`                                    | Places a restriction with tenants for subsequent operations.                                |
| `restrict tenants [<TENANT>...]`                                   | Places a restriction with tenants for subsequent operations.                                |
| `search activities <QUERY>`                                        | Query activities.                                                                           |
| `search activity <QUERY>`                                          | Query activities.                                                                           |
| `search advisor type <QUERY>`                                      | Query advisor types.                                                                        |
| `search advisor types <QUERY>`                                     | Query advisor types.                                                                        |
| `search application <QUERY>`                                       | Query applications.                                                                         |
| `search applications <QUERY>`                                      | Query applications.                                                                         |
| `search brand <QUERY>`                                             | Query brands.                                                                               |
| `search brands <QUERY>`                                            | Query brands.                                                                               |
| `search companies <QUERY>`                                         | Query companies.                                                                            |
| `search company <QUERY>`                                           | Query companies.                                                                            |
| `search company group type <QUERY>`                                | Query company group types.                                                                  |
| `search company group types <QUERY>`                               | Query company group types.                                                                  |
| `search company number type <QUERY>`                               | Query company number types.                                                                 |
| `search company number types <QUERY>`                              | Query company number types.                                                                 |
| `search company type <QUERY>`                                      | Query company types.                                                                        |
| `search company types <QUERY>`                                     | Query company types.                                                                        |
| `search contract state <QUERY>`                                    | Query contract states types.                                                                |
| `search contract states <QUERY>`                                   | Query contract states types.                                                                |
| `search contract type <QUERY>`                                     | Query contract types.                                                                       |
| `search contract types <QUERY>`                                    | Query contract types.                                                                       |
| `search external brand <QUERY>`                                    | Query external brands.                                                                      |
| `search external brands <QUERY>`                                   | Query external brands.                                                                      |
| `search function <QUERY>`                                          | Query functions.                                                                            |
| `search functions <QUERY>`                                         | Query functions.                                                                            |
| `search legal form <QUERY>`                                        | Query legal forms.                                                                          |
| `search legal forms <QUERY>`                                       | Query legal forms.                                                                          |
| `search number type <QUERY>`                                       | Query number types.                                                                         |
| `search number types <QUERY>`                                      | Query number types.                                                                         |
| `search person <QUERY>`                                            | Search for a person.                                                                        |
| `search persons <QUERY>`                                           | Search for a person.                                                                        |
| `store [<KEY>]`                                                    | Stores the URL and username/password to your prefernces.                                    |
| `swagger`                                                          | Opens the Swagger Documentation.                                                            |
| `token <TOKEN>`                                                    | Sets the authentication token.                                                              |
| `url [<URL>]`                                                      | Prints or overrides the predefined URL.                                                     |
| `user [<USERNAME>] [<PASSWORD>]`                                   | Prints or overrides the username and password.                                              |
| `visible items`                                                    | Restrict to visible items.                                                                  |
