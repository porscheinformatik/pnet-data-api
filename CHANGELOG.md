pnet-data-api 2.0.3
====================

* Add autoComplete to applications.
* Add autoComplete to activities.
* Add autoComplete to functions.

pnet-data-api 2.0.2
===================

* Add includeAllFunctions option to PersonDataSearch & -Find.

pnet-data-api 2.0.1
===================

* Add sort option for Person find operation.

pnet-data-api 2.0.0
===================

* Raised compiler compatibility level to Java 17.

* Compatibility with Spring Boot 3, but there weren't any changes, that may prevent you from using this version with an
  older Spring Boot version (since the Data API does not depend on it, nor does is use any Jakarta packages). You still
  have to use up-to-date libraries like Jackson 2.14 or greater, Spring-Web 6.0 or greater (if using the Spring
  connector) and Java 17 or greater.

pnet-data-api 1.21.3
====================

* Add autoComplete to applications.
* Add autoComplete to activities.
* Add autoComplete to functions.

pnet-data-api 1.21.2
====================

* Add includeAllFunctions option to PersonDataSearch & -Find.

pnet-data-api 1.21.1
====================

* Add sort option for Person find operation.

pnet-data-api 1.21.0
====================

* Add a Spring Boot sample.
* Upgrade dependencies.

pnet-data-api 1.20.6
====================

* Fix the impementation of the isCovering method of the ApprovalState.

pnet-data-api 1.20.5
====================

* Add registeredOffice to CompanyDataDTO.

pnet-data-api 1.20.4
====================

* Date values should be checked for equality by using a compare operation.
* Add settings to the PersonDataDTO.

pnet-data-api 1.20.3
====================

* Add the ability to search and find todos by assigned persons.

pnet-data-api 1.20.2
====================

* Some fixes to make the client compatible with Java 1.8

pnet-data-api 1.20.1
====================

* Added a refreshed date to todos.

pnet-data-api 1.20.0
====================

* Added possibility to use authentication tokens for Systemusers. Have a look at https://github.com/porscheinformatik/pnet-data-api/tree/master/pnet-data-api-java#readme for detailed instructions. Some code has been deprecated, notably the PnetDataApiPrefs were replaced by PnetDataApiLoginMethod.
* Fix compatibility issues with different types of RestCall implementations.
* Add Scopes to Application
* Add multifactorEnabled to persons.
* Add extensionNumber to persons.
* Add team to persons.
* Add approval state to number types.

pnet-data-api 1.19.5
====================

* Add the distribution network to companies.

pnet-data-api 1.19.3
====================

* Add enums ProposalAction and ProposalGroup.

pnet-data-api 1.19.2
====================

* Optimize RestCall implementations.
* Restore the deprecated id in PersonDataDto to prevent interface change.

pnet-data-api 1.19.1
====================

* DatedBackUntil feature for auto-complete.
* Added datedBackByDays(int) as comfort function.
* anyFunction() to filter persons with any active function
* anyActivity() to filter persons with any active activity
* anyBrand() to filter persons, companies, functions, ... with any active brand

pnet-data-api 1.19.0
====================

* Remove String.format from exceptions to prevent formatting errors.
* Removed some old deprecated code.
* Fixed unencoded variables in REST calls using the Java Client.
* Fixed unencoded variables in REST calls using the Apache Client.
* Fixed invalid charset in form parameters when using Apache Client.

pnet-data-api 1.18.5
====================

* Fix media type detection for RestCalls.
* Remove path option from RestCall methods.
* Prefer raw parsing over JSON in Apache and JavaRestCalls

pnet-data-api 1.18.4
====================

* Fix case-insensitive headers for Apache client

pnet-data-api 1.18.3
====================

* Added ZonedDateTime to Jackson definition.
* Added a "isCovering" method to the ApprovalState.

pnet-data-api 1.18.2
====================

* Added ApprovalState.DRAFT to the "inWork" ones.

pnet-data-api 1.18.1
====================

* Fix conversion of multiple content types
* Fix handling of default MediaType

pnet-data-api 1.18.0
====================

* Replaced any "String contentType" with a "MediaType contentType". This is a breaking change. It should be rarely used by any application
  and it is an internal change, basically. In case, you did you the contentType, just use the at.porscheinformatik.happyrest.MediaType
  class instead.
