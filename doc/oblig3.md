# Rapport – innlevering 3
**Team:** *The_Googlers* – *Fredrik Rød, Johnny Nhat Trung Le, Isak Yau*
**Link til google doc* - https://docs.google.com/document/d/1C1gdFduhjKWybFJDakmo6ZVFcGNxVnuHKYxVyz2ZYdo/edit?usp=sharing
# Prosjektrapport
* Hvordan fungerer rollene i teamet?
    * Rollene funker som det skal. Alle holder sitt ansvar for sine roller.
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
    * Ser at vi nå benytter færre møter siden arbeidet har nå blitt mer selvstendig, siden kodebasen er nå på plass.
    * Vi har nå begynt å ta i bruk Issue Board til å ha oversikt og styr på hva arbeid som skal gjøres.

* Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?
    * Til nå ingen merkbare dårlige valg.

* Hvordan er gruppedynamikken?
    * Vi kjenner hverandre fra før av, derfor er vi ganske trygge på hverandre og gruppedynamikken er fin.
* Er det uenigheter som bør løses?
    * Nei, så langt har vi ikke store uengiheter. Noen ganger kan det være ulike synspunkt på hvordan man skal utføre en oppgave eller at det er mistolkning.
      Men de blir ofte løst under møtene.
* Hvordan fungerer kommunikasjonen for dere?
    * Kommunikasjon funker strålende, siden vi ser hverandre (nesten) hver dag, dermed får vi god oppdatering på hvordan prosjektet ligger an.
    * I tillegg er alle fleksible og villig til å ha ekstra møter ukentlig for å jobbe med prosjektet.
* Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres.
    * Gått gjennom alle MVP kravene, men mangler fin pussing.
    * Begynt å bruke prosjektmetodikken vår som var en kombinasjon av Kanban og Scrum.
    * Vi er fornøyd med det vi har klart til nå, men det kommer ofte uforutsigbare detaljer/ problemer.
    * Det som kan forbedres er kodestil i koden.

* Under vurdering vil det vektlegges at alle bidrar til kodebasen.
  Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)
    * Relativt jevnt fordeling av committs, men vi fortsetter en del med parprogrammering under møtene.
    * På merges så jobber vi sammen.

* Referat fra møter siden forrige leveranse skal legges ved (mange av punktene over er typisk ting som havner i referat).
    * Se fra dato: Torsdag 11.April [i_referatfilen](referat.md)
  * Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.
    * Forbedre koden og kodestil.


# Krav og spesifikasjon:
* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.
  Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.
    * Siden forrige gang har vi gått gjennom kravene som vi hadde mål om å nå.
    * Alle våre 10 MVP mål har nå blitt nødd :D, men finpussing gjenstår.
    * Fra forrige gang har vi nå:
      1. Lagt til musikk i bakgrunn, samt Main menu
      2. Lagt HUD som visualliserer Items og HP.
      3. Lagt til Items
      4. Kan dø og vinne
      5. Når man dør kan man starte spillet på nytt
      6. Har begynt å planlegge og teste hvordan level 1
      7. Lagt til MenuScreen,men kan forbedres.
      8. Begynt å lage tester
    * Krav til neste sprint:
      * Mer tester
      * Legge til Victory og Help screen
      * Forbedre level design
      * Enkel fiende.

* For hvert krav dere jobber med, må dere lage
1) ordentlige brukerhistorier,
2) akseptansekriterier
3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester
 
    1. Legge til Victory og Help screen
       * Brukerhistorie:
         - Som spiller trenger jeg å vite når jeg har vunnet
         - Som spiller trenger jeg å vite formålet og uliek aspekter/ momenter av spillet, og vite hvordan man kontrollerer spilleren.
       * Akseptansekriterier:
         - Skal ha en skjerm som indikere at spilleren har vunnet
         - Skal ha en skjerm som skal vise informasjon om spillet og spilleren.
       * Arbeidsoppgave:
         - Implementere victory screen når spilleren har nådd målet
         - Implementere help screen som er mulig å hente fram når man skulle trenge det.
    
    2. Forbedre level design
         * Brukerhistorie:
           - Som spiller trenger jeg et mer utfordrende brett.
         * Akseptansekriterier:
           - Skal ha et brett der man har en progresjon vei med hindringer til et endemål. 
         * Arbeidsopppgave:
           - Design et brett som tilfredstiller en viss vanskelighets krav og gir spiller et mål nå.

   3. Mer tester
       * Brukerhistorie:
           - Som utvikler trenger jeg en måte å sjekke om koden min fungerer slik den skal.
       * Akseptansekriterier:
           - Gode "test coverage"
       * Arbeidsoppgave:
           - Skrive tester som dekker ulike elementer av spillet
    
    4. Enkel fiende
       * Brukerhistorie:
         - Som spiller trenger jeg en hindring som ikke er statisk.
       * Akseptansekriterier:
         -  SKal ha fiende som bevege
         -  Skal ha fiende som skade spiller
         -  Skal ha fiende som kan dø
       * Arbeidsoppgave:
         - Implementere en fiende som kan skade spilleren og har muligheten å dø.

* Forklar kort hvordan dere har prioritert oppgavene fremover
    * Framover har vi tenkt å prioritere oppgavene i rekkefølgen slik vi nevnte ovenfor med mvp-ene.
* Har dere gjort justeringer på kravene som er med i MVP?
    * Nei, vi har ikke gjort justeringer. Men noen har blitt mer prioritert enn andre fordi det føltes mer naturlig.

* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.
    * Se på svar til første spørsmål under Krav og spesifikasjon.
* Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).
    *  Når spilleren hopper mot en vegg, kan spilleren "bounce" tilbake.
    *  Når man beveger seg på bakken, med speed boost vil man noen ganger sprette.
    *  Når player står på kanten av tile-en klarer den ikke å gå inn på tile-en.

# Produkt og kode
* Utbedring av feil: hvis dere har rettet / forbedret noe som er påpekt tidligere, lag en liste med «Dette har vi fikset siden sist», så det er lett for gruppelederne å få oversikt.
    * Dette har vi fikset sist:
      Fikset at man ikke sitte fast når man beveger seg.
      Fikset README.md, hvor vi lat til credits for tileset og spritesheet.
      Forbedret bakgrunn
      Forbedret fysikken til player.
      Fikset Spike sin funksjonalitet og en bug med spike som gir player mer fart.
      Rengjort koden. 
      Lagt mer tester.
* Lag og lever et klassediagram.
    * Vi har laget en [enkel klassediagram](classDiagram.drawio) som inneholder de viktigste klassene, metoder og felt-variabler