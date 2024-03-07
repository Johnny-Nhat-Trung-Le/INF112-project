# Rapport – innlevering 2
**Team:** *The_Googlers* – *Fredrik Rød, Johnny Nhat Trung Le, Isak Yau*

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
  * Vi så tidlig at det var fordelaktig å ha flere møter ukentlig slik at man kan ha oppfølging på hverandre og motivere hverandre.
  * I tillegg hjelper det at alle er innaforstått hvordan programmet skal funke/skal se ut med god kommunikasjon.
  * Siden vi allerede er kjent med hverandre, så er kommunikasjonen god, og vi kan være ærlige med hverandre når ting ikke funker.
  * Git issue board var ikke noe vi følte trengte, siden vi bruker google docs til å ha prosjektmetodikken vår som var en kombinasjon av kanban og scum.
  * Vi føler at prosjektmetodikken ikke er så relevant i dette stadiet hvor man jobber med mvp. 
    Dette er fordi mvp er store koder som omhandler hverandre, og da blir det mye kommunikasjon, 
    noe som gjør at å sette opp deloppgaver i sprints, ikke er så relevant.
    Vi erstatte prosjektmetodikkens formål ved å ha flere møter, slik at alle deloppgaver som egentlig ligger i "boardet" blir gjennomført felles.
    Som et resultat, gir vi da mindre deloppgaver ut, fordi møtene våre forekomme ofte og vi gjør ting mer felles.
  

* Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?
  * Ja, vi har gjort gode valg. Inntil nå har det ikke vært tegn på dårlige valg. Alt går som det skal, og hvis det er problemer som dukker opp, er vi fleksible til å ha møter eller kommuniser med hverandre.

* Hvordan er gruppedynamikken?
  * Vi kjenner hverandre fra før av, derfor er vi ganske trygge på hverandre og gruppedynamikken er fin.
* Er det uenigheter som bør løses?
  * Nei, så langt har vi ikke store uengiheter. Noen ganger kan det være ulike synspunkt på hvordan man skal utføre en oppgave eller at det er mistolkning. 
    Men de blir ofte løst under møtene.
* Hvordan fungerer kommunikasjonen for dere?
  * Kommunikasjon funker strålende, siden vi ser hverandre (nesten) hver dag, dermed får vi god oppdatering på hvordan prosjektet ligger an.
  * I tillegg er alle fleksible og villig til å ha ekstra møter ukentlig for å jobbe med prosjektet.
* Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. 
   * Så langt har vi klart å ha god kommmunikasjon, noe som gjør det lett å få oppdateringer på prosjektet.
     Vi har klart å jobbe sammen, men også samtidig individuelt på prosjektet.
     Dessuten har alle til nå oversikt over alt som er i prosjektet, noe som betyr at alle er innaforstått hvordan kodene funker, hvordan strukturen er osv.
    Prosjektmetodikken har man ikke satt så stor fokus i denne sprinten, siden vi føler det ikke har vært en verktøy vi trenger å bruke tid på. 
    Dette er fordi vi har god kommunikasjon med hverandre og jobber ofte sammen, så når det trengs at man jobber individuelt er man displinert nok til å ta ansvaret uten press på sprinter som er i prosjektmetodikkene

* Under vurdering vil det vektlegges at alle bidrar til kodebasen. 
 Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)
  * Vi jobber ofte felles under gruppemøtene. Så det blir en type parprogrammering hele tiden. 
  * Derfor vil det se i committs at noen bidrar mer enn andre.
  * Vi bør til videre nevne når vi parprogrammere i referatene.
  
* Referat fra møter siden forrige leveranse skal legges ved (mange av punktene over er typisk ting som havner i referat).
  * Se fra dato:Tirsdag 20.februar [i referatfilen](referat.md)
* Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.
  * Integrere prosjektmetodikken mer i prosjektflyten vår. Altså assigne mer indivduelle oppgaver 



# Krav og spesifikasjon:

* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. 
Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.
  * Vi har nesten nådd vår MVP. Vi hadde i utgangspunkt 10 MVP punkter, hvor vi nå mangler 4/10.
  * Vi har tenkt å prioritere dem slik
    1. Mål for spiller (komme seg til enden og rømme)
    2. Spiller kan dø (ved kontakt med fiender, eller ved å falle utfor skjermen/ feller)
    3. Plukke opp "items" som skal gi en egenskap (raskere movement, høyere hopp, etc.)
    4. Hopp/run lyd avhengig av terreng.
    Men de viktigste/ de med høyest prioritering er på plass.
    Hva vi har gjort siden forrige var å genrere verden vha objektfabrikk, vise spiller og verden i programmet, 
    også har vi fått inn kontroller på spilleren slik at man kan styre spilleren.
  