* Add a NumberParser to the Rest framework to improve interoparability between technologies.


pnet-data-api 1.17.2
====================

* Optimized TodoCategory.

pnet-data-api 1.17.1
====================

* Added new TodoCategory.

pnet-data-api 1.17.0
====================

* Renamed approval in todos to submit (to avoid confusion)
* Added ApprovalState to items (improves "approved")

pnet-data-api 1.16.1
====================

* Upcoming approval needed and visibility information

pnet-data-api 1.16.0
====================

* Upcoming approval information
* Convenience methods in DTOs

pnet-data-api 1.15.3
====================

* Simplified Swagger dependencies.
* Add find by email to sample client.
* Fix some bugs in GenericType, that did not detect extended types of subclasses.
* Fix exception handling in JavaRestCall.
* Update build dependencies.

pnet-data-api 1.15.2
====================

* Allow filtering for contract states with companies (needs Partner.Net 6.28)

pnet-data-api 1.15.1
====================

* Adds the "credentialsAvailable" flag to persons and apicable searches  (needs Partner.Net 6.28)

pnet-data-api 1.15.0
====================

* Adds "includeInactive" for Company/Person "get" calls (needs Partner.Net 6.27)
* Fixes some references, to ensure, that the Java and Apache clients can be executed without Spring.
* Adds various assemblies for the sample client.

pnet-data-api 1.14.10
=====================

* Documented scroll id.
* Added code template to Sample application.
* Updated Postman collection and documentation accordingly.

pnet-data-api 1.14.9
====================

* Fix truncation of slash at the end of URLs, part II.

pnet-data-api 1.14.8
====================

* Fix truncation of slash at the end of URLs.

pnet-data-api 1.14.7
====================

* Fix possible NPE in StringParser (REST adapter).
* Allow Java-Client to follow redirects.

pnet-data-api 1.14.6
====================

* Fix missing content type in Java and Apache client.
* Removed UTF-8 from JSON content type, as it is the default.
* Added CharArrayParser and StringParser to the REST adapter.

pnet-data-api 1.14.5
====================

* Fix encoding of path segments.
* Timeout, proxy and user agent settings for Java and Apache client.
    - In Spring, you have to customize the RestTemplate.
* User agent for Java and Apache clients now match Spring client.
* User agent now contains technology hint.
* Fixed Spring 4 client to use SpringRestFormatter instead of ConversionService directly.
* Fixed portrait parsing in Java client.
* Removed some deprecated code.

pnet-data-api 1.14.4
====================

* Support Telegram in company contacts.
* Fix image stream conversion in Apache/Java client.
* Optimizations to the sample client.

pnet-data-api 1.14.3
====================

* This version was lost due to an unfortuate release accident.

pnet-data-api 1.14.2
====================

* Configurable ZoneId in the JacksonPnetDataApiModule, responsible for parsing server responses.
* Additional numbers (e.g. salesman number) in person find/search results.

pnet-data-api 1.14.1
====================

* "includeInactive" flag for companies and persons.
* Quality of life enhancements in the sample client.
* Fixed company type restrictions in sample client.

pnet-data-api 1.14.0
====================

* AutoComplete feature for:
    * Persons
    * Comapnies
* Better error handling in non-Spring clients.
* Dependency upgrades

pnet-data-api 1.13.5
====================

* Reuses Java's HttpClient to prevent too many listener threads in background.

pnet-data-api 1.13.4
====================

* Ordering for proposals
* Upgraded Jackson (minor step).

pnet-data-api 1.13.3
====================

* Proposals (internal)
* Upgraded Jackson (minor step).

pnet-data-api 1.13.2
====================

* Fixed an NPE with Spring initializiation for default RestCallFactory.

pnet-data-api 1.13.1
====================

* Upgraded Spring and Jackson (minor step).

pnet-data-api 1.13.0
====================

* Added a JavaRestCall using the Java 9 HTTP Client.
* Added an ApacheRestCall using the Apache HTTP Client.
* Some minor changes to the HappyRest code, e.g. RestConverter renamed to RestFormatter.
* Added a sample, that works without Spring. It uses a Java 9 HTTP Client and builds the necessary classes manually.
* Added a sample, that works without Spring. It uses an Apache HTTP Client and builds the necessary classes manually.
* Added a JavaClientFactory and an ApacheClientFactory for ease of use.
* Updated documentation.

