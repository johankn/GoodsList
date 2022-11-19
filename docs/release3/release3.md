# Release 3

## Mål for release

Det overordnede målet for release 3 er å ferdigstille applikasjonen som et "shippable" produkt. I denne sprinten har vi fokusert på å implementere manglende funksjonalitet og å sette opp REST API og REST SERVER.

### Brukerhistorier tilknyttet release 3

**US-4**

    "En bruker ønsker å kjøpe et produkt til hjemmet sitt. Han registrerer seg i appen og logger inn. På forsiden ser han en spennende høyttaler, og klikker på den for å se mer informasjon. Han finner ut at han skal ta seg råd til å kjøpe denne varen, og trykker 'buy now'."

**US-5**

    "En bruker ønsker å sjekke ut produktet han nettopp kjøpte. Han navigerer seg til 'My profile', og får opp en oversikt over aktuelle annonser. Her kan han se annonsene han selv har lagt ut, og de han har kjøpt. Han ser også hvor mye penger han har brukt totalt på produktene han har kjøpt."

**US-6**

    "En bruker ønsker å finne en prinsessekjole til datteren sin. Han registrerer seg i appen og logger inn. På forsiden møtes han med et hav av annonser. Han trykker på 'clothing' knappen og får opp en del klær. Etter litt scrolling finner han fortsatt ikke det han leter etter. Han søker etter prinsessekjole i søkefeltet og får opp to passende produkter."

Vi skal også dele opp AppController.java og App.fxml for å gjøre alt mer oversiktlig.

## Gjennomføring - arbeidsvaner
I forkant av den tredje sprinten hadde gruppen et møte der vi reviewet forrige sprint med hjelp av tilbakemeldingen vi fikk fra studass. Vårt valg med å dele release 2 inn i flere sprints viste seg å være unødvendig, da en release blir sett på som en sprint. Alt i alt synes vi at arbeidsvanene fra sprint 2 fungerte bra, men allikevel med forbedringspotensial. Vi har parprogrammert i sprint 2, men konsensusen i gruppen er at dette burde benyttes mer. Siden vi har planlagt mye funksjonalitet har fokuset vært på å implementere dette, og vi har derfor jobbet mer individuelt med issues. Etter å ha reviewet sprint 2, satt vi i gang med å lage en plan for sprint 3. Her kom gruppen frem til hva slags funksjonalitet som skulle implementeres og i hvilken rekkefølge ting skulle gjennomføres i. Vi bestemte oss for å bli mer eller mindre ferdig med logikk og funksjonalitet før vi starter med REST API, siden det virket lettere å slippe å oppdatere restmodulen samtidig som andre moduler. Til sprint 3 bestemte gruppen seg for å bruke parprogrammering mer aktivt enn det vi gjorde i sprint 2, spesielt på store og vanskelige issues. Dette gjøres for å sikre bedre kodekvalitet og effektivitet der det trengs. 
 
## Bruk av Git

Gruppen er enige om at vår bruk av gitlab har fungert bra, noe vi også har fått god tilbakemelding på. Opprettelsen av branches utfra issues(oppgaver som skal gjøres) har vært ryddig og gjort det enkelt for oss å delegere oppgaver og ha god oversikt. Vi har også hatt fokus på å holde master branchen så "clean" som mulig, ved å aldri jobbe direkte i master og heller ikke merge ufullstendige branches inn i master. Endringen fra én app-fxml fil og én app-kontroller med masse usynlige "scener" inni seg, til én fxml og én kontroller per "scene" kunne potensielt bli et problem. For å løse dette laget vi en branch ut av master, som vi videre lagde branches ut av for å slippe uønskede problemer i master. 


## Kodekvalitet
Gruppen har ryddet opp i alle CheckStyle warnings i sprint 3, noe som ikke ble gjort i sprint 2. Uniform og konsistent kode i tillegg til JavaDoc-kommentarer var med på å gjøre koden ryddigere og mer lesbar (spesielt metoder man selv ikke hadde laget). Vi diskuterte internt om vi skulle fortsette å ha JavaDoc-kommentarer for getters og setters, der bruk av dette kan ses på som unødvendig og overfladisk. Gruppen konkluderte med at vi skulle fortsette med kommentarene i samsvar med CheckStyle pluginen. Alle bugs i koden er fjernet ved hjelp av SpotBugs plugin, vi opplevde dette som hjelpsomt da vi oppdaget flere svakheter i koden. Jacoco benyttes på samme måte som i sprint 2, bare med en ekstra modul å dekke. Målet vårt har hele veien vært å dekke >90% av alle moduler og vi fortsetter med det.



