# Restserver
Vår gruppe valgte å ta i bruk Springboot-rammeverket for å sette opp en rest server. Springboot har en Spring Initializr som generer automatisk et springboot prosjekt som fikk serveren vår opp å kjøre tidlig. Restserver-modulen inneholder følgende klasser:
 - GoodsListApplication.java, som inneholder startmetoden for serverapplikasjonen.
 - GoodsListController.java, som gjør det mulig for serveren å lytte etter HTTP-forespørsler og behandle dem deretter.
 - GoodsListService.java, som brukes av kontrolleren for å håndtere logikken brukeren etterspør.
 - GoodsListApplicationTest.java, som tester at serveren laster inn innholdet som den skal ved hjelp av **Spring’s MockMvc**.

 ## Mulige forespørsler:

 ### Forespørsel om å få alle brukere
 > GET /users
 > 
 > Content-Type: application/json
 >
 > Host: localhost:8080
  
  Ved denne forsespørselen, blir responsen en liste med alle brukere i filen.

### Forespørsel om å få alle aktive ads
> GET /ads
> 
> Content-Type: application/json
>
> Host: localhost:8080

Ved denne forsespørselen, blir responsen en liste med alle aktive annonser i filen. 

 ### Forespørsel om å få alle ads
 > GET /allAds
 > 
 > Content-Type: application/json
 >
 > Host: localhost:8080

Ved denne forsespørselen, blir responsen en liste med alle annonser i filen.

  ### Forespørsel om å registrere ny bruker
 > PUT /newuser
 > 
 > Content-Type: application/json
 >
 > Host: localhost:8080
 > 
 > Body: 
 >
 > {
>
>       "username" : "johnny",
>
>       "password" : "Password123",
>
>       "fullname" : " "John Doe",
>
>       "myAds" : [ ],
>
>       "boughtAds" : [ ]
>
> }
>
 
 Ved denne forsespørselen, blir responsen 200 OK hvis PUT var suksessfull. 

  ### Forespørsel om å registrere en ny ad
 > PUT /newAd
 > 
 > Content-Type: application/json
 >
 > Host: localhost:8080
 > 
 > Body: 
 >
 > {
>
>        "adTitle": "samsung tv",
>        "product": {
>            "@type": "electronics",
>            "price": 300,
>            "condition": "Used",
>            "brand": "Samsung",
>            "type": "TV"
>        },
>        "date": "2022-11-13",
>        "description": "nice working tv",
>        "adId": "1",
>        "isSold": false
>
> }
>
 
 Ved denne forsespørselen, blir responsen 200 OK hvis PUT var suksessfull. 

   ### Forespørsel om å oppdatere en eksisterende bruker
 > PUT /updateUser
 > 
 > Content-Type: application/json
 >
 > Host: localhost:8080
 > 
 > Body: 
 >
 > {
>
>       "username" : "johnny",
>
>       "password" : "Password123",
>
>       "fullname" : " "John Doe",
>
>       "myAds" : [ 
>           "1", 
>       ],
>
>       "boughtAds" : [ ]
>
> }
>
 
 Ved denne forsespørselen, blir responsen 200 OK hvis PUT var suksessfull. 

  ### Forespørsel om å oppdatere en eksisterende ad
 > PUT /updateAd
 > 
 > Content-Type: application/json
 >
 > Host: localhost:8080
 > 
 > Body: 
 >
 > {
>
>        "adTitle": "samsung tv",
>        "product": {
>            "@type": "electronics",
>            "price": 300,
>            "condition": "Used",
>            "brand": "Samsung",
>            "type": "TV"
>        },
>        "date": "2022-11-13",
>        "description": "nice working tv",
>        "adId": "1",
>        "isSold": true
>
> }
>
 
 Ved denne forsespørselen, blir responsen 200 OK hvis PUT var suksessfull. 

  ### Forespørsel om å sette filnavn til hvilken fil som skal leses og skrives til
 > PUT /setFilename
 > 
 > Content-Type: application/json
 >
 > Host: localhost:8080
 > 
 > Body: 
 >
 > {
>
>       "..//ui/src/main/resources/ui/dataObjects.json"
>
> }
>
 
 Ved denne forsespørselen, blir responsen 200 OK hvis PUT var suksessfull. 

