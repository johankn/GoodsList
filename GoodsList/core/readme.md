# Beskrivelse av core-mappen

I denne mappen har vi to av modulene våre, persistens og logikk. Disse håndterer hver sin del av prosjektet vårt og samhandler også med hverandre og andre deler av prosjektet på en hensiktsmessig måte. Persistensklassene i json-mappen håndterer skriving til og fra json-fil. Logikklassene i core-mappen håndterer hovedlogikken og konsepter i prosjektet. Vi har gruppert de klassene vi mener hører sammen, i samme modul. Vi har også en test-mappe som tester de to modulene beskrevet over.

### For å les mer om core mappen se vedlagte linker.
- [main/core](src/main/java/core/README.md)
- [main/json](src/main/java/json/README.md)
- [test/core](src/test/java/core/README.md)
- [test/json](src/test/java/json/README.md)