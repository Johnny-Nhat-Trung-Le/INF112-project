# Rapport – innlevering 4
**Team:** *The_Googlers* – *Fredrik Rød, Johnny Nhat Trung Le, Isak Yau*
**Link til google doc* - https://docs.google.com/document/d/1C1gdFduhjKWybFJDakmo6ZVFcGNxVnuHKYxVyz2ZYdo/edit?usp=sharing
# Prosjektrapport
* Hvordan fungerer rollene i teamet?
    * Rollene funker som det skal og alle er pliktopfyllende på deres roller.
* Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?
    * Nei, alle gjør det de skal og alle er fornøyde med ansvaret de har fått.
    * Vi har hatt god gruppedynamikk så å endre hvem som er teamlead eller kundekontakt ødelegger denne dynamikken.
* Trenger dere andre roller?
    * Vi trenger ikke andre roller. For noen roller har alle sammen (eks tester, programmør, gamedesign osv)

* Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere.
    * Teamleder:
        * Tar siste avgjørelse, styrer prosjektet i riktig retning, passer på at alle har noe å bidra med.
    * Oppfølging/ kommunikasjon manager:
        * Planlegger møtene vi har, styrer møtene slik at vi går igjennom problemene vi har/ ting vi må ta opp.
        * Kommunisere/viderføre spørsmål som er rettet mot gruppeleder og foreleser.
        * Holde struktur på hva som blir gjort og hva som bør gjøres til neste møte.
    * Referat:
        * Skriver referat, holder styr på referat.

* Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne?
  * Det har vært flere møter enn forrige sprint, ettersom oppgavene har begynt å bli mindre individuelt
  * I tillegg er dette siste sprint, så vi peiser på med møter for å få siste innspurt
  * Vi har brukt mindre issueboardet siden vi har begynt å ha hyppige møter.
 

* Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?
    * Det har ikke vært noen tegn på dårlige valg som ødelegger  teamet.

* Hvordan er gruppedynamikken?
    * Vi kjenner hverandre fra før av, derfor er vi ganske trygge på hverandre og gruppedynamikken er fin.
* Er det uenigheter som bør løses?
    * Nei,vi har ingen store uengiheter. Noen ganger kan det være ulike synspunkt på hvordan man skal utføre en oppgave eller at det er mistolkning.
      Men de blir ofte løst under møtene.
* Hvordan fungerer kommunikasjonen for dere?
    * Kommunikasjon funker strålende, siden vi ser hverandre (nesten) hver dag, dermed får vi god oppdatering på hvordan prosjektet ligger an.
    * I tillegg er alle fleksible og villig til å ha ekstra møter ukentlig for å jobbe med prosjektet.
    * Vi har hatt mer møter denne sprinten ettersom dette er siste innspurt før final
* Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres.
    * Gått gjennom alle MVP kravene, men siste krav som handlet om at det skal være ulike lydeffekter for ulike terreng var for ambisiøst. 
      Vi endte opp med å ha lyder når spilleren hopper og plukker opp item.
    * Vi er fornøyd med det vi har klart til nå, men det kommer ofte uforutsigbare detaljer/ problemer.
    * Vi slet med pluginLoader hvor den funket, også funket ikke allikevel pga windows/mac og kjøring via jar, men dette er fikset.
    * Det som kan forbedres er kodestil i koden.
    * La til så mange tester som mulig. Fant ut at mange klasser ikke kunne testes siden man ikke kunne teste grafikk og lyd.
      Dermed ble det lav test coverage, men vi har makset ut på så mange tester som var relevant å teste.
      Mange av grafikk og lyd tester blir lagt i manuell testene [her](manualTesting.md)

* Under vurdering vil det vektlegges at alle bidrar til kodebasen.
  Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)
    * Relativt jevnt fordeling av committs og årsaken til forskjellen er fordi det er en del parprogrammering i møtene.
    * På merges så jobber vi sammen.

