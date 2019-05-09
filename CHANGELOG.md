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
