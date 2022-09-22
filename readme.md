[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2226/gr2226)

# GoodsList

Et repo som bruker modules-template som inneholder et javafx-prosjekt, med maven-oppsett for Java 18.0.1 og JavaFX 18.0.1, og JUnit 5 (Jupiter) og TestFX for testing.

Dette repoet inneholder den første iterasjonen av vårt gruppeprosjekt i kurset IT1901 - Informatikk, Prosjekt I. Konseptet er en side hvor brukeren kan kjøpe og selge forskjellige varer på nett, tilsvarende finn.no eller craigslist.org. I denne iterasjonen vil vi begynne på de tre arkitekturlagene: domenelogikk, brukergrensesnitt og datapersistens. Dette vil bli gjort med fokus på brukerinnloggingsfunksjonen, der filene kan finnes i core-mappen (se [README](GoodsList/core/src/main/java/core/README.md) for detaljer om funksjonene til appen), mens fxml-filene (som inneholder JavaFX-grensesnittet), appstarteren og kontrollerene som kobler logikken til brukergrensesnittet, kan finnes i ui-mappen (brukergrensesnitt). Vi har også laget tester for skrive til fil-prosessen, og en mulighet til å rapportere testdekningsprosenten ved hjelp av maven (se [README](GoodsList/ui/src/test/java/ui/README.md) for detaljer om tester).

## Prøv å kjøre

Prosjektet kan prøves ut ved å "cd" inn i GoodsList-mappen og bruke `mvn`:

- kompiler med "mvn compile"
- "mvn clean install" vil starte kompilering på nytt, teste og installere filene til Maven  
- test med "mvn test"
- kjør med "mvn javafx:run" (cd inn i "ui" før denne operasjonen)

- For å kjøre test-coverage gjennom JaCoCo, må du først gjøre "mvn clean install", deretter gå inn i target-mappen. Her skal det ligge en site-mappe hvor index.html ligger. Åpne denne i din nettleser for å se test-coverage. I Gitpod fant vi ut at beste måten å åpne denne filen var ved hjelp av en extension kalt "Live Server". Etter denne er installert kan du høyreklikke på index.html og trykker "open with Live Server". 

# GoodsList

Målet med dette prosjektet er å lage en applikasjon der brukere kan kjøpe og selge alle slags ting på nettet. Vi henter inspirasjon fra allerede eksisterende nettsider som finn.no og craigslist.org. Det ferdige produktet bør ha full implementering av alle nødvendige funksjoner, inkludert et skybasert system som holder styr på klassifiserte annonser og et komplett brukerpåloggingssystem. Brukeren vil kunne opprette, administrere og se annonser samt sende inn tilbud på disse. Det vil også være mulig å favorisere en annonse du er interessert i, slik at du kan se den senere. På Utforsk-siden vil det være en liste over lignende annonser som de du har favorisert. Ulike brukere kan også chatte med hverandre og bli varslet når de mottar tilbud eller andre meldinger.

## Brukerhistorie
Som student vil jeg opprette en bruker i denne applikasjonen, slik at jeg kan sjekke ut nettsiden.

