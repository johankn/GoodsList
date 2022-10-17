# main-mappen inne i ui

Her ligger fxml-filer og java-klasser som er knyttet opp mot brukergrensesnittet. 

Inne i /src/main/java/ui er det tre klasser: App.java (åpner appen og har metode for å bytte scene. I tillegg tar har den en metode som sender med hvilken bruker som er logget inn når man bytter scene), AppController.java (kontroller-klassen til hovedsiden, altså etter man er logget inn) og LoginController.java (knytter interaksjoner brukeren gjør i innloggings-scenen opp mot hva som skal skje ved de ulike interaksjonene). 

Inne i /src/main/resources/ui ligger Login.fxml (egen scene for login-fasen i appen), App.fxml (scene for resten av appen hvor bruker som er logget inn kan legge ut annonser, og se alle annonser) og dataObjects.json (filen som lagrer brukere og liste med brukers respektive annonser). 