pnet-data-api 1.12.3
====================

* Added legal forms.

pnet-data-api 1.12.2
====================

* Fixed typo: vibra -> viber

pnet-data-api 1.12.1
====================

* Added data for impressum to companies.
* SAP number is deprecated. Use company number instead.
* Removed project pages.

pnet-data-api 1.12.0
====================

* Added "additionalNameAffix" to companies.
* Name, NameAffix and Marketingname of companies are not deprecated anymore.
* SpeedDial of company is deprecated, as it is not maintained anymore.
* Added "facebookLink", "youTubeLink", "instagramLink" and "vibraLink" to companies.

pnet-data-api 1.11.3
====================

* Allow company searched to be restricted by company ids.

pnet-data-api 1.11.2
====================

* Added a possibility to specify the fields that will be searched by the query.

pnet-data-api 1.11.1
====================

* Fix typo in CompanyMerge.INTERET_GROUP. Both versions are available now.

pnet-data-api 1.11.0
====================

* Add currently active to Active*LinkDTOs

pnet-data-api 1.10.15
=====================

* Fix a typo with preferred user ids.
* Added Restrict* interfaces where applicable.

pnet-data-api 1.10.14
=====================

* Fix determination of the client version.

pnet-data-api 1.10.13
=====================

* Additional fields for the PersonItemDTO:
 * phoneNumber
 * mobileNumber
 * languages

pnet-data-api 1.10.12
=====================

* Critical fix for the client, that does not restore the token when getting portraits.
* Sugar for the sample app.

pnet-data-api 1.10.11
=====================

* Fixed aggregations in mocks for testing.

pnet-data-api 1.10.10
=====================

* Enhancements for the incredibly useful GenericType class
    * isInstance
    * isAssignableFrom
    * getSimpleTypeName

pnet-data-api 1.10.9
====================

* Additional options for the sample client.
* Fixed automatic login after token has expired.
* Fixed warnings and upgraded dependencies to the newest versions.

pnet-data-api 1.10.8
====================

* Removed unused dependency to Apache HTTP Client.

pnet-data-api 1.10.7
====================

* Updated libraries.
* Libraries are now "provided" - you have to add them manually. See the documentation, which dependencies you may need.
* Slf4j is now optional, too (use the Slf4jRestLoggerAdapter or implement your own RestLoggerAdapter, if necessary).

pnet-data-api 1.10.6
====================

* Clarifying error message, if version cannot be determined.
* Empty queries will not be replaces with "*" (* is not supported anymore)
* Unifying company labels, added label with number.
* Aggregations now have labels, where appicable.

pnet-data-api 1.10.5
====================

* Add a score to most ItemDTOs.
* All DTOs are Serializable, now.

pnet-data-api 1.10.4
====================

* Add company label where suitable.
* Add function and activity label where suitable.
* Add valid-from/valid-to to persons' activities.
* Add brands to person find and search methods.
* Add search for "roles" in order to search for "functions OR activities".
* Add "datedBackUntil" to request historic data.

pnet-data-api 1.10.3
====================

* Add salesman number to person find.

pnet-data-api 1.10.2
====================

* Add function filter to client mock.

pnet-data-api 1.10.1
====================

* Fix wrong JSON property in TodOGroupAggregationsDTO.
* Add missing serializable in TodoGroupItemDTO.

pnet-data-api 1.10.0
====================

* Add state aggregations for todos

pnet-data-api 1.9.9
===================

* Allow search with spaces in sample client.
* Display portraits in sample client.
* Simplified integration into Spring 4.

pnet-data-api 1.9.8
===================

* Add main functions directly to person items.

pnet-data-api 1.9.7
===================

* Add username to person items.
* Add contract company to persons.

pnet-data-api 1.9.6
===================

* Sending meaningful user-agent with REST requests.

pnet-data-api 1.9.5
===================

* Added methods with collections for all restrictions.
* Renamed "aggregates" to "aggregations" for consistency.
* Added merge by internet group feature to person queries.
* Added the portrait of a person to the client.

pnet-data-api 1.9.4
===================

* Added merge by internet group feature to company queries.

pnet-data-api 1.9.3
===================

* Added mocks for most clients to simplify testing.

pnet-data-api 1.9.2
===================

