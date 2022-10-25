# Oppsett av prosjekt
wldblqlkdqwlqbd

Dette repoet inneholder vårt gruppeprosjekt i kurset IT1901 - Informatikk, Prosjekt I. Konseptet er en side hvor brukeren kan kjøpe og selge forskjellige varer på nett, tilsvarende finn.no eller craigslist.org. Prosjektet er bygget opp av disse tre arkitekturlagene: domenelogikk, brukergrensesnitt og datapersistens. Filene kan finnes i core-mappen (se [README](../GoodsList/core/README.md) (denne linken fungerer i vscode, men ikke i preview) for detaljer om funksjonene til appen), mens fxml-filene (som inneholder JavaFX-grensesnittet), appstarteren og kontrollerene som kobler logikken til brukergrensesnittet, kan finnes i ui-mappen (brukergrensesnitt) ([README](../GoodsList/ui/README.md)). 

## Prøv å kjøre

Prosjektet kan prøves ut ved å "cd" inn i GoodsList-mappen og bruke `mvn`:

- "mvn clean install" vil starte kompilering på nytt, teste og installere filene til Maven. Sjekker også for spotbugs og checkstyle. Kjører også testen.   
- test med "mvn test"
- kjør med "mvn javafx:run" (cd inn i "ui" før denne operasjonen)

//NB!!!! Endre denne
- For å kjøre test-coverage gjennom JaCoCo, må du gå inn i target-mappen (må først ha kjørt "mvn test"). Her skal det ligge en site-mappe hvor index.html ligger. Åpne denne i din nettleser for å se test-coverage. I Gitpod fant vi ut at beste måten å åpne denne filen var ved hjelp av en extension kalt "Live Server". Etter denne er installert kan du høyreklikke på index.html og trykke "open with Live Server". 

# GoodsList

Et repo som bruker modules-template som inneholder et javafx-prosjekt, med maven-oppsett for Java 18.0.1 og JavaFX 18.0.1, og JUnit 5 (Jupiter) og TestFX for testing.

//Skribv om mappestruktur og hvor man finner ting

## Tilnærming til testing
Det er laget egne testklasser som tester hver java-klasse i prosjektet. Vi har brukt JaCoCo-plugin for å sjekke testdekningsgrad. Målet var mellom 80- og 90% testdekningsgrad for alle klassene i prosjektet. Dette har vi klart med over 90% i både core- og ui-testingen. Vi etterstreber å teste klassene etterhvert som de lages.

for å kjøre alle testtilleggen skriv cd GoddsList mvn verify

## Verktøy for kodekvalitet
**Checkstyle** brukes for å sjekke Java-koden opp mot Google Java Style - kodestandarden. Den sjekker mye automatisk som gjør det lettere for oss å finne problemer i f.eks. klasse- og metodedesignproblemer, kodelayout eller generelle formateringsproblemer. 

**Spotbugs** er et annet gratis program som sjekker prosjektet for bugs. Den ser etter forekomster av feil i kodemønsteret, og rapporterer disse slik at vi enkelt kan gå inn å forbedre koden. 


NBBB!!!!!!
For å kjøre alle testtilleggen skriv: mvn verify.


