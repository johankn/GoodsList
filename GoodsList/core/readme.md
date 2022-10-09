**Beskrivelse av core-mappen**

Vi har valgt å ha klasser som kun representerer data og logikk i core mappen. I release1 har vi tre klasser i core mappen: FileOperator.java (tar seg av skriving til fil og skal senere også ta seg av å lese fra fil), LoginUser.java (en enkel klasse som representerer en bruker som er logget inn) og FileOperatorTest.java (testklasse for FileOperator.java). Det er også en pom.xml-fil inne i core-mappen. Den inneholder dependency for Junit-tester samt plugins for maven og jacoco (testcoverage). Denne pom.xml-filen er en "child"-pom.xml med "parent"-pom.xml i /GoodsList.