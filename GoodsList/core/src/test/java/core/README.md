# Testing av kjernelogikk

Denne mappen inneholder JUnit-tester for kjernekoden i prosjektet vårt. Vi har vært spesielt opptatte av at core-mappen skal ha god testdekningsgrad og at alle java-filer i core skal være hensiktsmessig testet. For å holde styr på testdekningsgraden og også hvor mange linjer med kode som testene treffer har vi benyttet oss aktivt av JaCoCo. JaCoCo er en maven plugin som automatisk lager en rapport av testdekningsgraden til JUnit-testene. Informasjonen kan finnes i gr2226/GoodsList/core/target/site/jacoco/index.html etter at testene er kjørt. I de ulike testene har vi hatt fokus på å sjekke eventuelle spesialtilfeller.

## Hva testes
- **AdSorterTest** tester at en liste med annonser sorteres riktig med hensyn på dato publisert. 
- **AdTest** tester konstruktøren og setterne til klassen.
- **AdValidatorTest** tester at ugyldige ads ikke kan bli opprettet, og at gyldige annonser opprettes.
- **LoginValidatorTest** tester at man får en exception dersom login ikke er gyldig, og at login er gyldig når den skal være det.
- **RegistrationValidatorTest** tester at en bruker registreres riktig, får exception om registrering ikke er gyldig.
- **De ulike produkttestene** tester at konstruktører og settere fungerer.
