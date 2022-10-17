# Release 2

## Gjennomføring - arbeidsvaner
I release 2 (innlevering 2) har vi utarbeidet en mer strukturert måte å jobbe på sammenlignet med første release. Vi har delt release 2 opp i flere sprinter, hvor vi har brukt mye tid på å legge en plan før hver sprint. Vi har latt oss inspirere av SCRUM-metoden. Dette har fungert mye ryddigere, og gjør at gruppa jobber mer effektivt. Spesifikke arbeidsoppgaver blir tildelt hver enkeltperson i gruppa, også har vi noen møter i uken der vi jobber sammen og diskuterer. Vi har hatt møter før hver Sprint og en review etter. Sprint backlog-teknikken ble brukt for å oppsummere de nødvendige issues som måtte til før hver sprint. Her prøvde vi også å tidsestimere hvor lang tid hver sprint ville ta. All planlegging og refleksjon rundt hver release ble notert ned på et dokument som alle i gruppen har tilgang til. 

- ### Sprint 1
Målet var at brukeren skulle kunne opprette en ad, samt lage en klasse for lesing og skriving til JSON-fil. Det ble derfor opprettet en ny brukerhistorie for dette. Egne labels på issues for brukerhistoriene lagde vi også. I tillegg implementerte vi mye validering for innloggingsfasen, og et brukergrensesnitt for innlogging og regestrering. Tester for dette skulle også bli laget underveis. 

Tidsestimat: 2 dager. Ca. 18 timer. 

- ### Sprint 2
Målet var å skrive en ad til JSON-fil ved hjelp av Jackson-biblioteket og forbedre den generelle test-coveragen. Vi ville også teste UI med JavaFX robot. 

Tidsestimat: 2 dager. Ca. 16 timer.

- ### Sprint 3
Målet var å liste opp annonser når en bruker blir logget inn i appen. Her skulle også annonsene bli sortert etter dato, så nyeste annonse skulle dukke opp først i listen. Brukergrensesnittet måtte naturligvis oppdateres deretter. 

Tidsestimat: 1 dag. Ca. 10 timer. 

## Git
På git har vi opprettet "issues" for arbeidsoppgaver som må gjennomføres, og tildelt disse til enkeltpersoner. For hvert issue, har vi opprettet en separat branch hvor personen kan jobbe. Når oppgaven er løst, vil branchen bli "merga" inn i master igjen. Dette fører til smidigere arbeid, og det er mindre sannsynlig å støte på konflikter i koden. 

I release 2 har vi tatt i bruk labels på issues for å få en rask oversikt over hva issuet handler om. Hvert issue er også knyttet til en milestone (som vi nå kaller sprint 1, sprint 2, osv..). 

## Kodekvalitet
Prosjektet er arbeidet ut fra model-view-controller prinsippet. Dette gjør det smidig for oss å implementere ny data uten å ødelegge for brukergrensesnittet. 

Vi har forbedret måten vi dokumenterer på, og nå ligger det readme-filer i hver modul som forklarer mer av selve koden i sin respektive modul. 

- Generell readme for hensikt med appen og hvordan man kjører. Her ligger også tilnærming til testing, verktøy vi har brukt for kodekvalitet og brukerhistorier: [README](/GoodsList/readme.md)
- Mye ny logikk i core-modulen er implementert i denne releasen. Egen dokumentasjon for JSON-mappen og test-mapper er linket til inni her. Se [README](/GoodsList/core/README.md) (denne linken fungerer i vscode, men ikke i preview). 
- UI-modulen er oppgradert med testing og nytt brukergrensesnitt. Dokumentasjon for test-mapper er linket til inni her. Se [README](/GoodsList/ui/README.md)
- Et enkelt klassediagram for grunnklassene i logikken: [ClassDiagram](/docs/release2/classDiagram.plantuml)


## Refleksjon
I etterkant av releasen har vi opparbeidet oss en del tanker:
- Lengden på sprintene vi setter må fordeles mer jevnt, slik at sprint 1 ikke blir så mye lenger enn sprint 2. Nå ble tidsestimeringen litt feil, og sprint 1 tok lenger tid enn antatt. Dette er en viktig erfaring vi tar med oss til neste release. 
- Arbeidsmengde på hvert issue må begrenses, slik at man ikke blir sittende med det samme issuet i lang tid. Flere små issues er mer ryddig. Dette vil også gi færre merge conflicts. 
- Det ble litt glemt, men viktig at vi husker å kommentere koden underveis. Når vi er fire personer som jobber sammen hjelper kommentarer over metoder til å kjapt få en oversikt over andres kode. 
- Ikke fullfør noe som ikke er 100%. I starten av sprint 1 skrev vi til fil ved hjelp av FileWriter, når vi egentlig skulle bruke Jackson-biblioteket. Dette tapte vi mye tid på, og måtte skrive om flere klasser og tester etterpå.  
- Jobbe mer jevnt med releasen. På grunn av mye sykdom i gruppa har den siste uka vært veldig hektisk med mye jobbing. Vi har egentlig satt opp faste dager i uken hvor vi møtes og planlegger arbeidet, men som før denne releasen har blitt mye utsatt på grunn av sykdom og fravær. 



