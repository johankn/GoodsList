# Testing av brukergrensesnitt

Denne mappen inneholder UiTest.java der brukergrensesnittet i applikasjonen vår testes. Vi bruker TestFX for å simulere en brukertest, der vi har importert FxRobot-api. 

Ved kjøring av testen vil startmetoden i login kontrolleren overskrives, og filstien vil endres slik at all bruker -og annonseinformasjon vil skrives til en uiTest.json-fil. Dette valgte vi å gjøre for å holde den originale JSON-filen uavhengig fra testene. I det testen er ferdig vil all informasjon skrevet til uiTest.json-filen slettes. Dette gjør vi for at testen skal fungere på samme måte hver gang den kjøres, uten at det må endres noe i JSON-filen. 

## Hva gjør testen
Ved teststart vil man møtes av en innloggingsside. Roboten vil klikke seg rundt og prøve forskjellige inputs i feltene. 
### Simuleringsrekkefølge logg inn-side:

- Forsøke å logge inn med helt tomme felter
- Forsøke å logge inn med en bruker som ikke finnes
- Forsøke å opprette en bruker med helt tomme felter
- Forsøke å logge inn med passord som ikke er repetert riktig
- Lykkes med å registrere en bruker
- Lykkes med å logge inn med denne brukeren

Etter innlogging vil scenen endres fra Login.fxml til App.fxml. Videre vil roboten prøve å opprette en annonse til hver produkttype vi har, totalt fem stykker. Her vil den først forsøke å opprette en ugyldig annonse, for deretter fikse opp i feilen og opprette en som er korrekt.