* Added aggregations to fluent search API.
* Added `streamAll()` and `interatorAll()` to result pages to iterate over all result items of this page and all subsequent pages (calls `nextPage()` automatically).
* Added restrictions (filter) to the sample client.

Known Bugs:

* When using scrolling and the `next` interface, the `pageIndex` keeps being zero. This is a problem caused by a third-party library and will not be fixed right now.

pnet-data-api 1.9.1
===================

* Allow find operations for todo-ids.

pnet-data-api 1.9.0
===================

* Aggregations for companies, persons and todo groups.

pnet-data-api 1.8.4
===================

* DateTime values now get converted to system default.

pnet-data-api 1.8.3
===================

* Added phase to to-do groups.

pnet-data-api 1.8.2
===================

* The id of the person was renamed to personId (the old field is deprecated).
* Added the WithCompanyId interface, where applicable.
* Added externalId, guid, preferredUserId and companies to person items.

pnet-data-api 1.8.1
===================

* Added company numbers and matchcodes where to groups, too.

pnet-data-api 1.8.0
===================

* Languages for Persons.
* Academic title post nominal for Persons.
* Company numbers and matchcodes where applicable.

pnet-data-api 1.7.8
===================

* Added test to reproduce possible error.

pnet-data-api 1.7.7
===================

**Changes to the Java Client:**

* Removed updatedAfter from company groups (it's not supported)
* Added updatedAfter to functions (was missing)

pnet-data-api 1.7.7
===================

**Changes to the API:**

* Additional TodoStates.
* Replaced rejected flag in to-dos with state.

pnet-data-api 1.7.6
===================

**Changes to the API:**

* Adds categories with translations (PNET-1261)

pnet-data-api 1.7.5
===================

**Changes to the API:**

* Add PERSON_LOCK TodoCategory.

pnet-data-api 1.7.4
===================

**Changes to the API:**

* Added a reference matchcode to to-do groups.

pnet-data-api 1.7.3
===================

**Changes to the API:**

* Added email to person search and find results.

pnet-data-api 1.7.2
===================

**Changes to the API:**

* Added category label to to-do entries.

pnet-data-api 1.7.1
===================

**Changes to the API:**

* Added finished date to to-do entries.

pnet-data-api 1.7.0
===================

**Changes to the API:**

* Birthdate has been added

pnet-data-api 1.6.0
===================

**Changes to the API:**

* Added access to to-dos.

**Changes to the Java sample client:**

* The "help" command supports a query parameter.

pnet-data-api 1.5.2
===================

**Changes to the API:**

* Added deletion flag for persons.

pnet-data-api 1.5.1
===================

**Changes to the Java client:**

* Added missing LocalDate serializer and deserializer.

pnet-data-api 1.5.0
===================

**Changes to the API:**

* Added birthdate for persons.
* Documentation

**Others:**

* Changed Postman examples from PROD to QA.

pnet-data-api 1.4.0
===================

**Changes to the API:**

* Support for faster (scrolling) mass queries in:
  * activities (find)
  * applications (find)
  * companies (find)
  * company groups (get)
  * functions (find)
  * persons (find)

**Changes to the Java client:**

* Support for the scrolling mass queries.

pnet-data-api 1.3.1
===================

**Changes to the Java client:**

* Better error messages.
* NextPage returns null, if there are no more pages, instead of an empty page.

**Changes to the Java client sample:**

* Reordered methods by type.
* Adds export methods for testing scroll support.

pnet-data-api 1.3.0
===================

* Add the personnel number to the person search/find result.

**Changes to the Java client:**

* Add company number to company find interface.
* Add personnel number to person item.

**Changes to the Java client sample:**

* Ability to load and store local preferences.

pnet-data-api 1.2.1
===================

**Changes to the Java client:**

* Cosmetics: Cleanup and fixes for warnings.

pnet-data-api 1.2.0
===================

* Add mobile phone numbers to companies.
* Update some field descriptions.

**Changes to the Java client:**

* Fix equals methods to check for instance-of instead of class identity.
* Fix hashCode and equals methods for company contract types.
* OptionalList was removed, because it was not used anymore.

pnet-data-api 1.1.0
===================

* Add limitedExtentFrom field to CompanyContractTypeDTO.

pnet-data-api 1.0.0
===================

Intitial version.