## Eventuell videreutvikling
Gruppen er fornøyd med hvordan applikasjonen ser ut etter at sprint 3 er ferdig. Den aller viktigste funksjonaliteten for at appen skal kunne brukes til sin hensikt er på plass. Videreutvikling ville fokusert på å legge til mulighet for å sende meldinger mellom brukere, legge inn bud på annonser istedenfor å bare kjøpe de, og å få opp en personlig anbefaling av annonser.


## Refleksjon

### Tanker om arbeidsvaner og gjennomføring

Alt sett under ett er gruppen veldig fornøyd med hvordan gjennomføringen av oppgaver ble gjort. Arbeidsvanene våre var stort sett gode, og vi var flinke til å kommunisere og fordele oppgaver. I starten drev vi ikke noe særlig med parprogrammering, da problemene var mindre og funksjonaliteten som skulle implementeres var enklere. Senere i prosjektet brukte vi parprogrammering mer aktivt. Vi fant ut at dette var en spesielt god strategi, der store issues ble løst raskere med samarbeid. Vi valgte å opprette våre egne arbeidsoppgaver(issues) selv, altså å assigne seg selv til oppgaver. Dette kunne vært gjort annerledes ved at man assigner hverandre til issues, som fører til at man vet bedre hva andre gruppemedlemmer jobber med. Vår løsning med å ha et felles dokument med sjekkliste for ting som skal gjøres mener vi løser dette problemet bra.

### Gitlab og gruppekommunikasjon
Kommunikasjonen innad i gruppen har vært bra gjennom hele semesteret. Medlemmene hadde god kjennskap til hverandre før oppstart og hadde derfor ingen problemer med kontakten. Kanalen vi benyttet oss av har hovedsaklig vært Facebook, men vi har også brukt Microsoft Teams i tilfeller der vi ikke kunne møte fysisk. Før arbeidet med release 1 startet, laget vi en kontrakt der det ble satt en fast dag i uken til prosjektarbeid. På grunn av ulike årsaker har vi ikke alltid klart å sette av denne dagen, som førte til at vi ble nødt til å skvise inn disse timene andre dager. Antall timer i uken som var avtalt har vi passert, og alle gruppemedlemmene har mer eller mindre jobbet like mye med prosjektet. For noen av oss var dette første møte med gitlab, og læringskurven har vært bratt. Måten å jobbe med issues, milestones og labels var fremmed i starten, men man ble fort vant til det. Gruppen er enige om at måten vi brukte gitlab på fungerte bra.


### Kodekvalitetsverktøy

Vi brukte CheckStyle, SpotBugs og JaCoCo plugins for å sjekke kodekvalitet. Alle tilleggene har vært nyttige på hver sin måte. CheckStyle warnings ryddet vi opp i på starten av sprint 3, og vi forsøkte å sjekke disse videre så godt som mulig med vekslende hell. Noen av advarslene CheckStyle ga oss virket unødvendige, men vi ble enige om at det er en fordel at koden har felles stil. Sent i arbeidet med release 3 oppdaget vi at det var mulig å legge til en fil i prosjektet som gjør det umulig å merge branches med CheckStyle warnings og SpotBugs inn i master. Gruppen er enige om at dette burde vært gjort fra starten, slik at man fikser bugs og warnings fortløpende. Måten vi gjorde det på med å fikse en del warnings til slutt er ikke bærekraftig. Vi var flinkere til å rydde opp i spotbugs, da det var færre av disse og appen ikke kunne kjøre uten å fikse de. JaCoCo var et godt verktøy for å sjekke testdekningsgrad, mer presist hvilke linjer med kode som testene traff. Kvaliteten på testene var derimot noe vi var nødt til å vurdere selv, noe gruppen sier seg fornøyd med.

### Evaluering av hver release

