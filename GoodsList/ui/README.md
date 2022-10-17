 # Beskrivelse av ui-mappen
 
Ui-mappen inneholder alle modulene som har med brukergrensesnittet å gjøre. Her har vi en main-mappe som har kontroller-klassene og selve App-klassen, i tillegg til resources-mappen med fxml-filene og selve JSON-filen. JSON-filen med alt av lagring til fil er lagret her fordi den forandres etter hvordan bruker interagerer med appen. 

Det er også en pom.xml fil i ui-mappen. Den inneholder dependencies vi trenger for å kjøre tester for ui (TestFX og Junit), samt sjekke spotbugs, testdekningsgrad (JaCoCo) og checkstyle. Denne pom.xml-filen er en "child"-pom.xml med "parent"-pom.xml i /GoodsList.

For å lese mer om ui-mappen se vedlagte linker:
- [main](/GoodsList/ui/src/main/README.md)
- [test](/GoodsList/ui/src/test/README.md)