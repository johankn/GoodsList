[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2226/gr2226)

# GoodsList

A repository using the modules-template containing a javafx project, with maven setup for Java 18.0.1 and JavaFX 18.0.1, and JUnit 5 (Jupiter) and TestFX for testing.

This repository contains the first iteration of our group project in the course IT1901 - Informatics, Project I. The concept is a page where the user can buy and sell different goods online, similar to finn.no or craigslist.org. In this iteration we will begin forming the three architecture layers: domain logic, user interface and data persistence. This will be done focusing on the user login feature, where the files can be found in the core folder (see the [README](GoodsList/core/src/main/java/core/README.md) for details about the functions of the app), whereas the fxml files (containing the JavaFX-interface), app starter and controllers connecting the logic to the UI, can be found in the ui (user interface) folder. We have also created tests for the write to file process, and an ability to report the test coverage percentage using maven (see the [README](GoodsList/ui/src/test/java/ui/README.md) for details about the tests).


## Trying it out

The project can be tried out by cd-ing into the GoodsList folder and using `mvn`:

- compile with "mvn compile"
- test with "mvn test"
- run with "mvn javafx:run"