* Referat fra møter siden forrige leveranse skal legges ved (mange av punktene over er typisk ting som havner i referat).
    * Se fra dato: Onsdag 01.mai [i_referatfilen](referat.md)
    * For siste innlevering (Oblig 4): 
      * Gjør et retrospektiv hvor dere vurderer hvordan hele prosjektet har gått. Hva har dere gjort bra, hva hadde dere gjort annerledes hvis dere begynte på nytt?
        * Det som har vært veldig bra var at vi kjente hverandre godt, og at vi så hverandre (nesten) hver dag.
          Dette gjorde det lett for oss å kommunisere om ulike problemer som dukket opp eller ting som måtte fikses med hverandre
        * Det som var veldig bra var at vi brukte mye tid på planleggingsfasen i starten, noe som ga oss god struktur på hvordan prosjektet skulle se ut både når det gjaldt hvordan spillet skulle være og klassestruktern.
            Selv om ikke alle detaljer ble med, og noen detaljer måtte endre om, så var det fint å ha en helhetelig struktur på hvordan sluttresultatet skulle være.
            
        * Til neste gang tenker vi at det kan være lurt å delegere mer tid til ulike issues/oppgaver siden det ofte kom uforutsigbare problemer/bugs som man brukte mer tid enn forventet.
        * Til neste gang bør vi holde styr på issue boardet og utnytte verktøyet, siden vi brukte det ganske lite.
        * Til neste gang bør vi lage flere branches for ulike oppgavetyper.
        * Til neste gang bør man ha en annen struktur for hvordan man lager en verden.
          Måten vi gjorde det på nå var ikke den beste når det gjaldt å ha en tileFactory og laste inn de ulike tilesene.
          Dessuten ga det oss utfordringer med å laste inn teksturen til de ulike tilesene, ettersom vi endte opp med å bruke mye tid på å implementere en PluginLoader som lastet inn alle teksturene.
    


# Krav og spesifikasjon:
* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.
  Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.
    * Siden forrige gang har vi gått gjennom kravene som vi hadde mål om å nå.
    * Alle nesten alle 10 MVP mål har nå blitt nådd :D. Så vi begynte å legge til ekstra features som vi ikke hadde tenkt i utgangspunktet.
    * MVP 10 ble ikke nådd ettersom den var lite relevant, mer om det kommer senere i oppgaven.
    * Fra forrige gang har vi nå:
        1. Lagt til effektlyd til hendelser som plukking av items og jump
        2. Lagt til en hjelpeskjerm som gir informasjon om kontroll og hva ulike items gjør.
        3. Lagt til Hp item
        4. Kan dø når man faller ut av level
        5. Lagt til level 1 og 2
        6. Implementert levelsystem og hvordan man kan skifte til ulike levels
        7. Lagt til bakgrunn for spillskjermen
        8. Lagde en del tester
        9. Lagt til flere tiles.
        10. Implementert og fikset pluginLoader til for både windows, mac og linux
        11. Lagde en levelskjerm som man kan bruke til å velge hvilke level man vil spille på
        12. Nye abstrakte klasser for contactible tiles