* For hvert krav dere jobber med, må dere lage
1) ordentlige brukerhistorier, 
2) akseptansekriterier
3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester

   1. Mål for spiller (komme seg til enden og rømme)
      * Brukerhistorie:
        - Som spiller må jeg vite hvordan jeg skal vinne
            Akseptansekriterier:
              Må ha en plass hvor man Vet hvordan man vinner.
            ArbeidsOppgave:
              Ha en plass enten i README eller i menuScreen som viser hvordan man kan vinne i spillet.
        - Som spiller trenger jeg å ha en måte å vinne spillet på slik at jeg kan gå for det.
            Akseptansekriterier:
                Det må være mulig for spilleren å vinne.
            ArbeidsOppgaver:
                Programmere slik at spillet avslutter eller vise at man har vunnet.
                Koder at man har en objektiv i spillet som gjør at man vinner.
   2. Spiller kan dø (ved kontakt feller, eller ved å falle utfor skjermen)
      * Brukerhistorie:
        - Som spiller må jeg vite hvordan jeg kan dø i spillet slik at jeg kan unngå det.
            Akseptansekriterier:
                Må gjøre det mulig å differansiere mellom dødelige tiles og nøytrale tiles
            ArbeidsOppgaver:
                Bruke tiles som er tydelig i seg selv at de er dødelige
        - Som programmør må jeg implementere en funksjonalitet slik at en spiller kan dø.
            Akseptansekriterier:
                Må programmere funksjonalitet som gjør at spiller dør.
                Spiller må kunne gå fra å leve til å dø i spillet når den interagere med dødelige blokker
            ArbeidsOppgaver:
                Implementere funksjonalitet som gjør at spilleren dør.
                Implementere noe som dreper spilleren aka Traps.
   3. Plukke opp "items" som skal gi en egenskap (raskere movement, høyere hopp, etc.)
      * Brukerhistorie:
        - Som spiller trenger jeg å ha mulighet til å plukke og bruke forskjellige items
            Akseptansekriterier:
                Man har mulighet til å plukke ulike items.
                Man har mulighet til å få effekter fra items man bruker
            ArbeidsOppgaver:
                Programmer ulike items som gir ulike effekter
        - Som spiller må jeg vite hva slags items jeg kan plukke slik at jeg kan interagere med de riktige tiles.
            Akseptansekriterier:
              Man må kunne skille mellom hva som kan plukkes opp og det som ikke kan.
            ArbeidsOppgaver:
              Legge til sprites som er tydelig på at de er en item, og skille dem mellom ikke plukkbare tiles
       - Som spiller trenger jeg å vite hva slags egenskap en item gir slik at jeg vet hvordan jeg kan bruke den
          Akseptansekriterier:
            Man må ha en plass i programmet hvor man kan se hva de ulike items gjør.
          ArbeidsOppgaver:
            Lage i menuscreen og pausescreen en liste med alle items og hva de gjør
        - Som spiller trenger jeg å vite hva slags taster som gjør at jeg bruker en item/plukke en item slik at jeg kan bruke/plukke dem
          Akseptansekriterier:
            Man må ha en plass i programmet som viser hva slags knapper man kan trykke for å plukke/bruke item
          ArbeidsOppgaver:
            Lage i menuscreen og pausescreen hva knapp man trykker for å plukke eller bruke en item
        - Som spiller trenger jeg å vite hvilke effekter er i bruk, og hva items jeg har tilgjengelig
          Akseptansekriterier:
            Man må ha en HUD som holder styr på duration time på effekter i bruk
            Man må ha i HUD-en noe som indikere hva item spilleren har
          ArbeidsOppgaver:
            Lage en HUD som holder styr på duration time når man bruker en effekt.
            Ha i HUD-en symbol som viser hvilke item spilleren har
            Ha i HUD-en symbol som viser hvilke item spilleren bruker/ er i bruk
   4. Hopp/run lyd avhengig av terreng.
      * Brukerhistorie:
      - Som bruker vil jeg ha lyd i spillet
         Akseptansekriterier:
         Man må ha med lyd i spillet
         ArbeidsOppgaver:
         Implementer lyd i spillet, eventuelt ha ulike lyd til ulike situasjoner.

* Forklar kort hvordan dere har prioritert oppgavene fremover
  * Framover har vi tenkt å prioritere oppgavene i rekkefølgen slik vi nevnte ovenfor med mvp-ene.
* Har dere gjort justeringer på kravene som er med i MVP?
    * Nei, vi har ikke gjort justeringer. Men noen har blitt mer prioritert enn andre fordi det føltes mer naturlig.

* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.
    * Se på svar til første spørsmål under Krav og spesifikasjon.
* Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).
    * En bug vi har fått med er at spilleren noen ganger blir stuck når den beveger seg.
    * Når spilleren hopper mot en vegg, kan spilleren "bounce" tilbake. 

# Produkt og kode
(Evt. tekst / kommentarer til koden kan dere putte i en egen ## Kode-seksjon i doc/obligX.md.)


* Utbedring av feil: hvis dere har rettet / forbedret noe som er påpekt tidligere, lag en liste med «Dette har vi fikset siden sist», så det er lett for gruppelederne å få oversikt.
  * Dette har vi fikset sist:
    Til nå har vi ingenting ettersom dette er vår første innlevering med spillkode
I README.md:
* Lag og lever et klassediagram. (Hvis det er veldig mange klasser, lager dere for de viktigste.) Det er ikke nødvendig å ta med alle metoder og feltvariabler med mindre dere anser dem som viktige for helheten. (Eclipse har forskjellige verktøy for dette.)
* Kodekvalitet og testdekning vektlegges.
 - Vi prioriterte å få programmet til å kjøre spillet og få spillet i gang. Dermed  nedprioriterte vi testing.
    Vi har til nå manuelle tester foreløpig for det visuelle og kontrolleren.



