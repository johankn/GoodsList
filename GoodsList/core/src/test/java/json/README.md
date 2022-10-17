# Json-tester

Inne i denne mappen er tester for peristenteklasser. Vi har løst testing av persistens ved å opprette .json-filer hvor alle brukere blir slettet etter hver test slik at de ikke inneholder noen brukere etter en test er kjørt. Disse json-filene ligger i GoodsList/ui/src/test/resources/ui. På denne måten kan testene kjøres flere ganger uten å krasje. 

## Test-klasser:
### FileOperatorTest.json 
- Tester FileOperator.java
- I tillegg tester den hele DataObjects og JsonFileAsObject da disse klassene bruker via FileOperator.java

### UserInfoCollectorTest.java 
- tester UserInfoCollector.java