* For hvert krav dere jobber med, må dere lage
1) ordentlige brukerhistorier,
2) akseptansekriterier
   3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester
      1. 
      * Brukerhistorie:
        - Som spiller trenger jeg å vite hva slags elementer tilhører bakgrunnen slik at jeg kan vite hvor jeg kan bevege meg.
      * Akseptansekriterier:
        - Man må kunne se spilleren samt verden. Ha mulighet til å skille mellom bakgrunn og terreng.
      * Arbeidsoppgaver:
        - Lage grensesnitt som skille mellom bakgrunn og terreng.
        - Implementere grensesnittene slik at de kan bli brukt i programmet.
        - Lage en visning for verden og spilleren (skille mellom dem)

      2. Hvordan kontrollere spiller
         * Brukerhistorie:
           - Som spiller trenger jeg å vite hvordan man beveger karakteren og hva karakteren kan gjøre slik at jeg kan spille spillet. 
         * Akseptansekriterier:
           - Ha en oversikt hvordan man kan kontrollere spilleren.
         * Arbeidsoppgaver:
           - Lage en hjelpemeny hvor den gir beskrivelse av hvordan spilleren skal bevege seg.

      3. Mål
           * Brukerhistorie:
             - Som spiller trenger jeg å vite hva som er målet med spillet slik at jeg kan vinne/tape
           * Akseptansekriterier:
             - Er mulig å finne ut hva som er målet med spillet enten README.md eller i startskjerm.
           * Arbeidsoppgaver:
             - SKrive i README eller i startskjerm scenen.

      4. Items
           * Brukerhistorie:
             - Som spiller trenger jeg å vite hva de ulike items gjør og eventuelt hvordan jeg kan bruke dem.
           * Akseptansekriterier:
             - Det må være en måte å vise fram abstrakt hva den gjør og hvordan den brukes
             - Vite hva slags taster man skal trykke for å bruke den.
           * Arbeidsoppgaver:
             - Ha en ikon for item hver gang man plukke dem opp
             - Lage visining som gir riktig info

      5. Spillverden
         * Brukerhistorie:
           - Som spiller trenger jeg å vite hva de ulike plattformene/elementer sine funksjonalitet er slik at jeg kan vite hva som er trygge å gå på og hva som ikke er trygge.
         * Akseptansekriterier:
           - Skille mellom solide og ikke solide objekter
           - Ulike objekter representere ulike terreng i objektfabrikken
         * Arbeidsoppgaver:
           - Bruke ulike visninger på ulike type terreng/objekter for å forstå hva de skal gjøre.

      6. Hvordan skal klassestrukturen se ut
        * Brukerhistorie:
           - Som systemutvikler trenger jeg å vite hvilke klasser og grensesnitter tilhører hverandre slik at jeg kan ha god innkapsling.
        * Akseptansekriterier:
           - Tydelig og oversiktlig klasser og grensesnitt
           - God mappestruktur
        * Arbeidsoppgave:
           - Tydelig og beskrivende filer og mapper.

      7. Hvordan skal spillverden se ut
       * Brukehistorie:
         - Som systemutvikler trenger jeg å vite hva slags plattformer/elementer vi skal ha med slik at jeg kan programmere funksjonaliteter knyttet til de ulike plattformene/elementer. (eks: hvis det er traps, så skal man dø, eller hvis det er trapdoor så kan man bevege opp eller ned.)
       * Akseptansekriterier:
         - Ha en veldetaljert plan på hvilke typer terreng, platform og elementer som skal være med.
       * Arbeidsoppgave:
         - Et dokument/notat hvor man har oversikt over hva som skal være med i programmet når det gjelder platform, terreng, spiller osv.

      8. Hva skal en spiller kunne gjøre
         * Brukerhistorie:
           - Som systemutvikler trenger jeg å vite hva slags egenskaper en spiller skal ha slik at jeg kan ha med de nødvendige funksjonalitetene knyttet til en spiller.(Items, eneimes, movement)
         * Akseptansekriterier:
           - Ha en veldetaljert plan og oversikt over egenskapene til spilleren.
         * Arbeidsoppgaver:
           - Følge planen 
      
      9. Mål for spiller (komme seg til enden og rømme)
         * Brukerhistorie:
           - Som spiller må jeg vite hvordan jeg skal vinne
         * Akseptansekriterier:
          - Må ha en plass hvor man Vet hvordan man vinner.
         * ArbeidsOppgave:
           - Ha en plass enten i README eller i menuScreen som viser hvordan man kan vinne i spillet.
      
      10. Hvordan vinne spillet
          * Brukerhistorie:
            - Som spiller trenger jeg å ha en måte å vinne spillet på slik at jeg kan gå for det.
          * Akseptansekriterier:
            - Det må være mulig for spilleren å vinne.
          * ArbeidsOppgaver:
            - Programmere slik at spillet avslutter eller vise at man har vunnet.
            -  Koder at man har en objektiv i spillet som gjør at man vinner.

      11. Spiller kan dø (ved kontakt feller, eller ved å falle utfor skjermen)
          * Brukerhistorie:
            - Som spiller må jeg vite hvordan jeg kan dø i spillet slik at jeg kan unngå det.
            - Som programmør må jeg implementere en funksjonalitet slik at en spiller kan dø.
          * Akseptansekriterier:
            - Må gjøre det mulig å differansiere mellom dødelige tiles og nøytrale tiles
            -  Må programmere funksjonalitet som gjør at spiller dør.
            - Spiller må kunne gå fra å leve til å dø i spillet når den interagere med dødelige blokker
          * ArbeidsOppgaver:
            - Bruke tiles som er tydelig i seg selv at de er dødelige
            - Implementere funksjonalitet som gjør at spilleren dør.
            - Implementere noe som dreper spilleren aka Traps.

      12. Plukke opp "items" som skal gi en egenskap (raskere movement, høyere hopp, etc.)
          * Brukerhistorie:
            - Som spiller trenger jeg å ha mulighet til å plukke og bruke forskjellige items
            - Som spiller må jeg vite hva slags items jeg kan plukke slik at jeg kan interagere med de riktige tiles.
          * Akseptansekriterier:
           - Man har mulighet til å plukke ulike items.
           - Man har mulighet til å få effekter fra items man bruker
           - Man må kunne skille mellom hva som kan plukkes opp og det som ikke kan.
          * ArbeidsOppgaver:
           - Programmer ulike items som gir ulike effekter 
           - Legge til sprites som er tydelig på at de er en item, og skille dem mellom ikke plukkbare tiles
          
      13. Egenskaper til items
          * Brukerhistorie:
           - Som spiller trenger jeg å vite hva slags egenskap en item gir slik at jeg vet hvordan jeg kan bruke den
          * Akseptansekriterier:
           - Man må ha en plass i programmet hvor man kan se hva de ulike items gjør.
          * ArbeidsOppgaver:                                                                                         
           -  Lage i menuscreen og pausescreen en liste med alle items og hva de gjør.                                                                                                  

      14. Bruke en item
        * Brukerhistorie:
            - Som spiller trenger jeg å vite hva slags taster som gjør at jeg bruker en item/plukke en item slik at jeg kan bruke/plukke dem
        * Akseptansekriterier:
            - Man må ha en plass i programmet som viser hva slags knapper man kan trykke for å plukke/bruke item
        * ArbeidsOppgaver:
            - Lage i menuscreen og pausescreen hva knapp man trykker for å plukke eller bruke en item                                                                                               
                                                                                                                     
      15. Hva slags effekter i bruk
        * Brukerhistorie:
           - Som spiller trenger jeg å vite hvilke effekter er i bruk, og hva items jeg har tilgjengelig
        * Akseptansekriterier:
           - Man må ha en HUD som holder styr på duration time på effekter i bruk
           - Man må ha i HUD-en noe som indikere hva item spilleren har                                                                                                      
        * ArbeidsOppgaver:
          - Lage en HUD som holder styr på duration time når man bruker en effekt.
          - Ha i HUD-en symbol som viser hvilke item spilleren har
          - Ha i HUD-en symbol som viser hvilke item spilleren bruker/ er i bruk                                                                                                           A
      
      16. Hopp/plukke item spiller lydeffekt.
       * Brukerhistorie:
         - Som bruker vil jeg ha lyd i spillet
       * Akseptansekriterier:
         - Man må ha med lyd i spillet
       * ArbeidsOppgaver:
         - Implementer lyd i spillet, eventuelt ha ulike lyd til ulike situasjoner.

       17. Legge til Victory og Help screen
        * Brukerhistorie:
         - Som spiller trenger jeg å vite når jeg har vunnet
         - Som spiller trenger jeg å vite formålet og uliek aspekter/ momenter av spillet, og vite hvordan man kontrollerer spilleren.
        * Akseptansekriterier:
          - Skal ha en skjerm som indikere at spilleren har vunnet
          - Skal ha en skjerm som skal vise informasjon om spillet og spilleren.
        * Arbeidsoppgave:
          - Implementere victory screen når spilleren har nådd målet
          - Implementere help screen som er mulig å hente fram når man skulle trenge det.

       18. Forbedre level design
        * Brukerhistorie:
          - Som spiller trenger jeg et mer utfordrende brett.
        * Akseptansekriterier:
          - Skal ha et brett der man har en progresjon vei med hindringer til et endemål.
        * Arbeidsopppgave:
          - Design et brett som tilfredstiller en viss vanskelighets krav og gir spiller et mål nå.

        19. Mer tester
        * Brukerhistorie:
          - Som utvikler trenger jeg en måte å sjekke om koden min fungerer slik den skal.
        * Akseptansekriterier:
          - Gode "test coverage"
        * Arbeidsoppgave:
          - Skrive tester som dekker ulike elementer av spillet
   
        20. Enkel fiende
        * Brukerhistorie:
          - Som spiller trenger jeg en hindring som ikke er statisk.
        * Akseptansekriterier:
          -  SKal ha fiende som bevege
          -  Skal ha fiende som skade spiller
          -  Skal ha fiende som kan dø
        * Arbeidsoppgave:
          - Implementere en fiende som kan skade spilleren og har muligheten å dø.

   * Nesten alle brukerhistorier, akseptansekriterier og arbeidsoppgaver har blitt fullført.
   * Krav 20 var for ambisiøst, ettersom vi innså at tester tok mer tid enn forventet.
     * I tillegg dukket det opp uforutsigbare problemer som vi måtte delegere ressursene våre til å fikse på istedet for å lage noe nytt.
     * Vi hadde dermed ikke nok tid.
   * Krav 19 kan diskuteres om det har blitt fullført, siden vi ikke har klart å ha god automatiske tester på klasser med grafikk og lyd.
      Selv om vi har manuelle tester for det, så vil ikke test coveragen vår øke på rapporten, dermed vil det se ut til at vi har lav test coverage i rapporten.
      Men vi har sørget for å ha laget manuelle tester som dekker det vi mangler [her](manualTesting.md)

