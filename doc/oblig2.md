# Rapport – innlevering 2
**Team:** *The_Googlers* – *Fredrik Rød, Johnny Nhat Trung Le, Isak Yau*


* Hvordan fungerer rollene i teamet? 
  * Rollene funker som det skal. Alle holder sine ansvar for sine roller.
* Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?
  * Nei, alle gjør det de skal og alle er fornøyde med ansvaret de har fått.
* Trenger dere andre roller?
  * Vi trenger ikke andre roller. For noen roller har alle sammen (eks tester, programmør, gamedesign osv)
    

* Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere.
    * Teamleder:
      * Tar siste avgjørelse, styrer prosjektet i riktig retning, passer på at alle har noe å bidra med.
    * Oppfølging/ kommunikasjon manager:
      * Planlegger møtene vi har, styrer møtene slik at vi går igjennom problemene vi har/ ting vi må ta opp.
      * Kommunisere/viderføre spørsmål som er rettet mot gruppeleder og foreleser
    * Referat:
      * Skriver referat, holder styr på referat.
    


* Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? 
  * Vi så tidlig at det var fordelaktig å ha flere møter ukentlig slik at man kan ha oppfølging på hverandre og motivere hverandre.
  * I tillegg hjelper det at man alle er innaforstått hvordan programmet skal funke/skal se ut.
  * Siden vi allerede er kjent med hverandre, så er kommunikasjonen god, og vi kan være ærlige med hverandre når ting ikke funker.
  * Git issue board var ikke noe vi følte trengte, siden vi brukte google docs til å bruke prosjektmetodikken vår som var en kombinasjon av kanban og scum.
  

* Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?
  * 
* Hvordan er gruppedynamikken?
  * Vi kjenner hverandre fra før av, derfor er vi ganske trygge på hverandre og gruppedynamikken er fin.
* Er det uenigheter som bør løses?
  * 
* Hvordan fungerer kommunikasjonen for dere?
  * Kommunikasjon funker strålende, siden vi ser hverandre (nesten) hver dag, dermed får vi god oppdatering på hvordan prosjektet ligger an.
  * I tillegg er alle fleksible og villig til å ha ekstra møter ukentlig for å jobbe med prosjektet.
* Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. 
   Dette skal handle om prosjektstruktur, ikke kode. 
    Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.

* Under vurdering vil det vektlegges at alle bidrar til kodebasen. 
 Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)
Referat fra møter siden forrige leveranse skal legges ved (mange av punktene over er typisk ting som havner i referat).
Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.



Krav og spesifikasjon:

* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. 
Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.
  * Vi har nesten nådd vår MVP. Vi hadde i utgangspunkt 10 MVP punkter, hvor vi nå mangler 3? 
    Men de viktigste/ de med høyest prioritering er på plass.

* For hvert krav dere jobber med, må dere lage 
1) ordentlige brukerhistorier, 
1) 2) akseptansekriterier 
2) 3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester
Dersom dere har oppgaver som dere skal til å starte med, hvor dere har oversikt over både brukerhistorie, akseptansekriterier og arbeidsoppgaver, kan dere ta med disse i innleveringen også.
* Forklar kort hvordan dere har prioritert oppgavene fremover
* Har dere gjort justeringer på kravene som er med i MVP? 
  Forklar i så fall hvorfor. Hvis det er gjort endringer i rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?
* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.
* Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).
Kravlista er lang, men det er ikke nødvendig å levere på alle kravene hvis det ikke er realistisk. Det er viktigere at de oppgavene som er utført holder høy kvalitet. Utførte oppgaver skal være ferdige.


Produkt og kode
(Evt. tekst / kommentarer til koden kan dere putte i en egen ## Kode-seksjon i doc/obligX.md.)


* Utbedring av feil: hvis dere har rettet / forbedret noe som er påpekt tidligere, lag en liste med «Dette har vi fikset siden sist», så det er lett for gruppelederne å få oversikt.
  * Dette har vi fikset sist:
  * 
I README.md: 
* Dere må dokumentere hvordan prosjektet bygger, testes og kjøres, slik at det er lett for gruppelederne å bygge, teste og kjøre koden deres. Under vurdering kommer koden også til å brukertestes.
* Prosjektet skal kunne bygge, testes og kjøres på Linux, Windows og OS X – dere kan f.eks. spørre de andre teamene på gruppen om dere ikke har tilgang til alle platformene. OBS! Den vanligste grunnen til inkompatibilitet med Linux er at filnavn er case sensitive, mens store/små bokstaver ikke spiller noen rolle på Windows og OS X. Det er viktig å sjekke at stiene til grafikk og lyd og slikt matcher eksakt. Det samme vil antakelig også gjelde når man kjører fra JAR-fil.
* Lag og lever et klassediagram. (Hvis det er veldig mange klasser, lager dere for de viktigste.) Det er ikke nødvendig å ta med alle metoder og feltvariabler med mindre dere anser dem som viktige for helheten. (Eclipse har forskjellige verktøy for dette.)
* Kodekvalitet og testdekning vektlegges. 
* Dersom dere ikke har automatiske tester for GUI-et, lager dere manuelle tester som gruppelederne kan kjøre basert på akseptansekriteriene.
* Statiske analyseverktøy som SpotBugs eller SonarQube kan hjelpe med å finne feil dere ikke tenker på. Hvis dere prøver det, skriv en kort oppsummering av hva dere fant / om det var nyttig.
Automatiske tester skal dekke forretningslogikken i systemet (unit-tester). Coverage kan hjepe med å se hvor mye av koden som dekkes av testene
* Utførte oppgaver skal være ferdige. Slett filer/kode som ikke virker eller ikke er relevant (ennå) for prosjektet. (Så lenge dere har en egen git branch for innlevering, så er det ikke noe stress å fjerne ting fra / rydde den, selv om dere fortsetter utviklingen på en annen gren.


