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