* Har dere gjort justeringer på kravene som er med i MVP?
    * Ja, vi endret siste MVP som handlet om at vi skulle legge til ulike lyder når spiller løper avhengig av terreng.
      Dette ble lite relevant når vi bruker samme type tiles/terreng. Dermed endret vi det til at man har en lydeffekt når man plukker opp items og hopper.
  
* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.
    * Se på svar til første spørsmål under Krav og spesifikasjon.
* Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).
    *  Når spilleren hopper mot en vegg, kan spilleren "bounce" tilbake.
    *  Når man beveger seg på bakken, med speed boost vil man noen ganger sprette.
    *  Når spiller står på kanten av tile-en klarer den ikke å gå inn på tile-en.
    *  Når spiller er inntil en vegg og sklir ned, kan spilleren være "stuck" inntil veggtilen fram til man interagerer videre

# Produkt og kode
* Utbedring av feil: hvis dere har rettet / forbedret noe som er påpekt tidligere, lag en liste med «Dette har vi fikset siden sist», så det er lett for gruppelederne å få oversikt.
    * Dette har vi fikset sist:
      Fikset README.md, hvor vi la til credits for tileset, spritesheet, musikk og PluginLoader klassen.
      Forbedret bakgrunn.
      Lagt til victory,level og info skjerm.
      Fikset og implementert level design.
      Refaktorert koder.
      Lagt til javadocs og kommentarer til public og noen private metoder.
      Laget mer tester.
      Fjernet vekk unødvendige ressurser i resource package.
      Fikset PluginLoader klassen slik at den funker ved å kjøre jar og i windows/mac/linux
    * Dette droppet vi:
      * Vi droppet fiende, ettersom vi måtte full prioritere på å lage tester, og fikse koder som ikke funket helt som de skulle etter å ha oppdaget dem via tester
* Lag og lever et klassediagram.
    * Vi har laget en [klassediagram](diagram/diagram.svg) som inneholder de viktigste klassene, metoder og felt-variabler
