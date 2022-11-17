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

Vi skal også dele opp appController.java og App.fxml for å gjøre alt mer oversiktlig.

## Gjennomføring - arbeidsvaner
I forkant av den tredje sprinten hadde gruppen et møte der vi reviewet forrige sprint med hjelp av tilbakemeldingen vi fikk fra studass. Vårt valg med å dele release 2 inn i flere sprints viste seg å være unødvendig, da en release blir sett på som en sprint. Alt i alt synes vi at arbeidsvanene fra sprint 2 fungerte bra, men allikevel med forbedringspotensial. Vi har parprogrammert i sprint 2, men konsensusen i gruppen er at dette burde benyttes mer. Siden vi har planlagt mye funksjonalitet har fokuset vært på å implementere dette, og vi har derfor jobbet mer individuelt med issues. Etter å ha reviewet sprint 2, satt vi i gang med å lage en plan for sprint 3. Her kom gruppen frem til hva slags funksjonalitet som skulle implementeres og i hvilken rekkefølge ting skulle gjennomføres i. Vi bestemte oss for å bli mer eller mindre ferdig med logikk og funksjonalitet før vi starter med REST API, siden det virket lettere å slippe å oppdatere restmodulen samtidig som andre moduler. Til sprint 3 bestemte gruppen seg for å bruke parprogrammering mer aktivt enn det vi gjorde i sprint 2, spesielt på store og vanskelige issues. Dette gjøres for å sikre bedre kodekvalitet og effektivitet der det trengs. 
 
## Bruk av Git

Gruppen er enige om at vår bruk av gitlab har fungert bra, noe vi også har fått god tilbakemelding på. Opprettelsen av branches utfra issues(oppgaver som skal gjøres) har vært ryddig og gjort det enkelt for oss å delegere oppgaver og ha god oversikt. Vi har også hatt fokus på å holde master branchen så "clean" som mulig, ved å aldri jobbe direkte i master og heller ikke merge ufullstendige branches inn i master. Endringen fra én app-fxml fil og én app-kontroller med masse usynlige "scener" inni seg, til én fxml og én kontroller per "scene" kunne potensielt bli et problem. For å løse dette laget vi en branch ut av master, som vi videre lagde branches ut av for å slippe uønskede problemer i master. 


## Kodekvalitet
Gruppen har ryddet opp i alle CheckStyle warnings i sprint 3, noe som ikke ble gjort i sprint 2. Uniform og konsistent kode i tillegg til JavaDoc-kommentarer var med på å gjøre koden ryddigere og mer lesbar (spesielt metoder man selv ikke hadde laget). Vi diskuterte internt om vi skulle fortsette å ha JavaDoc-kommentarer for getters og setters, der bruk av dette kan ses på som unødvendig og overfladisk. Gruppen konkluderte med at vi skulle fortsette med kommentarene i samsvar med CheckStyle pluginen. Alle bugs i koden er fjernet ved hjelp av SpotBugs plugin, vi opplevde dette som hjelpsomt da vi oppdaget flere svakheter i koden. Jacoco benyttes på samme måte som i sprint 2, bare med en ekstra modul å dekke. Målet vårt har hele veien vært å dekke >90% av alle moduler og vi fortsetter med det.



## Eventuell videreutvikling


## Refleksjon og evaluering

xxx

### Tanker om arbeidsvaner og gjennomføring etter ferdig

Vi fant ut at dette(parprogrammering) var en spesielt god strategi, der store issues ble løst raskere med samarbeid.

### Gitlab og gruppekommunikasjon/ kontrakten?

### Kodekvalitetsverktøy


## Konklusjon