#### **Release 1**
Release 1 bestod av sprint 1, der hovedmålet var å bygge appen med maven og gjøre det mulig å logge inn. Vi valgte å gjøre det enkelt og begrense oss i denne sprinten, ved å kun skrive bruker til fil. I retrospekt er gruppen fornøyde med fokuset i denne første sprinten, men fant andre deler av sprinten som ikke fungerte like bra. Forbedringsområdene vi merket oss var aktiv bruk av labels, milestones og "peer review". I denne sprinten merget vi våre egne issues inn i master-branchen, noe som er bad practice. Vi hadde heller ikke labels på issues for å gruppere oppgaver knyttet til samme mål sammen. Gruppen er enige om at vi kunne brukt "sprint backlog"-teknikken bedre i denne sprinten, da vi kun hadde et mål for sprinten, og ikke en god plan.

#### **Release 2**
Hovedfokuset i denne releasen var å kunne lese og skrive bruker -og annonseobjekter til json-fil. En innlogget bruker skal kunne legge ut et produkt for salg, noe som skal vises på hjemmesiden. Før arbeidet på release 2 begynte, hadde vi et møte der vi opprettet en form for "product backlog". Gruppen ble enige om hvordan vi ønsket at applikasjonen skulle se ut til slutt, med rom for endringer. Release 2 delte vi inn i tre sprinter, noe gruppen bestemte seg for etter å ha vurdert den smidige utviklingsmetoden SCRUM. Vi så for oss at å dele releasen inn i flere små sprinter, med møter i mellom, ville være fordelaktig for effektivitet og kvalitet. Vi hadde planleggingsmøter før hver sprint der vi opprettet en "sprint backlog", og en sprint review etter. Sprint backlogen ble skrevet ned i en delt tekstfil, der gruppen laget en sjekkliste for ny implementasjon og forbedringer for hver sprint. I retrospekt ser vi at strategien med å ha tre ulike milestones i git var "overkill". Gruppen fikk også tilbakemelding på at denne løsningen ikke var slik det var planlagt. Hele release 2 burde vært samlet under én milestone kalt "sprint 2". Applikasjonen vi leverte i release 2 var heller ikke mulig å kjøre, da vi hadde ulik versjon på java og spotbugs. I denne releasen valgte vi å legge nesten alle JavaFx-panes inne i én App.fxml. Dette gjorde at AppControlleren ble lang og uoversiktlig, man var nødt til å endre på visibilty hver gang scenen skulle endres. Gruppen ble enige om at dette skulle deles inn i egne fxml-filer og kontrollere i release 3.

#### **Release 3**
Arbeidet på release 3 startet med å ferdigstille product backlogen, for å gjøre det klart hva som skulle implementeres. Sprint backlogen, planen for sprinten, ble i dette tilfellet laget utfra product backlog. Gruppen hadde et review-møte der vi diskuterte tilbakemeldingene fra forrige release. Det ble satt opp en steg-for-steg plan for sprint 3, med sjekkbokser for forbedringer og ny implementasjon. Gruppemedlemmer kunne selv gå inn i docs filen og sette seg selv som "assignee" for en oppgave. Dette gjorde oppgavefordelingen oversiktlig for alle gruppemedlemmer. Hvis en oppgave var kompleks eller stor, var gruppen enige om at to medlemmer skulle settes på denne oppgaven. Vi brukte parprogrammering så ofte som mulig. SCRUM var en viktig inspirasjon for sprint 3, og vi er fornøyde med hvordan arbeidet med release 3 foregikk. 

## Konklusjon

Etter på ha ferdigstilt applikasjonen er gruppen enige om at dette prosjektarbeidet har gitt mye lærdom. Dette har vært vårt første møte med både maven og REST API, og for noen av oss Git. Vi har brukt mye mer tid enn vi forventet å bruke i dette emnet, spesielt de siste ukene før levering. Gruppen skjønte lenge at applikasjonen vi laget var større enn det den trengte å være. Allikevel har arbeidsmengden vært mange ganger mer enn vi forventet. Til neste gang ville vi begrenset funksjonalitet betraktelig. Vi sitter dog igjen med mye bedre programmeringsevner og erfaring med å jobbe i team. Det har vært nyttig og interessant å jobbe på samme måte som man ville gjort som utvikler i et smidig team. Applikasjonen vi leverer er et produkt vi er fornøyde med, og vi tar med oss god lærdom og erfaring til neste prosjekt.